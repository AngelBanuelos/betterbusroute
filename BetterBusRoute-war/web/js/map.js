/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var labels = ['Origen', 'Destino'];
var labelIndex = 0;
var markers = [];
var map;

function initMap() {
    map = new google.maps.Map(document.getElementById('content'), {
        zoom: 13,
        center: {lat: 20.6674807, lng: -103.3991668},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var trafficLayer = new google.maps.TrafficLayer();
    trafficLayer.setMap(map);

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
    google.maps.event.addListener(map, 'rightclick', function(event) {
        addMarker(event.latLng, map);
    }); 
}
function addMarker(location, map) {
        var text;
        if (markers.length >= labels.length) {
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }
            markers = [];
            text = document.getElementById("origen");
            text.value = "";
            text = document.getElementById("destino");
            text.value = "";
            return;
        }
        if (labelIndex % labels.length == 0) {
            text = document.getElementById("origen");
            text.value = location;
        } else {
            text = document.getElementById("destino");
            text.value = location;
        }
        var marker = new google.maps.Marker({
            position: location,
            label: labels[labelIndex++ % labels.length],
            map: map
        });
        markers.push(marker);
    }