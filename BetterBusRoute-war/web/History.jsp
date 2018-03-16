<%-- 
    Document   : History
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
        <title>Best Bus Route </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bestbusrouteadaptive.css"/>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <script async="" src="js/map.js"></script>
    </head>
    <body>
        <header> 
            <div id="logo">
                <h1 class="toRigth"> 
                    <img src="images/bus.png" id="logoImg"/>
                    <span tabindex="0"> Mejor Ruta GDL </span> 
                </h1> 
            </div>
            <div id="topMenu" > <%@include file="TopMenu.jsp" %></div>
        </header>
        <div id='main'>
            <article id = "content"> 
                <div class="error"> 

                </div>
                <table>
                    <thead> </thead>
                </table>
            </article>
            <nav> 

            </nav>
            <aside>

            </aside>
        </div>
        <footer></footer>
    </body>
</html>
