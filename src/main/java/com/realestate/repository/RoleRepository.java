package com.realestate.repository;

import com.realestate.domain.Role;
import com.realestate.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Optional<Role> findByName(RoleType name);
}
