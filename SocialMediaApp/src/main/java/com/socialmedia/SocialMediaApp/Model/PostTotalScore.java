package com.socialmedia.SocialMediaApp.Model;

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
    private Integer positiveScoreTotal;
    private Integer negativeScoreTotal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}
