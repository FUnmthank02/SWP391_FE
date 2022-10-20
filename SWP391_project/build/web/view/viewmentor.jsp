
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/seeMentor.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <!--header-->
        <c:import url="./header.jsp"/>

        <!-- Outstanding Mentors with techology -->

        <div class="container-fluid p-5" style="padding-top: 150px !important;">
            <div class="row bg_search">
                <div class="col-md-6 col-sm-12-col-xs-12">
                    <h1 class="left_side_search_title">Hire and learn programing with mentors</h1>
                    <p class="h5">Find your own mentor here <i class="fa-solid fa-arrow-right-long ml-2"></i></p>
                </div>
                <div class="col-md-6 col-sm-12-col-xs-12">
                    <div class="contain_form_search">
                        <form action="" class="form_search">
                            <h2 class="title_search text-success text-uppercase">Search</h2>
                            <div class="contain_rating_search">
                                <p class="lable_input_search font-weight-bold">Rating:</p>
                                <label for="">0-1 <i id="star-icon" class="fa-solid fa-star"></i></label><input
                                    class="mr-4 ml-2" type="radio" name="rating_search">
                                <label for="">1-2 <i id="star-icon" class="fa-solid fa-star"></i></label><input
                                    class="mr-4 ml-2" type="radio" name="rating_search">
                                <label for="">2-3 <i id="star-icon" class="fa-solid fa-star"></i></label><input
                                    class="mr-4 ml-2" type="radio" name="rating_search">
                                <label for="">3-4 <i id="star-icon" class="fa-solid fa-star"></i></label><input
                                    class="mr-4 ml-2" type="radio" name="rating_search">
                                <label for="">4-5 <i id="star-icon" class="fa-solid fa-star"></i></label><input
                                    class="mr-4 ml-2" type="radio" name="rating_search">
                            </div>
                            <hr>
                            <div class="contain_technology_search form-group">
                                <label for="tech_search_select" class="lable_input_search mr-3 font-weight-bold">Technology:</label>
                                <select name="technology_search" id="tech_search_select" class="form-control">
                                    <option value="">java</option>
                                    <option value="">C</option>
                                    <option value="">C#</option>
                                    <option value="">Ruby</option>
                                </select>
                            </div>
                            <hr>
                            <button type="submit" class="btn_search btn btn-success">Search</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${requestScope.tech != null}">

            <div class="container wrap-outstanding-mentor">
                <div class="part"  data-aos="fade-up" data-aos-duration="1000">
                    <h4 style="text-transform: capitalize;">${requestScope.tech} Mentors</h4>
                    <div class="line_part"></div>
                </div>

                <div class="list-mentors">
                    <div class="row">
                        <c:forEach items="${requestScope.listMentor}" var="m">

                            <div class="col-md-3 col-sm-6 col-xs-12 card-mentor">
                                <div class="items-card-mentor">

                                    <c:forEach items="${requestScope.listUser}" var="u">
                                        <c:if test="${m.getUser().getUserId() == u.getUserId()}">
                                            <div class="card-upper-part">
                                                <img class="rounded-circle" src="img_upload/${u.getAvatar()}" alt="avatar">
                                                <h4>${u.getFullname()}</h4>
                                                <c:forEach var="r" items="${requestScope.rateMap}">
                                                    <c:if test="${r.key == m.getMentorID()}">
                                                        <span class="rating"><i id="star-icon" class="fa-solid fa-star"></i><span style="color:black;">${r.value}/5.0</span></span>
                                                            </c:if>
                                                        </c:forEach>

                                            </div>
                                        </c:if>
                                    </c:forEach>

                                    <div class="card-lower-part">
                                        <c:forEach items="${requestScope.listSkill}" var="s">
                                            <c:forEach items="${requestScope.listEnrollSkill}" var="es">
                                                <c:if test="${m.getMentorID() == es.getMentor().getMentorID() && es.getSkill().getSkillId() == s.getSkillId()}">

                                                    <div class="card-technologies-knowledge">${s.getSkillName()}</div>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </div>

                                    <div class="openCV">
                                        <button id="btn-openCV"><a href="#">Open CV</a></button>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                </div>

                <div class="seemore" data-aos="fade-left" data-aos-duration="1000">
                    <button id="btn_seemore" onclick="handleDisplayItemMentor()">See All &#9207;</button>
                </div>
            </div>
        </c:if>


        <!-- scroll to top -->
        <div>
            <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
        </div>


        <!-- footer -->
        <%@include file="./footer.jsp" %>


        <script src="myjs/viewmentor.js"></script>
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