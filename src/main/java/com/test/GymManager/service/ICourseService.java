package com.test.GymManager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.GymManager.entity.Course;
import com.test.GymManager.entity.Pages;

import java.util.List;

public interface ICourseService extends IService<Course> {
    List<Course> getAllCourses();
    Course getCourseById(Long courseId);
    List<Course> getAvailableCourses(); // 获取可预约的课程
    boolean reserveCourse(Long courseId, Long memberId); // 预约课程
}