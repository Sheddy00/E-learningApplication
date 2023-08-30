package service;

import lombok.Getter;
import model.Student;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

@Getter
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void insert(Student toInsert) {
        try {
            this.studentRepository.insertStudent(toInsert);
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when inserting this student");
        }
    }

    public List<Student> findAllStudent() {
        try {
            return studentRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all students");
        }
    }

    public List<Student> serviceFindStudentById(int id) {
        try {
            return studentRepository.findStudentById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching students with id : " +id);
        }
    }

    public List<Student> serviceToUpdateStudent(Student student) {
        try {
            return studentRepository.updateStudent(student);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when updating student");
        }
    }

    public void serviceToDeleteStudentById(int id) {
        try {
            studentRepository.deleteStudentById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when deleting student");
        }
    }
}
