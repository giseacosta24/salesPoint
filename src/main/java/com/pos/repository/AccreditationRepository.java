package com.pos.repository;

import com.pos.model.Accreditation;
import com.pos.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccreditationRepository extends JpaRepository<Accreditation, Long>
{
}
