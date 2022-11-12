google.charts.load('current', {packages: ['corechart', 'bar']});

$("#btnGetChartData").click(function () {
    $("#btnGetChartData").hide();
    $.ajax({
        url: "InventoryTable",
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

// function drawChart() {
//     var jsonData = $.ajax({
//         url: "InventoryTable",
//         type: "POST",
//         data: "{}",
//     }).responseText;
//
//     let arr2 = [];
//     arr2 = JSON.parse(jsonData);
//     let name = Object.keys(arr2[0]);
//     let arr1 = [name];
//
//     $.each(arr2, function (index, value) {
//         arr1.push(Object.values(arr2[index]));
//     });
//
//     let options = {
//         title: 'Inventory of Products',
//         chartArea: {width: '50%'},
//         colors: ['#b0120a', '#ffab91'],
//         hAxis: {
//             title: 'Inventory',
//             minValue: 0
//         },
//         vAxis: {
//             title: 'Product Name'
//         }
//     };
//
//     let data = google.visualization.arrayToDataTable(arr1);
//
//     var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
//     chart.draw(data, options);
//
// }
function createDataTable(jsonData) {
    var parseData = $.parseJSON(jsonData);
    var data = new Array();

    data[0] = new Array("Product Name","Inventory");

    for(var i=0;i<parseData.length;i++){
        var name = parseData[i]["name"];
        var inventory = parseData[i]["inventory"];
        data[i+1] = new Array(name,inventory);
        console.log(name,inventory);
    }

    draw(data);
}
function draw(data){
    var data = google.visualization.arrayToDataTable(data);
    var options = {
        title: 'Inventory of Product',
        height: 1000,
        chartArea: {width: '50%'},
        hAxis: {
            title: 'Inventory',
            minValue: 0
        },
        vAxis: {
            title: 'Product Name'
        }
    };
    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}