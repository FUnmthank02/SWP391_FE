

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/profilementor.css">
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
<<<<<<< HEAD
        <c:import url="./header.jsp"/>
=======
        <%@include file="./header.jsp" %>
>>>>>>> main

        <div class="container mb-5 contain_mentor_infor">
            <div class="part mt-5 mb-5">
                <h4 class="part-title">Mentor's profile</h4>
                <div class="line_part"></div>
            </div>

            <div class="container p-5 wrapCV">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12 wrap-infor" data-aos="fade-up" data-aos-duration="1000">
                        <div class="col-md-3 col-sm-2 col-xs-12 cv-avt-mentor">
                            <img class="rounded-circle" src="image/avtuser.png" alt="image">
                        </div>

                        <div class="col-md-9 col-sm-10 col-xs-12 cv-personal-infor">
                            <h2 class="ml-3">${u.fullname}</h2>

                            <div class="cv-content-infor">
                                <div class="col-md-6 col-sm-6 col-xs-12 cv-left-content-infor">
                                    <div class="item-content-infor mt-3 mb-3">
                                        <i class="ml-1 fa-solid fa-calendar-days"></i>
                                        <span class="ml-2 content-infor">${u.dob}</span>
                                    </div>

                                    <div class="item-content-infor mt-3 mb-3">
                                        <i class="ml-1 fa-solid fa-envelope"></i>
                                        <span class="ml-2 content-infor">${u.email}</span>
                                    </div>

                                    <div class="item-content-infor mt-3 mb-3">
                                        <i class="ml-1 fa-solid fa-location-arrow"></i>
                                        <span class="ml-2 content-infor">${u.address}</span>
                                    </div>
                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-12 cv-right-content-infor">
                                    <div class="item-content-infor mt-3 mb-3">
                                        <i class="ml-1 fa-solid fa-phone"></i>
                                        <span class="ml-2 content-infor">${u.phonenumber}</span>
                                    </div>

                                    <div class="item-content-infor mt-3 mb-3">
                                        <i class="ml-1 fa-solid fa-user"></i>
                                        <span class="ml-2 content-infor">${u.gender}</span>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- <div class="wrap-infor"> -->
                <div class="row contain-infor">

                    <div class="col-md-5 col-sm-6 col-xs-12 right-second-infor pl-4" data-aos="fade-up" data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-pencil"></i>
                            <span class="ml-2 content-infor">Skills</span>
                        </div>


                        <c:forEach var="skill" items="${requestScope.s}">
                            <div class="main-content-infor">
                                ${skill.skillName}
                            </div>
                        </c:forEach> 


                    </div>

                    <div class="col-md-5 col-sm-6 col-xs-12 left-second-infor pl-4" data-aos="fade-up" data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-briefcase"></i>
                            <span class="ml-2 content-infor">Work experience</span>
                        </div>

                        <div class="main-content-infor">
                            <p>${p.experience}</p>                       
                        </div>
                    </div>

                </div>

                <div class="row contain-infor">


                    <div class="col-md-5 col-sm-6 col-xs-12 right-second-infor pl-4" data-aos="fade-up" data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-award"></i>
                            <span class="ml-2 content-infor">Achievement</span>
                        </div>

                        <div class="main-content-infor">
                            <p class="subtitle">${p.achievement}</p>

                        </div>

                    </div>


                </div>

            </div>

            <div class="part mt-5 mb-5">
                <h4 class="part-title">Comment and rate</h4>
                <div class="line_part"></div>
            </div>
            <c:if test="${i.status eq 'Accepted'}">
                <form action="mentorprofile" method="POST">
                    <!--dung jstl check dieu kien da la mentor cua mentee moi hien thi doan nay-->
                    <input name="hiddenMentorID" type="hidden" value="${p.mentor.mentorID}">
                    <div class="contain_form_comment">
                        <form class="form_comment">
                            <label for="rate" class="form_comment_lable">Rate</label>
                            <select name="rate" id="rate">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <i class="fa-solid fa-star"></i><br>
                            <label for="comment" class="form_comment_lable">Comment</label>
                            <textarea name="comment" id="comment" rows="5" placeholder="Your comment..."></textarea>
                            <button class="btn_comment" type="submit">Submit</button>
                        </form>
                    </div>

                    <!--dung jstl check dieu kien da la mentor cua mentee moi hien thi doan nay-->                     
                </form>

            </c:if>



            <div class="rate-comment">
                <c:forEach var="rate" items="${requestScope.r}">
                    <c:forEach var="comment" items="${requestScope.c}">
                        <c:if test="${rate.mentor.mentorID == comment.mentor.mentorID && rate.mentee.menteeID == comment.mentee.menteeID}">
                            <div class="items-rate-comment" data-aos="fade-left" data-aos-duration="1000">
                                <p class="cv-comment"> ${comment.mentee.user.fullname}</p>
                                <span class="rating"><i class="fa-solid fa-star"></i><span class="rating-number">${rate.rateStar}</span></span>
                                        <c:forEach var="fd" items="${requestScope.fd}">
                                            <c:if test="${fd.key == comment.commentID}">
                                        <p class="cv-comment">${fd.value}</p>  
                                    </c:if>
                                </c:forEach>
                                <p class="cv-comment">${comment.cmtContent}</p>                  
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
<<<<<<< HEAD
=======

>>>>>>> main

        </div>
    </div>

    <!-- scroll to top -->
    <div>
        <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
    </div>

    <!--footer-->
    <%@include file="./footer.jsp" %>

    <script src="myjs/mentorprofile.js"></script>
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
