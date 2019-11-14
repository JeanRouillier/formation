package com.lm.service.formation.controller;

import com.lm.service.formation.assembler.TimelineEventDTOAssembler;
import com.lm.service.formation.domain.dto.history.TimelineEventDTO;
import com.lm.service.formation.service.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@Api(tags = "Demo Controller")
@RestController
@Slf4j
@Validated
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TimelineController extends AbstractController {

  private TimelineService timelineService;

  /* =========== GET ===========*/

  @ApiOperation(value = "Returns all work sites' timelines")
  @GetMapping(value = "/timelines")
  public List<TimelineEventDTO> getAllWorkSites(
  ) {
    return TimelineEventDTOAssembler.fromList(timelineService.getAll());
  }

  @ApiOperation(value = "Returns all timeline events associated to a work site")
  @GetMapping(value = "/timelines/work_sites/{id}/")
  public List<TimelineEventDTO> getWorkSiteTimeline(
      @ApiParam(name = "id", value = "Work site id", example = "0a558f14-b78e-4c53-b23a-d01ab8904fea", required = true)
      @PathVariable(name = "id") UUID id
  ) {
    return TimelineEventDTOAssembler.fromList(timelineService.getByWorkSiteId(id));
  }
}
