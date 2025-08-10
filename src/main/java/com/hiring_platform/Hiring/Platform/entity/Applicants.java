package com.hiring_platform.Hiring.Platform.entity;

import com.hiring_platform.Hiring.Platform.dto.ApplicantsDTO;
import com.hiring_platform.Hiring.Platform.dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicants {

    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime InterviewTime;

    public ApplicantsDTO toDTO(){
        return new ApplicantsDTO(this.applicantId, this.name, this.email,
                this.phone, this.website,
                this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null, this.coverLetter,
                this.timestamp, this.applicationStatus, this.InterviewTime);
         //the image data is encoded to a Base64 string in the DTO before sending it to frontend.
        //        You can't send raw byte[] data easily over HTTP (especially JSON).
    }
}

