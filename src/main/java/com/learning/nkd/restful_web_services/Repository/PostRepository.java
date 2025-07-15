package com.learning.nkd.restful_web_services.Repository;

import com.learning.nkd.restful_web_services.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
