package com.lm.service.formation.service;

import com.lm.service.formation.domain.entity.TimelineEvent;

import java.util.List;
import java.util.UUID;

public interface TimelineService {

  List<TimelineEvent> getAll();

  List<TimelineEvent> getByWorkSiteId(UUID id);

  TimelineEvent create(TimelineEvent t);
}
