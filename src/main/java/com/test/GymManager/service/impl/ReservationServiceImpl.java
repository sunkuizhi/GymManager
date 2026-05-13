package com.test.GymManager.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.Member;
import com.test.GymManager.entity.Reservation;
import com.test.GymManager.mapper.MemberMapper;
import com.test.GymManager.mapper.ReservationMapper;
import com.test.GymManager.service.IReservationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Position;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements IReservationService {
    @Autowired
    ReservationMapper reservationMapper;
    public Reservation getReservationById(Long reservationId){
        return reservationMapper.selectById(reservationId);
    }
    public PageInfo<Reservation> getData(int currentPage, int size) {
        //注意代码顺序,PageHelper分页插件,接管mybatisplus从数据库中查询到的数据
        //PageHelper要卸载userMapper.selAll()逻辑的前面,不然不生效
        PageHelper.startPage(currentPage,size);
        List<Reservation> reservations = reservationMapper.getAllReservations();
        for (Reservation reservation:reservations){
            Long reservationId = reservation.getReservationId();

        }
        // // 新增日志：打印 memberName 和 courseName 验证是否获取成功
        // for (Reservation reservation : reservations) {
        //     System.out.println("会员姓名: " + reservation.getMemberName() + ", 课程名称: " + reservation.getCourseName());
        // }
        //进行分页:含页码number,数据的总体条数total,分页后的数据List
        PageInfo<Reservation> pageInfo = new PageInfo<>(reservations);
        return pageInfo;
    }
    @Override
    @Transactional
    public boolean createReservation(Reservation reservation) {
        // 设置默认预约时间为当前时间
        if (reservation.getReserveTime() == null) {
            reservation.setReserveTime(LocalDateTime.now());
        }
        // 插入预约记录
        return save(reservation);
    }

    // 新增重载方法（处理 memberId 和 courseId）
    @Override
    @Transactional
    public boolean createReservation(Long memberId, Long courseId) {
        // 1. 创建 Reservation 实体并设置参数
        Reservation reservation = new Reservation();
        reservation.setMemberId(memberId);
        reservation.setCourseId(courseId);
        reservation.setReserveTime(LocalDateTime.now()); // 设置默认预约时间
        reservation.setStatus(0); // 设置默认状态（如未签到）
        // return createReservation(reservation);
        return save(reservation);
    }
    @Override
    @Transactional
    public boolean checkIn(Long reservationId) {
        // 检查预约是否存在
        Reservation reservation = reservationMapper.selectById(reservationId);
        if (reservation == null || reservation.getStatus() == 1) {
            return false;
        }
        // 更新状态为已签到
        reservation.setStatus(1);
        return reservationMapper.updateById(reservationId)>0;
    }
    public PageInfo<Reservation> getDataBy(int currentPage, int size,Reservation reservation) {
        //注意代码顺序,PageHelper分页插件,接管mybatisplus从数据库中查询到的数据
        //PageHelper要卸载userMapper.selAll()逻辑的前面,不然不生效
        PageHelper.startPage(currentPage,size);
        //获取所有的员工信息select * from employee
        List<Reservation> reservations = reservationMapper.getReservations(reservation);
        //进行分页:含页码number,数据的总体条数total,分页后的数据List
        PageInfo<Reservation> pageInfo = new PageInfo<>(reservations);
        return pageInfo;
    }
}