<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/request.css">
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

        <div class="container contain_listRequest">
            <div class="heading mt-4 mb-2">
                <div class="sub_heading pl-3 pr-3 request_page_active">
                    <h4><a href="" class="font-weight-bold">List Request</a></h4>
                </div>
                <div class="sub_heading pl-3 pr-3">
                    <h4><a href="./response.html" class="font-weight-bold">List Response</a></h4>
                </div>
                <div class="sub_heading pl-3 pr-3">
                    <h4><a href="./createRequest.html" class="font-weight-bold">Create Request</a></h4>
                </div>
            </div>
            <hr>
            <div class="wrap_request">
                <c:forEach var="r" items="${requestScope.requests}">
                    <c:if test="${r.status eq 'Processing'}">
                        <div class="request_item unread">
                            <div class="left_side_item">
                                <c:if test="${role eq false}">
                                    <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">From:</span> ${user.fullname}</p>                                 
                                    <c:forEach var="m" items="${requestScope.mentorUsers}">     
                                        <c:if test="${m.userId == r.mentor.user.userId}">
                                            <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">To:</span> ${r.mentor.user.fullname} </p>                                               
                                        </c:if>
                                    </c:forEach>                                                               
                                    <p class="left_side_text requestTitle mt-2 mb-2"><span class="left_side_span">Title:</span> ${r.title}</p>
                                    <p class="left_side_text requestContent mt-2 mb-2"><span class="left_side_span">Content:</span> ${r.reqContent}</p>
                                    <p class="left_side_text requestCreateAt mt-2 mb-2"><span class="left_side_span">Create At:</span>
                                        12/12/2022</p>                                    
                                    </c:if>
                                    <c:if test="${role eq true}">
                                        <c:forEach var="m" items="${requestScope.menteeUsers}">
                                            <c:if test="${m.userId == r.mentee.user.userId}">
                                            <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">From:</span> ${r.mentee.user.fullname} </p>                                               
                                        </c:if>
                                        <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">To:</span> ${user.fullname}</p>                                 
                                        <p class="left_side_text requestTitle mt-2 mb-2"><span class="left_side_span">Title:</span> ${r.title}</p>
                                        <p class="left_side_text requestContent mt-2 mb-2"><span class="left_side_span">Content:</span> ${r.reqContent}</p>
                                        <p class="left_side_text requestCreateAt mt-2 mb-2"><span class="left_side_span">Create At:</span>
                                            12/12/2022</p>
                                        </c:forEach>
                                    </c:if>
                            </div>

                            <div class="right_side_item">
                                <a class="text-primary action_button" style="cursor: pointer;" data-toggle="modal"
                                   data-target="#modal_form_reply">Reply</a><br>
                                <a class="text-primary action_button" style="cursor: pointer;" data-toggle="modal"
                                   data-target="#modal_form_update">Update</a>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${r.status eq 'Seen'}">
                        <div class="request_item">
                            <div class="left_side_item">
                                <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">From:</span> Nguyen Van
                                    A</p>
                                <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">To:</span> Mentor B</p>
                                <p class="left_side_text requestTitle mt-2 mb-2"><span class="left_side_span">Title:</span> ${r.title}</p>
                                <p class="left_side_text requestContent mt-2 mb-2"><span class="left_side_span">Content:</span> ${r.reqContent}</p>
                                <p class="left_side_text requestCreateAt mt-2 mb-2"><span class="left_side_span">Create At:</span>
                                    12/12/2022</p>
                            </div>

                            <div class="right_side_item">
                                <a class="text-primary action_button" style="cursor: pointer;" data-toggle="modal"
                                   data-target="#modal_form_reply">Reply</a><br>
                                <a class="text-primary action_button" style="cursor: pointer;" data-toggle="modal"
                                   data-target="#modal_form_update">Update</a>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <!-- modal reply the request -->
        <div class="modal" tabindex="-1" role="dialog" id="modal_form_reply">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title font-weight-bold">Send response</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="replyContent" class="">Content</label>
                                <textarea class="form-control" name="replyContent" id="replyContent" rows="5"
                                          placeholder="Enter content" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Reply</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- modal update -->
        <div class="modal" tabindex="-1" role="dialog" id="modal_form_update">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title font-weight-bold">Update request</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="updateRequest" class="">Content</label>
                                <textarea class="form-control" name="updateRequest" id="updateRequest" rows="5"
                                          placeholder="Enter content" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Update</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <c:import url="./footer.jsp" />


        <script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>

        <script>
            var replyValue;
            ClassicEditor
                    .create(document.querySelector('#replyContent'))
                    .then(editor => {
                        replyValue = editor;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            var updateValue;
            ClassicEditor
                    .create(document.querySelector('#updateRequest'))
                    .then(editor => {
                        updateValue = editor;
                    })
                    .catch(error => {
                        console.error(error);
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