
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>6HP - Happy Programing</title>
    <link rel="stylesheet" href="style/register_login.css">
    <link rel="icon" type="image/x-icon" href="image/mylogo.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>

<body>
    <div class="wrapper_login_register">
        <div class="login_register_form login_form">
            <h2 class="title">LOGIN</h2>

            <div class="mb-3 mt-3">
                <span class="text-danger">${requestScope.errWrongAcc}</span>
                
                <span class="text-success">${requestScope.activeSuccess}</span>
            </div>
            <form class="form" action="login" method="post">
                <input class="form-control form-input" type="text" name="username" id="username" placeholder="Enter username" required /><br>
                <input class="form-control form-input" type="password" id="password" name="password"
                    placeholder="Enter password" required /><br><br /><br>

                <!-- thong bao loi gui tu servlet ve -->

                <button class="btn-submit">Login</button><br /><br>
                <div class="action_loginform">
                    <a class='link_button' href="/forgotpassword">Forgot password ?</a>
                    <a class="login-register" href='./register.jsp'>REGISTER</a>
                </div>
            </form>

        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
</body>

</html>
