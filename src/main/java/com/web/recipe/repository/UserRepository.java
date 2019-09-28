package com.web.recipe.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.recipe.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByAccount(String account);
	
	List<User> findByNickname(String nickname);
	
}
