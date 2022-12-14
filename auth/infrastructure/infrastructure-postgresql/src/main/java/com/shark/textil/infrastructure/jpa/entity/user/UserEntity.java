package com.shark.textil.infrastructure.jpa.entity.user;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Data
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SHOP_USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_SHOP_USER", referencedColumnName = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_SHOP_USER_ROLE", referencedColumnName = "ID_USER_ROLE")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<UserRoleEntity> authoritiesEntity;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LAST_NAME")
    private String userLastName;

}
