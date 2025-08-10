package com.hiring_platform.Hiring.Platform.dto;

import com.hiring_platform.Hiring.Platform.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id ;
    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private String picture;
    private Long totalExp;
    private List<String> skills;
    private List<Experience> experience;
    private List<Certification> certification;
    private List<Long> savedJobs;

    public Profile toEntity() {
        return new Profile(this.id, this.name, this.email ,this.jobTitle,
                this.company,this.location, this.about
                ,this.picture!=null? Base64.getDecoder().decode(this.picture):null, this.totalExp, this.skills,
                this.experience, this.certification , savedJobs);
//        When receiving from frontend (as a Base64 string),
//        you decode it back to a byte array for storage in MongoDB.
    }
}
