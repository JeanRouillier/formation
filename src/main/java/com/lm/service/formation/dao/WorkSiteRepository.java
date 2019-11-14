package com.lm.service.formation.dao;

import com.lm.service.formation.domain.entity.WorkSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkSiteRepository extends JpaRepository<WorkSite, UUID> {

  //TODO time to shine
}
