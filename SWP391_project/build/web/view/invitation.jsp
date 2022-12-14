
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/invitation.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <c:import url="./header.jsp" />


        <div class="container contain_invitationPage" style="padding-top: 200px">

            <div class="head">
                <div class="subhead page_active">
                    <h4 class="font-weight-bold">List invitation</h4>
                </div>
            </div>

            <div class="contain_notify">
                <c:forEach items="${requestScope.invitations}" var="i">
                    <c:if test="${i.getStatus() eq 'processing'}">
                        <div class="content_invitation">
                            <div>
                                <p class="text_invitation">${i.mentee.user.fullname} has sent you a invitation to be mentor</p>
                            </div>
                            <div  style="display: flex;">
                                <form action="invitation" method="POST">
                                    <input type="hidden" name ="invitationId" value="${i.invitationID}">
                                    <input class="btn btn-outline-success mr-2" type="submit" name="accept" id="Accept" value="accept">
                                </form>
                                <form action="invitation" method="POST">
                                    <input type="hidden" name ="invitationId" value="${i.invitationID}">
                                    <input class="btn btn-outline-danger ml-2" type="submit" name="reject" id="Reject" value="reject">
                                </form>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>


        </div>

        <c:import url="./footer.jsp" />


        <!-- <script src="./myjs/home.js"></script> -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <script>
            AOS.init();
        </script>
    </body>

</html>