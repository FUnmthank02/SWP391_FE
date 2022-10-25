
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/mentorRegister.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <c:import url="./header.jsp"/>

        <div class="container contain_body">
            <h2 class="becomeMentor_title font-weight-bold">BECOME MENTOR</h2>
            <p class="text-danger">${requestScope.requestInProgress}</p>
            <p class="text-success">${requestScope.sendRequestSuccess}</p>
            
            <form class="form" method="post" action="mentor-register">
                <label for="bio" class="font-weight-bold label">Bio</label>
                <textarea class="form-control form-control-active" rows="4" name="bio" id="bio" placeholder="Your bio..."
                          required></textarea>
                <hr>
                <label for="skill" class="font-weight-bold label">Skills</label><br>
                <div class="row mb-2">

                    <!-- FOREACH o day -->
                    <c:forEach var="o" items="${requestScope.as}">
                    <div class="col-md-1">
                        <input class=" form-control-active" onchange="handleFillCkeckbox()" type="checkbox" name="skill" value="${o.getSkillId()}" id="skill" />
                        <span>${o.getSkillName()}</span>
                    </div>
                    </c:forEach>
                    <!-- FOREACH o day -->

                </div>
                <input class="form-control-active" onchange="handleToggleDisabled()" type="checkbox" name="ckbOtherTech"
                       id="ckbOtherTech" />
                <span>Other</span>
                <input class="form-control mt-1" id="other_technology" type="text" disabled name="otherSkills"
                       placeholder="Your other technology..." required pattern="[\w-]+(?:,+[\w-]+)*">
                <hr>

                <label for="exp" class="font-weight-bold label">Experience</label>
                <textarea class="form-control form-control-active" rows="4" name="exp" id="exp"
                          placeholder="Your experience..." required></textarea>
                <hr>
                <label for="achievement" class="font-weight-bold label">Achievement</label>
                <textarea class="form-control form-control-active" rows="4" name="achievement" id="achievement"
                          placeholder="Your achievement..." required></textarea>
                <hr>
                <input type="hidden" value="${requestScope.userId}" name="userId" />
                <button class="btn_submit" disabled>Submit</button>
            </form>
        </div>

        <c:import url="./footer.jsp"/>

        <script src="myjs/mentorRegister.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    </body>

</html>
