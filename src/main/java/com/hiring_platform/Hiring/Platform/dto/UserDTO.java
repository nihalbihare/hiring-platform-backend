package com.hiring_platform.Hiring.Platform.dto;

import com.hiring_platform.Hiring.Platform.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is null or blank.")
    private String name;

    @NotBlank(message = "Email is null or blank.")
    @Email(message = "Email is invalid.")
    private String email;

    @NotBlank(message = "Password is null or blank.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,15}$",
            message = "Password is invalid."
    )
    private String password;

    private AccountType accountType;
    private Long profileId;

    // Convert the DTO to the Entity
    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, this.accountType,
                this.profileId);
    }
}
