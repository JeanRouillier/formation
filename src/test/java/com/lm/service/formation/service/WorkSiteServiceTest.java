package com.lm.service.formation.service;

import com.lm.service.formation.dao.WorkSiteRepository;
import com.lm.service.formation.domain.entity.WorkSite;
import com.lm.service.formation.service.impl.WorkSiteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WorkSiteServiceTest {

  @MockBean
  private WorkSiteRepository repo;

  @InjectMocks
  private WorkSiteServiceImpl service;

  @Before
  public void init() {
    Mockito.reset(repo);
    service = new WorkSiteServiceImpl(repo);
  }

  @Test
  public void can_t_getAll_because_empty() {
    when(repo.getAll()).thenReturn(new HashSet<>());
    List<WorkSite> all = service.getAll();
    assertThat(all).isEmpty();
  }

  @Test
  public void can_getAll() {
    Set<WorkSite> mock = Stream.of(
        new WorkSite().withId(UUID.randomUUID())
            .withComment("first comment"),
        new WorkSite().withId(UUID.randomUUID())
            .withComment("second comment")
    ).collect(Collectors.toSet());

    when(repo.getAll()).thenReturn(mock);

    List<WorkSite> all = service.getAll();
    assertThat(all).isNotEmpty();
    Iterator<WorkSite> iterator = mock.iterator();
    assertThat(all).containsExactlyInAnyOrder(iterator.next(), iterator.next());
  }
}
