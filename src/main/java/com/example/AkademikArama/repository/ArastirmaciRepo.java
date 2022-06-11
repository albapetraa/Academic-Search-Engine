/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.AkademikArama.repository;

import com.example.AkademikArama.model.Arastirmaci;
import java.util.ArrayList;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emree
 */
@Repository
public interface ArastirmaciRepo extends Neo4jRepository<Arastirmaci, Long> {

    ArrayList<Arastirmaci> findAll();

    @Query("match (a:Arastirmaci) where a.arastirmaciAdi+ \" \"+a.arastirmaciSoyadi=$arastirmaciAdi return a")
    public Arastirmaci findArastirmaci(String arastirmaciAdi);

    @Query("match (a:Arastirmaci),(b:Yayin) where b.yayinAdi=$yayinAdi and b.yayinYili=$yayinYili and a.arastirmaciAdi+\" \"+a.arastirmaciSoyadi=$arastirmaciAdi+ \" \"+$arastirmaciSoyadi merge (a)-[REL:YAYIN_YAZARI] ->(b) return count(a)")
    public int arastirmaciYayinIliskiOlustur(String yayinAdi, String yayinYili, String arastirmaciAdi, String arastirmaciSoyadi);

    @Query("MERGE (a:Arastirmaci {arastirmaciAdi:$ad, arastirmaciSoyadi:$soyad}) RETURN count(a)")
    public int arastirmaciEkle(String ad, String soyad);

    @Query("match(a:Arastirmaci)-[b:YAYIN_YAZARI]-(c:Yayin)-[d:YAYIN_ICERIR]-(e:Tur) where a.arastirmaciAdi+\" \"+a.arastirmaciSoyadi contains $arastirmaci return a,collect(b),collect(c),collect(d),collect(e)")
    public Arastirmaci arastirmaciYayinBulma(String arastirmaci);

    @Query("match(n1:Arastirmaci)-[r1:YAYIN_YAZARI]-(b:Yayin)-[r2:YAYIN_YAZARI]-(c:Arastirmaci) where n1.arastirmaciAdi+\" \"+n1.arastirmaciSoyadi contains $arastirmaci return c")
    public ArrayList<Arastirmaci> arastirmaciOrtakArastirmaciBul(String arastirmaci);

}
