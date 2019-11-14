package com.lm.service.formation.controller;

import com.lm.service.formation.service.TimelineService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WorkSiteController.class)
public class TimelineControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private TimelineController timelineController;

  @MockBean
  private TimelineService timelineService;

  @Before
  public void setUp() {
    timelineController = new TimelineController(timelineService);
  }

  @Test
  public void can_getAll_empty() throws Exception {
    when(timelineService.getAll()).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/v1/timelines")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("[]"));
  }
}
