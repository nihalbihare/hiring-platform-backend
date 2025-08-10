package com.hiring_platform.Hiring.Platform.entity;
import com.hiring_platform.Hiring.Platform.dto.Certification;
import com.hiring_platform.Hiring.Platform.dto.Experience;
import com.hiring_platform.Hiring.Platform.dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile")

public class Profile {
    @Id
    private Long id ;
    private String name;
    private String email;
     private String jobTitle;
     private String company;
     private String location;
     private String about;
     private byte[] picture;
     private Long totalExp;
     private List<String> skills;
     private List<Experience> experience;
     private List<Certification> certification;
    private List<Long> savedJobs;


    public ProfileDTO toDto(){
         return new ProfileDTO(this.id , this.name, this.email , this.jobTitle, this.company,
         this.location , this.about,
                 this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null,this.totalExp, this.skills ,
                 this.experience, this.certification, savedJobs);
        //the image data is encoded to a Base64 string in the DTO before sending it to frontend.
//        You can't send raw byte[] data easily over HTTP (especially JSON).
     }

}
