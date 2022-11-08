package com.socialmedia.SocialMediaApp.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.socialmedia.SocialMediaApp.Util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @JsonView(Views.MyResponseViews.class)
    private String topicName;
    private String topicDescription;
}
