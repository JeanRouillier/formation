package com.lm.service.formation.service.impl;

import com.lm.service.formation.dao.WorkSiteRepository;
import com.lm.service.formation.domain.entity.WorkSite;
import com.lm.service.formation.service.WorkSiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WorkSiteServiceImpl implements WorkSiteService {

  private WorkSiteRepository workSiteRepository;


  @Override
  @Transactional
  public List<WorkSite> getAll() {
    return new ArrayList<>(workSiteRepository.getAll());
  }

  @Override
  @Transactional
  public Optional<WorkSite> getById(UUID id) {
    return workSiteRepository.getById(id);
  }
}
