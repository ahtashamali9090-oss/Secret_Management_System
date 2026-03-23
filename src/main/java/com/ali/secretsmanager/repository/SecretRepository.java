package com.ali.secretsmanager.repository;
import com.ali.secretsmanager.entity.Secret;
import com.ali.secretsmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretRepository extends JpaRepository<Secret, Long> {

    List<Secret> findByUser(User user);

    List<Secret> findByServiceNameContainingIgnoreCase(String serviceName);

    List<Secret> findByCategoryContainingIgnoreCase(String category);
}