package com.jungmook.blog.Repository;

import com.jungmook.blog.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public boolean existsByUserEmailAndUserPassword(String userEmail, String passWord);
    public UserEntity findByUserEmail(String userEmail);

}
