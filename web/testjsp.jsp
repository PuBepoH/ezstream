
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World!</title>
</head>
<body>
    <h1>RESULT:<h1/>
    <h1>${answer}</h1>
    <br />
    Usage:
    <br />
    Write YOURDATA: http://localhost:8080/?type=write&data=YOURDATA
    <br />
    Read: any parameter "type" except "write" (for example no parameters at all: http://localhost:8080/)
</body>
</html>
