package com.lm.service.formation.controller;

import com.lm.service.formation.domain.entity.TimelineEvent;
import com.lm.service.formation.domain.entity.TimelineEventMetadata;
import com.lm.service.formation.service.TimelineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lm.service.formation.domain.dto.history.TimelineTypeEnum.SMS_SENT;
import static com.lm.service.formation.util.TestUtils.getResourceAsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TimelineController.class)
public class TimelineControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TimelineService timelineService;


  @Test
  public void can_getAll_empty() throws Exception {
    when(timelineService.getAll()).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/v1/timelines")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }

  @Test
  public void can_getAll() throws Exception {
    List<TimelineEvent> mock = Stream.of(
        new TimelineEvent().withId(UUID.randomUUID()).withDate(ZonedDateTime.of(2018, 9, 24, 13, 0, 15, 0, ZoneOffset.UTC)).withCorrelationId(UUID.randomUUID()).withType(SMS_SENT)
            .withMetadata(Stream.of(
                new TimelineEventMetadata().withId(UUID.randomUUID()).withLabel("first comment").withKey("Random key"),
                new TimelineEventMetadata().withId(UUID.randomUUID()).withLabel("second comment").withKey("Another random key")
            ).collect(Collectors.toSet()))
    ).collect(Collectors.toList());

    when(timelineService.getAll()).thenReturn(mock);
    mockMvc.perform(get("/api/v1/timelines")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(getResourceAsString("json/timeline/GET_all_200.json")));
  }
}
