
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/profileuser.css">
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

        <div class="container wrap-profile">
            <div class="row">
                <div class="col-md-8 col-sm-12 col-xs-12" data-aos="fade-up" data-aos-duration="1000">
                    <div class="Profile-line"></div>
                    <div class="Profile-header">
                        <div class="anchor_header">
                            <a href="" class="Profile-title">Basic Profile</a>
                            <c:if test="${requestScope.isMentor == true}">
                                <a href="mentorprofile?mentorID=${requestScope.currentMentor.getMentorID()}" class="Profile-title">Mentor's Profile</a>
                            </c:if>
                        </div>
                        <div>
                            <p class="Profile-action" onclick="handleUpdateProfile()">Update</p>
                        </div>
                    </div>

                    <form class="form" action="update_userprofile" enctype="multipart/form-data" method="post">

                        <div class="wrapper-avt">
                            <label for="">Avatar</label>
                            <div class="contain-avt">
                                <c:if test="${!sessionScope.user.getAvatar() eq 'avtuser.png'}">
                                    <img class="avt-user" src="img_upload/${sessionScope.user.getAvatar()}" alt="avtuser" />
                                </c:if>
                                <c:if test="${sessionScope.user.getAvatar() eq 'avtuser.png'}">
                                    <img class="avt-user" src="image/avtuser.png" alt="avtuser"/>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group upload-avt">
                            <label for="image">Upload image</label> <br />
                            <input type="file" onchange="handleGetImage()" name="image" id="image" class="hiddenInput form-control-active" disabled/>
                            <label for="image" class="lable_imagebtn">Select file</label>
                            <span id="file-name">${requestScope.userinfor.getAvatar()}</span>
                        </div><hr />

                        <label for="fullname">Full Name</label>
                        <input class="form-control form-control-active" type="text" name="fullname"
                               value="${requestScope.userinfor.getFullname()}" disabled id="fullname" placeholder="Your full name" required />
                        <hr>
                        <label for="dob">Date of birth</label>
                        <input class="form-control form-control-active" type="date" name="dob" value="${requestScope.userinfor.getDob()}" disabled
                               id="dob" placeholder="Your DOB" required />
                        <hr>
                        <label for="address">Address</label>
                        <input class="form-control form-control-active" type="text" name="address" value="${requestScope.userinfor.getAddress()}" disabled
                               id="address" placeholder="Your address" required />
                        <hr>
                        <label for="email">Email</label>
                        <input class="form-control form-control-active" type="email" name="email"
                               value="${requestScope.userinfor.getEmail()}" disabled id="email" placeholder="Your email" required />
                        <hr>

                        <label for="phonenumber">Phone numbers</label>
                        <input class="form-control form-control-active" type="text" name="phonenumber"
                               value="${requestScope.userinfor.getPhonenumber()}" disabled id="phonenumber"  placeholder="Your phonenumber"
                               required />
                        <hr>

                        <label for="gender">Gender</label><br>

                        <!-- su dung jstl gan cho value la gia tri cua gender: 'Male' or 'Female' -->
                        <input class="form-control form-control-active gender-value" type="text"
                               value="${requestScope.userinfor.getGender()}" disabled id="gender" placeholder="Your gender"
                               required />

                        <div class="contain-form-gender">
                            <!-- if current gender is male => checked in 'Male input', else => checked 'Female input'  -->
                            Male<input class="form-input-gender form-control-active ml-2 mr-3" type="radio" name="gender"
                                       value="male" ${requestScope.userinfor.getGender() eq 'Male' ? "checked" : ""} disabled id="gender" placeholder="Your gender" required />

                            Female<input class="form-input-gender form-control-active  ml-2" type="radio" name="gender"
                                         value="female" ${requestScope.userinfor.getGender() eq 'Female' ? "checked" : ""} disabled id="gender" placeholder="Your gender" required />
                        </div>
                        <hr>

                        <div style="display: flex;">
                        <button type="submit"  class="Profile_btn_update mr-5">Submit</button>
                        <button type="reset" onclick="handleCancelUpdate()" style="display:none;" class="Profile_btn_update">Cancel</button>
                    </div>
                    </form>
                </div>

                <div class="col-md-4 col-sm-12 col-xs-12">
                    <div class="wrap-profile-img" data-aos="fade-left" data-aos-duration="1500">
                        <img class="profile-img" src="image/profile_img1.png" alt="img">
                    </div>
                </div>

            </div>

        </div>

        <!-- scroll to top -->
        <div>
            <button onclick="topFunction()" id="myBtn" class="scrollBtn" title="Go to top">Top</button>
        </div>



        <!-- footer -->
        <%@include file="./footer.jsp" %>


        <script>
            const form_input = document.getElementsByClassName('form-control-active')
            const contain_gender = document.querySelector('.contain-form-gender')
            const gender = document.querySelector('.gender-value')
            const btn_update_profile = document.querySelectorAll('.Profile_btn_update')
            const upload_avt = document.querySelector('.upload-avt')
            const fileInput = document.getElementById('image')
            const fileName = document.getElementById('file-name')
            const wrapAvt = document.querySelector('.wrapper-avt')


            const handleUpdateProfile = () => {
                for (var i = 0; i < form_input.length; i++) {
                    form_input[i].removeAttribute('disabled')
                }

                contain_gender.style.display = 'block'
                gender.style.display = 'none'
                btn_update_profile[0].style.display = 'block'
                btn_update_profile[1].style.display = 'block'
                upload_avt.style.display = 'block'
                wrapAvt.classList.add('disable')
            }
            
            const handleCancelUpdate = () => {
                for (var i = 0; i < form_input.length; i++) {
                    form_input[i].setAttribute('disabled', '')
                }

                contain_gender.style.display = 'none'
                gender.style.display = 'block'
                btn_update_profile[0].style.display = 'none'
                btn_update_profile[1].style.display = 'none'
                upload_avt.style.display = 'none'
                wrapAvt.classList.remove('disable')
            }

            const handleDisableProfile = (e) => {
                e.preventDefault()
                for (var i = 0; i < form_input.length; i++) {
                    form_input[i].setAttribute('disabled', '')
                }

                contain_gender.style.display = 'none'
                gender.style.display = 'block'
                btn_update_profile.style.display = 'none'
                upload_avt.style.display = 'none'
                wrapAvt.classList.remove('disable')
            }


            const handleGetImage = () => {
                const filename = fileInput.files[0].name
                fileName.innerHTML = filename
            }

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