package com.hiring_platform.Hiring.Platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "otp")
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

    @Id
    private String email;
    private String otpCode;
    private LocalDateTime creationTime;




}
