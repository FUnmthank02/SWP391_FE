
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>6HP - Happy Programing</title>
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="icon" type="image/x-icon" href="image/mylogo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>
            .select_form {
                width: fit-content !important;
                display: inline !important;
                cursor: pointer;
            }
            .btn_page {
                width: fit-content;
                padding: 4px 7px;
                background-color: #25c481;
                color: #ffffff;
                border: 1px solid #25b7c4;
                cursor: pointer;
            }
            .btn_page:hover {
                background-color: rgba(37, 196, 129, 0.6);
                color: #000000
            }

            .phantrang {
                margin: 50px;
                display: flex;
                justify-content: end;
            }
        </style>
    </head>

    <body>
        <!--header-->
        <c:import url="header.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <div class="side-bar col-md-2" style="padding: 100px 0 !important;">
                    <h2 class="text-light mb-5 mt-5" data-aos="fade" data-aos-duration="1000">Dashboard</h2>
                    <ul>
                        <div class="block-item-sidebar active" onclick="handleShow(this)">
                            <li class="item-sidebar" data-aos="fade" data-aos-duration="1000">Statistic</li>
                            <div class="line" data-aos="fade-right" data-aos-duration="1000"></div>
                        </div>
                        <div class="block-item-sidebar" onclick="handleShow(this)">
                            <li class="item-sidebar" data-aos="fade" data-aos-duration="1000">Mentor</li>
                            <div class="line" data-aos="fade-right" data-aos-duration="1000"></div>
                        </div>
                        <div class="block-item-sidebar" onclick="handleShow(this)">
                            <li class="item-sidebar" data-aos="fade" data-aos-duration="1000">Mentee</li>
                            <div class="line" data-aos="fade-right" data-aos-duration="1000"></div>
                        </div>

                    </ul>
                </div>


                <div class="main col-md-10">

                    <!-- statistic part -->
                    <div class="container-fluid contain-statistic mb-5 show">
                        <div class="row">
                            <div class="wrap-welcome col-md-12">
                                <h3 class="text-light font-weight-bold">Welcome, Admin.<i class="fa-solid fa-hand text-warning ml-3"></i>
                                </h3>
                                <p class="text-dark">Here is the statistic about your website</p>
                            </div>

                            <!-- bieu do thong ke so luong -->
                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Request</span>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Response</span>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Invitation</span>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Mentor Register</span>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Mentor</span>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-5 col-md-2">
                                <div class="pl-3 pr-3 pt-5 pb-5">
                                    <div class="contain_counting">
                                        <span class="num" data-val="100">400</span>
                                        <span class="text">Mentee</span>
                                    </div>
                                </div>
                            </div>

                            <!-- bieu do thong ke so luong -->

                            <!-- bieu do thong ke request -->
                            <div class="mt-4 col-md-10 offset-1">
                                <div class="pl-3 pr-3 pt-3 pb-5 item-statistic">
                                    <h5 class="">Pie Chart</h5>
                                    <hr>
                                    <canvas id="myChart" style="width:100%; max-width:1000px;"></canvas>
                                </div>
                            </div>
                            <div class="mt-4 col-md-10 offset-1">
                                <div class="pl-3 pr-3 pt-3 pb-5 item-statistic">
                                    <h5 class="">Line Chart</h5>
                                    <hr>
                                    <canvas id="myChart1" style="width:100%;max-width:1000px;"></canvas>
                                </div>
                            </div>
                            <!-- bieu do thong ke request -->

                            <!-- bieu do thong ke response -->
                            <div class="mt-4 col-md-10 offset-1">
                                <div class="pl-3 pr-3 pt-3 pb-5 item-statistic">
                                    <h5 class="">Bar Chart</h5>
                                    <hr>
                                    <canvas id="myChart2" style="width:100%; max-width:1000px;"></canvas>
                                </div>
                            </div>
                            <div class="mt-4 col-md-10 offset-1">
                                <div class="pl-3 pr-3 pt-3 pb-5 item-statistic">
                                    <h5 class="">Line Chart</h5>
                                    <hr>
                                    <canvas id="myChart3" style="width:100%;max-width:1000px;"></canvas>
                                </div>
                            </div>
                            <!-- bieu do thong ke response -->


                        </div>

                    </div>
                    <!-- statistic part -->

                    <!-- mentee part -->
                    <div class="contain-mentee mb-5">
                        <section>
                            <h1 class="text-uppercase pt-3 pb-3 text-warning font-weight-bold" style="text-align: center;">All Mentee</h1>
                            <div class="tbl-header">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <thead>
                                        <tr>
                                            <th>Fullname</th>
                                            <th>Email</th>
                                            <th>Birthday</th>
                                            <th>Address</th>
                                            <th>Gender</th>
                                            <th>Phone number</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="tbl-content">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <tbody>
                                        <c:forEach items="${requestScope.listMentee}" var="o" begin="${cpMentee.begin}" end="${cpMentee.end}">
                                            <tr>
                                                <td>${o.getUser().getFullname()}</td>
                                                <td>${o.getUser().getEmail()}</td>
                                                <td>${o.getUser().getDob()}</td>
                                                <td>${o.getUser().getAddress()}</td>
                                                <td>${o.getUser().getGender()}</td>
                                                <td>${o.getUser().getPhonenumber()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </section>

                        <!--phan trang-->
                        <div class="phantrang">
                            <form action="dashboard" method="post">
                                <c:if test="${cpMentee.cp!=0}">
                                    <input class="btn_page" type="submit" name="home" value="Home"/>
                                    <input class="btn_page" type="submit" name="pre" value="Pre"/>
                                </c:if>

                                <c:forEach begin="${cpMentee.pageStart}" end="${cpMentee.pageEnd}" var="i">
                                    <span><input class="btn_page" type="submit" name="btn${i}" value="${i+1}"/></span>
                                    </c:forEach>

                                <c:if test="${cpMentee.cp != cpMentee.np-1}">
                                    <input class="btn_page" type="submit" name="next" value="Next"/>
                                    <input class="btn_page" type="submit" name="end" value="End"/>
                                </c:if>
                                <input type="text" hidden name="cp" value="${cpMentee.cp}"/>
                                <input type="text" hidden name="np" value="${cpMentee.np}"/>
                                <select class="form-control select_form ml-2" name="nrpp">
                                    <c:forEach items="${cpMentee.arrNrpp}" var="i" varStatus="loop">
                                        <option value="${loop.index}"
                                                <c:if test="${loop.index==sessionScope.nrpp}">
                                                    selected
                                                </c:if>
                                                >${i}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                    </div>
                    <!-- mentee part -->

                    <!-- mentor part -->
                    <div class="contain-mentor mb-5">
                        <section>
                            <h1 class="text-uppercase pt-3 pb-3 text-warning font-weight-bold" style="text-align: center;">All Mentor</h1>
                            <div class="tbl-header">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <thead>
                                        <tr>
                                            <th>Fullname</th>
                                            <th>Email</th>
                                            <th>Birthday</th>
                                            <th>Address</th>
                                            <th>Gender</th>
                                            <th>Phone number</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="tbl-content">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <tbody>
                                        <c:forEach items="${requestScope.listMentor}" var="o" begin="${cpMentor.begin}" end="${cpMentor.end}">
                                            <tr>
                                                <td>${o.getUser().getFullname()}</td>
                                                <td>${o.getUser().getEmail()}</td>
                                                <td>${o.getUser().getDob()}</td>
                                                <td>${o.getUser().getAddress()}</td>
                                                <td>${o.getUser().getGender()}</td>
                                                <td>${o.getUser().getPhonenumber()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </section>
                        
                        <!--phan trang-->
                        <div class="phantrang">
                            <form action="dashboard" method="post">
                                <c:if test="${cpMentor.cp!=0}">
                                    <input class="btn_page" type="submit" name="home" value="Home"/>
                                    <input class="btn_page" type="submit" name="pre" value="Pre"/>
                                </c:if>

                                <c:forEach begin="${cpMentor.pageStart}" end="${cpMentor.pageEnd}" var="i">
                                    <span><input class="btn_page" type="submit" name="btn${i}" value="${i+1}"/></span>
                                    </c:forEach>

                                <c:if test="${cpMentor.cp != cpMentor.np-1}">
                                    <input class="btn_page" type="submit" name="next" value="Next"/>
                                    <input class="btn_page" type="submit" name="end" value="End"/>
                                </c:if>
                                <input type="text" hidden name="cp" value="${cpMentor.cp}"/>
                                <input type="text" hidden name="np" value="${cpMentor.np}"/>
                                <select class="form-control select_form ml-2" name="nrpp">
                                    <c:forEach items="${cpMentor.arrNrpp}" var="i" varStatus="loop">
                                        <option value="${loop.index}"
                                                <c:if test="${loop.index==sessionScope.nrpp}">
                                                    selected
                                                </c:if>
                                                >${i}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                    </div>
                    <!-- mentor part -->

                </div>
            </div>
        </div>


        <!--footer-->
        <c:import url="footer.jsp"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

        <script src="myjs/dashboard.js"></script>

        <script>
                            let valueDisplays = document.querySelectorAll(".num");
                            let interval = 1000;

                            valueDisplays.forEach((valueDisplay) => {
                                let startValue = 0;
                                let endValue = parseInt(valueDisplay.getAttribute("data-val"));
                                let duration = Math.floor(interval / endValue);
                                let counter = setInterval(function () {
                                    startValue += 1;
                                    valueDisplay.textContent = startValue;
                                    if (startValue == endValue) {
                                        clearInterval(counter);
                                    }
                                }, duration);
                            });
        </script>

        <!-- bieu do demo request -->
        <script>
            var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
            var yValues = [55, 49, 44, 24, 15];
            var barColors = [
                "#E0144C",
                "#FF884B",
                "#14279B",
                "#e8c3b9",
                "#95CD41"
            ];

            new Chart("myChart", {
                type: "pie",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: "World Wide Wine Production 2018"
                    }
                }
            })
        </script>
        <!-- bieu do demo request -->

        <!-- bieu do demo request line chart -->
        <script>
            var xValues = [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150];
            var yValues = [1, 3, 7, 6, 9, 9, 9, 6, 4, 1, 5];
            // <c:forEach var="c" items="${requestScope.countRequest}">
            // xValues.push("${c.key.toString()}")
            // </c:forEach>
            // <c:forEach var="c" items="${requestScope.countRequest}">
            // yValues.push(${c.value})
            // </c:forEach>
            new Chart("myChart1", {
                type: "line",
                data: {
                    labels: xValues,
                    datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: yValues
                        }]
                },
                options: {
                    legend: {display: false},
                    scales: {
                        yAxes: [{ticks: {min: 0, max: 10}}],
                    }
                }
            });
            </script>
            <!-- bieu do demo request line chart -->

            <!-- bieu do demo bar chart -->
            <script>
                var xValues = ['Jun 2022', 'Jul 2022', 'Aug 2022', 'Sep 2022', 'Oct 2022', 'Nov 2022'];
                var yValues1 = [55, 49, 44, 24, 15, 8];
                var yValues2 = [12, 19, 3, 5, 2, 3];
                var barColors = ["#E0144C", "#95CD41", "#14279B", "#FF884B", "#A64B2A"];

                new Chart("myChart2", {
                    type: "bar",
                    data: {
                        labels: xValues,
                        datasets: [{
                                label: 'Request',
                                backgroundColor: '#E0144C',
                                data: yValues1
                            },
                            {
                                label: 'Response',
                                backgroundColor: '#FF884B',
                                data: yValues2
                            }
                        ]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            </script>
            <!-- bieu do demo bar chart -->

            <!-- bieu do demo request line chart -->
            <script>
                var myData = [2, 5, 8, 9, 3, 7, 10, 9, 10, 8, 1];
                // <c:forEach var="c" items="${requestScope.countRequest}">
                // xValues.push("${c.key.toString()}")
                // </c:forEach>
                // <c:forEach var="c" items="${requestScope.countRequest}">
                // yValues.push(${c.value})
                // </c:forEach>
                new Chart("myChart3", {
                    type: 'line',
                    data: {
                        labels: ['Jun 2022', 'Jul 2022', 'Aug 2022', 'Sep 2022', 'Oct 2022', 'Nov 2022'],
                        datasets: [{
                                label: 'Mentee invited',
                                tension: 0.2,
                                data: myData,
                                borderColor: 'rgb(75, 192, 192)',
                                borderWidth: 3
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
        </script>
        <!-- bieu do demo request line chart -->

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
