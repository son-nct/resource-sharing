<%-- 
    Document   : rentalPage2
    Created on : May 11, 2021, 9:50:33 AM
    Author     : WIN 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Request</title>
        <link style="width: 300px; height: 300px" rel = "icon" href ="./assets/img/rental-removebg-preview.png" type ="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./assets/css/rentalPage.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}" scope="session"/>

        <c:if test="${empty user}">
            <c:redirect url="signIn.jsp"></c:redirect>
        </c:if>
        <c:if test="${user.role eq 'Manager'}">
            <c:redirect url="manager.jsp"></c:redirect>
        </c:if>


        <div class="container" style="overflow: hidden">
            <div class="header-container">
                <nav class="py-2 bg-light border-bottom sticky-top">
                    <div class="container d-flex flex-wrap">
                        <ul class="nav me-auto">
                            <c:if test="${not empty user}">
                                <div style="display: flex; justify-content: center; align-items: center">
                                    <i class="far fa-user"></i>
                                    <li style="margin: 10px" class="nav-item">${user.role}: ${user.displayName}</li>
                                </div>
                            </c:if>
                        </ul>
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="DispatchServlet?btnAction=rentalPage&category=${sessionScope.CATEGORY}&date=${sessionScope.DATE}&searchValue=${sessionScope.SEARCH_VALUE}&page=${sessionScope.PAGE}" class="nav-link link-dark px-2">
                                    Home
                                </a>
                            </li>
                            <li class="nav-item"><a href="DispatchServlet?btnAction=logOut" class="nav-link link-dark px-2">Logout</a></li>
                        </ul>

                    </div>
                </nav>
            </div>



            <header class="py-3 mb-4 border-bottom" style="display: flex;">
                <div style="margin: auto"  class="container d-flex flex-wrap justify-content-center">
                    <a  style="margin-top: -10px; min-width: 200px" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                        <img style="width: 50px" class="bi me-2" src="./assets/img/rental-removebg-preview.png"/>
                        <span  class="fs-4">Your Request</span>
                    </a>
                </div>
            </header>


            <c:set var="map" value="${sessionScope.MAP_ITEMS}" scope="session"/>
            <c:if test="${empty map}">
                <div style="text-align: center; margin-bottom: 50px">
                    <img width="150px" height="150px" src="./assets/img/sad-removebg-preview.png"/>
                    <h3 style="text-align: center; color: red">You don't have any request !</h3>
                </div>
                
            </c:if>
            <c:set var="totalItems" value="${sessionScope.TOTAL_ITEMS}" scope="session"/>
            <c:if test="${not empty totalItems}">
                <h3 style="text-align: right; margin-bottom: 50px;">Total Request: ${totalItems}</h3>
            </c:if>

            <c:if test="${not empty map}">
                <button type="button" onclick="SendRequest()" class="btn btn-primary" style="margin-bottom: 10px">Send Request</button>
                <table class="table table-striped"  style="background-color: rgba(0,0,0,0.05); margin-bottom: 20px">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">Color</th>
                            <th scope="col">Category</th>
                            <th scope="col">RentalDate</th>
                            <th scope="col">Quanity</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="listError" value="${sessionScope.LIST_ERROR}" scope="session"/>
                        <c:set var="idError" value=""/>
                        <c:set var="dateError" value=""/>
                        <c:forEach var="dto" items="${map.keySet()}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.nameResource}
                                </td>
                                <td>${dto.color}</td>
                                <td>${dto.nameCate}</td>
                                <td>${dto.rentalDate}</td>
                                <c:if test="${not empty listError}">
                                    <c:forEach var="error" items="${listError}">
                                        <c:if test="${error.idResource eq dto.idResource and error.rentalDate eq dto.rentalDate}">
                                            <c:set var="idError" value="${error.idResource}"/>
                                            <c:set var="dateError" value="${error.rentalDate}"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <td>
                                    ${map.get(dto)}
                                    <c:if test="${idError eq dto.idResource and dateError eq dto.rentalDate}">
                                        <p style="color: red">*You can only rental maximum ${dto.quantityResource} resource</p>
                                    </c:if>
                                </td>
                                <td>
                                    <button id="btn-update" type="submit" onclick="UpdateQuantity('${dto.idResource}', '${dto.rentalDate}')"  class="btn btn-outline-primary">Update Quantity</button>
                                    <button id="btn-delete" type="submit" onclick="AskToDelete('${dto.idResource}', '${dto.rentalDate}')" class="btn btn-outline-danger">Delete</button>
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </c:if>




            <section class="" style="position: static; bottom: 0; width: 100%">
                <footer class="text-center text-white" style="background-color: #0a4275;">
                    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                        © 2020 Copyright By:
                        <a class="text-white" href="https://www.facebook.com/profile.php?id=100007759153267">Nguyễn Công Thái Sơn</a>
                    </div>
                </footer>
            </section>
        </div>
        <script>
            function checkInputLength() {
                var searchValue = document.getElementById('searchValue').value;

                if (searchValue === "") {
                    event.preventDefault();
                } else {
                    this.form.submit();
                }
            }

            function AskToDelete(idResource, date) {
                event.preventDefault();
                swal({
                    title: "Are you sure to delete this request?",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true
                })
                        .then((willDelete) => {
                            if (willDelete) {
                                window.location.href = "DispatchServlet?btnAction=deleteRequest"
                                        + "&idResource=" + idResource
                                        + "&date=" + date;
                            }
                        });
            }

            function SendRequest() {
                window.location.href = "DispatchServlet?btnAction=sendRequest";
            }

            function UpdateQuantity(idResource, date) {
                swal({
                    title: "Input new quantity: ",
                    content: {
                        element: "input",
                        attributes: {
                            placeholder: "Type positive number",
                            type: "number",
                        },
                    },
                    buttons: true,
                    dangerMode: true
                })
                        .then((value) => {
                            if (value.length > 0 && parseInt(value) > 0) {
                                window.location.href = "DispatchServlet?btnAction=updateQuantity"
                                        + "&idResource=" + idResource
                                        + "&date=" + date
                                        + "&newQuantity=" + value;
                            } else {
                                swal(`Please input positive number!`);
                            }

                        });
            }
        </script>
    </body>
</html>
