package com.test.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Course {
    private Long courseId;       // 课程ID
    private String courseName;   // 课程名称
    private Long coachId;        // 教练ID
    private LocalDateTime scheduleTime; // 上课时间
    private Integer maxCapacity; // 最大人数
    private Integer currentCount; // 当前人数
    @TableField(exist = false) // 明确声明非数据库字段
    private String coachName;        // 教练名称
    @TableField(exist = false) // 明确声明非数据库字段
    private String scheduleTime2;
    // @TableField(exist = false)
    // private Coach coach;         // 关联教练信息
    @TableField(exist = false)
    private Boolean isReserved;  //判断是否被预约
    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
        this.setScheduleTime2();
    }
    public void setScheduleTime2() {
        LocalDateTime scheduleTime1 = this.getScheduleTime();
        // 2. 转换为 ZonedDateTime（这里使用系统默认时区）
        ZonedDateTime zonedDateTime = scheduleTime1.atZone(ZoneId.systemDefault());
        // 4. 转换为 Date
        Date date = Date.from(zonedDateTime.toInstant());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        this.scheduleTime2 = format;
    }
}

