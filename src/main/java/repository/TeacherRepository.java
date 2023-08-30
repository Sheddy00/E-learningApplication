package repository;


import lombok.Getter;
import model.Student;
import model.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class TeacherRepository {
    private final Connection connection;

    public TeacherRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertTeacher(Teacher toInsert) throws SQLException {
        String sql = "INSERT INTO teacher (firstName,lastName,mail,password,gender,availability) VALUES (?,?,?,?,?,?);";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toInsert.getFirstName());
            statement.setString(2, toInsert.getLastName());
            statement.setString(3, toInsert.getMail());
            statement.setString(4, toInsert.getPassword());
            statement.setString(5, toInsert.getGender());
            statement.setBoolean(6, toInsert.isAvailability());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in inserting teacher");
        }
    }

    public List<Teacher> findAll() throws SQLException{
        List<Teacher> allTeachers = new ArrayList<>();
        String sql = "SELECT * FROM teacher";

        try(PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ExtractFromResultset(allTeachers, sql, statement);
        } catch (SQLException e) {
            System.out.println("Error on finding teachers");
        }
        return allTeachers;
    }

    private void ExtractFromResultset(List<Teacher> allTeacher, String sql,PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            String gender = resultSet.getString("gender");
            boolean availability = resultSet.getBoolean("availability");
            Teacher teacher = new Teacher(id, firstName, lastName, mail, password, gender, availability);
            allTeacher.add(teacher);
        }
    }

    public List<Teacher> findTeacherById(int idValue) throws SQLException {
        List<Teacher> aTeacher = new ArrayList<>();
        String sql = "SELECT * FROM teacher WHERE id = ? ";

        try(PreparedStatement statement = getConnection().prepareStatement(sql))  {
            statement.setInt(1, idValue);
            ExtractFromResultset(aTeacher, sql, statement);
        } catch (SQLException e) {
            System.out.println("Error on finding a teacher");
        }
        return aTeacher;
    }

    public List<Teacher> updateTeacher(Teacher teacher) throws SQLException{
        String sql = "UPDATE teacher SET firstname = ?, lastname = ?, mail = ?, password = ?, gender = ?, availability = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getMail());
            statement.setString(4, teacher.getPassword());
            statement.setString(5, teacher.getGender());
            statement.setBoolean(6, teacher.isAvailability());
            statement.setInt(7, teacher.getId());
            int rowUpdating = statement.executeUpdate();
            if (rowUpdating > 0) {
                System.out.println("Update successfuly");
            } else {
                System.out.println("Not found");
            }
        } catch (SQLException e) {
            System.out.println("Error on updating teacher");
        }
        return null;
    }

    public void deleteTeacherById(int id) throws SQLException {
        String sql = "DELETE FROM teacher WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            int teacherDelete = statement.executeUpdate();
            if (teacherDelete > 0) {
                System.out.println("Deleting successfuly");
            } else {
                System.out.println("Not found");
            }
        }  catch (SQLException e) {
            System.out.println("Error on deleting teacher");
        }
    }
}