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
        String command = request.getParameter("command");
        if (command == null){
            command = "LIST";
        }

        switch (command){
            case "ADD":
                addStudent(request, response);
                break;
            case "LIST":
                listStudents(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }

    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student(firstName,lastName,email);
        studentDataUtil.addStudent(student);

        listStudents(request,response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Student> students = studentDataUtil.getStudents();
        request.setAttribute("students",students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/studentView.jsp");
        dispatcher.forward(request,response);
    }
}
