package com.learning.nkd.restful_web_services.Controller;

import com.learning.nkd.restful_web_services.Entity.Post;
import com.learning.nkd.restful_web_services.Entity.User;
import com.learning.nkd.restful_web_services.Repository.PostRepository;
import com.learning.nkd.restful_web_services.Repository.UserRepository;
import com.learning.nkd.restful_web_services.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/post")
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private PostService postService;

    @Autowired
    public PostController(PostService postService, UserRepository userRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Post>> getAllPost() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<Object> addPost(@Valid @RequestBody Post post, @PathVariable int userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        post.setUser(user.get());

        Post savePost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savePost.getId())
                .toUri();

        return ResponseEntity.created(location).body(location);
    }
}
