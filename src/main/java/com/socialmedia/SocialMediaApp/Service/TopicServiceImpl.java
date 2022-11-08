package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.Topic;
import com.socialmedia.SocialMediaApp.Repo.TopicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TopicServiceImpl implements TopicService{

    private final TopicRepo topicRepo;

    @Override
    public List<Topic> findAllTopics() {
        return topicRepo.findAll();
    }

    @Override
    public void saveTopic(Topic topic) {
        topicRepo.save(topic);
    }

    @Override
    public void deleteTopic(Topic topic) {

    }

    @Override
    public void updateTopic(Topic topic) {

    }

    @Override
    public Topic findTopicByName(String topicName) {
        return topicRepo.findByTopicName(topicName);
    }
}
