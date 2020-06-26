<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Money Manage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
        integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.0-alpha/dist/Chart.min.js"></script>
</head>

<body>
    <header>
        <div class="row bg-primary">
            <div class="col-md-4 col-xs-8 col-sm-8 col-9 text-white pl-4 p-1">
                <h2>Money Manage</h2>
            </div>
            <div class="col-md-8 col-xs-4 col-sm-4 col-3 text-sm-right">

                <nav class="navbar navbar-expand-lg bg-primary navbar-dark">
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto text-white">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Income <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Expence <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Accounts
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="#">Action</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Something else here</a>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                            </li>
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                </nav>

            </div>

        </div>
        <div class="row pl-3 bg-dark">
            <div class="card p-2 m-2 " style="width: 38.5rem;">
                <div class="card-body">
                    <canvas id="mypieChart" width="400" height="300"></canvas>
                </div>
            </div>
            <div class="card p-2 m-2" style="width: 38.5rem;">
                <div class="card-body">
                    <canvas id="myChart" width="400" height="300"></canvas>
                </div>
            </div>

            <div class="card p-2 m-2 " style="width: 38.5rem;">
                <div class="card-body">
                    <canvas id="lastmonthctx" width="400" height="300"></canvas>
                </div>
            </div>
        </div>
        <div class="row pl-3 bg-info">
            <div class="card p-1 m-2 " style="width: 117.5rem;">
                <div class="card-body">
                    <canvas id="lyChart" width="700" height="200"></canvas>
                </div>
            </div>

        </div>



    </header>

    <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var piectx = document.getElementById('mypieChart').getContext('2d');
        var lastmonthctx = document.getElementById('lastmonthctx').getContext('2d');
        var lsLinectx = document.getElementById('lyChart').getContext('2d');


        var expcat = new XMLHttpRequest();
        var expcaturl = "https://moneymanageapp.azurewebsites.net/api/category/expence"
        expcat.open("GET", expcaturl);
        expcat.send();
        var curmon = CurrentMonth()
        var curyr = CurrentYear()
        console.log(curyr)
        const monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        var ExpMon = new XMLHttpRequest();
        var expmonurl = 'https://moneymanageapp.azurewebsites.net/api/expence/' + curmon + '/' + curyr
        ExpMon.open("GET", expmonurl)
        ExpMon.send();


        var LastExpMon = new XMLHttpRequest();
        var Lastexpmonurl = 'https://moneymanageapp.azurewebsites.net/api/expence/' + (curmon - 1) + '/' + curyr
        LastExpMon.open("GET", Lastexpmonurl)
        LastExpMon.send();

        //pull the data for las one year on monthly basis

        var Lastyear = new XMLHttpRequest();
        var Lastyearurl = 'https://moneymanageapp.azurewebsites.net/api/balance/lastyear'
        Lastyear.open("GET", Lastyearurl)
        Lastyear.send();


        Lastyear.onreadystatechange = function () {

            if ((Lastyear.readyState == 4) && (Lastyear.status == 200)) {

                var lyresponse = JSON.parse(Lastyear.responseText)
                var MonExpences = [];
                var MonIncome = [];
                var Monbalances = [];
                var monthorder = [];
                var moncount = 0;
                var month = CurrentMonth()-1
                var year = CurrentYear()
                lyresponse.forEach(balancesheet => {
                     
                    
                    MonExpences[moncount] = balancesheet.total_expence
                    MonIncome[moncount] = balancesheet.total_income
                    Monbalances[moncount] = balancesheet.balance
                    monthorder[moncount] = monthNames[month]+" "+year
                    if (month == 0){
                        month = 12
                        year= year-1
                    }
                    moncount++
                    month--
                })

            }

            lastyearchart(MonExpences, MonIncome, Monbalances,monthorder)
        }

        ExpMon.onreadystatechange = function () {

            if ((ExpMon.readyState == 4) && (ExpMon.status == 200) && (expcat.status == 200)) {
                var catResponse = JSON.parse(expcat.responseText);
                var exResponse = JSON.parse(ExpMon.responseText);

                var thmondata = dataProcess(catResponse, exResponse)

                thisMonthChart(thmondata[1], thmondata[0])

            }


        }
        LastExpMon.onreadystatechange = function () {

            if ((LastExpMon.readyState == 4) && (LastExpMon.status == 200) && (expcat.status == 200) && (expcat.readyState == 4)) {

                var catResponse = JSON.parse(expcat.responseText);
                var lsexResponse = JSON.parse(LastExpMon.responseText);


                var lsmondata = dataProcess(catResponse, lsexResponse)

                Lastmonth(lsmondata[1], lsmondata[0])

            }


        }

        function dataProcess(catResponse, exResponse) {

            var Label = [];
            var Data = [];
            var catlabel = [];



            catResponse.forEach(element => {

                if (element.catType == "Normal") {
                    //   console.log((element.catogeryName)+ "test")

                    Label.push((element.catogeryName))
                }


            })

            // console.log(Label)
            var count = 0;


            Label.forEach(function (item, index) {
                var sum = 0;
                exResponse.forEach(exelement => {

                    if (exelement.expence_Catogory == item) {
                        sum += exelement.expence_Amount
                    }
                })
                if (sum != 0) {
                    catlabel.push(item)
                    Data.push(sum)
                    console.log(count++)
                }

            })

            return [Data, catlabel];
        }

        function Lastmonth(excatlabel, expData) {

            var lastmonthchart = new Chart(lastmonthctx, {
                type: 'bar',
                data: {
                    labels: excatlabel,
                    datasets: [{
                        label: monthNames[CurrentMonth()-1]+" "+CurrentYear(),
                        data: expData,
                        barThickness: 'flex',
                        backgroundColor: ['rgba(80, 200, 120)',
                            'rgba(252, 161, 3)',
                            'rgba(3, 152, 252)',
                            'rgba(252, 3, 198, 0.6)',
                            'rgb(255, 0, 0)',
                            'rgb(0, 179, 0)',
                            'rgb(3, 252, 240)',
                            'rgb(3, 227, 252)',
                            'rgb(3, 252, 181)',
                            'rgb(144, 252, 3)',
                            'rgb(190, 252, 3)',
                            'rgb(252, 128, 3)'],

                        borderColor: 'rgba(	0, 255, 0)',

                        borderWidth: 1
                    }]
                },

            });
        }

        function thisMonthChart(catlabel, Data) {

            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: catlabel,
                    datasets: [{
                        label: monthNames[CurrentMonth()]+" "+CurrentYear(),
                        data: Data,
                        barThickness: 'flex',
                        backgroundColor: ['rgba(80, 200, 120)',
                            'rgba(252, 161, 3)',
                            'rgba(3, 152, 252)',
                            'rgba(252, 3, 198, 0.6)',
                            'rgb(255, 0, 0)',
                            'rgb(0, 179, 0)',
                            'rgb(3, 252, 240)',
                            'rgb(3, 227, 252)',
                            'rgb(3, 252, 181)',
                            'rgb(144, 252, 3)',
                            'rgb(190, 252, 3)',
                            'rgb(252, 128, 3)'],

                        borderColor: 'rgba(	0, 255, 0)',

                        borderWidth: 1
                    }]
                },

            });

            var mypieChart = new Chart(piectx, {
                type: 'doughnut',
                data: {
                    labels: catlabel,
                    datasets: [{

                        backgroundColor: ['rgba(80, 200, 120)',
                            'rgba(252, 161, 3)',
                            'rgba(3, 152, 252)',
                            'rgba(252, 3, 198, 0.6)',
                            'rgb(255, 0, 0)',
                            'rgb(0, 179, 0)',
                            'rgb(3, 252, 240)',
                            'rgb(3, 227, 252)',
                            'rgb(3, 252, 181)',
                            'rgb(144, 252, 3)',
                            'rgb(190, 252, 3)',
                            'rgb(252, 128, 3)'

                        ],
                        data: Data,

                    }]
                },
                options: {

                    cutoutPercentage: 60,

                    animation: {
                        animateScale: true
                    },
                    legend: {
                        labels: {

                            fontSize: 10,

                        },
                        position: 'left',

                    },



                    title: {
                        display: true,
                        text: monthNames[CurrentMonth()]+" "+CurrentYear(),

                        fontSize: 15,
                        fontColor: '#03d3fc'
                    },
                    elements: {

                    },
                    plugins: {
                        datalabels: {
                            formatter: (value, ctx) => {
                                let sum = 0;
                                let dataArr = ctx.chart.data.datasets[0].data;
                                dataArr.map(data => {
                                    sum += data;
                                });
                                let percentage = (value * 100 / sum).toFixed(2) + "%";
                                return percentage;
                            },
                        }
                    },

                    tooltips: {
                        callbacks: {


                        },

                        titleAlign: 'left'
                    }


                },
                legend: {

                    display: true
                }
            });

        }

        function lastyearchart(MonExpences, MonIncome, Monbalances,monthorder) {

            var LsYear = new Chart(lsLinectx, {
                type: 'line',

                // The data for our dataset
                data: {
                    labels: monthorder,
                    datasets: [{
                        label: 'Expence',
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: MonExpences
                    }]
                },

              // Configuration options go here
                options: {}
            });
        }
        //get current month
        function CurrentMonth() {
            var d = new Date();
            var n = d.getMonth();
            return n;
        }
        function CurrentYear() {
            var d = new Date();
            var n = d.getFullYear();
            return n;
        }

    </script>
</body>

</html>