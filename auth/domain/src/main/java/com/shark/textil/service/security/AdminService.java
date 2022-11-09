package com.shark.textil.service.security;

import com.shark.textil.domain.user.UserRole;

import java.util.List;

public interface AdminService {

    void setPermission(Long targetUserId, List<UserRole> userRole);
}
