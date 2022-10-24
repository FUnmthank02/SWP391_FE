<!DOCTYPE html>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<body>
<canvas id="bar-chart" width="1000" height="150"></canvas>

<script>
//var xyValues = [
//  {x:50, y:7},
//  {x:60, y:8},
//  {x:70, y:8},
//  {x:80, y:9},
//  {x:90, y:9},
//  {x:100, y:9},
//  {x:110, y:10},
//  {x:120, y:11},
//  {x:130, y:14},
//  {x:140, y:14},
//  {x:150, y:15}
//];

new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
});
</script>

</body>
</html>
