package web_student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
    private DataSource dataSource;

    public StudentDataUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));

                students.add(student);
            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        } finally {
            close(connection, statement, resultSet);
        }
        return students;
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public void addStudent(Student student) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO student (first_name,last_name,email) VALUE (?,?,?);");
            statement.setString(1,student.getFirstName());
            statement.setString(2,student.getLastName());
            statement.setString(3,student.getEmail());
            statement.execute();
        }catch (SQLException exc){
            exc.getErrorCode();
        }finally {
            close(connection,statement,null);
        }
    }
}
