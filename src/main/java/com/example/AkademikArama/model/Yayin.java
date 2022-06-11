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

/**
 *
 * @author Emree
 */
@Node("Yayin")
public class Yayin {

    @Id @GeneratedValue
    private long yayinId;

    @Property("yayinAdi")
    private String yayinAdi;

    @Property("yayinYili")
    private String yayinYili;

    @Relationship(type = "YAYIN_YAZARI", direction = Relationship.Direction.INCOMING)
    public ArrayList<Arastirmaci> yayininYazarlari;

    @Relationship(type = "YAYIN_ICERIR", direction = Relationship.Direction.OUTGOING)
    public Tur yayinIcerik;

    public Yayin() {
    }

    public Yayin(String yayinAdi, String yayinYili) {
        this.yayinAdi = yayinAdi;
        this.yayinYili = yayinYili;
    }

    public String getYayinAdi() {
        return yayinAdi;
    }

    public void setYayinAdi(String yayinAdi) {
        this.yayinAdi = yayinAdi;
    }

    public String getYayinYili() {
        return yayinYili;
    }

    public void setYayinYili(String yayinYili) {
        this.yayinYili = yayinYili;
    }

    @Override
    public String toString() {
        return "Yayin{" + "yayinId=" + yayinId + ", yayinAdi=" + yayinAdi + ", yayinYili=" + yayinYili + ", yayininYazarlari=" + yayininYazarlari + ", yayinIcerik=" + yayinIcerik + '}';
    }

    public ArrayList<Arastirmaci> getYayininYazarlari() {
        return yayininYazarlari;
    }

    public void setYayininYazarlari(ArrayList<Arastirmaci> yayininYazarlari) {
        this.yayininYazarlari = yayininYazarlari;
    }

    public Tur getYayinIcerik() {
        return yayinIcerik;
    }

    public void setYayinIcerik(Tur yayinIcerik) {
        this.yayinIcerik = yayinIcerik;
    }

    public long getYayinId() {
        return yayinId;
    }

    public void setYayinId(long yayinId) {
        this.yayinId = yayinId;
    }

        
    

}
