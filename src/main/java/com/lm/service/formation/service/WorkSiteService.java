package com.lm.service.formation.service;

import com.lm.service.formation.domain.entity.WorkSite;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkSiteService {

  List<WorkSite> getAll();

  Optional<WorkSite> getById(UUID id);
}
