package com.hiring_platform.Hiring.Platform.entity;
import com.hiring_platform.Hiring.Platform.dto.AccountType;
import com.hiring_platform.Hiring.Platform.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

    @Data
    @Document(collection = "users")
@NoArgsConstructor
    @AllArgsConstructor
    public class User {

        @Id
        private Long id;
        private String name;
        @Indexed(unique = true) // no duplicate email should be
        // inserted in the database
        private String email;
        private String password;
        private AccountType accountType;
        private Long profileId;

        public UserDTO toDTO() {
            return new UserDTO(this.id, this.name, this.email, this.password, this.accountType
            , this.profileId);
        }


    }
