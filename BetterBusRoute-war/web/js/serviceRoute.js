/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var routeURL = "./rs/route";
$(document).ready(function() {
    $("#addRoute").click(function(event) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: routeURL + '/add',
            dataType: "text",
            data: getRouteForm(),
            success: routeAdded,
            error: function(jqXHR, textStatus, errorThrown) {
                alert('add error: ' + textStatus);
            }
        });
    });

    function routeAdded(data) {
        alert(data);
    }
    function getRouteForm() {
        return JSON.stringify({
            "name": $("#name").val(),
            "path1": $("#path1").val(),
            "path2": $('#path2').val()
        });
    }
});