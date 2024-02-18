package com.spring.office.domain;

import jakarta.persistence.*;
import lombok.Data;
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

    private Boolean active;

    @Column(name = "isDeleted")
    private Boolean delete;
    @CreatedDate
    private LocalDateTime createdAt;
    private Long createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Long updatedBy;

    @PrePersist
    public void prePersist(){
        this.active = true;
        this.delete = false;
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


}
