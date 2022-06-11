<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Akademik Arama Projesi</title>
        <style>
            .b-example-divider {
                height: 1rem;
                background-color: rgba(0, 0, 0, .1);
                border: solid rgba(0, 0, 0, .15);
                border-width: 1px 0;
                box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
            }
        </style>
    </head>
    <body>
    <div class="row mt-5">
        <div class="col-md-6 m-auto">
            <div class="card card-body">
                <img src="kouLogo.png" class="rounded mx-auto d-block" alt="..." height="300px">
            </div>

            <form class = "form-control" action="search" method="get">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <select class="form-select" id="searchType" name="searchType">
                            <option>Arama Türünü Seçiniz</option>
                            <option selected value="1">Araştırmacı Adına Göre</option>
                            <option value="2">Yayına Göre</option>
                            <option value="3">Yıla Göre</option>
                        </select>             
                    </div>

                    <input type="text" class="form-control" name="searchText" id="searchText" placeholder="Aramak istediğiniz kelimeyi girin" aria-label="" aria-describedby="basic-addon1">
                        <button type="submit" class="btn btn-dark">Arama Yap</button>
                </div>
            </form>

            <c:forEach items="${Yayinlar}" var="yayin">
                <div class="dropdown-divider"></div>
                <div class="card">
                    <div class="card-header">
                        <h3>Yazarlar</h3>
                        <c:forEach items="${yayin.getYayininYazarlari()}" var="yazar">
                            <a href="?searchType=1&searchText=${yazar.getArastirmaciAdi()} ${yazar.getArastirmaciSoyadi()}" class="link-dark">${yazar.getArastirmaciAdi()} ${yazar.getArastirmaciSoyadi()}</a> </c:forEach>
                        </div>
                        <div class="card-body ">
                            <h5 class="card-title">${yayin.getYayinAdi()} , ${yayin.getYayinIcerik().getYayinYeri()} , <a href="?searchType=3&searchText=${yayin.getYayinYili()}" class="link-dark">${yayin.getYayinYili()}</a>
                        </h5>
                        <p class="card-text">${yayin.getYayinIcerik().getYayinTuru()}</p>
                    </div>

                    <h2 class='badge bg-secondary'>Çizge Yapılarını Görmek İçin Araştırmacı'ya tıklayın</h2>
                    <c:forEach items="${yayin.getYayininYazarlari()}" var="yazar">
                        <a href="/graph?graphSearchText=${yazar.getArastirmaciAdi()} ${yazar.getArastirmaciSoyadi()}" target="_blank" class="btn btn-outline-success">${yazar.getArastirmaciAdi()} ${yazar.getArastirmaciSoyadi()}</a>
                    </c:forEach>
                </div>   
            </c:forEach>
            <div class="dropdown-divider"></div>

        </div>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
    crossorigin="anonymous"></script>
</body>
</html>
