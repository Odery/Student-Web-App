package web_student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/test")
public class ConnectionTest extends HttpServlet {

    @Resource(name = "jdbc/tomcat_connection")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet =statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()){
                out.println(resultSet.getString("email"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
