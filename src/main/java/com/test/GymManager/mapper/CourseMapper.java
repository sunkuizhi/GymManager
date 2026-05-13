package com.test.GymManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.GymManager.entity.Course;
import com.test.GymManager.entity.Member;
import com.test.GymManager.entity.Reservation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {
    @Override
    int insert(Course Course);
    // 联合教练表和课程表查询课程列表
    @Select("SELECT cc.*, c.name AS coachName " +
            "FROM course cc " +
            "LEFT JOIN coach c ON cc.coach_id = c.coach_id")
    public List<Course> getAllCourses();
    // // 根据传入的课程Id删除课程
    // @Delete("DELETE FROM course WHERE course_id = #{courseId}")
    // int deleteByCourseId(@Param("courseId") Long courseId);
    // @Select("select * from course where course_id=#{param1}")
    // public Course getCourse(@Param("courseId")Long courseId);
    // public int updateCourse(Course course);
    @Select("select * from course WHERE course_id = #{courseId}")
    public Course selectById(@Param("courseId") Long courseId);

    @Update("UPDATE course SET current_count = #{currentCount} WHERE course_id = #{courseId}")
    int manualUpdateById(@Param("courseId") Long courseId, @Param("currentCount") Integer currentCount);
}
