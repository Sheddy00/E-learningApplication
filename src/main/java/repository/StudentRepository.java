package repository;

import lombok.Getter;
import model.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class StudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertStudent(Student toInsert) throws SQLException {
        String sql = "INSERT INTO student (firstname,lastname,mail,password,gender,actif) VALUES (?,?,?,?,?,?);";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toInsert.getFirstName());
            statement.setString(2, toInsert.getLastName());
            statement.setString(3, toInsert.getMail());
            statement.setString(4, toInsert.getPassword());
            statement.setString(5, toInsert.getGender());
            statement.setBoolean(6, toInsert.isActive());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in inserting student");
        }
    }

    public List<Student> findAll() throws SQLException{
        List<Student> allStudents = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try(PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ExtractFromResultset(allStudents, sql, statement);
        } catch (SQLException e) {
            System.out.println("Error on finding Students");
        }
        return allStudents;
    }

    private void ExtractFromResultset(List<Student> allStudent, String sql,PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            String gender = resultSet.getString("gender");
            boolean active = resultSet.getBoolean("actif");
            Student student = new Student(id, firstName, lastName, mail, password, gender, active);
            allStudent.add(student);
        }
    }

    public List<Student> findStudentById(int idValue) throws SQLException {
        List<Student> aStudent = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE id = ? ";

        try(PreparedStatement statement = getConnection().prepareStatement(sql))  {
            statement.setInt(1, idValue);
            ExtractFromResultset(aStudent, sql, statement);
        } catch (SQLException e) {
            System.out.println("Error on finding a student");
        }
        return aStudent;
    }

    public List<Student> updateStudent(Student student) throws SQLException{
        String sql = "UPDATE student SET firstname = ?, lastname = ?, mail = ?, password = ?, gender = ?, actif = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getMail());
            statement.setString(4, student.getPassword());
            statement.setString(5, student.getGender());
            statement.setBoolean(6, student.isActive());
            statement.setInt(7, student.getId());
            int rowUpdating = statement.executeUpdate();
            if (rowUpdating > 0) {
                System.out.println("Update successfuly");
            } else {
                System.out.println("Not found");
            }
        } catch (SQLException e) {
            System.out.println("Error on updating student");
        }
        return null;
    }

    public void deleteStudentById(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            int studentDelete = statement.executeUpdate();
            if (studentDelete > 0) {
                System.out.println("Deleting successfuly");
            } else {
                System.out.println("Not found");
            }
        }  catch (SQLException e) {
            System.out.println("Error on deleting student");
        }
    }
}
