package com.lm.service.formation.dao;

import com.lm.service.formation.domain.entity.TimelineEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TimelineRepositoryTest {

  @Autowired
  private TimelineRepository repository;


  @Test
  @Transactional
  public void can_getAll() {
    List<TimelineEvent> all = repository.findAll();
    assertThat(all.size()).isGreaterThan(0);
  }

  @Test
  @Transactional
  public void can_getByWorkSiteId() {
    Set<TimelineEvent> byWorkSiteId = repository.getByWorkSiteId(UUID.fromString("e5607d38-8fc1-43ef-b44e-34967083c80a"));
    assertThat(byWorkSiteId).isNotEmpty();
  }
}
