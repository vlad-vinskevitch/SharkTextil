package com.shark.textil.postgresql.entity.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STAFF")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STUFF", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "COLOR")
    private Integer color;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_STAFF_TYPE", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StaffTypeEntity staffType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_STAFF_STATUS", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StaffStatusEntity staffStatus;
}
