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
            case "LOAD":
                loadStudent(request,response);
                break;
            case "UPDATE":
                updateStudent(request,response);
                break;
            case "DELETE":
                deleteStudent(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }

    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentDataUtil.deleteStudent(Integer.parseInt(request.getParameter("id")));

        listStudents(request,response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        student.setId(Integer.parseInt(request.getParameter("id")));
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));

        studentDataUtil.updateStudent(student);
        listStudents(request,response);
    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("id");

        request.setAttribute("student",studentDataUtil.getStudent(studentId));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update-student-form.jsp");
        requestDispatcher.forward(request,response);
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
