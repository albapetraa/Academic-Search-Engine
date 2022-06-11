/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.AkademikArama.controller;

import com.example.AkademikArama.model.Arastirmaci;
import com.example.AkademikArama.model.Yayin;
import com.example.AkademikArama.repository.ArastirmaciRepo;
import com.example.AkademikArama.repository.TurRepo;
import com.example.AkademikArama.repository.YayinRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
public class WelcomeController {

    @Autowired
    ArastirmaciRepo arastirmaciRepo;

    @Autowired
    TurRepo turRepo;

    @Autowired
    YayinRepo yayinRepo;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchGet(Model model, HttpServletRequest request, ModelMap mod, @RequestParam(required = false, name = "searchType") String searchType,
            @RequestParam(required = false, name = "searchText") String searchText) throws IOException {
        int type = Integer.parseInt(searchType);
        ArrayList<Yayin> Yayinlar = new ArrayList<Yayin>();
        if (type == 1) {
            Arastirmaci arastirmaci = arastirmaciRepo.arastirmaciYayinBulma(searchText);
            ArrayList<Yayin> arastirmacininYayinlari = arastirmaci.getArastirmacininYayinlari();

            System.out.println("Bu çalışmalarda katkıları olanlar----------");
            for (int i = 0; i < arastirmacininYayinlari.size(); i++) {
                Yayin temp = yayinRepo.yayinBilgileriniAl(arastirmacininYayinlari.get(i).getYayinAdi());
//                System.out.println(yayinRepo.yayinBilgileriniAl(arastirmacininYayinlari.get(i).getYayinAdi().toString()));
                Yayinlar.add(temp);
            }
//            System.out.println("*********************");
//            for (int i = 0; i < Yayinlar.size(); i++) {
//                System.out.println(Yayinlar.get(i).toString());
//                System.out.println("-------------------------");
//            }

        } else if (type == 2) {
            Yayin temp = yayinRepo.yayinBilgileriniAl(searchText);
            Yayinlar.add(temp);

        } else if (type == 3) {
            ArrayList<Yayin> temp = yayinRepo.yayinBilgileriniAlYil(searchText);
            for (int j = 0; j < temp.size(); j++) {
                Yayinlar.add(temp.get(j));
//                System.out.println(temp.get(j).toString());
            }
        }
        mod.addAttribute("Yayinlar", Yayinlar);

        return "welcome";

    }

    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public String graphDeneme(Model model, HttpServletRequest request, ModelMap mod,
            @RequestParam(required = false, name = "graphSearchText") String graphSearchText) throws IOException {
        ArrayList<Arastirmaci> ortakCalistigiArastirmacilar = arastirmaciRepo.arastirmaciOrtakArastirmaciBul(graphSearchText);
//        System.out.println("Şunlarla ortaklaşa çalışır: ");
//        for (int i = 0; i < ortakCalistigiArastirmacilar.size(); i++) {
//            Arastirmaci temp = ortakCalistigiArastirmacilar.get(i);
//            System.out.println(temp.getArastirmaciAdi() + " " + temp.getArastirmaciSoyadi());
//        }

        ArrayList<Yayin> Yayinlar = new ArrayList<Yayin>();

        Arastirmaci arastirmaci = arastirmaciRepo.arastirmaciYayinBulma(graphSearchText);
        ArrayList<Yayin> arastirmacininYayinlari = (ArrayList<Yayin>) yayinRepo.arastirmacininYayinlari(graphSearchText);
        for (int i = 0; i < arastirmacininYayinlari.size(); i++) {
            Yayin temp = yayinRepo.yayinBilgileriniAl(arastirmacininYayinlari.get(i).getYayinAdi());
            Yayinlar.add(temp);
        }
//        for (int i = 0; i < Yayinlar.size(); i++) {
//            System.out.println(Yayinlar.get(i).toString());
//            System.out.println("---------------c----------");
//
//        }
        ObjectMapper objectMapper = new ObjectMapper();
        mod.addAttribute("arastirmaci", objectMapper.writeValueAsString(arastirmaci));
        mod.addAttribute("ortakCalistigiArastirmacilar", objectMapper.writeValueAsString(ortakCalistigiArastirmacilar));
        mod.addAttribute("arastirmacininYayinlari", objectMapper.writeValueAsString(Yayinlar));
        return "graph";

    }
}
