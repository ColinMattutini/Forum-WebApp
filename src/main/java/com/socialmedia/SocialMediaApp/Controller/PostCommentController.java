package com.socialmedia.SocialMediaApp.Controller;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostComment;
import com.socialmedia.SocialMediaApp.Service.AppUserServiceImpl;
import com.socialmedia.SocialMediaApp.Service.PostCommentServiceImpl;
import com.socialmedia.SocialMediaApp.Service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostCommentController {

    private final PostCommentServiceImpl postCommentService;
    private final AppUserServiceImpl appUserService;
    private final PostServiceImpl postService;

    @PostMapping("/user/{email}/post/{postId}/comment/newcomment")
    public ResponseEntity<?> saveCommentToPost(@PathVariable String email, @PathVariable Long postId, @RequestBody PostComment postComment){
        try{
            AppUser appUser = appUserService.getAppUserByEmail(email);
            Post post = postService.getPost(postId);
            postCommentService.savePostComment(postComment, appUser, post);
            return ResponseEntity.ok().body("Comment saved to post successfully!");
        }
        catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Comment unable to be saved. Validate the post Id and user.");
        }
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<PostComment>> getAllPostComments(@PathVariable Long postId){
        Post post = postService.getPost(postId);
        return ResponseEntity.ok(postCommentService.getAllPostComments(post));
    }

}
