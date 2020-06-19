package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Identifiable, Serializable, Comparable<BaseEntity> {

    public static final String ID = "id";
    public static final String CREATION_DATE = "creationDate";
    public static final String MODIFICATION_DATE = "modificationDate";

    @Id
    @Column(name = "id", updatable = false)
    @JsonProperty
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Instant creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private Instant modificationDate;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        BaseEntity entity = (BaseEntity) object;
        return Objects.equals(entity.id, id);
    }

    @Override
    public int compareTo(BaseEntity entity) {
        if (id == null) {
            return -1;
        }
        if (entity.id == null) {
            return 1;
        }
        return id.compareTo(entity.id);
    }
}
