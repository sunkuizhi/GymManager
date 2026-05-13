package com.test.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reservation {
    @TableId(value = "reservation_id", type = IdType.AUTO) // 关键：声明自增主键
    private Long reservationId; // 预约ID
    private Long memberId;       // 预约会员ID
    private Long courseId;       // 课程ID
    private LocalDateTime reserveTime; // 预约时间
    private Integer status;      // 状态（0未签到/1已签到）

    @TableField(exist = false)
    private String reserveTime2; // 预约时间
    // 新增：非数据库字段，用于存储会员姓名和课程名称
    @TableField(exist = false)
    private String memberName;        // 会员姓名（来自 member 表）
    @TableField(exist = false)
    private String courseName;        // 课程名称（来自 course 表）
    public void setReserveTime(LocalDateTime reserveTime) {
        this.reserveTime = reserveTime;
        this.setReserveTime2();
    }
    public void setReserveTime2() {
        LocalDateTime reserveTime1 = this.getReserveTime();
        // 2. 转换为 ZonedDateTime（这里使用系统默认时区）
        ZonedDateTime zonedDateTime = reserveTime1.atZone(ZoneId.systemDefault());
        // 4. 转换为 Date
        Date date = Date.from(zonedDateTime.toInstant());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        this.reserveTime2 = format;
    }
}

