
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/home.css">
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

        <!--body-->
        <div class="container-fluid banner">
            <div class="banner_intro" data-aos="fade-up" data-aos-duration="1000">
                <h1>Happy</h1>
                <h1>Programing</h1>
                <h3>Programing is fun, come with us, you will be a hero.</h3>
            </div>

            <div class="banner_img" data-aos="fade-up" data-aos-duration="1000">
                <img onclick="handleReverseBanner()" id="imgForBanner" src="image/banner_img.jpg" alt="image">
            </div>
        </div>

        <!-- body details part 2 -->

        <div class="container body_details_2" data-aos="fade-up" data-aos-duration="1000">
            <div class="part">
                <h4 class="font-weight-bold">Roadmap to become a programmer</h4>
                <div class="line_part"></div>
            </div>

            <div class="row">
                <div class="col-md-4 col-sm-6 col-xs-12 items_details_2">
                    <div class="details_2_wrap_img">
                        <img src="image/home_img3.png" alt="img_details">
                    </div>
                    <h5>Learning to code</h5>
                </div>

                <div class="col-md-4 col-sm-6 col-xs-12 items_details_2">
                    <div class="details_2_wrap_img">
                        <img src="image/home_img2.png" alt="img_details">
                    </div>
                    <h5>Find mentor help code</h5>
                </div>

                <div class="col-md-4 col-sm-6 col-xs-12 items_details_2">
                    <div class="details_2_wrap_img">
                        <img src="image/home_img1.png" alt="img_details">
                    </div>
                    <h5>Being a hero of coding</h5>
                </div>
            </div>
        </div>

        <!-- End body details part 2 -->


        <!-- techonologies -->
        <div class="container technology">
            <div class="part">
                <h4 class="font-weight-bold">TECHNOLOGIES</h4>
                <div class="line_part"></div>
            </div>

            <div class="items_technologies">
                <div class="row" data-aos="fade-up" data-aos-duration="1000">
                    <c:forEach var="skills" items="${requestScope.as}">
                        <div class="col-md-3 col-sm-6 col-xs-12 item_contain">
                            <div class="item_techonology">
                                <h3><a href="#" class="item_name">${skills.skillName}</a></h3>
                                <!--                                <span class="rating"><i class="fa-solid fa-star"></i><span>4/5 average rating</span></span>-->
                                <p class="item_technology_description">${skills.skillDescription}</p>
                            </div>
                        </div>                        
                    </c:forEach>    
                </div>
            </div>

            <div class="seemore" data-aos="fade-left" data-aos-duration="1000">
                <button id="btn_seemore" onclick="handleDisplayItemTech()">See All &#9207;</button>
            </div>
        </div>



        <!-- scroll to top -->
        <div>
            <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
        </div>

        <!--footer-->
        <%@include file="./footer.jsp" %>


        <script src="myjs/myhome.js"></script>
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
