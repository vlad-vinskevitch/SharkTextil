package com.shark.textil.infrastructure.jpa.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOP_USER_ROLE")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER_ROLE")
    private Long roleId;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SHOP_USER_ROLE",
            inverseJoinColumns = @JoinColumn(name = "ID_SHOP_USER", referencedColumnName = "ID_USER"),
            joinColumns = @JoinColumn(name = "ID_SHOP_USER_ROLE", referencedColumnName = "ID_USER_ROLE")
    )
    private List<UserEntity> userEntity;
}
