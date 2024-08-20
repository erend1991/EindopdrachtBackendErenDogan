package com.example.eindopdrachtbackenderendogan.repositories;

import com.example.eindopdrachtbackenderendogan.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
