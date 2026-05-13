package com.test.GymManager.controller;// package com.test.GymManager.controller;
//
import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.Course;
import com.test.GymManager.service.impl.CourseServiceImpl;
import com.test.GymManager.service.impl.ReservationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
@Api(value = "课程控制类", tags = {"关于课程的CourseController"})
public class CourseController {
    //调用业务层
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private ReservationServiceImpl reservationService;
    //显示所有的课程信息
    @GetMapping("/showcourses")
    @ApiOperation(value = "显示所有课程", notes = "查询所有可用课程")
    public String showCourseList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {

        PageInfo<Course> pageInfo = courseService.getData(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("courses", pageInfo.getList()); // 关键：将分页数据绑定到 courses
        // System.out.println("pageInfo:" +pageInfo.getList());
        return "courseList";

    }
    //点击预约按钮，跳转到预约界面
    @GetMapping("/reserve/{courseId}")
    @ApiOperation(value = "预约课程页面", notes = "跳转到课程预约页面")
    public String showReservePage(@PathVariable Long courseId, Model m) {
        Course course = courseService.getCourseById(courseId);
        m.addAttribute("course", course);
        // 模拟当前登录会员ID（实际应从Session获取）
        m.addAttribute("memberId", 123L);
        return "courseReserve";
    }
    //提交课程预约
    @PostMapping("/reserve")
    @ResponseBody
    @ApiOperation(value = "提交课程预约", notes = "处理课程预约请求")
    public boolean reserveCourse(Long courseId, Long memberId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null || course.getCurrentCount() >= course.getMaxCapacity()) {
            return false;
        }
        // 创建预约记录（假设 reservationService 已正确实现）
        boolean reservationSuccess = reservationService.createReservation(memberId, courseId);
        if (!reservationSuccess) {
            return false;
        }
        // 更新课程当前人数（关键：通过 MyBatis-Plus 提供的 updateById 方法）
        course.setCurrentCount(course.getCurrentCount() + 1);
        return courseService.updateBy(course)>0;
    }
}