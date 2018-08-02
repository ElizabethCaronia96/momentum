$(document).ready(function() {
    smoothScroll();
    plotCharts();
});

function smoothScroll() {
    // Select all links with hashes
    $('a[href*="#"]')
    // Remove links that don't actually link to anything
        .not('[href="#"]')
        .not('[href="#0"]')
        .click(function(event) {
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
                    }, 1000, function() {
                        // Callback after animation
                        // Must change focus!
                        var $target = $(target);
                        $target.focus();
                        if ($target.is(":focus")) { // Checking if the target was focused
                            return false;
                        } else {
                            $target.attr('tabindex', '-1'); // Adding tabindex for elements not focusable
                            $target.focus(); // Set focus again
                        };
                    });
                }
            }
        });
}

function plotCharts(){

    Chart.defaults.global.defaultFontFamily = "Roboto";

    var ctx = document.getElementById("canvas").getContext('2d');

    var gradientFill = ctx.createLinearGradient(500, 0, 100 , 0);
    gradientFill.addColorStop(0, "rgba(255, 255, 255, 0.5)");
    gradientFill.addColorStop(1, "rgba(255, 255, 255, 0.0)");


    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10],
            datasets: [{
                data: [95.22,103.12,112.32,112.52,117.23,126.32,182.42,172.42,112.21,123.32,95.22,103.12,112.32,112.52,117.23,126.32,182.42,172.42,112.21,123.32,95.22,103.12,112.32,112.52,117.23,126.32,182.42,172.42,112.21,123.32,95.22,103.12,112.32,112.52,117.23,126.32,182.42,172.42,112.21,123.32],
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
                line: {
                    tension: 0 // disables bezier curves
                }
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
                        beginAtZero:true,
                    }
                }],
                xAxes: [{
                    gridLines: {
                        color: 'white',
                        display: false
                    },
                    ticks: {
                        fontColor: "white",
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}



