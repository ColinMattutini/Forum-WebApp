package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostTotalScore;

public interface PostTotalScoreService {

    PostTotalScore savePostTotalScore(Post post);

    void increasePositiveScore(Post post);
    void decreasePositiveScore(Post post);
    void increaseNegativeScore(Post post);
    void decreaseNegativeScore(Post post);

}
