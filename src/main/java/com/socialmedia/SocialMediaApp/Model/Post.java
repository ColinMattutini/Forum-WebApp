package com.socialmedia.SocialMediaApp.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.socialmedia.SocialMediaApp.Util.Views;
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
    @JsonView(Views.MyResponseViews.class)
    private Long postId;

    @JsonView(Views.MyResponseViews.class)
    private String postName;

    @Lob
    @Column(columnDefinition = "TEXT", name = "postDescription")
    @JsonView(Views.MyResponseViews.class)
    private String postDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
    @JsonView(Views.MyResponseViews.class)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicId", referencedColumnName = "topicId")
    @JsonView(Views.MyResponseViews.class)
    private Topic topic;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postTotalScoreId", referencedColumnName = "postTotalScoreId")
    @JsonView(Views.MyResponseViews.class)
    private PostTotalScore postTotalScore;

    private Date postCreationDate;
    private Date postDeletionDate = null;

}
