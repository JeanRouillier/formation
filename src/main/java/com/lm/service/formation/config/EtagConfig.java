package com.lm.service.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagConfig extends ShallowEtagHeaderFilter {

}
