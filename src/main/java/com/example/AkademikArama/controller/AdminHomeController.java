/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.AkademikArama.controller;

import com.example.AkademikArama.model.Arastirmaci;
import com.example.AkademikArama.model.Tur;
import com.example.AkademikArama.model.Yayin;
import com.example.AkademikArama.repository.ArastirmaciRepo;
import com.example.AkademikArama.repository.TurRepo;
import com.example.AkademikArama.repository.YayinRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Emree
 */
@Controller
public class AdminHomeController {

    @Autowired
    ArastirmaciRepo arastirmaciRepo;

    @Autowired
    TurRepo turRepo;

    @Autowired
    YayinRepo yayinRepo;

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public String adminhome(Map<String, Object> model, HttpServletRequest request) throws IOException {
        if (request.getSession().getAttribute("sessionUser") != null) {
            return "adminHome";
        }
        return "redirect:/adminlogin";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser", null);
        return "redirect:/adminlogin";
    }

    @RequestMapping(value = "/arastirmaciekle", method = RequestMethod.GET)
    public String akademisyenEkleGet(Map<String, Object> model, HttpServletRequest request) throws IOException {
        if (request.getSession().getAttribute("sessionUser") == null) {
            return "redirect:/adminlogin";
        }
        return "arastirmaciEkleme";
    }

    @RequestMapping(value = "/arastirmaciekle", method = RequestMethod.POST)
    public String akademisyenEklePost(Map<String, Object> model, HttpServletRequest request,
            @RequestParam(required = false, name = "ad") String ad, @RequestParam(required = false, name = "soyad") String soyad, ModelMap mod) throws IOException {
        if (request.getSession().getAttribute("sessionUser") == null) {
            return "redirect:/adminlogin";
        }
        int q = arastirmaciRepo.arastirmaciEkle(ad, soyad);
        mod.addAttribute("addMessage", "Araştırmacı Başarıyla eklendi.");

        return "arastirmaciEkleme";
    }

    @RequestMapping(value = "/yayinekle", method = RequestMethod.GET)
    public String yayinEkleGet(Map<String, Object> model, HttpServletRequest request, ModelMap mod) throws IOException {
        if (request.getSession().getAttribute("sessionUser") == null) {
            return "redirect:/adminlogin";
        }
        ArrayList<Arastirmaci> arastirmacilar = arastirmaciRepo.findAll();
        mod.addAttribute("arastirmacilar", arastirmacilar);
        
        return "yayinEkle";
    }

    @RequestMapping(value = "/yayinekle", method = RequestMethod.POST)
    public String yayinEklePost(Map<String, Object> model, HttpServletRequest request, ModelMap mod,
            @RequestParam(required = false, name = "yayinAdi") String yayinAdi,
            @RequestParam(required = false, name = "yayinYili") String yayinYili,
            @RequestParam(required = false, name = "yayinTuru") String yayinTuru,
            @RequestParam(required = false, name = "yayinYeri") String yayinYeri,
            @RequestParam(required = false, name = "arastirmaciselect") String arastirmaciselect) throws IOException {
        if (request.getSession().getAttribute("sessionUser") == null) {
            return "redirect:/adminlogin";
        }
        String[] x = arastirmaciselect.toString().split(",");
        ArrayList<String> yayinArastirmacilari = new ArrayList<String>();
//        System.out.println("1: " + yayinAdi + " 2: " + yayinYili + " 3: " + yayinTuru + " 4: " + yayinYeri + " ");

        for (int i = 0; i < x.length; i++) {
            yayinArastirmacilari.add(x[i]);
        }
//        for (int i = 0; i < yayinArastirmacilari.size(); i++) {
//            System.out.println("Yazar: " + yayinArastirmacilari.get(i) + " ");
//
//        }
        Tur t = new Tur(yayinTuru, yayinYeri);
        int q1 = turRepo.turEkle(yayinTuru, yayinYeri);
//        System.out.println("Tur kaydedildi.");
        Yayin y = new Yayin(yayinAdi, yayinYili);
        int q2 = yayinRepo.yayinEkle(yayinAdi, yayinYili);
//        System.out.println("Yayin Kaydedildi.");
        int q3 = yayinRepo.iliskiOlustur(yayinAdi, yayinYili, yayinYeri, yayinTuru);
//        System.out.println("q3 : " + q3);
    
        for (int i = 0; i < yayinArastirmacilari.size(); i++) {
            Arastirmaci tempArastirmaci = arastirmaciRepo.findArastirmaci(yayinArastirmacilari.get(i));
            int q4 = arastirmaciRepo.arastirmaciYayinIliskiOlustur(yayinAdi, yayinYili, tempArastirmaci.getArastirmaciAdi(), tempArastirmaci.getArastirmaciSoyadi());
//            System.out.println("q4: " + q4);

        }
        mod.addAttribute("articleAdddMessage", "Yayın Başarıyla eklendi.");

        return "yayinEkle";
    }

}
