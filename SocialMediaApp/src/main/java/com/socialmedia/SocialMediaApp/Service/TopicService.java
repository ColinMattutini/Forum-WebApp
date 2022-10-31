package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAllTopics();
    void saveTopic(Topic topic);
    void deleteTopic(Topic topic);
    void updateTopic(Topic topic);
    Topic findTopicByName(String topicName);
}
