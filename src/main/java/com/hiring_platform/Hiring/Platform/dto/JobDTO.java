package com.hiring_platform.Hiring.Platform.dto;

import com.hiring_platform.Hiring.Platform.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    @Id
    private long id;
    private String jobTitle;
    private String company;
    private List<ApplicantsDTO> applicants;
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;
    //This builds a JobDTO object from a Job entity, using the constructor.
    //This allows you to convert your entity to a transport-friendly object for APIs.


   // Save or update data in database
    public Job toEntity() {
        return new Job(
                this.id, this.jobTitle, this.company,
                this.applicants !=null ? this.applicants.stream().map((x)-> x.toEntity()).toList():null,
                // then after frontend send some response  and the dto convert every element into to
                // entity
                this.about, this.experience,
                this.jobType, this.location, this.packageOffered, this.postTime, this.description,
                this.skillsRequired, this.jobStatus , this.postedBy
        );
    }
}
