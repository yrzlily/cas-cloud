package com.yrz.user.repository;

import com.yrz.user.orm.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yrz
 */
@Repository
public interface RoleRepository extends JpaRepository<Role , Integer> {
}
