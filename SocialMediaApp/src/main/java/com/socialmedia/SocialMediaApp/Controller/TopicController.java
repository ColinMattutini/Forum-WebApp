package com.socialmedia.SocialMediaApp.Controller;


import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.Topic;
import com.socialmedia.SocialMediaApp.Service.TopicServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicServiceImpl topicService;

    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> getAllTopics(){
        return ResponseEntity.ok().body(topicService.findAllTopics());
    }

    @PostMapping("/topic/newTopic")
    public ResponseEntity<?> saveTopic(@RequestBody Topic topic){
        try{
            topicService.saveTopic(topic);
            return ResponseEntity.ok("Topic saved.");
        }
        catch (Error error){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to save topic. Check all arguments are valid.");
        }
    }


}
