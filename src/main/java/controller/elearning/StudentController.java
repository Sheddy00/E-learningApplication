package controller.elearning;

import model.Student;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService; }

    @GetMapping({"/student/","/student/{id}"})
    public List<Student> getAllStudent(@PathVariable(required = false) String id) {
        return id == null ? studentService.findAllStudent() : studentService.serviceFindStudentById(Integer.parseInt(id));
    }

    @PostMapping({"/addStudent"})
    public void addStudent(@RequestBody Student student) {
        studentService.insert(student);
    }

    @PutMapping({"/updateStudent"})
    public List<Student> updateStudent(@RequestBody Student student) {
        return studentService.serviceToUpdateStudent(student);
    }

    @DeleteMapping({"/deleteStudent/{id}"})
    public void deleteStudentById(@PathVariable int id) {
        studentService.serviceToDeleteStudentById(id);
    }
}
