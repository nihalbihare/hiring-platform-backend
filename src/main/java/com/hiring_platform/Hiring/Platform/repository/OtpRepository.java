package com.hiring_platform.Hiring.Platform.repository;

import com.hiring_platform.Hiring.Platform.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OtpRepository extends MongoRepository<Otp, String> {

    List<Otp>findByCreationTimeBefore(LocalDateTime expiry);
}
