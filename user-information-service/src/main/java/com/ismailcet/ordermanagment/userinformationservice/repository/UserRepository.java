package com.ismailcet.ordermanagment.userinformationservice.repository;

import com.ismailcet.ordermanagment.userinformationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
