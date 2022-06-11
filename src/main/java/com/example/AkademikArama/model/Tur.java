/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AkademikArama.model;

import java.util.List;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;


/**
 *
 * @author Emree
 */
@Node("Tur")
public class Tur {

    @Id @GeneratedValue
    private long turId;

    @Property("yayinTuru")
    private String yayinTuru;

    @Property("yayinYeri")
    private String yayinYeri;
    
    @Relationship(type = "YAYIN_ICERIR", direction = Relationship.Direction.INCOMING)
    private List<Yayin> y;

    public Tur() {
    }

    public Tur(String yayinTuru, String yayinYeri) {
        this.yayinTuru = yayinTuru;
        this.yayinYeri = yayinYeri;
    }

    public String getYayinTuru() {
        return yayinTuru;
    }

    public void setYayinTuru(String yayinTuru) {
        this.yayinTuru = yayinTuru;
    }

    public String getYayinYeri() {
        return yayinYeri;
    }

    public void setYayinYeri(String yayinYeri) {
        this.yayinYeri = yayinYeri;
    }

    @Override
    public String toString() {
        return "Tur{" + "turId=" + turId + ", yayinTuru=" + yayinTuru + ", yayinYeri=" + yayinYeri + '}';
    }

    public long getTurId() {
        return turId;
    }

    public void setTurId(long turId) {
        this.turId = turId;
    }

    

}
