package com.hiring_platform.Hiring.Platform.dto;

import com.hiring_platform.Hiring.Platform.entity.Applicants;
import com.hiring_platform.Hiring.Platform.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantsDTO {
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private  LocalDateTime interviewTime;

    public Applicants toEntity(){
        return new Applicants(this.applicantId, this.name, this.email, this.phone,
                this.website,this.resume!=null? Base64.getDecoder().decode(this.resume):null, this.coverLetter,
                this.timestamp, this.applicationStatus, this.interviewTime);
        //        When receiving from frontend (as a Base64 string),
//        you decode it back to a byte array for storage in MongoDB.
    }
}
