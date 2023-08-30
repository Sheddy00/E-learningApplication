package controller.elearning;

import model.Course;
import org.springframework.web.bind.annotation.*;
import service.CourseService;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/ping")
    public String pingPong(){
        return "pong";
    }

    @GetMapping({"/course/","/course/{id}"})
    public List<Course> getAllCourse(@PathVariable (required = false) String id) {
        return id == null ? courseService.findAllCourse() : courseService.serviceFindCourseById(Integer.parseInt(id));
    }

    @PostMapping({"/addCourse"})
    public void addCourse(@RequestBody Course course) {
        courseService.insert(course);
    }

    @PutMapping({"/updateCourse"})
    public List<Course> updateCourse(@RequestBody Course course) {
        return courseService.serviceToUpdateCourse(course);
    }

    @DeleteMapping({"/deleteCourse/{id}"})
    public void deleteCourseById(@PathVariable int id) {
        courseService.serviceToDeleteCourseById(id);
    }

}
