package org.example.coffeeshopee.model.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@ToString

public class Base {
    @Version
    @JsonbTransient
    private Long version;

    @JsonbTransient
    private boolean editing = false;

    @JsonbTransient
    private boolean deleted = false;

    @JsonbTransient
    private String createdBy;

    @JsonbTransient
    private String updatedBy;

    @JsonbTransient
    private Date createdDate;

    @JsonbTransient
    private Date updatedDate;

    @JsonbTransient
    private boolean locked = false;
}
