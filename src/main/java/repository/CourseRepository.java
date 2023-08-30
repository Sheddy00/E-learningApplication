package repository;

import lombok.Getter;
import model.Course;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class CourseRepository {
    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertCourse(Course toInsert) throws SQLException {
        String sql = "INSERT INTO course (name,code,duration) VALUES (?,?,?);";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toInsert.getName());
            statement.setString(2, toInsert.getCode());
            statement.setString(3, toInsert.getDuration());

            statement.executeUpdate();
        }
    }

    public List<Course> findAll() throws SQLException{
        List<Course> allCourse = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try(PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ExtractFromResultset(allCourse, sql, statement);
        } catch (SQLException e) {
            System.out.println("error");
        }
        return allCourse;
    }

    private void ExtractFromResultset(List<Course> allCourse, String sql,PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String code = resultSet.getString("code");
            String duration = resultSet.getString("duration");
            Course course = new Course(id, name, code, duration);
            allCourse.add(course);
        }
    }

    public List<Course> findCourseById(int idValue) throws SQLException {
        List<Course> aCourse = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE id = ? ";

        try(PreparedStatement statement = getConnection().prepareStatement(sql))  {
            statement.setInt(1, idValue);
            ExtractFromResultset(aCourse, sql, statement);
        } catch (SQLException e) {
            System.out.println("Error on finding a course");
        }
        return aCourse;
    }

    public List<Course> updateCourse(Course course) throws SQLException{
        String sql = "UPDATE course SET name = ?, code = ?, duration = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getCode());
            statement.setString(3, course.getDuration());
            statement.setInt(4, course.getId());
            int rowUpdating = statement.executeUpdate();
            if (rowUpdating > 0) {
                System.out.println("Update successfuly");
            } else {
                System.out.println("Not found");
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return null;
    }

    public void deleteCourseById(int id) throws SQLException {
        String sql = "DELETE FROM course WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            int courseDelete = statement.executeUpdate();
            if (courseDelete > 0) {
                System.out.println("Deleting successfuly");
            } else {
                System.out.println("Not found");
            }
        }  catch (SQLException e) {
            System.out.println("Error");
        }
    }

}
