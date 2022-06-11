/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AkademikArama.model;

import java.util.ArrayList;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;


/**
 *
 * @author Emree
 */
@Node("Arastirmaci")
public class Arastirmaci {

    @Id @GeneratedValue
    private long arastirmaciId;

    @Property("arastirmaciAdi")
    private String arastirmaciAdi;

    @Property("arastirmaciSoyadi")
    private String arastirmaciSoyadi;

    @Relationship(type = "YAYIN_YAZARI", direction = Direction.OUTGOING)
    private ArrayList<Yayin> arastirmacininYayinlari;


    
    public Arastirmaci() {
    }

    public Arastirmaci(String arastirmaciAdi, String arastirmaciSoyadi) {
        this.arastirmaciAdi = arastirmaciAdi;
        this.arastirmaciSoyadi = arastirmaciSoyadi;
    }

    

    public long getArastirmaciId() {
        return arastirmaciId;
    }

    public void setArastirmaciId(long arastirmaciId) {
        this.arastirmaciId = arastirmaciId;
    }

    public String getArastirmaciAdi() {
        return arastirmaciAdi;
    }

    public void setArastirmaciAdi(String arastirmaciAdi) {
        this.arastirmaciAdi = arastirmaciAdi;
    }

    public String getArastirmaciSoyadi() {
        return arastirmaciSoyadi;
    }

    public void setArastirmaciSoyadi(String arastirmaciSoyadi) {
        this.arastirmaciSoyadi = arastirmaciSoyadi;
    }

    @Override
    public String toString() {
        return "Arastirmaci{" + "arastirmaciId=" + arastirmaciId + ", arastirmaciAdi=" + arastirmaciAdi + ", arastirmaciSoyadi=" + arastirmaciSoyadi + ", arastirmacininYayinlari=" + arastirmacininYayinlari+ '}';
    }

    public ArrayList<Yayin> getArastirmacininYayinlari() {
        return arastirmacininYayinlari;
    }

    public void setArastirmacininYayinlari(ArrayList<Yayin> arastirmacininYayinlari) {
        this.arastirmacininYayinlari = arastirmacininYayinlari;
    }
    
    

    

}
