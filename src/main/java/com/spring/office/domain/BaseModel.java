package com.spring.office.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseModel implements Serializable {


    private static final Long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active = true;


    private Boolean deleted = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Long updatedBy;



}
