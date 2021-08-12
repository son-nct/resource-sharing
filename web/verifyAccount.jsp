<%-- 
    Document   : verificationAccount
    Created on : May 15, 2021, 12:45:09 AM
    Author     : WIN 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/verifyAccount.css">    
        <title>Verify Account</title>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2/dist/email.min.js"></script>
        <script type="text/javascript">
            (function () {
                emailjs.init("user_Rz8Jy1nmUEDqIfvhP39J0");
            })();
        </script>
    </head>
    <body>
        <c:set var="user_register" value="${sessionScope.user_register}" scope="session"/>
        <c:if test="${empty user_register}">
            <c:redirect url = "signIn.jsp"/>
        </c:if>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="height: 100vh">
            <tbody>
                <tr>
                    <td bgcolor="#FFA73B" align="center">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">
                            <tbody><tr>
                                    <td align="center" valign="top" style="padding: 40px 10px 40px 10px;"> </td>
                                </tr>
                            </tbody></table>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#FFA73B" align="center" style="padding: 0px 10px 0px 10px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">
                            <tbody style="position: absolute; top: 5%">
                                <tr>
                                    <td bgcolor="#ffffff" align="center" valign="top" style="padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;">
                                        <h1 style="font-size: 48px; font-weight: 400; margin: 2;">Welcome!</h1> <img src=" https://img.icons8.com/clouds/100/000000/handshake.png" width="125" height="120" style="display: block; border: 0px;">
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#f4f4f4" align="center" style="padding: 0px 10px 0px 10px;">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">
                                            <tbody>
                                                <tr>
                                                    <td bgcolor="#ffffff" align="left" style="padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;">
                                                        <p style="margin: 0;">We're excited to have you get started. Enter the activation code in the box below to activate your account.</p>
                                                        <p style="padding: 20px 0">We have sent the activation code into: 
                                                            <strong>${user_register.email}</strong>
                                                        </p>

                                                    </td>
                                                </tr>
                                            <form action="DispatchServlet" method="POST">

                                                <tr>
                                                    <td style="text-align: center; border: none">
                                                        <c:set var="error_code" value="${requestScope.error_code}"/>
                                                        <c:if test="${not empty empty_code}">
                                                            <p style="color: red; font-size: 18px; margin-top: -25px">${empty_code}</p>
                                                        </c:if>
                                                        <c:if test="${not empty error_code}">
                                                            <p style="color: red; font-size: 18px; margin-top: -25px">${error_code}</p>
                                                        </c:if>
                                                        <input type="number" name="txtCode" placeholder="Activation code" style="width: 200px; height: 40px;">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td bgcolor="#ffffff" align="left">
                                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td bgcolor="#ffffff" align="center" style="padding: 20px 30px 60px 30px;">
                                                                        <table border="0" cellspacing="0" cellpadding="0">
                                                                            <tbody>
                                                                                <tr>
                                                                            <button name="btnAction" value="checkActivate" style="margin-top: 10px" bgcolor="#FFA73B" type="submit" class="btn-confirm">Confirm Account</button>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </tbody>
                                        </table>
                                    </td>
                                </tr> 
                            <input name="txtEmail" id="emailGG" type="hidden"  value="${user_register.email}"/>
                            <input id="codeGG" type="hidden"  value="${sessionScope.code}"/>
                            <input id="check" type="hidden"  value="${requestScope.already_send}"/>

                            </form>
            </tbody>
        </table>
    </td>
</tr>
</tbody>
</table>
</td>
</tr>

</tbody>
</table>

<script>
    var check = document.getElementById('check').value;
    if (check !== "true") {
        sendMail();
        function sendMail() {
            var to_email = document.getElementById('emailGG').value;
            var code = document.getElementById('codeGG').value;
            var tempParams = {
                from_name: "RentalResource@gmail.com",
                to_name: to_email,
                message: code
            };
            emailjs.send("service_qcbjxwg", "template_oe1t18l", tempParams).then(function (res) {
            });
        }
    }


</script>                                                
</body>
</html>
