package com.example.eindopdrachtbackenderendogan.repositories;

import com.example.eindopdrachtbackenderendogan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

