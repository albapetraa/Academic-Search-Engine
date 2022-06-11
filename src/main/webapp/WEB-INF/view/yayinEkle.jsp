<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Akademik Arama Projesi Yönetim Paneli</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/dashboard.css" />
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
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
                    <a href="/arastirmaciekle">Akademisyen Ekle</a>
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
                <form action="/yayinekle" method="POST">
                    <div class="col-7">
                        <h1>YazLab Akademik Arama Yönetim Paneli</h1>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="yayinAdi" name="yayinAdi" placeholder="Yayın Adı">
                                <label for="yayinAdi">Yayın Adı</label>
                        </div>
                        <div class="form-floating">
                            <input type="text" class="form-control" id="yayinYili" name="yayinYili"
                                   placeholder="Yayın Yılı">
                                <label for="yayinYili">Yayın Yılı</label>
                        </div>
                        <br>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="yayinTuru" name="yayinTuru" placeholder="Tür Adı">
                                <label for="yayinTuru">Tür Adı</label>
                        </div>
                        <div class="form-floating">
                            <input type="text" class="form-control" id="yayinYeri" name="yayinYeri"
                                   placeholder="Yayın Yeri">
                                <label for="yayinYeri">Yayın Yeri</label>
                        </div>

                        <br>
                        <select class="js-example-basic-multiple form-select" id="arastirmaciselect" name="arastirmaciselect" multiple="multiple">
                            <c:forEach items="${arastirmacilar}" var="arastirmaci">
                                <option>${arastirmaci.arastirmaciAdi} ${arastirmaci.arastirmaciSoyadi}</option>
                            </c:forEach>
                            <option selected>Araştırmacı Seçin</option>
                        </select>
                        <br>

                        <br><!-- comment -->
                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit">Yayın Ekle</button>
                        </div>
                        <c:if test="${not empty articleAdddMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${articleAdddMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>



                </form>

            </div>
        </div>



    </div>

    <script type="text/javascript">
        // In your Javascript (external .js resource or <script> tag)
        $(document).ready(function () {
            $('.js-example-basic-multiple').select2();
        });
    </script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

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