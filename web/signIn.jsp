<%-- 
    Document   : signIn
    Created on : May 11, 2021, 11:30:38 AM
    Author     : WIN 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/signIn.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="184645806440-5sk0ndomcfgq17g2rj0b3hmjfsl7e644.apps.googleusercontent.com">
        <script type="text/javascript">
            function disableBack() {
                window.history.forward();
            }
            setTimeout("disableBack()", 0);
            window.onunload = () => {
                null;
            };
        </script>
    </head>
    <body>

        <div class="container">
            <div class="header">
                <div class="container">
                    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                        <a href="rentalPage.jsp" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                            <img style="width: 50px" class="bi me-2" src="./assets/img/rental-removebg-preview.png"/>
                            <span class="fs-4">Rental resource</span>
                        </a>

                        <ul class="nav nav-pills">
                            <li class="nav-item"><a href="signUp.jsp" class="nav-link active">Sign Up</a></li>
                        </ul>
                    </header>
                </div>
            </div>

            <div class="body-page">
                <div class="container col-xl-10 col-xxl-8 px-4 " >
                    <div class="row align-items-center g-lg-5 py-2">
                        <div class="col-md-10 mx-auto col-lg-5" >
                            <form action="DispatchServlet" method="POST" class="p-4 p-md-5 border rounded-3 bg-light" style="width: 400px; position: relative">
                                <h3 style=" text-align: center; margin: -25px 0 20px 0">Sign In</h3>
                                <div class="form-floating mb-3">
                                    <input name="txtEmail" type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
                                    <label for="floatingInput">Email</label>

                                </div>
                                <div class="form-floating mb-3">
                                    <input name="txtPassword" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                    <label for="floatingPassword">Password</label>
                                </div>
                                <div class="checkbox mb-3">
                                    <label>
                                        <input type="checkbox" value="remember-me"> Remember me
                                    </label>
                                </div>

                                <c:set var="error_login" value="${sessionScope.error_login}" scope="session"/>
                                <c:if test="${not empty error_login}">
                                    <div style="margin:-15px 0 15px 0; text-align: center">
                                        <span class="eror_message">${error_login}</span>
                                    </div>
                                    <c:set var="error_login" value="" scope="session"/>
                                </c:if>
                                <c:if test="${not empty not_active}">
                                    <div style="margin:-15px 0 15px 0; text-align: center">
                                        <span class="eror_message">${not_active}</span>
                                        <br/>
                                        <a href="verifyAccount.jsp">
                                            <i class="far fa-hand-point-right"></i> Click here to activate 
                                        </a>
                                    </div>

                                    <c:set var="not_active" value="" scope="session"/>
                                </c:if>

                                <div class="g-recaptcha" data-callback="recaptcha_callback" 
                                     data-sitekey="6Le-8s8aAAAAAIj62ZQ14hK_ppfWCtYgxwKUJwlt"
                                     ></div>
                                <br/>

                                <button name="btnAction" value="Login" id="login-btn" class="w-100 btn btn-lg btn-primary" type="submit" disabled>
                                    Sign in
                                </button >
                                <hr class="my-4">
                                <div style="text-align: center">
                                    <small style="margin-bottom: 25px; display: block" class="text-muted">Or Login with Google</small>
                                    <div style="padding: 0px 85px" class="g-signin2" 
                                         data-onsuccess="onSignIn"
                                         data-scope="https://www.googleapis.com/auth/plus.login"
                                         ></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>

            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                window.location.href = "http://localhost:8084/Lab1_Resource_Sharing/DispatchServlet?btnAction=loginGG&ggEmail="
                        + profile.getEmail()
                        + '&nameGG=' + profile.getName();
            }


            function recaptcha_callback() {
                var btn_login = document.getElementById('login-btn');
                btn_login.removeAttribute('disabled');
            }


        </script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </body>
</html>
