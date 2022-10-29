
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


        <div class="container contain_invitationPage">

            <div class="head">
                <div class="subhead">
                    <h4 class="font-weight-bold"><a href="invitation.jsp">List invitation</a></h4>
                </div>
                <div class="subhead page_active">
                    <h4 class="font-weight-bold">List mentor register's request</h4>
                </div>
            </div>

            <div class="contain_notify">

                <c:forEach var="o" items="${requestScope.listMentorRegister}">
                    <div class="content_invitation">
                        <div>
                            <p class="text_invitation">${o.getUser().getFullname()} has sent you a request to become a mentor</p>
                        </div>
                            <c:if test="${requestScope.dao.getMentorByUserId(o.getUser()) == null && !(requestScope.dao.getMentorByUserId(o.getUser()).getStatus eq 'active')}">
                                <div style="display: flex;">
                                    <input style="border-radius: 5px; padding: 1px 2px;" class="btn-outline-success" type="button" 
                                           data-toggle="modal" data-target="#modal_view_cv" data-id="${o.getUser().getUserId()}" value="Quick view"/>

                                    <form method="post" action="action-manage-mentor-register">
                                        <input type="hidden" value="accept" name="accept"/>
                                        <input type="hidden" value="${o.getUser().getUserId()}" name="userID"/>                                
                                        <input style="border-radius: 5px; padding: 1px 2px;" class="btn-outline-primary ml-2" type="submit" value="Accept"/>
                                    </form>

                                    <form method="post" action="action-manage-mentor-register">
                                        <input type="hidden" value="reject" name="reject"/>
                                        <input type="hidden" value="${o.getUser().getUserId()}" name="userID"/>
                                        <input style="border-radius: 5px; padding: 1px 2px;" class="btn-outline-danger ml-2" type="submit" value="Reject"/>
                                    </form>
                                </div>      
                            </c:if>
                    </div>
                </c:forEach>



            </div>

            <!-- modal view cv -->
            <div class="modal" tabindex="-1" role="dialog" id="modal_view_cv">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title font-weight-bold text-success">Mentor's profile</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                            <div class="modal-body">
                                <p class="fieldName font-weight-bold text-primary">Bio</p>
                                <p class="ml-2" id="bio"></p>
                                <hr>
                                <p class="fieldName font-weight-bold text-primary">Skill</p>
                                <p><span class="m-2" id="existedSkill" style="word-spacing: 10px; text-transform: capitalize;"></span></p>
                                <p class="fieldName text-primary ml-2">New Skill</p>
                                <p><span class="m-2" id="newSkill" style="word-spacing: 10px; text-transform: capitalize;"></span></p>
                                <hr>

                                <p class="fieldName font-weight-bold text-primary">Experience</p>
                                <p class="ml-2" id="exp"></p>
                                <hr>

                                <p class="fieldName font-weight-bold text-primary">Achievement</p>
                                <p class="ml-2" id="achieve"></p>
                                <hr>
                            </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <c:import url="./footer.jsp" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var id, userID;

                $('#modal_view_cv').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    id = button.data('id'); // Extract info from data-* attributes

                <c:forEach var="o" items="${requestScope.listMentorRegister}">
                        userID = ${o.getUser().getUserId()}
                        if(id == userID) {
                            document.getElementById('bio').innerHTML = "${o.getBio()}"
                            document.getElementById('existedSkill').innerHTML = "${o.getExistedSkill()}"
                            document.getElementById('newSkill').innerHTML = "${o.getNewSkill()}"
                            document.getElementById('exp').innerHTML = "${o.getExp()}"
                            document.getElementById('achieve').innerHTML = "${o.getAchievement()}"
                        }
                </c:forEach>
                });
            });
        </script>
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
