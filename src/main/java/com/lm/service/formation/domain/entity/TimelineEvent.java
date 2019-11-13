package com.lm.service.formation.domain.entity;

import com.lm.service.formation.domain.dto.history.TimelineTypeEnum;
import com.lm.service.formation.domain.entity.converter.TimelineConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Entity
@Table(name = "TIMELINE")
@EqualsAndHashCode(of = "id")
@ToString(exclude = "workSite")
public class TimelineEvent implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_WORK_SITE")
  private WorkSite workSite;

  @Column(name = "DATE")
  private ZonedDateTime date;

  @Convert(converter = TimelineConverter.class)
  @Column(name = "TYPE")
  private TimelineTypeEnum type;

  @Column
  private UUID correlationId;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<TimelineEventMetadata> metadata;

  @Column(name = "CREATED_BY")
  private String createdBy;
}
