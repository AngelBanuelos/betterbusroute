/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var savedPlaceURL = "./rs/places";
$(document).ready(function() {
    getAll();
    $("#addPlace").click(function(event) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: savedPlaceURL + '/add',
            dataType: "text",
            data: getSavedPlaceForm(),
            success: savedPlaceAdded,
            error: function(jqXHR, textStatus, errorThrown) {
                alert('add error: ' + textStatus);
            }
        });
    });

    function savedPlaceAdded(data) {
        alert(data);
        getAll();
    }
    function getSavedPlaceForm() {
        var location = $('#location').val().replace("(", "").replace(")", "").split(",");
        var latitude = location[0];
        var longitude = location[1];

        return JSON.stringify({
            "latitude": latitude,
            "longitude": longitude,
            "email": $('#email').val(),
            "placeName": $('#name').val(),
            "ratio": $('#ratio').val()
        });
    }
    function getAll() {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: savedPlaceURL + '/getall/' + $("#email").val(),
            dataType: "json",
            success: showPlaces,
            error: function(jqXHR, textStatus, errorThrown) {
//                alert('g error: ' + textStatus);
            }
        });
    }
    function showPlaces(data) {
        var list = data == null ? [] : (data instanceof Array ? data : [data]);

        $('#savedPlacesByUser li').remove();
        $.each(list, function(index, place) {
            $('#savedPlacesByUser').append('<li><a href="#" data-identity="'
                    + place.latitude + "," + place.longitude + '">' + place.placeName + '</a></li>');
        });
    }
    $(document).on('click', '#savedPlacesByUser a', function() {
        var location = $(this).data('identity').split(",");
        var loc = new google.maps.LatLng(location[0], location[1]);
        addMarker(loc, map);
    });

});
