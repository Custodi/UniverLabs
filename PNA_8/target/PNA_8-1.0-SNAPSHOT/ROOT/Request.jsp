<%@ page contentType="text/html;    charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab #8 | Request</title>
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
<form action = "ROOT/Responce.jsp" method = "POST">
    <h1 font="Trebuchet MS">Enter key of a collection</h1>
    <hr>
    <p>Key: <input type = "text" name = "input_key">
        <input type = "submit" value = "Get a value" />
    </p>

</form>

</body>
</html>