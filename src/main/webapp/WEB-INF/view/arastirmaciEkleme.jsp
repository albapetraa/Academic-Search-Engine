
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Akademik Arama Projesi Yönetim Paneli</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" type="text/css" href="css/dashboard.css"/>
    </head>
    <body>

    <div id="wrapper" class="toggled">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/">Yönetim Paneli</a>
                </li>
                <li>
                    <a href="/adminhome">Ana Sayfa</a>
                </li>
                <li>
                    <a href="/arastirmaciekle">Araştırmacı Ekle</a>
                </li>
                <li>
                    <a href="/yayinekle">Yayın Ekle</a>
                </li>
                <li>
                    <a href="/welcome">Arama Sayfası</a>
                </li>
                <li>
                    <a href="/logout">Çıkış Yap</a>
                </li>


            </ul>
        </div>

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <form action="/arastirmaciekle" method="POST">
                    <div class="col-7">
                        <h1>YazLab Akademik Arama Yönetim Paneli</h1>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="ad" name="ad" placeholder="Adı">
                                <label for="ad">Araştırmacı Adı</label>
                        </div>
                        <div class="form-floating">
                            <input type="text" class="form-control" id="soyad" name="soyad" placeholder="Soyadı">
                                <label for="soyad">Araştırmacı Soyadı</label>
                        </div>
                        <br>
                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit">Araştırmacı Ekle</button>
                        </div>
                        <c:if test="${not empty addMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${addMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>
                </form>

            </div>
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