package com.paramkansagra.Hospital.Management.System.repository;

import com.paramkansagra.Hospital.Management.System.entity.AuthProviderEnums.AuthProviderTypeEnums;
import com.paramkansagra.Hospital.Management.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByProviderIdAndProviderType(String providerId , AuthProviderTypeEnums providerType);
}