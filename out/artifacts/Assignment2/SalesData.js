google.charts.load('current', {packages: ['corechart', 'bar']});

$("#btnGetChartData").click(function () {
    $("#btnGetChartData").hide();
    $.ajax({
        url: "ProductSold",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });
});

function createDataTable(jsonData) {
    var parseData = $.parseJSON(jsonData);
    var data = new Array();

    data[0] = new Array("Product Name","Total Sales");

    for(var i=0;i<parseData.length;i++){
        var name = parseData[i]["productName"];
        var total = parseData[i]["total_sales"];
        data[i+1] = new Array(name,total);
        console.log(name,total);
    }

    draw(data);
}
function draw(data){
    var data = google.visualization.arrayToDataTable(data);

    var options = {
        title: 'Product Sales',
        height: 1000,
        chartArea: {width: '50%'},
        vAxis: {
            title: 'Product Name'
        },
        hAxis: {
            title: 'Total Sales',
            minValue: 0
        }

    };
    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}