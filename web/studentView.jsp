<%@ page import ="java.util.List, web_student.*"%>
<html>
<head>
    <title>
        Student View
    </title>
</head>
<body>
<%List<Student> students = (List<Student>) request.getAttribute("students");%>
<%=students%>
</body>
</html>