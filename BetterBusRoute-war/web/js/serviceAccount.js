/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var userURL = "./rs/user";
$(document).ready(function() {
    $("#addUser").click(function(event) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: userURL + '/add',
            dataType: "text",
            data: getUserForm(),
            success: userAdded,
            error: function(jqXHR, textStatus, errorThrown) {
                alert('add error: ' + textStatus);
            }
        });
    });

    function userAdded(data) {
        alert(data);
    }
    function getUserForm() {
        return JSON.stringify({
            "firstName": $('#firstName').val(),
            "lastName": $('#lastName').val(),
            "email": $('#email').val(),
            "password": $('#password').val()
        });
    }
});


