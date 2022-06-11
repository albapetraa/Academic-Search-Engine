/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.AkademikArama.repository;

import com.example.AkademikArama.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emree
 */
@Repository
public interface UserRepo extends Neo4jRepository<User, Long> {

    @Query("match (u:User) where u.userMail=$userName and u.userPassword=$password return count(u)")
    public int checkLogin(String userName, String password);

}
