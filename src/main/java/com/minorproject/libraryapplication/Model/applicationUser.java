package com.minorproject.libraryapplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class applicationUser implements UserDetails{

    private static final String AUTHORITIES_SEPERATOR= ":";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private  String password;

    private String authorities;

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.authorities.split(AUTHORITIES_SEPERATOR))
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
