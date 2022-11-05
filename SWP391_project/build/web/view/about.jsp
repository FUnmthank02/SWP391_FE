
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/about.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">
    
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
            integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
            integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            *{
                font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            }
        </style>
    </head>
<body>
    
    <c:import url="header.jsp"/>
    
    <div class="container-fluid" style="padding-top: 80px;">
        <div class="row">
            <div class="col-md-12 contain-intro">
                <!-- <div class="contain-intro"> -->
                    <div class="contain-main">
                        <img src="image/mylogo.png" alt="logo" height="100" width="100">
                        <h2 class="font-weight-bold">About Us</h2>
                        <p>Hello everybody, welcome to our website. We are students of FPT University, out group named <span class="text-success font-weight-bold">6HP</span>, this is out project of software web application. We hope that you will have good experience with our website and It can help you improve your skills. <span class="text-danger">Finally, thank you for using our website.</span></p>
                    </div>
                <!-- </div> -->
            </div>
        </div>
    </div>

    <div class="container-fluid contain-member">
        <div class="part">
            <h4 class="font-weight-bold">Members</h4>
            <div class="line_part"></div>
        </div>
        
        <div class="row">
            <div class="col-md-2 offset-1">
                <div class="card card-1">
                    <img src="image/hungnm.png"
                        class="card-img-top" height="240" alt="image-product">
                    <div class="card-body">
                        <p class="card-text mt-4 text-info">Leader</p>
                        <h5 class="card-title text-success">Nguyen Manh Hung</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card card-2">
                    <img src="image/thanhnm.jpg"
                        class="card-img-top" height="240" alt="image-product">
                    <div class="card-body">
                        <p class="card-text mt-4 text-info">Lead Code</p>
                        <h5 class="card-title text-success">Nguyen Minh Thanh</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card card-3">
                    <img src="image/dungdt.jpg"
                        class="card-img-top" height="240" alt="image-product">
                    <div class="card-body">
                        <p class="card-text mt-4 text-info">Lead Analysis</p>
                        <h5 class="card-title text-success">Dao Tien Dung</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card card-4">
                    <img src="image/anhndq.jpg"
                        class="card-img-top" height="240" alt="image-product">
                    <div class="card-body">
                        <p class="card-text mt-4 text-info">Lead Docs</p>
                        <h5 class="card-title text-success">Nguyen Dinh Quang Anh</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card card-5">
                    <img src="image/dattt.jpg"
                        class="card-img-top" height="240" alt="image-product">
                    <div class="card-body">
                        <p class="card-text mt-4 text-info">Lead Test</p>
                        <h5 class="card-title text-success">Tran Tien Dat</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:import url="footer.jsp"/>


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
