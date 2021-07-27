package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByUsernameAndIdIsNot(String username, Integer id);

  boolean existsByEmail(String email);

  boolean existsByEmailAndIdIsNot(String email, Integer id);

  boolean existsByPhoneNumber(String phoneNumber);

  boolean existsByPhoneNumberAndIdIsNot(String phoneNumber, Integer id);

}
