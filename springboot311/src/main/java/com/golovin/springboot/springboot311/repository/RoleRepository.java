package com.golovin.springboot.springboot311.repository;

import com.golovin.springboot.springboot311.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Query(value = "select r.*\n" +
            "from role  r \n" +
            "   join user_role ur on r.id = ur.role_id\n" +
            "where user_id= :id",
            nativeQuery = true)
    Set<Role> findAllRolesByUserId(@Param("id") Long userId);

    Set<Role> findAll();
}
