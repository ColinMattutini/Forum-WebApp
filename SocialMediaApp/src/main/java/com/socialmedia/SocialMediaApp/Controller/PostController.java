package com.socialmedia.SocialMediaApp.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.Topic;
import com.socialmedia.SocialMediaApp.Service.PostServiceImpl;
import com.socialmedia.SocialMediaApp.Service.TopicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostServiceImpl postService;
    private final TopicServiceImpl topicService;


    @PostMapping("/user/{email}/topic/{topicName}/post/savepost")
    public ResponseEntity<?> savePost(@RequestBody Post post, @PathVariable String email, @PathVariable String topicName){
        try{
            postService.savePost(post, topicName, email);
            return ResponseEntity.ok().body("Post created successfully");
        }catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to save post. Check topic is correct");
        }
    }

    @GetMapping("/topic/{topicName}/posts")
    public ResponseEntity<?> getAllPostsByTopicId(@PathVariable String topicName){
        try{
            Topic topic = topicService.findTopicByName(topicName);
            return ResponseEntity.ok().body(postService.getAllPostsByTopic(topic));

        }
        catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to save post. Check that the post is valid.");
        }
    }

    @PutMapping("user/{email}/topic/{topicName}/posts/{postId}")
    public ResponseEntity<?> deletePostByUser(@PathVariable String email, @PathVariable Long postId, @RequestHeader (value = "Authorization") String jwt){
        Post post = postService.getPost(postId);
        AppUser appUser = post.getAppUser();
        String emailChecker = appUser.getEmail();
        if(jwt != null){
            try{
                String token = jwt.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                if(!(username.equals(emailChecker))){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User token decoded does not match current user to delete post");
                }
                postService.deletePost(post);
            } catch(Error error){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User token does not match current user to delete post");
                }
            }

        return ResponseEntity.ok().body("Post deleted successfully!");

    }

}
