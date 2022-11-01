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
        <link rel="stylesheet" href="style/viewingMentor.css">

        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .items-card-mentor{
                transition: all 0.5s ease-in-out;
                box-shadow: rgba(86, 172, 243, 0.4) -5px 5px, rgba(86, 172, 243, 0.3) -10px 10px, rgba(86, 172, 243, 0.2) -15px 15px, rgba(86, 172, 243, 0.1) -20px 20px, rgba(86, 172, 243, 0.05) -25px 25px;
            }
            .items-card-mentor:hover {
                transform: scale(1.04);
            }
            #btn-openCV:hover {
                border: 1px solid #4dc2ef;
                outline: 1px solid #4dc2ef;
            }
            .item_techonology {
                box-shadow: rgba(86, 172, 243, 0.4) -5px 5px, rgba(86, 172, 243, 0.3) -10px 10px, rgba(86, 172, 243, 0.2) -15px 15px, rgba(86, 172, 243, 0.1) -20px 20px, rgba(86, 172, 243, 0.05) -25px 25px;
            }
            .select_form {
                width: fit-content !important;
                display: inline !important;
                cursor: pointer;
            }
            .btn_page {
                width: fit-content;
                padding: 4px 7px;
                background-color: #25c481;
                color: #ffffff;
                border: 1px solid #25b7c4;
                cursor: pointer;
            }
            .btn_page:hover {
                background-color: rgba(37, 196, 129, 0.6);
                color: #000000
            }
            .items_details_2 {
                animation: animation_slider 1s ease-in-out;
            }
            @keyframes animation_slider {
                from {
                    opacity: 0;
                    transform: translate(10%, 0);
                    filter: blur(3px);
                }
                to {
                    opacity: 1;
                    transform: translate(0, 0);

                }
            }
        </style>
    </head>
    <body>
        <!--header-->

        <c:import url="header.jsp"/>

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

            <div class="row" id="contain_slider">
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
            <div class="part" data-aos="fade-up" data-aos-duration="1000">
                <h4 class="font-weight-bold">TECHNOLOGIES</h4>
                <div class="line_part"></div>
            </div>

            <div class="items_technologies">
                <div class="row" data-aos="fade-up" data-aos-duration="1000">
                    <c:forEach var="skills" items="${requestScope.as}">
                        <div class="col-md-3 col-sm-6 col-xs-12 item_contain">
                            <div class="item_techonology">
                                <h3><a href="search?technologyID=${skills.skillId}" class="item_name">${skills.skillName}</a></h3>
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

        <!--all mentor-->
        <div class="container wrap-outstanding-mentor">
            <div class="part"  data-aos="fade-up" data-aos-duration="1000">
                <h4 style="text-transform: capitalize;" class="font-weight-bold">Mentors</h4>
                <div class="line_part"></div>
            </div>

            <!--search only by tech-->
            <div class="list-mentors">
                <div class="row">
                    <c:forEach items="${requestScope.listMentor}" var="m" begin="${cp.begin}" end="${cp.end}">

                        <div class="col-md-3 col-sm-6 col-xs-12 card-mentor" data-aos="fade-up" data-aos-duration="1000">
                            <div class="items-card-mentor">

                                <c:forEach items="${requestScope.listUser}" var="u">
                                    <c:if test="${m.getUser().getUserId() == u.getUserId()}">
                                        <div class="card-upper-part">
                                            <c:if test="${!u.getAvatar() eq 'avtuser.png'}">
                                                <img class="rounded-circle" src="img_upload/${u.getAvatar()}" alt="avatar">
                                            </c:if>
                                            <c:if test="${u.getAvatar() eq 'avtuser.png'}">
                                                <img class="rounded-circle" src="image/avtuser.png" alt="avatar">
                                            </c:if>
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
                                    <c:forEach items="${requestScope.as}" var="s">
                                        <c:forEach items="${requestScope.listEnrollSkill}" var="es">
                                            <c:if test="${m.getMentorID() == es.getMentor().getMentorID() && es.getSkill().getSkillId() == s.getSkillId()}">
                                                <div class="card-technologies-knowledge">${s.getSkillName()}</div>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </div>

                                <div class="openCV">
                                    <button id="btn-openCV"><a href="mentorprofile?mentorID=${m.getMentorID()}">Open CV</a></button>
                                </div>
                            </div>
                        </div>

                    </c:forEach>



                </div>
            </div>
            <!--phan trang-->
            <div>
                <form action="home" method="post">
                    <c:if test="${cp.cp!=0}">
                        <input class="btn_page" type="submit" name="home" value="Home"/>
                        <input class="btn_page" type="submit" name="pre" value="Pre"/>
                    </c:if>

                    <c:forEach begin="${cp.pageStart}" end="${cp.pageEnd}" var="i">
                        <span><input class="btn_page" type="submit" name="btn${i}" value="${i+1}"/></span>
                        </c:forEach>

                    <c:if test="${cp.cp!=cp.np-1}">
                        <input class="btn_page" type="submit" name="next" value="Next"/>
                        <input class="btn_page" type="submit" name="end" value="End"/>
                    </c:if>
                    <input type="text" hidden name="cp" value="${cp.cp}"/>
                    <input type="text" hidden name="np" value="${cp.np}"/>
                    <select class="form-control select_form ml-2" name="nrpp">
                        <c:forEach items="${cp.arrNrpp}" var="i" varStatus="loop">
                            <option value="${loop.index}"
                                    <c:if test="${loop.index==sessionScope.nrpp}">
                                        selected
                                    </c:if>
                                    >${i}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </div>



        <!-- scroll to top -->
        <div>
            <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
        </div>

        <!--footer-->
        <%@include file="./footer.jsp" %>


        <script src="myjs/myhome.js"></script>
        <script>
                const handleSlider = () => {
                    setInterval(() => {
                        let lists = document.querySelectorAll('.items_details_2')

                        document.getElementById('contain_slider').appendChild(lists[0])
                    }, 3000)
                }

                handleSlider()
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