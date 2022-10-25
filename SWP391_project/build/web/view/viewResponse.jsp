
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/request.css">
        <link rel="icon" type="image/x-icon" href="../image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <div class="container contain_listRequest">
            <div class="heading mt-4 mb-2">
                <div class="sub_heading pl-3 pr-3">
                    <h4><a href="listRequest" class="font-weight-bold">List Request</a></h4>
                </div>
                <div class="sub_heading pl-3 pr-3 request_page_active">
                    <h4><a href="" class="font-weight-bold">List Response</a></h4>
                </div>
                <div class="sub_heading pl-3 pr-3">
                    <h4><a href="createResponse" class="font-weight-bold">Create Request</a></h4>
                </div>
            </div>
            <hr>
            <div class="wrap_request">
                <c:if test="${requestScope.resList.size()-1 < 0}">
                    <p>There is no response now</p>
                </c:if>
                <c:if test="${requestScope.resList.size()-1 >= 0}">
                    <c:forEach var="i" begin="0" end="${requestScope.resList.size()-1}">
                        <div class="request_item unread">
                            <div class="left_side_item">
                                <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">From:</span> ${resList.get(i).getMentor().getUser().getFullname()}</p>
                                <p class="left_side_text requestFrom mt-2 mb-2"><span class="left_side_span">To:</span> ${resList.get(i).getMentee().getUser().getFullname()}</p>
                                <p class="left_side_text requestTitle mt-2 mb-2"><span class="left_side_span">Question:</span> ${resList.get(i).getRequest().getReqContent()}</p>
                                <p class="left_side_text requestContent mt-2 mb-2"><span class="left_side_span">Content:</span> ${resList.get(i).getResContent()}</p>
                                <p class="left_side_text requestCreateAt mt-2 mb-2"><span class="left_side_span">Create At:</span> ${dateList[i]}</p>
                            </div>

                            <div class="right_side_item">
                                <a class="text-primary action_button" style="cursor: pointer;" data-id="${resList.get(i).getRequest().getRequestID()}" data-toggle="modal"
                                   data-target="#modal_form_reply">Reply</a><br>
                                <a class="text-primary action_button" style="cursor: pointer;" data-id="${resList.get(i).getResponseID()}" data-toggle="modal"
                                   data-target="#modal_form_update">Update</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>    
            </div>
        </div>


        <!-- modal reply the request -->
        <div class="modal" tabindex="-1" role="dialog" id="modal_form_reply">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title font-weight-bold">Send reply</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="" method="post" name="reply-form" novalidate="">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="replyContent" class="">Content</label>
                                <textarea class="" name="replyContent" id="replyContent" rows="5" placeholder="Enter content" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" id="btn-reply-response">Reply</button>
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
                        <h5 class="modal-title font-weight-bold">Update response</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="" method="post" name="update-form" novalidate="">
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="updateResponse" class="">Content</label>
                                <textarea class="" name="updateResponse" id="updateResponse" rows="5" placeholder="Enter content" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" id="btn-update-response">Update</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var id;
                var updateForm = document.forms['update-form'];
                var replyForm = document.forms['reply-form'];
                var btnUpdateResponse = document.getElementById('btn-update-response');
                var btnReplyResponse = document.getElementById('btn-reply-response');

                $('#modal_form_update').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    id = button.data('id'); // Extract info from data-* attributes
                });

                btnUpdateResponse.onclick = function () {
                    updateForm.action = 'viewResponse?reqId=' + id + '&action=update';
                };

                $('#modal_form_reply').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    id = button.data('id'); // Extract info from data-* attributes
                });

                btnReplyResponse.onclick = function () {
                    replyForm.action = 'viewResponse?reqId=' + id + '&action=reply';
                };
            });

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
                    .create(document.querySelector('#updateResponse'))
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
