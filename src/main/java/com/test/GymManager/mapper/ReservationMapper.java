package com.test.GymManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.GymManager.entity.Course;
import com.test.GymManager.entity.Member;
import com.test.GymManager.entity.Reservation;
import com.test.GymManager.service.IReservationService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReservationMapper extends BaseMapper<Reservation> {
    // 关联 member 和 course 表查询预约列表
    @Select("SELECT r.*, m.name AS memberName, c.course_name AS courseName " +
            "FROM reservation r " +
            "LEFT JOIN member m ON r.member_id = m.member_id " +
            "LEFT JOIN course c ON r.course_id = c.course_id")
    public List<Reservation> getAllReservations();

    public List<Reservation> getReservations(Reservation reservation);
    @Update("UPDATE reservation SET status = 1 WHERE reservation_id = #{reservationId} ")
    int updateById(@Param("reservationId") Long reservationId);

    @Select("select * from reservation WHERE reservation_id = #{r}")
    public Reservation selectById(@Param("r") Long r);
}
