<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://bootswatch.com/5/sandstone/bootstrap.min.css">
        <script src="https://kit.fontawesome.com/514ed7772a.js" crossorigin="anonymous"></script>
        <title>Akademik Arama Projesi</title>
    </head>
    <body>
    <div class="row mt-5">
        <div class="col-md-6 m-auto">
            <div class="card card-body">
                <h1 class="text-center mb-3"><i class="fas fa-sign-in-alt"></i>Giriş Yap</h1>

                <form action="adminlogin" method="POST" modelAttribute="userModel">


                    <div class="form-group">
                        <label for="email">Mail Adresi</label>
                        <input
                            type="email"
                            id="userMail"
                            name="userMail"
                            class="form-control"
                            placeholder="Mail Adresinizi Girin"
                            />
                    </div>
                    <div class="form-group">
                        <label for="password">Şifre</label>
                        <input
                            type="password"
                            id="userPassword"
                            name="userPassword"
                            class="form-control"
                            placeholder="Şifrenizi girin"
                            />
                    </div>
                    <c:if test="${not empty loginMessage}">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            ${loginMessage}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary btn-block">Giriş Yap</button>

                </form>
                <a href="/welcome" class="btn btn-link">Arama Sayfasına Gitmek İçin Tıklayın</a>

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
