package com.hiring_platform.Hiring.Platform.Jwt;

import com.hiring_platform.Hiring.Platform.dto.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String name;
    private String password;
    private Long profileId;
    private AccountType accountType;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
}
