package com.aditya.SpringSecurity.Repository;

import com.aditya.SpringSecurity.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findByUsername( String username);

}
