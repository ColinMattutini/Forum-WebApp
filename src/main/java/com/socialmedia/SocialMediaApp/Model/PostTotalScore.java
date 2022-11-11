package com.socialmedia.SocialMediaApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.socialmedia.SocialMediaApp.Util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostTotalScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postTotalScoreId;
    @JsonView(Views.MyResponseViews.class)
    private Integer positiveScoreTotal;
    @JsonView(Views.MyResponseViews.class)
    private Integer negativeScoreTotal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    @JsonIgnore
    private Post post;
}
