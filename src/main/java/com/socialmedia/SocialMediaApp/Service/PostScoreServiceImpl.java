package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostScore;
import com.socialmedia.SocialMediaApp.Model.ReviewEnum;
import com.socialmedia.SocialMediaApp.Repo.PostScoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostScoreServiceImpl implements PostScoreService{

    private final PostScoreRepo postScoreRepo;
    private final PostTotalScoreServiceImpl postTotalScoreService;

    @Override
    public void saveScore(PostScore postScore, AppUser appUser, Post post) {
        List<PostScore> postScores = getAllScoresByPost(post).stream().filter(e -> e.getAppUser().equals(appUser)).collect(Collectors.toList());
        if(postScores.isEmpty()){
            postScore.setAppUser(appUser);
            postScore.setPost(post);
            postScoreRepo.save(postScore);
            if(postScore.getReview().equals(ReviewEnum.POSITIVE)){
                postTotalScoreService.increasePositiveScore(post);
            } else if(postScore.getReview().equals(ReviewEnum.NEGATIVE)){
                postTotalScoreService.increaseNegativeScore(post);
            }
        }else{
            PostScore oldPostScore = postScores.get(0);
            if(!oldPostScore.getReview().equals(postScore.getReview())){
                if(postScore.getReview().equals(ReviewEnum.POSITIVE)){
                    postTotalScoreService.increasePositiveScore(post);
                    postTotalScoreService.decreaseNegativeScore(post);
                } else if(postScore.getReview().equals(ReviewEnum.NEGATIVE)){
                    postTotalScoreService.increaseNegativeScore(post);
                    postTotalScoreService.decreasePositiveScore(post);
                }
            }
            oldPostScore.setReview(postScore.getReview());
            postScoreRepo.save(oldPostScore);

        }

    }

    @Override
    public PostScore getUserPostScore(AppUser appUser) {
        return null;
    }

    @Override
    public List<PostScore> getAllScoresByPost(Post post) {
        List<PostScore> postScores = new ArrayList<>();
        postScoreRepo.findByPost(post).forEach(postScores::add);
        return postScores;
    }

    @Override
    public int getTotalPositivePostScore(Post post) {
        List<PostScore> positiveScores = postScoreRepo.findByPost(post).stream().filter(e -> e.getReview().equals(ReviewEnum.POSITIVE)).collect(Collectors.toList());
        return positiveScores.size();
    }

    @Override
    public int getNegativePostScore(Post post) {
        List<PostScore> negativeScores = postScoreRepo.findByPost(post).stream().filter(e -> e.getReview().equals(ReviewEnum.NEGATIVE)).collect(Collectors.toList());
        return negativeScores.size();
    }
}
