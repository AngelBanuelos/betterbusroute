<%-- 
    Document   : NewAccont
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
    <%@ page import = "java.util.ResourceBundle" %> 
    <head>
        <%@include file="Language.jsp" %>
        <title><%=BbrMain_title%> </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bestbusrouteadaptive.css"/>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <script src="js/serviceAccount.js"></script>
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
                <form>
                    <h1><%=BbrNewAccount_title%></h1>
                    <div class="error"> 
                        <h5><p></p></h5>
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewAccount_firstName%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewAccount_firstName%>" id="firstName"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewAccount_lastName%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewAccount_lastName%>" id="lastName"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewAccount_email%> </p> 
                        <input type="text" class="input" placeholder="<%=BbrNewAccount_email%>" id="email"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewAccount_password%> </p> 
                        <input type="password" class="input" placeholder="<%=BbrNewAccount_password%>" id="password"/> 
                    </div>
                    <div class="row"> 
                        <p tabindex="0"> <%=BbrNewAccount_addUser%> </p> 
                        <input type="button" class="input" id="addUser" value="<%=BbrNewAccount_addUser%>"/> 
                    </div>
                </form>
            </article>
            <nav> 

            </nav>
            <aside>

            </aside>
        </div>
        <footer></footer>
    </body>
</html>
