
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="../style/home.css">
        <link rel="icon" type="image/x-icon" href="../image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .box-drop {
                box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;
            }
        </style>
    </head>
    <body>
        <!-- navbar -->
        <div class="wrap_nav_home">

            <nav id="nav_home" class="navbar navbar-expand-lg navbar-light">
                <div>
                    <a href="home"><img class="navbar-brand logo" src="image/mylogo.png" alt="logo" /></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <p class="nav-link nav_item_text up_down_icon" onclick="openBar()">Find mentors
                                    &#9207;</p>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link nav_item_text" href="mentor-register">Become Mentor</a>
                            </li>
                            <c:if test="${requestScope.isAdmin == true}">
                                <li class="nav-item active">
                                    <a class="nav-link nav_item_text" href="dashboard">Dashboard</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                    <!--su dung sessionScope check DA dang nhap thi hien thi-->
                    <c:if test="${sessionScope.user != null}">

                        <div class="acti">
                            <div id='user_email' class="text-primary">${sessionScope.user.getFullname()}</div>
                            <div class="nav-item dropdown nav_drop">
                                <c:if test="${!sessionScope.user.getAvatar() eq 'avtuser.png'}">
                                    <img class="nav-link dropdown-toggle rounded-circle" src="img_upload/${sessionScope.user.getAvatar()}" alt="avtuser"
                                         id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                         aria-expanded="false" />
                                </c:if>
                                <c:if test="${sessionScope.user.getAvatar() eq 'avtuser.png'}">
                                    <img class="nav-link dropdown-toggle rounded-circle" src="image/avtuser.png" alt="avtuser"
                                         id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                         aria-expanded="false" />
                                </c:if>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="change-password">Change password</a>
                                    <a class="dropdown-item" href="userprofile">Profile</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="logout">Log out</a>
                                </div>
                            </div>

                            <!--check da dang nhap-->
                            <c:if test="${sessionScope.user != null}"> 
                                <div class="contain_notify_parent nav-item dropdown ml-3 mr-5">
                                    <div class="contain_notify nav-link dropdown-toggle" id="notifyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                         aria-expanded="false">
                                        <i class="fa fa-bell"></i>
                                        <c:if test="${listInviteSize>0 || listReqSize>0 || listResSize>0 || listMentorRegisterSize>0}">
                                            <span class="text-light new_notify" id="newNoti">new</span>
                                        </c:if>
                                    </div>
                                    <div class="dropdown-menu dropdown-menu-right box-drop mt-2" aria-labelledby="notifyDropdown" >
                                        <c:if test="${requestScope.isMentor == true}">
                                            <a class="dropdown-item" href="invitation">Invitation
                                                <c:if test="${requestScope.listInviteSize > 0}">
                                                    <span class="ml-3 new_notify">${requestScope.listInviteSize}</span>
                                                </c:if>
                                            </a>
                                            <div class="dropdown-divider"></div>
                                        </c:if>
                                        
                                        <c:if test="${!requestScope.isAdmin == true}">
                                            <a class="dropdown-item" href="viewRequest">Request
                                                <c:if test="${requestScope.listReqSize > 0}">
                                                    <span class="ml-3 new_notify">${requestScope.listReqSize}</span>
                                                </c:if>
                                            </a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="viewResponse">Response
                                                <c:if test="${requestScope.listResSize > 0}">
                                                    <span class="ml-3 new_notify">${requestScope.listResSize}</span>
                                                </c:if>
                                            </a>
                                            <div class="dropdown-divider"></div>
                                        </c:if>
                                        
                                        <c:if test="${requestScope.isAdmin == true}">
                                            <a class="dropdown-item" href="view-list-mentor-register">List mentor register request
                                                <c:if test="${requestScope.listMentorRegisterSize > 0}">
                                                    <span class="ml-3 new_notify">${requestScope.listMentorRegisterSize}</span>
                                                </c:if>
                                            </a>
                                        </c:if>
                                    </div>
                                </div> 
                            </c:if> 
                        </div> 
                    </c:if>


                    <!--su dung sessionScope check CHUA dang nhap thi hien thi-->
                    <c:if test="${sessionScope.user == null}">
                        <div class="">
                            <a class="login_text" href='login'>Login</a>
                            <a class="login_text" href='register'>Register</a>
                        </div>
                    </c:if>


                </div>
            </nav>

            <div class="container-fluid bar" id="bar-container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-12 left">
                        <div class="left_bar">
                            <ul>
                                <li class="category cate1 bg_active" onclick="openCate1()">Technologies ></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-9 col-sm-10 col-xs-12 right">
                        <div class="right_bar">
                            <div class="firstSubCategory active">
                                <div class="row">
                                    <c:forEach var="s" items="${as}">
                                        <div class="col-md-4 item_subcategory" style="cursor: auto">
                                            <a class="subCategory" href="search?technologyID=${s.skillId}">${s.skillName}</a>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="myjs/header.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>
</html>
