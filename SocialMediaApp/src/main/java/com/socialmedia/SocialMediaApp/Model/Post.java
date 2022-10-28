package com.socialmedia.SocialMediaApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    //private Long postId
    //private String postName
    //private Text? postDescription
    //Many to One / One to Many private AppUser appUser;

    //Many posts to one topic
    ////One to Many/Many to One private Topics topics;

    //private Date postCreationDate;

}
