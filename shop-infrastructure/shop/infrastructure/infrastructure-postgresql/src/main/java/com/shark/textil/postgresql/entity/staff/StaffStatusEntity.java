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
@Table(name = "STAFF_STATUS")
public class StaffStatusEntity {

    @Id
    @Column(name = "STATUS_ID")
    private Integer statusId;

    @Column(name = "DESCRIPTION")
    private String typeDescription;
}
