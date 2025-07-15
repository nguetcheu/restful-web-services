package com.learning.nkd.restful_web_services.Service;

import com.learning.nkd.restful_web_services.Entity.Post;
import com.learning.nkd.restful_web_services.Entity.User;
import com.learning.nkd.restful_web_services.Repository.PostRepository;
import com.learning.nkd.restful_web_services.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findOne(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post savePost(Post post, Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            post.setId(id);
            postRepository.save(post);
        }
        return null;
    }

    public Post deleteById(int id) {
        Post post = findOne(id);
        if (post != null) {
            postRepository.deleteById(id);
        }
        return null;
    }
}
