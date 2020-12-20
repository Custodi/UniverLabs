<%@ page import="sun.applet.Main" %>
<%@ page import="MainPackage.MainClass" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab #8 | Responce</title>
    <style>
        h1 {
            font-family: 'Trebuchet MS';
        }
        p {
            font-family: 'Trebuchet MS';
        }
    </style>
</head>
<body>
    <% String key = request.getParameter("input_key"); %>
    <h1>Value of <%= key%> key</h1>
    <hr><hr><hr>
    <%
        MainClass map = new MainClass();
        String res = map.main_map.get(Integer.parseInt(key));
        if(res == null) res = "There is no value belongs to your key!";
    else res = "Value of your key is " + res;
    %>
    <p><%=res%></p>
</body>
</html>
