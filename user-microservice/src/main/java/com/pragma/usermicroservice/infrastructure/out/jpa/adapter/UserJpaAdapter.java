package com.pragma.usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.usermicroservice.domain.model.UserModel;
import com.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
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
    public UserModel findByEmail(String email) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
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