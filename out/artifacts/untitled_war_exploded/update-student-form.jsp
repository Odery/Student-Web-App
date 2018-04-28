<html>
<head>
    <title>Add Student</title>
    <link type="text/css" rel="stylesheet" href="css/main.css">
</head>
<body>
<div id = "wrapper" align="center">
    <div id = "header">
        <h1>Update Student</h1>
    </div>

    <div id="container">
        <form action = "StudentControllerServlet" method="GET">

            <input type="hidden" name = "command" value="UPDATE"/>
            <input type="hidden" name = "id" value="${student.id}"/>

            <table class="table-fillAdd">
                <tr>
                    <td><label>First Name: </label></td>
                    <td><input type="text" name = "firstName" value="${student.firstName}" required/></td>
                </tr>
                <tr>
                    <td><label>Last Name: </label></td>
                    <td><input type="text" name = "lastName" value="${student.lastName}" required/></td>
                </tr>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="text" name = "email" value="${student.email}" required/></td>
                </tr>
            </table>
            <br/><br/>
            <input type="submit" value="Save"
                   class="button" style="background-color:#42cc8c;"/>
            <input type="button" value="Back" onclick = "window.location.href = 'StudentControllerServlet'; return false;"
                   class="button" style="background-color:#c5d2d6;"/>
        </form>
    </div>
</div>
</body>
</html>