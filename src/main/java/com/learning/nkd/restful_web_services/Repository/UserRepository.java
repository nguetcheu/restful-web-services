package com.learning.nkd.restful_web_services.Repository;

import com.learning.nkd.restful_web_services.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
