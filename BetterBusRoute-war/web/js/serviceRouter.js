/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var routerURL = "./rs/router";
var savedPlaceURL = "./rs/places";
var busPath = [];
var busIndex = 0;
var lineSymbol;
var busesCoord = [];
var colors = ['#481d76', '#ffde6b', '#1d7276', '#85dda8', '#383a35', '#67a0e1'];
var colorIndex = 0;
var paths = [];

function paintRouteOnMap() {
    lineSymbol = {
        path: google.maps.SymbolPath.FORWARD_OPEN_ARROW
    };
    busPath.push(new google.maps.Polyline({
        path: busesCoord,
//        icons: [{
//                icon: lineSymbol,
//                offset: '10%',
//                repeat: '100px'
//            }],
        geodesic: true,
        strokeColor: colors[colorIndex++ % colors.length],
        strokeOpacity: 0.75,
        strokeWeight: 8
    }));
    busPath[busIndex++].setMap(map);
}

$(document).ready(function () {
    getAll();
    $("#clearButton").click(function (event) {
        clear(event);
    });
    $("#searchButton").click(function (event) {
        $('#renderPath option').remove();
        $('#renderPath').hide();
        $.ajax({
            type: 'GET',
            url: createNearbyURL(),
            dataType: "json",
            success: renderNeaby
        });
    });
    function createNearbyURL() {
        var origen = $("#origen").val().replace("(", "").replace(")", "").split(",");
        var destination = $("#destino").val().replace("(", "").replace(")", "").split(",");
        destination = origen;
        return routerURL + '/paintNearbyRoutes/' + origen[0].trim() + "/" + origen[1].trim() + "/"
                + destination[0].trim() + "/" + destination[1].trim() + "/" + $("#ratio").val().trim() + "/" + $("#budget").val().trim();
    }
    $(document).on('click', '#nerbyRoutes a', function (e) {
        clear(e);
        switch (e.which) {
            case 1:
                alert('Left Mouse button pressed.');
                var routeID = $(this).data('identity');
                $.ajax({
                    type: 'GET',
                    url: createFasterURL(routeID),
                    dataType: "json",
                    success: renderPath2
                });
                break;
            case 2:
                alert('Middle Mouse button pressed.');
                break;
            case 3:
                alert('Right Mouse button pressed.');
                var routeID = $(this).data('identity');
                $.ajax({
                    type: 'GET',
                    url: createFasterSerialURL(routeID),
                    dataType: "json",
                    success: renderPath2
                });
                break;
            default:
                alert('You have a strange Mouse!');
        }
//        var routeID = $(this).data('identity');
//        $.ajax({
//            type: 'GET',
//            url: createFasterURL(routeID),
//            dataType: "json",
//            success: renderPath2
//        });
    });
//    $(document).on('click', '#nerbyRoutes a', function(e) {
//        clear(e);
//        var routeID = $(this).data('identity');
//        $.ajax({
//            type: 'GET',
//            url: createFasterSerialURL(routeID),
//            dataType: "json",
//            success: renderPath2
//        });
//    });

//    $('#nerbyRoutes a').mousedown(function (event) {
//        switch (event.which) {
//            case 1:
//                alert('Left Mouse button pressed.');
//                break;
//            case 2:
//                alert('Middle Mouse button pressed.');
//                break;
//            case 3:
//                alert('Right Mouse button pressed.');
//                break;
//            default:
//                alert('You have a strange Mouse!');
//        }
//    });

    function createFasterURL(routeID) {
        var origen = $("#origen").val().replace("(", "").replace(")", "").split(",");
        var destination = $("#destino").val().replace("(", "").replace(")", "").split(",");
        return routerURL + '/paintFasterBusRoute/' + origen[0].trim() + "/" + origen[1].trim() + "/"
                + destination[0].trim() + "/" + destination[1].trim() + "/" + $("#ratio").val().trim() + "/" + routeID;
    }
    //paintFasterBusRouteSerial
    function createFasterSerialURL(routeID) {
        var origen = $("#origen").val().replace("(", "").replace(")", "").split(",");
        var destination = $("#destino").val().replace("(", "").replace(")", "").split(",");
        return routerURL + '/paintFasterBusRouteSerial/' + origen[0].trim() + "/" + origen[1].trim() + "/"
                + destination[0].trim() + "/" + destination[1].trim() + "/" + $("#ratio").val().trim() + "/" + routeID;
    }
    $("#startButton").click(function (event) {
        clear(event);
        $('#nerbyRoutes li').each(function (index) {
            var routeID = this.id;
            $.ajax({
                type: 'GET',
                url: createExistURL(routeID),
                dataType: "text",
                success: function (data) {
                    if (data == "false") {
                        $('#' + routeID).remove();
                    }
                }
            });
        });
    });
    function clear(event) {
        for (var i = 0; i < busPath.length; i++) {
            busPath[i].setMap(null);
        }
        busIndex = 0;
        busPath = [];
        event.preventDefault();
    }
    function getAll() {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: savedPlaceURL + '/getall/' + $("#UserName").val(),
            dataType: "json",
            success: fillCombo,
            error: function (jqXHR, textStatus, errorThrown) {
//                alert('g error: ' + textStatus);
            }
        });
    }

    function fillCombo(data) {
        var list = data == null ? [] : (data instanceof Array ? data : [data]);

        $('#savePlaceDestination option').remove();
        $("#savePlaceDestination").append('<option>Select<option>');
        $('#savePlaceOrigin option').remove();
        $("#savePlaceOrigin").append('<option>Select<option>');
        $.each(list, function (index, savePlace) {
            $("#savePlaceDestination").append('<option id="' + index + '" value = "'
                    + savePlace.latitude + "," + savePlace.longitude + '" >'
                    + savePlace.placeName + '</option>');
            $("#savePlaceOrigin").append('<option id="' + index + '" value = "'
                    + savePlace.latitude + "," + savePlace.longitude + '" >'
                    + savePlace.placeName + '</option>');
        });
    }
    $("#savePlaceOrigin").change(function () {
        if (this.value !== "" && this.value !== "Select") {
            var location = this.value.split(",");
            var loc = new google.maps.LatLng(location[0], location[1]);
            addMarker(loc, map);
        }
    });
    $("#savePlaceDestination").change(function () {
        if (this.value !== "" && this.value !== "Select") {
            var location = this.value.split(",");
            var loc = new google.maps.LatLng(location[0], location[1]);
            addMarker(loc, map);
        }
    });
    $('#renderPath').change(function () {
        clearGraphMarkers();
        if (this.value !== "") {
            renderPath(paths[this.value]);
        }
    });
});

function createExistURL(routeID) {
    var origen = $("#origen").val().replace("(", "").replace(")", "").split(",");
    var destination = $("#destino").val().replace("(", "").replace(")", "").split(",");
    return routerURL + '/existsRoute/' + origen[0].trim() + "/" + origen[1].trim() + "/"
            + destination[0].trim() + "/" + destination[1].trim() + "/" + $("#ratio").val().trim() + "/" + routeID;
}

function renderNeaby(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#nerbyRoutes li').remove();
    $.each(list, function (index, route) {
        renderRoute(route.path1);
        renderRoute(route.path2);
        $("#nerbyRoutes").append('<li id="' + route.routeID + '"><a href="#" data-identity="'
                + route.routeID + '">' + route.routeName + '</a></li>');
    });
}

function renderRoute(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    busesCoord = [];
    $.each(list, function (index, point) {
        busesCoord.push(point);
    });
    paintRouteOnMap();
}

function renderPath(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#nerbyRoutes li').remove();
    $.each(list, function (index, path) {
        renderRoute(path.path)
        $("#nerbyRoutes").append('<li id="' + index + '"> Path ' + index + '');
        $("#nerbyRoutes").append('<li>Distance: ' + path.distance + '</li>');
        $("#nerbyRoutes").append('<li>Busses: ' + path.busses + '</li>');
        $.each(path.routes, function (i, route) {
            $("#nerbyRoutes").append('<li>Route: ' + route.name + '</li>');
        });
        $("#nerbyRoutes").append('</li>');
        $.each(path.points, function (i, point) {
            var tmp = {lat: point.lat, lng: point.lng};
            var loc = new google.maps.LatLng(tmp);
            addGraphMarkers(loc, map);
        });
    });
}

function renderPath2(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#renderPath').show();
    paths = list;
    $.each(list, function (index, path) {
        $('#renderPath').append('<option id = ' + index + ' value=' + index + '> Path ' + index + ' dis: ' + path.distance + '<option>');
    });
}

var graphLabels = '01234';
var graphIndex = 0;
var graphMarkers = [];
// Adds a marker to the map.
function addGraphMarkers(location, map) {
    var marker = new google.maps.Marker({
        position: location,
        label: graphLabels[graphIndex++ % graphLabels.length],
        map: map
    });
    graphMarkers.push(marker);
}

function clearGraphMarkers() {
    for (var i = 0; i < graphMarkers.length; i++) {
        graphMarkers[i].setMap(null);
    }
    graphMarkers = [];
    graphLabels = 0;
}


