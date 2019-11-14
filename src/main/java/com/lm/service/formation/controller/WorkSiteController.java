package com.lm.service.formation.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Demo Controller")
@RestController
@Slf4j
@Validated
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WorkSiteController extends AbstractController {

  //TODO Your time to shine

}
