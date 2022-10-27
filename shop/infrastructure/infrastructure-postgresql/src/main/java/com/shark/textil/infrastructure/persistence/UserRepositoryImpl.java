package com.shark.textil.infrastructure.persistence;

import com.shark.textil.domain.user.User;
import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import com.shark.textil.infrastructure.jpa.repository.UserJPARepository;
import com.shark.textil.infrastructure.mapper.UserMapper;
import com.shark.textil.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;

    private final UserMapper userMapper;

    @Override
    public User save(final User user) {
        final UserEntity entity = this.userJPARepository.save(this.userMapper.asUserEntity(user));
        return this.userMapper.asUser(entity);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return this.userJPARepository.findByEmail(email)
                .map(this.userMapper::asUser);
    }

    @Override
    public User getByEmail(String email) {
        return this.userMapper.asUser(this.userJPARepository.getByEmail(email));
    }
}
