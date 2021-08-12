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
        <title>Rental Resource</title>
        <link style="width: 300px; height: 300px" rel = "icon" href ="./assets/img/rental-removebg-preview.png" type ="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="./assets/css/rentalPage.css">
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
                            <li class="nav-item"><a href="DispatchServlet?btnAction=loadRequestHistory" class="nav-link link-dark px-2">History</a></li>

                            <li class="nav-item"><a href="DispatchServlet?btnAction=ViewRequest" class="nav-link link-dark px-2">View Your Request</a></li>

                            <li class="nav-item"><a href="DispatchServlet?btnAction=logOut" class="nav-link link-dark px-2">Logout</a></li>
                        </ul>

                    </div>
                </nav>
            </div>

            <header class="py-3 mb-4 border-bottom" style="display: flex;">
                <div style="margin: auto"  class="container d-flex flex-wrap justify-content-center">
                    <a  style="margin-top: -10px; min-width: 200px" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                        <img style="width: 50px" class="bi me-2" src="./assets/img/rental-removebg-preview.png"/>
                        <span  class="fs-4">Search resource</span>
                    </a>
                </div>

                <div class="filter" style="display: flex">
                    <div style="margin: auto; display: flex; align-items: center; justify-content: center">

                        <div style="display: flex; align-items: center; padding: 0 20px; margin-top: 5px">
                            <p style="margin-right: 5px">Category: </p>
                            <c:set var="listCateEmp" value="${sessionScope.LISTCATE}" scope="session"/>
                            <c:if test="${not empty listCateEmp}">

                                <select  id="cbo" name ="cbo" style="margin-top: -12px; height: 30px" class="mdb-select md-form">
                                    <option value="">All</option>
                                    <c:forEach var="cate" items="${listCateEmp}">
                                        <option
                                            <c:if test="${param.category eq cate.idCate}">
                                                selected="true"
                                            </c:if>
                                            value="${cate.idCate}">${cate.nameCate}</option>
                                    </c:forEach>
                                </select>
                            </c:if>

                            <c:set var="listCateLea" value="${sessionScope.LISTCATELEADER}" scope="session"/>
                            <c:set var="category" value="${sessionScope.CATEGORY}" scope="session"/>
                            <c:if test="${not empty listCateLea}">
                                <select   id="cbo" name="cbo" style="margin-top: -12px; height: 30px" class="mdb-select md-form">
                                    <option value="" >All</option>
                                    <c:forEach var="cate" items="${listCateLea}">
                                        <option
                                            <c:if test="${category eq cate.idCate}">
                                                selected="true"
                                            </c:if>
                                            value="${cate.idCate}">${cate.nameCate}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </div>

                        <c:set var="date" value="${sessionScope.DATE}" scope="session"/>
                        <div style="display: flex; align-items: center; margin: 5px 0 0 20px">
                            <p style="margin-right: 5px;min-width: 100px">Rental Date: </p>
                            <input value="${date}" id="datefield" style=" margin-top: -12px" type="date" class="btn btn-light"/>
                        </div>


                    </div>
                    <input id="searchValue" value="${param.searchValue}"  style="margin: 0 5px 0 20px; width:200px; height: 38px" type="search" class="form-control" placeholder="Search..." aria-label="Search">
                    <button onclick="SearchResource()" style="height: 38px" type="button" class="btn btn-primary">Search</button>

                </div>

            </header>

            <c:set var="totalPage" value="${sessionScope.TOTALPAGE}" scope="session"/>
            <c:set var="curPage" value="${param.page}" scope="request"/>
            <c:set var="searchValue" value="${param.searchValue}" scope="request"/>
            <c:set var="date" value="${param.date}" scope="request"/>
            <c:set var="totalElement" value="${sessionScope.TOTALELEMENT}" scope="session"/>

            <c:if test="${not empty totalPage}">
                <h4 style="text-align: right">Total resource: ${totalElement}</h4>
            </c:if>

            <nav class="header-pagination"  aria-label="Page navigation example">

                <ul class="pagination">

                    <c:if test="${not empty totalPage and totalPage > 1}">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <c:set var="pageIndex" value="${i}"/>
                            <c:if test="${curPage != pageIndex}">
                                <li class="page-item">
                                    <a class="page-link" href="DispatchServlet?btnAction=search&category=${param.category}&date=${date}&searchValue=${searchValue}&page=${i}">
                                        ${i}
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPage == pageIndex}">
                                <li class="page-item active">
                                    <a class="page-link">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </ul>
            </nav>

            <c:set var="listResource" value="${sessionScope.LIST_RESOURCE}" scope="session"/>
            <c:if test="${not empty listResource}">
                <table class="table table-striped"  style="background-color: rgba(0,0,0,0.05); margin-bottom: 20px">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">Quanity</th>
                            <th scope="col">Color</th>
                            <th scope="col">Category</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>



                        <c:forEach var="dto" items="${listResource}">
                        <form action="DispatchServlet" method="POST">
                            <tr style="line-height: 35px; text-align: left; margin-left: 10px">
                                <th scope="row">${dto.ordinalNumber}</th>
                                <td>
                                    ${dto.nameResource}
                                    <input type="hidden"  name="nameResource" value="${dto.nameResource}"/>

                                </td>
                                <td>${dto.quantity}
                                    <input type="hidden"  name="quantityResource" value="${dto.quantity}"/>
                                </td>
                                <td>
                                    ${dto.color}
                                    <input type="hidden"  name="color" value="${dto.color}"/>
                                </td>
                                <td>
                                    ${dto.nameCate}
                                    <input type="hidden"  name="nameCate" value="${dto.nameCate}"/>
                                </td>
                                <td>
                                    <input type="hidden"  name="IdResource" value="${dto.resourceId}"/>
                                    <input type="hidden"  name="rentalDate" value="${date}"/>
                                    <input type="hidden"  name="searchValue" value="${param.searchValue}"/>
                                    <input type="hidden"  name="page" value="${curPage}"/>
                                    <input type="hidden"  name="category" value="${param.category}"/>

                                    <c:set var="sendRequest" value="${sessionScope.sendRequest}" scope="session"/>
                                    <button name="btnAction" value="AddRequest"  class="btn btn-outline-success">Add Request</button>

                                   
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
            function validationDate() {
                date_request = document.getElementById('datefield');

                var dtToday = new Date();

                var month = dtToday.getMonth() + 1;
                var year = dtToday.getFullYear();
                var day = dtToday.getDate();

                if (month < 10)
                    month = '0' + month.toString();
                if (day < 10)
                    day = '0' + day.toString();

                var minDate = year + "-" + month + "-" + day;
                date_request.setAttribute('min', minDate);

            }

            validationDate();

            <c:if test="${not empty sendRequest}">
            swal({
                title: "Added Request Success!",
                icon: "success",
                button: "OK"
            });
                <c:set var="sendRequest" value="" scope="session"/>
            </c:if>






            function SearchResource() {

                var chooseDate = document.getElementById('datefield').value;

                if (chooseDate === "") {
                    swal("Oops!", "Please select a rental date!", "error");
                } else {
                    var search = document.getElementById('searchValue').value;
                    var category = document.getElementById("cbo").value;
                    window.location.href = 'DispatchServlet?btnAction=search&category='
                            + category + '&date=' + chooseDate
                            + '&searchValue=' + search + '&page=1';



                }
            }
            function loadResourceByCate() {
                var category = document.getElementById("cbo").value;
                window.location = "loadResource?cbo=" + category + "&page=1";
            }
        </script>
    </body>
</html>
