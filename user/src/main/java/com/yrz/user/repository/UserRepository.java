package com.yrz.user.repository;

import com.yrz.user.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yrz
 */
@Repository
public interface UserRepository extends JpaRepository<User  , Integer> {

    /**
     * 用户名查询
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);

}
