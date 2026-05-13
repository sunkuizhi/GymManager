package com.test.GymManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.Course;
import com.test.GymManager.entity.Reservation;
import com.test.GymManager.mapper.CourseMapper;
import com.test.GymManager.mapper.ReservationMapper;
import com.test.GymManager.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseMapper.selectById(courseId);
    }
    public int updateBy(Course course) {
        return courseMapper.manualUpdateById(course.getCourseId(),course.getCurrentCount());
    }
    @Override
    public List<Course> getAvailableCourses() {
        // 查询当前人数小于最大容量且未过期的课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        // Date date = new Date();
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // String format = simpleDateFormat.format(date);
        wrapper.lt("current_count", "max_capacity")
                .gt("schedule_time", LocalDateTime.now());
        return courseMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public boolean reserveCourse(Long courseId, Long memberId) {
        // 检查课程是否存在且可预约
        Course course = getById(courseId);
        if (course == null || course.getCurrentCount() >= course.getMaxCapacity()) {
            return false;
        }
        // 创建预约记录
        Reservation reservation = new Reservation();
        reservation.setMemberId(memberId);
        reservation.setCourseId(courseId);
        reservation.setStatus(0); // 0-未签到
        boolean success = reservationService.createReservation(reservation);
        // 更新课程当前人数
        if (success) {
            course.setCurrentCount(course.getCurrentCount() + 1);
            return updateById(course);
        }
        return updateById(course); // 来自 BaseMapper 的 updateById
    }
    public PageInfo<Course> getData(int currentPage, int size) {
        //注意代码顺序,PageHelper分页插件,接管mybatisplus从数据库中查询到的数据
        //PageHelper要卸载userMapper.selAll()逻辑的前面,不然不生效
        PageHelper.startPage(currentPage,size);
        List<Course> emps = courseMapper.getAllCourses();
        for (Course course:emps){
            Long courseId = course.getCourseId();
        }
        //进行分页:含页码number,数据的总体条数total,分页后的数据List
        PageInfo<Course> pageInfo = new PageInfo<>(emps);
        return pageInfo;
    }

}

