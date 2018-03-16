<%-- 
    Document   : AddRoute
    Created on : Nov 24, 2015, 9:12:54 PM
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
        <script async="" src="js/mapAddRoute.js"></script>
        <script async="" src="js/serviceRoute.js"></script>
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
                <form>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewRoute_title%> </p> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewRoute_name%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewRoute_name%>" id="name"/> 

                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewRoute_path1%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewRoute_path1%>" id="path1" />
                        <input type="button" value="Select" onclick="selectPath1()"/>
                        <input type="button" value="clear" onclick="cleanPath2(1)"/>
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewRoute_path2%> </p>
                        <input type="text" class="input" placeholder="<%=BbrNewRoute_path2%>" id="path2" />
                        <input type="button" value="Select" onclick="selectPath2()"/>
                        <input type="button" value="clear" onclick="cleanPath2(2)"/>
                    </div>
                    <div class="row">
                        <p tabindex="0"> <%=BbrNewRoute_addRoute%> </p> 
                        <input type="button" class="input" id="addRoute" value="<%=BbrNewRoute_addRoute%>"/>
                    </div>
                </form>
            </nav>
            <aside>

            </aside>
        </div>
        <footer></footer>
    </body>
</html>