package com.kcanmin.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tbl_attach")
@Builder
@ToString(exclude = "note")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Attach extends BaseEntity{
  // uuid
  @Id
  // @GeneratedValue(strategy = GenerationType.UUID)
  private String uuid;

  // origin
  private String origin;

  // image
  private boolean image;

  // path
  private String path;
  private String fileName;

  private String ext;
  private String mime;
  private String url;
  private Long size;
  
  // note 
  @ManyToOne(fetch = FetchType.LAZY)
  private Note note;

  
}
