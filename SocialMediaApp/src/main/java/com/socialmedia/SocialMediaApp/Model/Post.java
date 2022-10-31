package com.socialmedia.SocialMediaApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postName;

    @Lob
    @Column(columnDefinition = "TEXT", name = "postDescription")
    private String postDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicId", referencedColumnName = "topicId")
    private Topic topic;

    private Date postCreationDate;

}
