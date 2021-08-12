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
        <title>Request Resource</title>
        <link style="width: 300px; height: 300px" rel = "icon" href ="./assets/img/rental-removebg-preview.png" type ="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
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

        <c:set var="totalRequest" value="${sessionScope.TOTAL_REQUEST}" scope="session"/>
        <c:set var="totalPage" value="${sessionScope.TOTAL_PAGE}" scope="session"/>
        <c:set var="curPage" value="${param.page}" scope="request"/>
        <c:set var="dateFrom" value="${sessionScope.DATE_FROM}" scope="session"/>
        <c:set var="dateTo" value="${sessionScope.DATE_TO}" scope="session"/>


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
                            <li class="nav-item">
                                <a href="DispatchServlet?btnAction=rentalPage&category=${sessionScope.CATEGORY}&date=${sessionScope.DATE}&searchValue=${sessionScope.SEARCH_VALUE}&page=${sessionScope.PAGE}" class="nav-link link-dark px-2">
                                    Home
                                </a>
                            </li>
                            <li class="nav-item"><a href="DispatchServlet?btnAction=logOut" class="nav-link link-dark px-2">Logout</a></li>
                        </ul>

                    </div>
                </nav>


                <header class="py-3 mb-4 border-bottom" style="display: flex;">
                    <div style="margin: auto"  class="container d-flex flex-wrap justify-content-center">
                        <a  style="margin-top: -10px; min-width: 200px" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                            <img style="width: 50px" class="bi me-2" src="./assets/img/rental-removebg-preview.png"/>
                            <span  class="fs-4">History</span>
                        </a>
                    </div>
                    <!--Form-->
                    <form id="formSearch" action="DispatchServlet" method="GET">

                        <!--xử lí xóa request employee-->
                        <input type="hidden" name="btnAction" value="loadRequestHistory"/>
                        <div class="filter" style="display: flex">
                            <button style="height: 38px; min-width: 120px; margin-left: 20px" onclick="ResetDate()" type="button" class="btn btn-outline-secondary">Reset Date</button>
                            <div style="margin: auto; display: flex; align-items: center; justify-content: center">
                                <div style="display: flex; align-items: center; margin: 5px 0 0 20px">
                                    <p style="margin-right: 5px">From: </p>
                                    <input id="date-from" value="${dateFrom}" name="dateFrom" style=" margin-top: -12px" type="date" class="btn btn-light"/>
                                </div>

                                <div style="display: flex; align-items: center; margin: 5px 0 0 20px">
                                    <p style="margin-right: 5px">To: </p>
                                    <input name="dateTo" id="date-to" value="${dateTo}" style=" margin-top: -12px" type="date" class="btn btn-light"/>
                                </div>



                            </div>

                            <button onclick="checkDate()" style="height: 38px; margin-left: 20px" type="button" class="btn btn-primary">Search</button>
                        </div>
                    </form>
                </header>





                <h4>Total Request: ${totalRequest}</h4>

                <div style="display: flex">
                    <nav style="margin: 0 auto" class="header-pagination"  aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${totalPage}" step="1" var="i">
                                <c:set var="index" value="${i}"/>
                                <c:if test="${curPage != index}">
                                    <li class="page-item">
                                        <a class="page-link" 
                                           href="DispatchServlet?btnAction=loadRequestHistory&dateFrom=${dateFrom}&dateTo=${dateTo}&page=${i}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${curPage == index}">
                                    <li class="page-item active">
                                        <a class="page-link">${i}</a>
                                    </li>
                                </c:if>

                            </c:forEach>

                        </ul>
                    </nav>
                </div>

                <c:set var="listHistory" value="${sessionScope.LIST_HISTORY}" scope="session"/>
                <c:if test="${not empty listHistory}">
                    <div>

                    </div>
                    <table class="table table-striped"  style="background-color: rgba(0,0,0,0.05)">
                        <thead>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Request Date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${listHistory}">
                            <form action="DispatchServlet" method="POST">
                                <tr style="line-height: 35px">
                                    <th scope="row">${dto.ordinaryNumber}</th>
                                    <td>
                                        ${dto.requestDate}
                                        <input name="requestDate" type="hidden" value="${dto.requestDate}"/>
                                    </td>
                                    <c:set var="status" value="${dto.status}"/>
                                    <c:if test="${status eq 'New'}">
                                        <td>
                                            <div style="display: flex; align-content: center">
                                                <p style="font-size: 18px; ">Pending</p> 
                                                <i style="margin: 9px 0 0 5px" class="fas fa-hourglass-half"></i> 
                                            </div>
                                        </td>
                                    </c:if>

                                    <c:if test="${status eq 'Delete'}">
                                        <td>
                                            <div style="color: red; display: flex; align-content: center; font-size: 18px">
                                                <p>Reject</p>
                                                <i class="fas fa-times" style="margin: 10px 0 0 5px;"></i>
                                            </div>
                                        </td>
                                    </c:if>

                                    <c:if test="${status eq 'Accept'}">
                                        <td>
                                            <div style="display: flex; align-items: center; color:green">
                                                <p style="font-size: 18px">Accepted</p>
                                                <i style="margin: -12px 0 0 5px" class="far fa-check-circle"></i>
                                            </div> 
                                        </td>
                                    </c:if>
                                <input name="status" type="hidden" value="${dto.status}"/>
                                <td>
                                    <input type="hidden" name="idRental" value="${dto.idRental}"/>
                                    <button name="btnAction" value="detailHistory" type="submit" class="btn btn-outline-primary">View Detail</button>
                                    <c:if test="${status ne 'Accept'}">
                                        <button onclick="AskToDelete('${dto.idRental}', '${status}')" type="button" class="btn btn-outline-danger">Delete</button>
                                    </c:if>
                                </td>
                                </tr>
                            </form>

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
                function ResetDate() {
                    var date_from = document.getElementById('date-from');
                    var date_to = document.getElementById('date-to');

                    if (date_from.value.length >= 1 && date_to.value.length >= 1) {
                        date_from.valueAsDate = null;
                        date_to.valueAsDate = null;
                    }
                }

                function checkDate() {
                    var date_from = document.getElementById('date-from').value;
                    var date_to = document.getElementById('date-to').value;
                    var formSearch = document.getElementById('formSearch');


                    if (date_from !== "") {
                        if (date_to === "") {
                            swal({
                                icon: 'error',
                                title: "Please choose Date to !"
                            });

                        } else if (date_to < date_from) {
                            swal({
                                icon: 'error',
                                title: "Date to must larger than Date from!"
                            });
                        } else {
                            formSearch.submit();
                        }

                    } else {
                        formSearch.submit();
                    }

                }

                function AskToDelete(idRental, status) {
                    event.preventDefault();

                    swal({
                        title: "Are you sure to delete this history?",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true
                    })
                            .then((willDelete) => {
                                if (willDelete) {
                                    window.location.href = "DispatchServlet?btnAction=deleteHistory"
                                            + "&idRental=" + idRental
                                            + "&status=" + status;
                                }
                            });
                }
            </script>
    </body>
</html>
