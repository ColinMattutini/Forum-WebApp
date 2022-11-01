package com.socialmedia.SocialMediaApp.Controller;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostScore;
import com.socialmedia.SocialMediaApp.Service.AppUserServiceImpl;
import com.socialmedia.SocialMediaApp.Service.PostScoreServiceImpl;
import com.socialmedia.SocialMediaApp.Service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostScoreController {

    private final PostScoreServiceImpl postScoreService;
    private final AppUserServiceImpl appUserService;
    private final PostServiceImpl postService;

    @PostMapping("/user/{email}/post/{postId}/review")
    public ResponseEntity<?> saveScore(@RequestBody PostScore postScore, @PathVariable String email, @PathVariable Long postId){
        try{
            AppUser appUser = appUserService.getAppUserByEmail(email);
            if(appUser == null){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found in database. Unable to save score.");
            }
            Post post = postService.getPost(postId);
            if(post == null){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Post not found in database. Unable to save score.");
            }
            postScoreService.saveScore(postScore, appUser, post);
            return ResponseEntity.ok().body("Successfully saved score.");
        }catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to save user score to post.");
        }
    }

    @GetMapping("post/{postId}/positiveScore")
    public ResponseEntity<?> getPositiveScorePost(@PathVariable Long postId){
        try{
            Post post = postService.getPost(postId);
            return ResponseEntity.ok().body(postScoreService.getTotalPositivePostScore(post));
        }catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to retrieve positive scores for post.");
        }
    }

    @GetMapping("post/{postId}/negativeScore")
    public ResponseEntity<?> getNegativeScorePost(@PathVariable Long postId){
        try{
            Post post = postService.getPost(postId);
            return ResponseEntity.ok().body(postScoreService.getNegativePostScore(post));
        }catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to retrieve negative scores for post.");
        }
    }

    @GetMapping("post/{postId}/allScores")
    public List<PostScore> getAllScoresByPost(@PathVariable Long postId){
        Post post = postService.getPost(postId);
        return postScoreService.getAllScoresByPost(post);
    }
}
