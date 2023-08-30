package service;

import model.Course;
import org.springframework.stereotype.Service;
import repository.CourseRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void insert(Course toInsert) {
        try {
            this.courseRepository.insertCourse(toInsert);
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when inserting this course");
        }
    }

    public List<Course> findAllCourse() {
        try {
            return courseRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all courses");
        }
    }

    public List<Course> serviceFindCourseById(int id) {
        try {
            return courseRepository.findCourseById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching courses with id : " +id);
        }
    }

    public List<Course> serviceToUpdateCourse(Course course) {
        try {
            return courseRepository.updateCourse(course);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when updating course");
        }
    }

    public void serviceToDeleteCourseById(int id) {
        try {
            courseRepository.deleteCourseById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when deleting course");
        }
    }
}
