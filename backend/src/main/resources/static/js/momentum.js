$(document).ready(function () {
    smoothScroll();

    initializeAllStrats();

    initializeOpenPositions();


    addNewStratFormInitialization();


});

function smoothScroll() {
    // Select all links with hashes
    $('a[href*="#"]')
    // Remove links that don't actually link to anything
        .not('[href="#"]')
        .not('[href="#0"]')
        .click(function (event) {
            // On-page links
            if (
                location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') &&
                location.hostname == this.hostname
            ) {
                // Figure out element to scroll to
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                // Does a scroll target exist?
                if (target.length) {
                    // Only prevent default if animation is actually gonna happen
                    event.preventDefault();
                    $('html, body').animate({
                        scrollTop: target.offset().top
                    }, 1000, function () {
                        // Callback after animation
                        // Must change focus!
                        var $target = $(target);
                        $target.focus();
                        if ($target.is(":focus")) { // Checking if the target was focused
                            return false;
                        } else {
                            $target.attr('tabindex', '-1'); // Adding tabindex for elements not focusable
                            $target.focus(); // Set focus again
                        }
                        ;
                    });
                }
            }
        });
}


function initializeAllStrats() {
    var activeStrategyCardsRow = $("#active-strategies-row .strategy-cards");
    var inactiveStrategyCardsRow = $("#inactive-strategies-row .strategy-cards");

    $.ajax({
        url: "/api/strategieses?size=50",
        type: 'GET',
        success: function (result) {

            var listOfStrats = result["_embedded"]["strategieses"];

            // get list of all Strategy objects, loop through and print each out
            $.each(listOfStrats, function (index, value) {
                var strategyId = value["strategyId"];
                var stock = value["stock"];
                var stockSize = value["size"];
                var stratStatus = value["status"];
                var stratType = value["type"];
                var stratTypeID = value["typeId"];
                var profitLoss = value["profitLoss"];

                var movingAvgRange = '';
                var stdDevMultiple = '';
                var longAvgRange = '';
                var shortAvgRange = '';
                var percentToExit = '';
                var condInfoMain = '';
                var condInfoSub = '';

                console.log("Getting strategy {0}, ID {1}...".f(stratType, stratTypeID));

                if (stratType === "bb") {
                    $.ajax({
                        url: "/api/bBs/" + stratTypeID,
                        type: 'GET',
                        success: function (result) {
                            movingAvgRange = result["movingAvgRange"];
                            stdDevMultiple = result["stdDevMultiple"];
                            percentToExit = result["percentToExit"];

                            condInfoMain = '<h6 class="main-info">{0} ({1})</h6>'.f(movingAvgRange, stdDevMultiple);
                            condInfoSub = '<p class="font-small sub-info">M. RANGE (σ)</p>'

                            var active = stratStatus !== "finished";
                            var html = (active ? makeActiveStrat() : makeInactiveStrat());

                            if (active === true) {
                                activeStrategyCardsRow.append(html);
                                plotCharts(strategyId);
                            } else {
                                inactiveStrategyCardsRow.append(html);
                            }
                        }
                    });
                } else {
                    $.ajax({
                        url: "/api/twoMAs/" + stratTypeID,
                        type: 'GET',
                        success: function (result) {
                            longAvgRange = result["longAvgRange"];
                            shortAvgRange = result["shortAvgRange"];
                            percentToExit = result["percentToExit"];

                            condInfoMain = '<h6 class="main-info">{0}/{1}</h6>'.f(shortAvgRange, longAvgRange);
                            condInfoSub = '<p class="font-small sub-info">SHORT/LONG</p>';

                            var active = stratStatus !== "FINISHED";
                            var html = (active ? makeActiveStrat() : makeInactiveStrat());

                            if (active === true) {
                                activeStrategyCardsRow.append(html);
                                plotCharts(strategyId);
                            } else {
                                inactiveStrategyCardsRow.append(html);
                            }
                        }

                    });
                }



                function makeActiveStrat() {
                    var newActiveStratCard = '<!-- STRAT CARD -->' +
                        '<div class="col-12 strategy-card-active">' +
                        '<div class="row">' +
                        '<div class="col-12 strategy-card-info">' +
                        '<div class="row">' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}</h6>'.f(strategyId) +
                        '<p class="font-small sub-info">STRATEGY ID</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}</h6>'.f(stock) +
                        '<p class="font-small sub-info">STOCK</p>' +
                        '</div>' +
                        '<div class="col-1">' +
                        '<h6 class="main-info">{0}</h6>'.f(stockSize) +
                        '<p class="font-small sub-info">QTY</p>' +
                        '</div>' +
                        '<div class="col-1">' +
                        '<h6 class="main-info text-uppercase">{0}</h6>'.f(stratType) +
                        '<p class="font-small sub-info">TYPE</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        condInfoMain +
                        condInfoSub +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}%</h6>'.f(percentToExit) +
                        '<p class="font-small sub-info">EXIT %</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info text-success">${0}</h6>'.f(profitLoss) +
                        '<p class="font-small sub-info">CURRENT P/L</p>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-12 strategy-card-graph">' +
                        '<canvas id="canvas-{0}"></canvas>'.f(strategyId) +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<!-- STRAT CARD -->'

                    return newActiveStratCard;

                }


                function makeInactiveStrat() {
                    var newInactiveStratCard = '<!-- STRAT CARD -->' +
                        '<div class="col-12 strategy-card-inactive">' +
                        '<div class="row">' +
                        '<div class="col-12 strategy-card-info">' +
                        '<div class="row">' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}</h6>'.f(stock) +
                        '<p class="font-small sub-info">STOCK</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}</h6>'.f(stockSize) +
                        '<p class="font-small sub-info">QTY</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info text-uppercase">{0}</h6>'.f(stratType) +
                        '<p class="font-small sub-info">STRATEGY</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        condInfoMain +
                        condInfoSub +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">{0}%</h6>'.f(percentToExit) +
                        '<p class="font-small sub-info">EXIT %</p>' +
                        '</div>' +
                        '<div class="col-2">' +
                        '<h6 class="main-info">${0}</h6>'.f(profitLoss) +
                        '<p class="font-small sub-info">FINAL P/L</p>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<!-- STRAT CARD -->';

                    return newInactiveStratCard;
                }

            });

        }
    });


}

function initializeOpenPositions() {

    var openPosTableBody = $("#open-pos-table tbody");

    $.ajax({
        url: "/order/open-pos",
        type: 'GET',
        beforeSend: function () {
            console.log("Getting list of open positions...");
        },
        complete: function () {
        },
        success: function (result) {

            // get list of all Order objects, loop through and print each out
            $.each(result, function (index, value) {

                var orderId = value["orderID"];
                var strategyId = value["strategyId"];
                var crossoverStartType = value["crossoverStartType"];
                var crossoverStartDatetimeObj = moment(value["crossoverStartDatetime"]);
                var crossoverStartDatetime = crossoverStartDatetimeObj.format("MM/DD HH:mm:ss.SSS")
                var crossoverStartPrice = value["crossoverStartPrice"];

                var newRow = '<tr>' +
                    '<th scope="row" class="text-center text-white">{0}</th>'.f(orderId) +
                    '<td class="text-center text-white">{0}</td>'.f(strategyId) +
                    '<td class="text-center text-white text-uppercase">{0}</td>'.f(crossoverStartType) +
                    '<td class="text-center text-white">{0}</td>'.f(crossoverStartDatetime) +
                    '<td class="text-center text-white">${0}</td></tr>'.f(crossoverStartPrice)

                openPosTableBody.append(newRow);
            });
        }
    });
}


function plotCharts(id) {

    console.log("Plotting chart for strat id # " + id);
    Chart.defaults.global.defaultFontFamily = "Roboto";
    var ctx = document.getElementById('canvas-{0}'.f(id)).getContext('2d');

    var gradientFillBuy = ctx.createLinearGradient(500, 0, 100, 0);
    gradientFillBuy.addColorStop(0, "rgba(72, 209, 204, 0.0)");
    gradientFillBuy.addColorStop(1, "rgba(72, 209, 204, 0.2)");

    var gradientFillSell = ctx.createLinearGradient(500, 0, 100, 0);
    gradientFillSell.addColorStop(0, "rgba(255, 255, 255, 0.2)");
    gradientFillSell.addColorStop(1, "rgba(255, 255, 255, 0.0)");

    $.ajax({
        url: "/order/strategy-id/" + id,
        type: 'GET',
        success: function (resultList) {

            console.log("Getting orders associated with strategy # " + id);
            var listOfTimesAndCrossovers = [];

            $.each(resultList, function (index, value) {
                var crossoverSType = value["crossoverStartType"];
                var crossoverSTimeObj = moment(value["crossoverStartDatetime"]);
                var crossoverSTime = crossoverSTimeObj.format("HH:mm:ss")
                var crossoverSPrice = value["crossoverStartPrice"];

                listOfTimesAndCrossovers.push({
                    "time": crossoverSTime,
                    "type": crossoverSType,
                    "price": crossoverSPrice
                });

                if (value["crossoverEndType"] !== undefined) {
                    var crossoverEType = value["crossoverEndType"];
                    var crossoverETimeObj = moment(value["crossoverEndDatetime"]);
                    var crossoverETime = crossoverETimeObj.format("HH:mm:ss")
                    var crossoverEPrice = value["crossoverEndPrice"];
                    listOfTimesAndCrossovers.push({
                        "time": crossoverETime,
                        "type": crossoverEType,
                        "price": crossoverEPrice
                    });
                }
            });

            var sortedCrossOvers = sortByKey(listOfTimesAndCrossovers, "time");
            var listOfTimes = [];
            var listOfBuys = [];
            var listOfSells = [];

            $.each(sortedCrossOvers, function (index, value) {
                listOfTimes.push(value["time"]);
                if (value["type"] === "buy") {
                    listOfBuys.push(value["price"]);
                    listOfSells.push(null);
                } else {
                    listOfBuys.push(null);
                    listOfSells.push(value["price"]);
                }
            });


            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: listOfTimes,
                    datasets: [
                        {
                            data: listOfBuys,
                            label: "BUY",
                            borderWidth: 1,
                            fill: true,
                            borderColor: "#48D1CC",
                            backgroundColor: gradientFillBuy
                        },
                        {
                            data: listOfSells,
                            label: "SELL",
                            borderWidth: 1,
                            fill: true,
                            borderColor: "white",
                            backgroundColor: gradientFillSell
                        }
                    ]
                },
                options: {
                    spanGaps: true,
                    responsive: true,
                    maintainAspectRatio: false,
                    elements: {
                        // line: {
                        //     tension: 0 // disables bezier curves
                        // }
                    },
                    legend: {
                        display: false,
                        labels: {
                            defaultFontFamily: 'Roboto',
                            fontColor: 'white'
                        }
                    },
                    scales: {
                        yAxes: [{
                            gridLines: {
                                color: 'white',
                                display: false
                            },
                            ticks: {
                                fontColor: "white",
                                beginAtZero: false
                            }
                        }],
                        xAxes: [{
                            gridLines: {
                                color: 'white',
                                display: false
                            },
                            ticks: {
                                fontColor: "white",
                                beginAtZero: false
                            }
                        }]
                    }
                }
            });


        }
    });


}


function addNewStratFormInitialization() {

    $("#addStrategyForm").submit(function (event) {

        event.preventDefault();

        var values = {};
        $.each($(this).serializeArray(), function () {
            values[this.name] = this.value;
        });

        var jsonToSend = {};
        var urlToSend = "";

        if (values["selectStrategy"] === "BB") {
            console.log("Adding BB strategy to DB...");
            urlToSend = "/api/bBs";
            jsonToSend = {
                "movingAvgRange": parseInt(values["stratVariableOne"]),
                "stdDevMultiple": parseFloat(values["stratVariableTwo"]),
                "percentToExit": parseFloat(values["inputExitPercent"])
            }
        } else if (values["selectStrategy"] === "2MA") {
            console.log("Adding 2MA strategy to DB...");
            urlToSend = "/api/twoMAs";
            jsonToSend = {
                "shortAvgRange": parseInt(values["stratVariableOne"]),
                "longAvgRange": parseInt(values["stratVariableTwo"]),
                "percentToExit": parseFloat(values["inputExitPercent"])
            }
        } else {
            console.log("Error adding strat to DB. EXITING.");
            console.log(values);
            return false;
        }

        $.ajax({
            url: urlToSend,
            type: 'POST',
            data: JSON.stringify(jsonToSend),
            contentType: "application/json",
            success: function (result) {
                var typeIDAssigned = result["strategyId"];
                var nowDateTime = moment().toISOString();

                var stratJson = {
                    "type": values["selectStrategy"].toLowerCase(),
                    "typeId": typeIDAssigned,
                    "stock": values["inputStock"].toUpperCase(),
                    "size": parseInt(values["inputQuantity"]),
                    "status": "pending",
                    "addedTime": nowDateTime,
                    "profitLoss": 0.0
                };

                $.ajax({
                    url: '/api/strategieses',
                    type: 'POST',
                    data: JSON.stringify(stratJson),
                    contentType: "application/json",
                    success: function (result) {
                        console.log("Added strategy into DB.");
                        console.log(result);
                        location.reload();
                    }
                });
            }
        });

        console.log(values);

    });

    var selectStrat = $("#selectStrategy");
    var stratVariableOneLabel = $("#stratVariableOneLabel");
    var stratVariableTwoLabel = $("#stratVariableTwoLabel");

    selectStrat.change(function () {
        var currentSelected = selectStrat.val();

        if (currentSelected === "BB") {
            stratVariableOneLabel.html("Moving Avg Range (e.g. 20)");
            stratVariableTwoLabel.html("Standard Dev (e.g. 1.75)");
        } else if (currentSelected === "2MA") {
            stratVariableOneLabel.html("Low Range (e.g. 15)");
            stratVariableTwoLabel.html("High Range (e.g. 50)");
        } else {
        }
    });
}


// HELPER METHODS - DO NOT DELETE

String.prototype.format = String.prototype.f = function () {
    var s = this,
        i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};


function sortByKey(array, key) {
    return array.sort(function (a, b) {
        var x = a[key];
        var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}