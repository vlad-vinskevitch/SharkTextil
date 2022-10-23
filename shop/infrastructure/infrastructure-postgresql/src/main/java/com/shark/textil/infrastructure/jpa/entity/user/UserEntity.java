package com.shark.textil.infrastructure.jpa.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOP_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SHOP_USER_STATUS", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserStatusEntity userStatusEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SHOP_USER_ROLE", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserRoleEntity userRoleEntity;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LAST_NAME")
    private String userLastName;

}
