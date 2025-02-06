package com.salespoint.dto;




public class PointOfSaleDTO {



                private Long id;

                private String name;
    public PointOfSaleDTO() {

    }

    public PointOfSaleDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


