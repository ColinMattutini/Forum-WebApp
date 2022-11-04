package com.socialmedia.SocialMediaApp.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.socialmedia.SocialMediaApp.Util.Views;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @JsonView(Views.MyResponseViews.class)
    private String username;
    private Date userCreatedDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    private boolean enabled;


}
