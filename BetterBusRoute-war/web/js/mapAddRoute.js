/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var labels = '0123456789ABCDEFGHIJKLMNOPQRSTUWXYZ';
var labelIndex = 0;
var markers = [];
var path1 = true;
var cleanPath1 = false;

function initMap() {
    var map = new google.maps.Map(document.getElementById('content'), {
        zoom: 13,
        center: {lat: 20.6674807, lng: -103.3991668},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            map.setCenter(pos);
        }, function() {
            console.write("");
        });
    } else {
        // Browser doesn't support Geolocation
        console.write("");
    }
    // This event listener calls addMarker() when the map is clicked.
    google.maps.event.addListener(map, 'click', function(event) {
        addMarker(event.latLng, map);
    });

    // Adds a marker to the map.
    function addMarker(location, map) {
        var text;

        if (path1) {
            text = document.getElementById("path1");
            text.value += location;
        } else {
            text = document.getElementById("path2");
            text.value += location;
        }
        var marker = new google.maps.Marker({
            position: location,
            label: labels[labelIndex++ % labels.length],
            map: map
        });
        markers.push(marker);

    }
}

function cleanPath2(num) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
    text = document.getElementById("path" + num);
    text.value = "";
    labelIndex = 0;
}

function selectPath2() {
    if (path1) {
        path1 = false;
        labelIndex = 0;
    }
}

function selectPath1() {
    if (!path1) {
        path1 = true;
        labelIndex = 0;
    }
}