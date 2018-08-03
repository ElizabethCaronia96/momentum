$(document).ready(function () {
    smoothScroll();

    initializeAllStrats();

    initializeOpenPositions();



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

function plotCharts(id) {

    Chart.defaults.global.defaultFontFamily = "Roboto";

    var ctx = document.getElementById(id).getContext('2d');

    var gradientFill = ctx.createLinearGradient(500, 0, 100, 0);
    gradientFill.addColorStop(0, "rgba(255, 255, 255, 0.5)");
    gradientFill.addColorStop(1, "rgba(255, 255, 255, 0.0)");

    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["9:05", "9:06", "9:07", "9:11", "9:15", "9:32", "9:42", "9:50", "9:53", "9:58", "10:27", "10:33", "10:47", "10:51", "10:58", "10:59", "11:08", "11:21", "11:36", "12:00", "12:09", "12:16", "12:17", "12:25", "12:37", "12:53", "13:46", "13:50", "13:53", "14:05", "14:08", "14:09", "14:13", "14:22", "14:32", "14:33", "14:47", "14:51", "14:56", "15:00", "15:16", "15:31", "15:47", "15:48", "15:50", "16:01", "16:32", "16:52", "16:58", "17:00"],
            datasets: [{
                data: [103.88, 104.95, 98.28, 103.01, 101.76, 105.75, 99.8, 101.26, 100.94, 98.23, 103.97, 98.78, 102, 105.58, 104.64, 96.84, 102.74, 96.66, 98.39, 96.4, 98.39, 103.89, 97.33, 101.01, 103.18, 95.11, 97.35, 102.66, 100.12, 105.12, 96.89, 99.5, 103.03, 104.17, 98.83, 96.93, 102.47, 98.67, 105.85, 99.73, 105.23, 98.72, 102.85, 105.92, 102.61, 101.5, 102.32, 96.78, 102.78, 98.71],
                label: "Price",
                borderWidth: 1,
                fill: true,
                borderColor: "white",
                backgroundColor: gradientFill
            }
            ]
        },
        options: {
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

                            if (stratStatus === "FINISHED") {
                                addNewInactiveStratCard();
                            } else {
                                addNewActiveStratCard();
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
                            condInfoSub = '<p class="font-small sub-info">SHORT/LONG</p>'

                            if (stratStatus === "FINISHED") {
                                addNewInactiveStratCard();
                            } else {
                                addNewActiveStratCard();
                            }
                        }
                    });
                }

                function addNewActiveStratCard() {
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

                    activeStrategyCardsRow.append(newActiveStratCard);
                    plotCharts("canvas-{0}".f(strategyId));
                }


                function addNewInactiveStratCard() {
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
                        '<!-- STRAT CARD -->'

                    inactiveStrategyCardsRow.append(newInactiveStratCard);
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


// HELPER METHODS - DO NOT DELETE

String.prototype.format = String.prototype.f = function () {
    var s = this,
        i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};
