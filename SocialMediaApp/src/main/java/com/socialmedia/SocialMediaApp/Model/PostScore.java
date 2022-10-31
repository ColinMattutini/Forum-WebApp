package com.socialmedia.SocialMediaApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postScoreId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ReviewType")
    private ReviewEnum review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}
