
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/viewingMentor.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
    <c:import url="./header.jsp"/>

    <div class="container-fluid p-5" style="padding-top: 150px !important;">
        <div class="row bg_search">
            <div class="col-md-6 col-sm-12-col-xs-12">
                <h1 class="left_side_search_title">Hire and learn programing with mentors</h1>
                <p class="h5">Find your own mentor here <i class="fa-solid fa-arrow-right-long ml-2"></i></p>
            </div>
            <div class="col-md-6 col-sm-12-col-xs-12">
                <div class="contain_form_search">
                    <form action="search" class="form_search">
                        <h2 class="title_search text-success text-uppercase">Search</h2>
                        <div class="contain_rating_search">
                            <p class="lable_input_search font-weight-bold">Rating:</p>
                            <div class="filter level-filter level-req">
                                <div id="rangeSlider" class="range-slider">

                                    <div class="contain_range ml-1">
                                        <div id="slider-range"></div>

                                        <!-- getParameter here -> slider-range-value1 & slider-range-value2 -->
                                        <div class="slider-labels mt-4">
                                            <strong id="slider-range-value1"></strong><span class="ml-2 mr-2 font-weight-bold">TO</span>
                                            <strong id="slider-range-value2"></strong>
                                            <i style="font-size: 20px;" class="fa-solid fa-star ml-2 mt-1"></i>

                                        </div>
                                        <input type="hidden" id="minValue" name="preRating" value="">
                                        <input type="hidden" id="maxValue" name="aftRating" value="">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="contain_technology_search form-group">
                            <label for="tech_search_select" class="lable_input_search mr-3 font-weight-bold">Technology:</label>
                            <select name="technologyID" id="tech_search_select" class="form-control">
                                <c:forEach items="${requestScope.as}" var="s">
                                    <option value="${s.getSkillId()}">${s.getSkillName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <hr>
                        <button type="submit" class="btn_search btn btn-success">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:import url="./footer.jsp"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="myjs/toSearch.js"></script>
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
