package com.test.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.Position;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member {
    @TableId(value = "member_id", type = IdType.AUTO)
    private Long memberId;       // 会员ID（雪花ID）
    private String name;         // 姓名
    private Integer age;         // 年龄
    private String gender;       // 性别
    private String phone;        // 手机号（唯一）
    @TableField("register_date")
    private LocalDate registerDate; // 注册时间
    private BigDecimal balance;  // 账户余额
    @TableField(exist = false) // 标注非数据库字段
    private List<FitnessPlan> plans; // 关联健身计划
    @TableField(exist = false) // 标注非数据库字段
    //我的预约
    private List<Reservation> myReservation;
}



