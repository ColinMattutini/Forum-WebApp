package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostTotalScore;
import com.socialmedia.SocialMediaApp.Repo.PostTotalScoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostTotalScoreServiceImpl implements PostTotalScoreService{

    private final PostTotalScoreRepo postTotalScoreRepo;

    @Override
    public PostTotalScore savePostTotalScore(Post post){
        PostTotalScore postTotalScore = new PostTotalScore();
        postTotalScore.setPositiveScoreTotal(0);
        postTotalScore.setNegativeScoreTotal(0);
        postTotalScore.setPost(post);
        return postTotalScoreRepo.save(postTotalScore);
    }

    @Override
    public void increasePositiveScore(Post post) {
        PostTotalScore postTotalScore = postTotalScoreRepo.findByPost(post);
        postTotalScore.setPositiveScoreTotal(postTotalScore.getPositiveScoreTotal() + 1);
        postTotalScoreRepo.save(postTotalScore);
    }

    @Override
    public void decreasePositiveScore(Post post) {
        PostTotalScore postTotalScore = postTotalScoreRepo.findByPost(post);
        postTotalScore.setPositiveScoreTotal(postTotalScore.getPositiveScoreTotal() - 1);
        postTotalScoreRepo.save(postTotalScore);
    }

    @Override
    public void increaseNegativeScore(Post post) {
        PostTotalScore postTotalScore = postTotalScoreRepo.findByPost(post);
        postTotalScore.setNegativeScoreTotal(postTotalScore.getNegativeScoreTotal() + 1);
        postTotalScoreRepo.save(postTotalScore);
    }

    @Override
    public void decreaseNegativeScore(Post post) {
        PostTotalScore postTotalScore = postTotalScoreRepo.findByPost(post);
        postTotalScore.setNegativeScoreTotal(postTotalScore.getNegativeScoreTotal() - 1);
        postTotalScoreRepo.save(postTotalScore);
    }
}
