<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <body>

        <canvas id="myChart" style="width:100%;max-width:600px"></canvas>

        <script>
            var xValues = ["Not responsed", "Responsed"];
            var yValues = [];
            <c:forEach var="p" items="${requestScope.percentage}">
            yValues.push(${p});
            </c:forEach>
            var barColors = [
                "#b91d47",
                "#00aba9"
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
                        text: "Percentage of responsed request"
                    }
                }
            });
        </script>
        <br/>
    <h style="font-weight: bold">Requests each month</h> <br>
        <canvas id="myChart1" style="width:100%;max-width:600px"></canvas>
    
    <script>
        var xValues = [];
        var yValues = [];
        <c:forEach var="c" items="${requestScope.countRequest}">
        xValues.push("${c.key.toString()}")
        </c:forEach>
        <c:forEach var="c" items="${requestScope.countRequest}">
        yValues.push(${c.value})
        </c:forEach>
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
</body>

</html>


