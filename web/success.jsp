<%-- 
    Document   : success
    Created on : May 15, 2021, 11:58:00 PM
    Author     : WIN 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success Sign Up</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    </head>
    <body style="display: flex">
        <div style="margin: 15% auto">
            <h1 style="color: green">successfull activation <i class="fas fa-check"></i> </h1>
            <h3>
                ...Redirecting to sign in page after <span id="countdown">5</span> seconds
            </h3>
        </div>

        <!-- JavaScript part -->
        <script type="text/javascript">

            var seconds = 5;

            // Run countdown function
            countdown();
            function countdown() {
                seconds = seconds - 1;
                if (seconds < 0) {
                    // Chnage your redirection link here
                    window.location.href = "http://localhost:8084/Lab1_Resource_Sharing/signIn.jsp";
                } else {
                    // Update remaining seconds
                    document.getElementById("countdown").innerHTML = seconds;
                    // Count down using javascript
                    window.setTimeout("countdown()", 1000);
                }
            }




        </script>
    </body>
</html>
