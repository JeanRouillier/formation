package com.lm.service.formation.dao;

import com.lm.service.formation.domain.entity.WorkSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface WorkSiteRepository extends JpaRepository<WorkSite, UUID> {

  @Query(value = "SELECT w FROM WorkSite w " +
      "WHERE w.id = :id ")
  Optional<WorkSite> getById(@Param("id") UUID id);

  @Query(value = "SELECT w FROM WorkSite w ")
  Set<WorkSite> getAll();

}
