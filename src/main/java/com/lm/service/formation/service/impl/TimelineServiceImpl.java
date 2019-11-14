package com.lm.service.formation.service.impl;

import com.lm.service.formation.dao.TimelineRepository;
import com.lm.service.formation.domain.entity.TimelineEvent;
import com.lm.service.formation.service.TimelineService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TimelineServiceImpl implements TimelineService {

  private TimelineRepository timelineRepository;

  @Override
  public List<TimelineEvent> getAll() {
    return timelineRepository.findAll();
  }

  @Override
  public List<TimelineEvent> getByWorkSiteId(UUID id) {
    return new ArrayList<>(timelineRepository.getByWorkSiteId(id));
  }

  @Override
  public TimelineEvent create(TimelineEvent t) {
    return timelineRepository.save(t);
  }
}
