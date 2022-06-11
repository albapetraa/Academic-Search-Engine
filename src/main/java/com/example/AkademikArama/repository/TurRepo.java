/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.AkademikArama.repository;

import com.example.AkademikArama.model.Tur;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 *
 * @author Emree
 */
public interface TurRepo extends Neo4jRepository<Tur, Long> {
    
    @Query("MERGE (a:Tur {yayinTuru:$yayinTuru, yayinYeri:$yayinYeri}) RETURN count(a)")
    public int turEkle(String yayinTuru, String yayinYeri);
}