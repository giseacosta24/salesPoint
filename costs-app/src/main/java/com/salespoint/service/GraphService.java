package com.salespoint.service;

import com.salespoint.dto.MinimunRouteDTO;
import com.salespoint.model.Cost;
import com.salespoint.model.PointOfSale;
import com.salespoint.repository.CostRepository;
import com.salespoint.repository.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
public class GraphService {

    private final CostRepository costRepository;


    private final PointOfSaleRepository pointOfSaleRepository;

    private Map<String, Map<String, Double>> grafo = new HashMap<>();

    @Autowired
    public GraphService(CostRepository costRepository, PointOfSaleRepository pointOfSaleRepository) {
        this.costRepository = costRepository;
        this.pointOfSaleRepository = pointOfSaleRepository;
        cargarGrafo();
    }

    private void cargarGrafo() {
        List<Cost> costos = costRepository.findAll();
        grafo.clear();

        for (Cost costo : costos) {
            String origen = obtenerNombrePorId(costo.getPointAId());
            String destino = obtenerNombrePorId(costo.getPointBId());

            if (origen != null && destino != null) {
                Double valor = costo.getCost();
                grafo.computeIfAbsent(origen, k -> new HashMap<>()).put(destino, valor);
                grafo.computeIfAbsent(destino, k -> new HashMap<>()).put(origen, valor); // Si el grafo es bidireccional
            }
        }
    }

    private String obtenerNombrePorId(Long id) {
        return pointOfSaleRepository.findById(id).map(PointOfSale::getName).orElse(null);
    }

    public MinimunRouteDTO calcularRutaMinima(String origen, String destino) {
       if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            return null;
        }

        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> previos = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingDouble(p -> p.cost));

        for (String punto : grafo.keySet()) {
            distancias.put(punto, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);
        pq.add(new Pair(origen, 0.0));

        while (!pq.isEmpty()) {
            Pair actual = pq.poll();
            String nodoActual = actual.name;

            if (nodoActual.equals(destino)) break;

            for (Map.Entry<String, Double> vecino : grafo.get(nodoActual).entrySet()) {
                String vecinoNombre = vecino.getKey();
                Double nuevoCosto = distancias.get(nodoActual) + vecino.getValue();

                if (nuevoCosto < distancias.get(vecinoNombre)) {
                    distancias.put(vecinoNombre, nuevoCosto);
                    previos.put(vecinoNombre, nodoActual);
                    pq.add(new Pair(vecinoNombre, nuevoCosto));
                }
            }
        }

        if (!previos.containsKey(destino)) return null; // No hay camino

        List<String> ruta = new ArrayList<>();
        for (String at = destino; at != null; at = previos.get(at)) {
            ruta.add(at);
        }
        Collections.reverse(ruta);

        return new MinimunRouteDTO(ruta, distancias.get(destino));
    }

    private static class Pair {
        String name;
        Double cost;
        Pair(String name, Double cost) {
            this.name = name;
            this.cost = cost;
        }
    }
}
