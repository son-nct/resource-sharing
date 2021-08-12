
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/signIn.css">
    </head>
    <script type="text/javascript">
        function disableBack() {
            window.history.forward();
        }
        setTimeout("disableBack()", 0);
        window.onunload = () => {
            null;
        };
    </script>
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
                            <li class="nav-item"><a href="signIn.jsp" class="nav-link active">Sign In</a></li>
                        </ul>
                    </header>
                </div>
            </div>

            <div class="body-page">
                <div class="container col-xl-10 col-xxl-8 px-0 py-1" >
                    <div class="row align-items-center g-lg-5 py-2">
                        <div class="col-md-10 mx-auto col-lg-5" >
                            <form action="DispatchServlet" method="POST" class="p-4 p-md-5 border rounded-3 bg-light" style="width: 400px">

                                <h3 style=" text-align: center; margin: -25px 0 20px 0">Sign Up</h3>

                                <div class="form-floating mb-3">
                                    <input name="txtEmail" value="${param.txtEmail}" type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
                                    <label for="floatingInput">Email</label>

                                    <c:set var="exit_user" value="${requestScope.exit_user}" scope="request"/>
                                    <c:set var="empty_email" value="${requestScope.empty_email}" scope="request"/>
                                    <c:set var="invalid_email" value="${requestScope.invalid_email}" scope="request"/>
                                    <c:choose>
                                        <c:when test="${not empty exit_user}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${exit_user}</span>
                                            </div>
                                            <c:set var="exit_user" value="" scope="request"/>
                                        </c:when>

                                        <c:when test="${not empty empty_email}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${empty_email}</span>
                                            </div>
                                            <c:set var="empty_email" value="" scope="request"/>
                                        </c:when>

                                        <c:when test="${not empty invalid_email}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${invalid_email}</span>
                                            </div>
                                            <c:set var="invalid_email" value="" scope="request"/>
                                        </c:when>
                                    </c:choose>

                                </div>

                                <div class="form-floating mb-3" style="position: relative">
                                    <input name="txtPassword" value="${param.txtPassword}" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                    <label for="floatingPassword">Password</label>

                                    <c:set var="empty_password" value="${requestScope.empty_password}" scope="request"/>
                                    <c:if test="${not empty empty_password}">
                                        <div class="eror_message" style="margin:2px 0 0px 0;">
                                            <span class="eror_message">${empty_password}</span>
                                        </div>
                                        <c:set var="empty_password" value="" scope="request"/>
                                    </c:if>
                                </div>

                                <div class="form-floating mb-3" style="position: relative">
                                    <input name="txtRepassword" value="${param.txtRepassword}" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                    <label for="floatingPassword">Repassword</label>

                                    <c:set var="not_match" value="${requestScope.not_match}" scope="request"/>
                                    <c:if test="${not empty not_match}">
                                        <div class="eror_message" style="margin:2px 0 0px 0;">
                                            <span class="eror_message">${not_match}</span>
                                        </div>
                                        <c:set var="not_match" value="" scope="request"/>
                                    </c:if>
                                </div>



                                <div class="form-floating mb-3">
                                    <input name="txtDisplayName" value="${param.txtDisplayName}" type="text" class="form-control" id="floatingInput"  placeholder="name@example.com">
                                    <label for="floatingInput">Display Name</label>

                                    <c:set var="empty_displayName" value="${requestScope.empty_displayName}" scope="request"/>
                                    <c:if test="${not empty empty_displayName}">
                                        <div class="eror_message" style="margin:2px 0 0px 0;">
                                            <span class="eror_message">${empty_displayName}</span>
                                        </div>
                                        <c:set var="empty_displayName" value="" scope="request"/>
                                    </c:if>
                                </div>

                                <div class="form-floating mb-3">
                                    <input name="txtPhone" value="${param.txtPhone}" type="text" class="form-control" id="floatingInput" placeholder="Phone">
                                    <label for="floatingInput">Phone</label>

                                    <c:set var="empty_phone" value="${requestScope.empty_phone}" scope="request"/>
                                    <c:set var="error_phone" value="${requestScope.error_phone}" scope="request"/>
                                    <c:choose>
                                        <c:when test="${not empty empty_phone}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${empty_phone}</span>
                                            </div>
                                            <c:set var="empty_phone" value="" scope="request"/>
                                        </c:when>

                                        <c:when test="${not empty error_phone}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${error_phone}</span>
                                            </div>
                                            <c:set var="error_phone" value="" scope="request"/>
                                        </c:when>
                                    </c:choose>
                                </div>

                                <div class="form-floating mb-3">
                                    <input name="txtAddress" value="${param.txtAddress}" type="text" class="form-control" id="floatingInput" placeholder="Address">
                                    <label for="floatingInput">Address</label>

                                    <c:set var="empty_address" value="${requestScope.empty_address}" scope="request"/>
                                    <c:if test="${not empty empty_address}">
                                        <div class="eror_message" style="margin:2px 0 0px 0;">
                                            <span class="eror_message">${empty_address}</span>
                                        </div>
                                        <c:set var="empty_address" value="" scope="request"/>
                                    </c:if>
                                </div>

                                <div class="checkbox mb-3">
                                    <label>
                                        <input type="checkbox" value="remember-me"> Remember me
                                    </label>
                                </div>

                                <button name="btnAction" value="signUp" class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>



