package com.pragma.userservice.infrastructure.out.jpa.adapter;

import com.pragma.userservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.userservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;
import com.pragma.userservice.infrastructure.exception.NoDataFoundException;
import com.pragma.userservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;


    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(userModel));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserModel(user.get());
    }

    @Override
    public UserModel findUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserModel(user.get());
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = userRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserModelList(entityList);
    }
}