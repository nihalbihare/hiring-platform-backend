package com.hiring_platform.Hiring.Platform.repository;

import com.hiring_platform.Hiring.Platform.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile ,Long> {


}
