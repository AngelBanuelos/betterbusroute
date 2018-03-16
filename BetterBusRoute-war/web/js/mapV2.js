/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var labels = ['Origen', 'Destino'];
var labelIndex = 0;
var markers = [];
var map;
function initialize() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: new google.maps.LatLng(20.657286, -103.3399879),
        mapTypeControl: false

    });
    var iconBase = 'http://maps.google.com/mapfiles/kml/';
    var icons = {
        origin: {
            name: 'Origin',
            icon: iconBase + 'paddle/O.png',
            obj: '<input type="text" class="input" placeholder="Origin" id="origen"/><select id="savePlaceOrigin" class="input"></select>'
        },
        destination: {
            name: 'Destination',
            icon: iconBase + 'paddle/D.png',
            obj: '<input type="text" class="input" placeholder="Destination" id="destino" /><select id="savePlaceDestination" class="input" ></select>'
        },
        budget: {
            name: 'Budget',
            icon: iconBase + 'shapes/dollar.png',
            obj: '<input type="text" class="input" placeholder="Budget" id="budget" />'
        },
        ratio: {
            name: 'Ratio',
            icon: iconBase + 'shapes/ruler.png',
            obj: '<select id="ratio" class="input"><option>100</option><option>200</option><option>300</option><option>400</option><option>500</option><option>600</option><option>700</option><option>800</option><option>1000</option></select>'
        },
        search: {
            name: 'Search',
            icon: iconBase + 'pal4/icon8.png',
            obj: '<input type="button" class="input" id="searchButton" value="Search"/>'
        },
        clear: {
            name: 'Clear',
            icon: 'https://cdn4.iconfinder.com/data/icons/32x32-free-design-icons/32/Clear.png',
            obj: '<input type="button" class="input" id="clearButton" value="Clear"/>'
        }
    };


    var legend = document.getElementById('legend');
    var smallLegend = document.getElementById('small-legend');

    for (var key in icons) {
        var type = icons[key];
        var name = type.name;
        var icon = type.icon;
        var obj = type.obj
        var div = document.createElement('div');
        div.innerHTML = '<img src="' + icon + '" width="32" height="32"> ' + name + '<div class="row">' + obj + '</div>';
        legend.appendChild(div);
    }

    map.controls[google.maps.ControlPosition.TOP_LEFT].push(smallLegend);
    map.controls[google.maps.ControlPosition.LEFT_TOP].push(legend);
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

google.maps.event.addDomListener(window, 'load', initialize);
