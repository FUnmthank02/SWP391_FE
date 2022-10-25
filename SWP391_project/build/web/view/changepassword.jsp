
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="../style/resetpass.css"/>
        <link rel="icon" type="image/x-icon" href="../image/mylogo.png">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://cdn.lineicons.com/2.0/LineIcons.css" rel="stylesheet">

    </head>
    <body class="auth_class">

        <div class="container login-container">
            <img class="triangleA" src="https://res.cloudinary.com/procraftstudio/image/upload/v1613965232/triangleA_lwqhnl.png" alt='Onestop triangle'>
            <div class="row">
                <div class="col-md-6 welcome_auth">
                    <div class="auth_welcome">6HP, programing is happy</div>
                </div>         
                <div class="col-md-6 login-form">
                    <div class="login_form_in">
                        <div class="form-group other_auth_links">
                            <div class="contain_logo">
                                <a href="../home" class="original-logo"><img src="../image/mylogo.png" alt="img"></a>
                            </div>
                        </div>

                        <h1 class="auth_title text-left text-success">Change password</h1>

                        <form action="../resetpassword" method="post">
                            <div class="alert alert-success bg-soft-primary border-0" role="alert">
                                Enter your old password and new password.
                            </div>                 
                            
                            <div class="form-group">
                                <input type="password" class="form-control" name="newpass" placeholder="New password">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="rpnewpass" placeholder="Repeat new password">
                            </div>

                            <!-- get error or noti from servlet -->
                            <!-- <p id="noti">${requestScope.resetSuccess}</p>  
                            <p class="err">${requestScope.errRpPassNotMatch} </p> 
                            <p class="err">${requestScope.errNewPassNotValid} </p>  -->

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
                            </div>

                        </form>
                    </div>
                </div>       
            </div>
        </div>

        <img class="triangleB" src="https://res.cloudinary.com/procraftstudio/image/upload/v1613965232/triangleB_isffjy.png" alt='Onestop triangle'>
    
    
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
        </script>

    </body>

</html>
