
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
        <link rel="stylesheet" type="text/css" href="css/bestbusrouteadaptive.css"/>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <script async="" src="js/map.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="js/serviceRouter.js"></script>

    </head>
    <body>
        <header> 
            <div id="logo">
                <h1 class="toRigth"> 
                    <img src="images/bus.png" id="logoImg"/>
                    <span tabindex="0"><%=BbrMain_title%></span> 
                </h1> 
            </div>
            <div id="topMenu" > <%@include file="TopMenu.jsp" %>
                <input type="text" id="UserName" value="ing.angel.banuelos@gmail.com" disabled="true" hidden="true" />
            </div>
        </header>
        <div id='main'>
            <article id = "content"> 
                <script 
                    async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDa6pOSMzTvSD5wyxF7pu62u45EAi16hp4&signed_in=true&callback=initMap">
                </script>
            </article>
            <nav> 
                <div class="row"> 
                    <p tabindex="0"> <%=BbrMain_origin%> </p> 
                    <input type="text" class="input" placeholder="<%=BbrMain_origin%>" id="origen"/> 
                    <select id="savePlaceOrigin" class="input" >
                        
                    </select>
                </div>
                <div class="row"> 
                    <p tabindex="0"> <%=BbrMain_destination%> </p> 
                    <input type="text" class="input" placeholder="<%=BbrMain_destination%>" id="destino" />
                    <select id="savePlaceDestination" class="input" >
                        
                    </select>
                </div>
                <div class="row"> 
                    <p tabindex="0"> <%=BbrMain_budget%> </p>
                    <input type="text" class="input" placeholder="<%=BbrMain_budget%>" id="budget" /> 
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
                    <p tabindex="0"> <%=BbrMain_search%> </p> 
                    <input type="button" class="input" id="searchButton" value="<%=BbrMain_search%>"/>
                </div>
                <div class="row">
                    <p tabindex="0"> <%=BbrMain_clear%> </p> 
                    <input type="button" class="input" id="clearButton" value="<%=BbrMain_clear%>"/>
                </div>
            </nav>
            <aside>
                <h1>Nearby Routes</h1>
                <input type="button" class="input" id="startButton" value="Intersections"/>
                <select id="renderPath" hidden="true"> 
                    
                </select>
                <ul id="nerbyRoutes">

                </ul>

            </aside>
        </div>
        <footer></footer>
    </body>
</html>