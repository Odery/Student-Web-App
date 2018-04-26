<%@ page import ="java.util.List, web_student.*"%>
<% List<Student> students = (List<Student>) request.getAttribute("students");%>
<html>
<head>
    <title>
        Student View
    </title>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <link type="text/css" rel="stylesheet" href="css/main.css">
</head>
<body>
<div id = "wrapper">
    <div id = "header">
        <h1>Student List</h1>
    </div>
</div>
<div id = "container">
    <div id = "content">
        <table>
            <tr>
                <th class="text-left">ID</th>
                <th class="text-left">First Name</th>
                <th class="text-left">Last Name</th>
                <th class="text-left">Email</th>
            </tr>
            <%for(Student student: students){%>
            <tr>
                <td class="text-left"><%=student.getId()%></td>
                <td class="text-left"><%=student.getFirstName()%></td>
                <td class="text-left"><%=student.getLastName()%></td>
                <td class="text-left"><%=student.getEmail()%></td>
            </tr>
            <%}%>
        </table>
    </div>
</div>
</body>
</html>