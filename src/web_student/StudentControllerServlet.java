package web_student;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    @Resource(name = "jdbc/tomcat_connection")
    private DataSource dataSource;

    private StudentDataUtil studentDataUtil;

    @Override
    public void init() throws ServletException {
        studentDataUtil = new StudentDataUtil(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listStudents(request,response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Student> students = studentDataUtil.getStudents();
        request.setAttribute("students",students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/studentView.jsp");
        dispatcher.forward(request,response);
    }
}
