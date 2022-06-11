/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.AkademikArama.repository;

import com.example.AkademikArama.model.Yayin;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 *
 * @author Emree
 */
public interface YayinRepo extends Neo4jRepository<Yayin, Long> {

    ArrayList<Yayin> findAll();

    @Query("match (u:User) where u.userMail=$userName and u.userPassword=$password return count(u)")
    public int checkLogin(String userName, String password);
    //match (a:Yayin),(b:Tur) where a.yayinAdi="Real Time Driver Fatigue Detection System Based on Multi-Task ConNN" and b.yayinTuru ="Journal Articles" create (a)-[REL:YAYIN_ICERIR] ->(b) return a,b

    @Query("match (a:Yayin),(b:Tur) where a.yayinAdi=$yayinAdi and a.yayinYili=$yayinYili and b.yayinYeri=$yayinYeri and b.yayinTuru=$yayinTuru merge (a)-[REL:YAYIN_ICERIR] ->(b) return count(a)")
    public int iliskiOlustur(String yayinAdi, String yayinYili, String yayinYeri, String yayinTuru);

    @Query("MERGE (a:Yayin {yayinAdi:$yayinAdi, yayinYili:$yayinYili}) RETURN count(a)")
    public int yayinEkle(String yayinAdi, String yayinYili);

    @Query("MATCH (a:Yayin)-[:YAYIN_YAZARI]-(b:Arastirmaci) where b.arastirmaciAdi+\" \"+b.arastirmaciSoyadi=$arastirmaciAdi return a")
    public List<Yayin> arastirmacininYayinlari(String arastirmaciAdi);

   
    @Query("match(a:Arastirmaci)-[b:YAYIN_YAZARI]-(c:Yayin)-[d:YAYIN_ICERIR]-(e:Tur) where c.yayinAdi contains $yayinAdi return c,collect(b),collect(a),collect(d),collect(e)")
    public Yayin yayinBilgileriniAl(String yayinAdi);

    @Query("match(a:Arastirmaci)-[b:YAYIN_YAZARI]-(c:Yayin)-[d:YAYIN_ICERIR]-(e:Tur) where c.yayinYili contains $yayinYili return c,collect(b),collect(a),collect(d),collect(e)")
    public ArrayList<Yayin> yayinBilgileriniAlYil(String yayinYili);

    @Query("match(a:Yayin) where a.yayinYili contains $yayinYili return count(a)")
    public int yayinSayisi(String yayinYili);

}
