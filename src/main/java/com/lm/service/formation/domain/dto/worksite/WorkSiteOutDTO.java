package com.lm.service.formation.domain.dto.worksite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class WorkSiteOutDTO implements Serializable {

  private UUID id;
  private String comment;
  private String createdBy;
  private String updatedBy;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
