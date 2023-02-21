package com.shark.textil.postgresql.entity.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STAFF_TYPE")
public class StaffTypeEntity {

    @Id
    @Column(name = "TYPE_ID")
    private Integer typeId;

    @Column(name = "DESCRIPTION")
    private String typeDescription;

}
