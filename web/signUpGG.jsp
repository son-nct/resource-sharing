
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Google</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/signIn.css">
        <script>
            function preventBack() {
                window.history.forward();
            }
            setTimeout("preventBack()", 0);
            window.onunload = function () {
                null
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
                            <li class="nav-item"><a href="rentalPage.jsp" class="nav-link active" aria-current="page">Home</a></li>
                        </ul>
                    </header>
                </div>
            </div>

            <div class="body-page">
                <div class="container col-xl-10 col-xxl-8 px-0 py-1" >
                    <div class="row align-items-center g-lg-5 py-2">
                        <div class="col-md-10 mx-auto col-lg-5" >
                            <form action="DispatchServlet" method="POST" class="p-4 p-md-5 border rounded-3 bg-light" style="width: 400px">
                                <h3 style=" text-align: center; margin: -25px 0 20px 0">Sign Up With Google</h3>

                                <div class="form-floating mb-3">
                                    <input type="text" value="${sessionScope.email}" class="form-control" id="floatingInput" placeholder="name@example.com" disabled>
                                    <label for="floatingInput">Email</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input name="TxtGGName" value="${sessionScope.name}"  type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
                                    <label for="floatingInput">Display Name</label>

                                    <c:set var="empty_name" value="${requestScope.empty_name}" scope="request"/>
                                    <c:if test="${not empty empty_name}">
                                        <div style="margin:2px 0 15px 0;">
                                            <span class="eror_message">${empty_name}</span>
                                        </div>
                                    </c:if>
                                </div>

                                <div class="form-floating mb-3">
                                    <input name="TxtGGPhone" value="${param.TxtGGPhone}" type="text" class="form-control" id="floatingInput" placeholder="Phone">
                                    <label for="floatingInput">Phone</label>

                                    <c:set var="empty_phone" value="${requestScope.empty_phone}" scope="request"/>
                                    <c:set var="error_phone" value="${requestScope.error_phone}" scope="request"/>
                                    <c:choose>
                                        <c:when test="${not empty empty_phone}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${empty_phone}</span>
                                            </div>

                                        </c:when>

                                        <c:when test="${not empty error_phone}">
                                            <div style="margin:2px 0 15px 0">
                                                <span class="eror_message">${error_phone}</span>
                                            </div>
                                        </c:when>
                                    </c:choose>

                                </div>
                                <div class="form-floating mb-3">
                                    <input value="${param.TxtGGAddress}" name="TxtGGAddress" type="text" class="form-control" id="floatingInput" placeholder="Address">
                                    <label for="floatingInput">Address</label>

                                    <c:set var="empty_address" value="${requestScope.empty_address}" scope="request"/>
                                    <c:if test="${not empty empty_address}">
                                        <div style="margin:2px 0 15px 0">
                                            <span class="eror_message">${empty_address}</span>
                                        </div>
                                    </c:if>
                                </div>

                                <button name="btnAction" value="signUpGG" style="margin-top: 20px" class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

</body>
</html>
