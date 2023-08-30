package service;

import lombok.Getter;
import model.Student;
import model.Teacher;
import org.springframework.stereotype.Service;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.sql.SQLException;
import java.util.List;

@Getter
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void insert(Teacher toInsert) {
        try {
            this.teacherRepository.insertTeacher(toInsert);
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when inserting this student");
        }
    }

    public List<Teacher> findAllTeacher() {
        try {
            return teacherRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all students");
        }
    }

    public List<Teacher> serviceFindTeacherById(int id) {
        try {
            return teacherRepository.findTeacherById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching students with id : " +id);
        }
    }

    public List<Teacher> serviceToUpdateTeacher(Teacher teacher) {
        try {
            return teacherRepository.updateTeacher(teacher);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when updating student");
        }
    }

    public void serviceToDeleteTeacherById(int id) {
        try {
            teacherRepository.deleteTeacherById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when deleting student");
        }
    }
}
