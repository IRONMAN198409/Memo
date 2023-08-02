package com.ironman.memo.user.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ironman.memo.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByIdPassword(String id, String password);
}
