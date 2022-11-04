

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/mentorProfile.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .part:hover .part-title {
                transform: translateX(10%);
                color: #A91079;
            }
        </style>
    </head>
    <body>

        <!--header-->
        <c:import url="./header.jsp"/>


        <div class="container mb-5 contain_mentor_infor">
            <div class="contain_heading mt-5 mb-5">
                <div class="part">
                    <h4 class="part-title font-weight-bold">Mentor's profile</h4>
                    <div class="line_part"></div>
                </div>

                <div style="display: flex;">

                    <c:if test="${m.mentorID == currentMentor.getMentorID()}">
                        <button class="btn btn-primary"  data-id="" data-toggle="modal"
                                data-target="#modal_update_profile">Update</button>
                    </c:if>
                    <c:if test="${mt ne null}">
                        <form action="invitation-handler" method="POST">  
                            <input type="hidden" value="${m.mentorID}" name="mentorID">
                            <input type="hidden" value="${mt.menteeID}" name="menteeID">
                            <c:if test="${i eq null}">
                                <div class="contain_invitation">
                                    <button name="button" value="1" class="btn_invitation"> Send Invitation </button>
                                </div>
                            </c:if>
                            <c:if test="${i.status eq 'accepted'}">
                                <div class="contain_invitation">
                                    <button name="button" value="-1" class="btn_invitation"> Break relationship </button>
                                </div>
                            </c:if>
                            <c:if test="${i.status eq 'processing'}">
                                <div class="contain_invitation">
                                    <button name="button" value="0" class="btn_invitation"> Cancel Invitation  </button>
                                </div>                          
                            </c:if>
                        </form>
                    </c:if>
                </div>
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

                    <div class="col-md-5 col-sm-6 col-xs-12 pb-2 right-second-infor pl-4" data-aos="fade-up"
                         data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-paper-plane"></i>
                            <span class="ml-2 content-infor">Bio</span>
                        </div>

                        <div class="main-content-infor">
                            <p class="subtitle">${p.bio}</p>

                        </div>

                    </div>

                    <div class="col-md-5 col-sm-6 col-xs-12 right-second-infor pl-4" data-aos="fade-up" data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-pencil"></i>
                            <span class="ml-2 content-infor">Skills</span>
                        </div>


                        <c:forEach var="skill" items="${requestScope.s}">
                            <div class="mr-2 d-md-inline-block mb-3">
                                ${skill.skillName}
                            </div>
                        </c:forEach> 
                    </div>
                </div>

                <div class="row contain-infor">

                    <div class="col-md-5 col-sm-6 col-xs-12 left-second-infor pl-4" data-aos="fade-up" data-aos-duration="1000">
                        <div class="title mb-3">
                            <i class="fa-solid fa-briefcase"></i>
                            <span class="ml-2 content-infor">Work experience</span>
                        </div>

                        <div class="main-content-infor">
                            <p>${p.experience}</p>                       
                        </div>
                    </div>

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
            <c:if test="${i.status eq 'accepted' and checkcmt eq false}">
                <form action="mentorprofile" method="POST">
                    <!--dung jstl check dieu kien da la mentor cua mentee moi hien thi doan nay-->
                    <input name="hiddenMentorID" type="hidden" value="${p.mentor.mentorID}">
                    <div class="contain_form_comment">
                        <div class="form_comment">
                            <input name="hiddenMentorID" type="hidden" value="${p.mentor.mentorID}">
                            <label for="rate" class="form_comment_lable">Rate</label>
                            <select name="rate" id="rate">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <i class="fa-solid fa-star text-warning"></i><br>
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
                            <div style="display: flex; justify-content: space-between;" class="items-rate-comment pl-4" data-aos="fade-left" data-aos-duration="1000">
                                <div>
                                    <h5 class="cv-comment font-weight-bold"> ${comment.mentee.user.fullname}</h5>
                                    <span class="rating"><i class="fa-solid fa-star"></i><span class="rating-number font-weight-bold">${rate.rateStar}</span></span>
                                            <c:forEach var="fd" items="${requestScope.fd}">
                                                <c:if test="${fd.key == comment.commentID}">
                                            <p class="cv-comment" style="color: #777;">${fd.value}</p>  
                                        </c:if>
                                    </c:forEach>
                                    <h5 class="cv-comment">${comment.cmtContent}</h5>
                                </div>

                                <c:if test="${comment.mentee.menteeID == mt.menteeID}">
                                    <div>
                                        <div class="dropdown">
                                            <p class="dropdown-toggle text-primary" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                Edit
                                            </p>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                                                <button class="dropdown-item text-info" type="submit" data-id="${comment.mentee.menteeID}" data-toggle="modal"
                                                        data-target="#modal_update_comment">Update</button>
                                                <div class="dropdown-divider"></div>
                                                <form action="deleteComment" method="POST">
                                                    <input type="hidden"  name="mentorID" value="${p.mentor.mentorID}">
                                                    <input type="hidden" name="menteeID" value="${comment.mentee.menteeID}">
                                                    <button class="dropdown-item text-danger" type="submit">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>


        </div>
    </div>

    <!-- modal update comment -->
    <div class="modal" tabindex="-1" role="dialog" id="modal_update_comment">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title font-weight-bold">Update Comment</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="" method="post" novalidate="" name="update-form">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="rate" class="form_comment_lable">Rate</label>
                            <select name="rateUpdate" id="rate">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <i class="fa-solid fa-star text-warning"></i> <br>
                            <label for="replyContent" class="">Comment</label>
                            <textarea class="form-control" name="replyContent" id="replyContent" rows="5"
                                      placeholder="Enter content" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" id="btn-update-comment">Update</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- modal update mentor profile  -->
    <div class="modal" tabindex="-1" role="dialog" id="modal_update_profile">

        <div class="modal-dialog" style="max-width: 60vw;" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title font-weight-bold" style="color:#14c38e;">Update mentor profile</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="update-mentor-profile" method="post" novalidate="" name="update-profile-form">
                    <div class="modal-body">
                        <label for="bio" class="font-weight-bold label">Bio</label>
                        <textarea class="form-control form-control-active" rows="4" name="bio" id="bio" placeholder="Your bio..."
                                  required>${p.bio}</textarea>
                        <hr>
                        <label for="skill" class="font-weight-bold label">Skills</label><br>
                        <div class="mb-2" style="display: flex; flex-wrap: wrap;">

                            <!-- FOREACH o day -->
                            <c:forEach var="o" items="${requestScope.as}">
                                <div class="" style="margin: 2px 6px">
                                    <input class=" form-control-active checkboxSkill" onchange="handleFillCkeckbox()" type="checkbox" name="skill" value="${o.getSkillId()}" id="skill" />
                                    <span>${o.getSkillName()}</span>
                                </div>
                            </c:forEach>
                            <!-- FOREACH o day -->

                        </div>
                        <hr>

                        <label for="exp" class="font-weight-bold label">Experience</label>
                        <textarea class="form-control form-control-active" rows="4" name="exp" id="exp"
                                  placeholder="Your experience..." required>${p.experience}</textarea>
                        <hr>
                        <label for="achievement" class="font-weight-bold label">Achievement</label>
                        <textarea class="form-control form-control-active" rows="4" name="achievement" id="achievement"
                                  placeholder="Your achievement..." required>${p.achievement}</textarea>
                        <input type="hidden" value="${requestScope.m.getMentorID()}" name="mentorId" />
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" disabled id="btn-update-profile">Update</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>

        </div>
    </div>

    <!-- Reply hidden form -->

    <!-- scroll to top -->
    <div>
        <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
    </div>

    <!--footer-->
    <%@include file="./footer.jsp" %>


    <script>
        const ckb = document.querySelectorAll('.checkboxSkill')
        const btn_update = document.querySelector('#btn-update-profile')


        const handleFillCkeckbox = () => {
            let check = false
            btn_update.setAttribute('disabled', '')
            for (var i = 0; i < ckb.length; i++) {
                if (ckb[i].checked) {
                    check = true
                }
            }
            if (check) {
                btn_update.removeAttribute('disabled')
            }
        }



        var id;
        var updateForm = document.forms['update-form'];
        var btnUpdateComment = document.getElementById('btn-update-comment');
        var updateProfile = document.forms['update-profile-form'];
        var btnUpdateProfile = document.getElementById('btn-update-profile');

        $('#modal_update_comment').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            id = button.data('id'); // Extract info from data-* attributes
        });
        btnUpdateComment.onclick = function () {
            updateForm.action = 'editComment?menteeID=' + id + '&mentorID=' + ${p.mentor.mentorID};
            
        };
        $('#modal_update_profile').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            id = button.data('id'); // Extract info from data-* attributes
        });

    </script>
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
