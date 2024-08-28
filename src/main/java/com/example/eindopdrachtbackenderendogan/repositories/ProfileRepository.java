package com.example.eindopdrachtbackenderendogan.repositories;

import com.example.eindopdrachtbackenderendogan.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
