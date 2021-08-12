<%-- 
    Document   : rentalPage2
    Created on : May 11, 2021, 9:50:33 AM
    Author     : WIN 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Request</title>
        <link style="width: 300px; height: 300px" rel = "icon" href ="./assets/img/rental-removebg-preview.png" type ="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <script>
            function preventBack() {
                window.history.forward();
            }
            setTimeout("preventBack()", 0);
            window.onunload = () => {
                null;
            };
        </script>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}" scope="session"/>
        <c:if test="${empty user}">
            <c:redirect url="signIn.jsp"></c:redirect>
        </c:if>
        <c:if test="${user.role ne 'Manager'}">
            <c:redirect url="rentalPage.jsp"></c:redirect>
        </c:if>
        <c:set var="page" value="${sessionScope.PAGE}" scope="session"/>
        <div class="container">
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
                            <c:if test="${empty page}">
                                <c:set var="page" value="1"/>
                            </c:if>
                            <li class="nav-item"><a href="DispatchServlet?btnAction=logOut" class="nav-link link-dark px-2">Logout</a></li>
                        </ul>

                    </div>
                </nav>


                <header class="py-3 mb-4 border-bottom" style="display: flex;">
                    <div style="margin: auto"  class="container d-flex flex-wrap justify-content-center">
                        <a  style="margin-top: -10px; min-width: 200px" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                            <img style="width: 50px" class="bi me-2" src="./assets/img/rental-removebg-preview.png"/>
                            <span  class="fs-4">Manage request</span>
                        </a>
                    </div>
                    <!--Form-->

                    <c:set var="email" value="${sessionScope.EMAIL}" scope="session"/>
                    <c:set var="reqDate" value="${sessionScope.REQ_DATE}" scope="session"/>
                    <c:set var="idRental" value="${sessionScope.IDRENTAL}" scope="session"/>
                    <c:set var="listDetail" value="${sessionScope.LIST_DETAIL}" scope="session"/>
                    <c:set var="status" value="${sessionScope.STATUS}" scope="session"/>

                </header>


                <a class="btn btn-primary" href="DispatchServlet?btnAction=manager&status=&searchValue=&page=${page}" role="button">
                    Back
                </a>

                <div style="display: flex; margin-top: 15px">
                    <div class="card" style="width: 100%; margin: auto">
                        <div class="card-header" style="display: flex; justify-content: space-between; align-items: center; padding-top: 10px">
                            <span style="display: flex;">
                                <p style="font-size: 18px; font-weight: 600; margin-right: 5px"> Email:<p> 
                                <p style="font-size: 18px">${email}</p>
                            </span>
                            <span style="display: flex;">
                                <p style="font-size: 18px; font-weight: 600; margin-right: 5px"> RequestDate:<p> 
                                <p style="font-size: 18px">${reqDate}</p>
                            </span>
                            <span style="display: flex;">
                                <p style="font-size: 18px; font-weight: 600; margin-right: 5px"> Status: <p> 
                                    <c:if test="${status eq 'New'}">
                                    <div style="display: flex; align-content: center">
                                        <p style="font-size: 18px; ">Pending</p> 
                                        <i style="margin: 5px 0 0 5px" class="fas fa-hourglass-half"></i> 
                                    </div>
                                </c:if>

                                <c:if test="${status eq 'Delete'}">
                                    <div style="color: red; display: flex; align-content: center; font-size: 18px">
                                        <p style="font-size: 18px">Reject</p> <i class="fas fa-times" style="margin: 5px 0 0 5px;"></i>
                                    </div>
                                </c:if>

                                <c:if test="${status eq 'Accept'}">
                                    <div style="display: flex; align-items: center; color:green">
                                        <p style="font-size: 18px">Accepted</p>
                                        <i style="margin: -12px 0 0 5px" class="far fa-check-circle"></i>
                                    </div>
                                </c:if>
                            </span>
                        </div>
                        <form action="DispatchServlet">
                            <div class="card-body"  style="text-align: center">
                                <table class="table table-striped"  style="background-color: rgba(0,0,0,0.05)">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name Resource</th>
                                            <th>Color</th>
                                            <th>Category</th>
                                            <th>Quantity</th>
                                            <th>Rental Date</th>
                                            <th>Return Date</th>
                                            <th>Note</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="listError" value="${sessionScope.LIST_ERROR}" scope="session"/>

                                        <c:if test="${not empty listDetail}">
                                            <c:forEach var="dto" items="${listDetail}" varStatus="counter">
                                                <c:if test="${not empty listError}">
                                                    <c:set var="idResource" value=""  scope="request"/>
                                                    <c:set var="rentalDate" value=""  scope="request"/>
                                                    <c:forEach var="error" items="${listError}">
                                                        <c:if test="${dto.idResource eq error.idResource and dto.rentalDate eq error.rentalDate}">
                                                            <c:set var="errorResource" value="${error.idResource}"/>
                                                            <c:set var="errorRentalDate" value="${error.rentalDate}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>

                                                <tr>
                                                    <td>
                                                        ${counter.count}

                                                    </td>
                                                    <td>${dto.nameResource}</td>                                              
                                                    <td>${dto.color}</td>
                                                    <td>${dto.nameCategory}</td>
                                                    <td>                                                   
                                                        ${dto.quantityRequest}
                                                    </td>

                                                    <td>${dto.rentalDate}</td>
                                                    <td>${dto.returnDate}</td>
                                                    <c:if test="${dto.idResource eq errorResource and dto.rentalDate eq errorRentalDate}">
                                                        <td>
                                                            <p style="color: red">*Not enough quantity</p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dto.idResource ne errorResource and dto.rentalDate ne errorRentalDate}">
                                                        <td>

                                                        </td>
                                                    </c:if>

                                                </tr>
                                            </c:forEach>
                                        </c:if>

                                    </tbody>
                                </table>
                            </div>
                            <c:set var="removeAccept" value="${sessionScope.CANT_ACCEPT}" scope="session"/>


                            <c:if test="${status ne 'Accept' and status ne 'Delete'}">
                                <div class="card-footer text-muted" style="text-align: center">

                                    <button name="btnAction" value="DeletePending" type="submit" class="btn btn-danger">Delete</button>
                                    <c:if test="${empty removeAccept}">
                                        <button name="btnAction" type="submit" value="AcceptPending" class="btn btn-success">Accept</button>
                                    </c:if>
                                    <input name="idRental" type="hidden" value="${idRental}"/>
                                </div>
                            </c:if>

                        </form>
                    </div>
                </div>


            </div>
        </div>
        <section class="" style="position: fixed; bottom: 0; width: 85%; left: 7%">
            <footer class="text-center text-white" style="background-color: #0a4275;">
                <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                    © 2020 Copyright By:
                    <a class="text-white" href="https://www.facebook.com/profile.php?id=100007759153267">Nguyễn Công Thái Sơn</a>
                </div>
            </footer>
        </section>
        <script>

        </script>
    </body>
</html>
