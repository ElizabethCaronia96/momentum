$(document).ready(function () {
        smoothScroll();
        plotCharts();
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

function plotCharts() {

    Chart.defaults.global.defaultFontFamily = "Roboto";

    var ctx = document.getElementById("canvas").getContext('2d');

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

function updatePositions() {

    new Vue({
        el: '#app',
        data: {
            users: []
        },
        created () {
            var vm = this
            axios.get('https://jsonplaceholder.typicode.com/users')
                .then(function (response) {
                    vm.users = response.data
                })
        }
    })
}
