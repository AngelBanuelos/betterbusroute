<%-- 
    Document   : SavedPlace
    Created on : Nov 24, 2015, 9:13:36 PM
    Author     : Angel.Sahagun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="Language.jsp" %>
        <title><%=BbrMain_title%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bestbusrouteadaptive.css"/>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <script async="" src="js/savedPlace.js"></script>
        <script async="" src="js/serviceSavedPlace.js"></script>
    </head>
    <body>
        <header> 
            <div id="logo">
                <h1 class="toRigth"> 
                    <img src="images/bus.png" id="logoImg"/>
                    <span tabindex="0"> <%=BbrMain_title%> </span> 
                </h1> 
            </div>
            <div id="topMenu" > <%@include file="TopMenu.jsp" %></div>
        </header>
        <div id='main'>
            <article id = "content"> 
                <script 
                    async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDa6pOSMzTvSD5wyxF7pu62u45EAi16hp4&signed_in=true&callback=initMap">
                </script>
            </article>
            <nav> 
                <div class="error"> 
                    <h5><p id="errorMessage"> </p></h5>
                </div>
                <div class="row"><p tabindex="0"> <%=BbrNewPlace_title%> </p></div>
                <form>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewPlace_user%> </p>
                        <input type="text" class="input" disabled="true" id="email" value="ing.angel.banuelos@gmail.com"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewPlace_name%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewPlace_name%>" id="name"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewPlace_location%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewPlace_location%>" id="location" />
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewPlace_ratio%> </p>
                        <select id="ratio" class="input"> 
                            <option>100</option>  
                            <option>200</option> 
                            <option>300</option>
                            <option>400</option>
                            <option>500</option>
                            <option>600</option>
                            <option>700</option>
                            <option>800</option>
                            <option>1000</option>
                        </select>
                    </div>
                    <div class="row">
                        <p tabindex="0"> Add </p> 
                        <input type="button" class="input" id="addPlace" value="<%=BbrNewPlace_addPlace%>"/>
                    </div>
                </form>
            </nav>
            <aside>
                <ul id="savedPlacesByUser">

                </ul>

            </aside>
        </div>
        <footer></footer>
    </body>
</html>