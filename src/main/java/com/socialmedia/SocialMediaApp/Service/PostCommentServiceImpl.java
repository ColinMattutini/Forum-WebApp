package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostComment;
import com.socialmedia.SocialMediaApp.Repo.PostCommentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostCommentServiceImpl implements PostCommentService{

    private final PostCommentRepo postCommentRepo;


    @Override
    public PostComment savePostComment(PostComment postComment, AppUser appUser, Post post) {
        postComment.setAppUser(appUser);
        postComment.setPost(post);
        post.setPostCreationDate(new Date(System.currentTimeMillis()));
        return postCommentRepo.save(postComment);
    }

    @Override
    public void deletePostComment(PostComment postComment) {

    }

    @Override
    public List<PostComment> getAllPostComments(Post post) {
        List<PostComment> comments = new ArrayList<>();
        postCommentRepo.findByPost(post).forEach(comments::add);
        return comments;
    }
}
