package com.socialmedia.SocialMediaApp.Controller;


import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.Topic;
import com.socialmedia.SocialMediaApp.Service.AppUserServiceImpl;
import com.socialmedia.SocialMediaApp.Service.PostServiceImpl;
import com.socialmedia.SocialMediaApp.Service.TopicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostServiceImpl postService;
    private final TopicServiceImpl topicService;
    private final AppUserServiceImpl appUserService;

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
    public ResponseEntity getAllPostsByTopicId(@PathVariable String topicName){
        try{
            Topic topic = topicService.findTopicByName(topicName);
            return ResponseEntity.ok().body(postService.getAllPostsByTopic(topic));

        }
        catch(Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to save post. Check that the post is valid.");
        }
    }

}
