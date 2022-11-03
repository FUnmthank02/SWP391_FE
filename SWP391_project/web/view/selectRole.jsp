
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>6HP - Happy Programing</title>
    <link rel="stylesheet" href="style/selectRole.css">
    <link rel="icon" type="image/x-icon" href="image/mylogo.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
        integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

    <div class="contain-logo mt-5 ml-5">
        <a href="home"><img src="image/mylogo.png" alt="logo" height="80" width="80"></a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-2">
                <div class="wrapper">
                    <h1 class="title">Join as a mentee or mentor</h1>
                    <!-- <a href="" class="left-side col-md-5"> -->
                    <form action="select-role" method="post">
                        <div class="row contain-choice">

                            <div onclick="handleCheckedMentee()" class="left-side col-md-5">
                                <input type="radio" name="rdRole" value="mentee" id="rdMentee">
                                <img class="mb-4" src="image/mentee.png" alt="mentee-icon" height="60" width="60">
                                <h4>I'm a mentee finding my own mentor</h4>
                            </div>

                            <!-- </a> -->
                            <!-- <a href=""  class="right-side col-md-5"> -->
                            <div onclick="handleCheckedMentor()" class="right-side col-md-5">
                                <input type="radio" name="rdRole" value="mentor" id="rdMentor">
                                <img class="mb-4" src="image/mentor.png" alt="mentee-icon" height="60" width="60">
                                <h4>I'm a mentor, subcribe me, please!</h4>
                            </div>
                        </div>

                        <!-- </a> -->
                        <button type="submit" disabled class="btn btn-success button-join pt-2 pb-2">Join as a </button>
                    </form>

                </div>
            </div>
        </div>
    </div>


    <script src="myjs/becomeMentor.js"></script>
    <script>
        const handleCheckedMentee = () => {
            document.getElementById('rdMentee').checked = true;
            if(document.getElementById('rdMentee').checked == true) {
                document.querySelector('.left-side').classList.add('active')
                document.querySelector('.right-side').classList.remove('active')
                document.querySelector('.button-join').removeAttribute('disabled')
            }
        }
        const handleCheckedMentor = () => {
            document.getElementById('rdMentor').checked = true;
            if(document.getElementById('rdMentor').checked == true) {
                document.querySelector('.left-side').classList.remove('active')
                document.querySelector('.right-side').classList.add('active')
                document.querySelector('.button-join').removeAttribute('disabled')
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</body>

</html>