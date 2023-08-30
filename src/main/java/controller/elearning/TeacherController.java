package controller.elearning;

import model.Teacher;
import org.springframework.web.bind.annotation.*;
import service.TeacherService;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService; }

    @GetMapping({"/teacher/","/teacher/{id}"})
    public List<Teacher> getAllTeacher(@PathVariable(required = false) String id) {
        return id == null ? teacherService.findAllTeacher() : teacherService.serviceFindTeacherById(Integer.parseInt(id));
    }

    @PostMapping({"/addTeacher"})
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.insert(teacher);
    }

    @PutMapping({"/updateTeacher"})
    public List<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.serviceToUpdateTeacher(teacher);
    }

    @DeleteMapping({"/deleteTeacher/{id}"})
    public void deleteTeacherById(@PathVariable int id) {
        teacherService.serviceToDeleteTeacherById(id);
    }
}
