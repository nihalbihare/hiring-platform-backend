package com.hiring_platform.Hiring.Platform.entity;
import com.hiring_platform.Hiring.Platform.dto.JobDTO;
import com.hiring_platform.Hiring.Platform.dto.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class Job {
    @Id
    private long id;
    private String jobTitle;
    private String company;
    private List<Applicants> applicants;
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


    //Send data to frontend / client
    public JobDTO toDTO() {
        return new JobDTO(
                this.id, this.jobTitle, this.company,
                this.applicants !=null ?
                        this.applicants.stream().map((x)->x.toDTO()).toList():null,
                //basically in entity we take each elements in from the db and convert it into dto
                this.about, this.experience,
                this.jobType, this.location, this.packageOffered, this.postTime, this.description,
                this.skillsRequired, this.jobStatus,this.postedBy
        );
    }
}
