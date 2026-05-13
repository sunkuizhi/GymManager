package com.test.GymManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Coach {
    private Long coachId;        // 教练ID
    private String name;         // 姓名
    private String specialty;   // 擅长领域（瑜伽/力量训练）
    private Integer workYears;   // 从业年限
    // private Course course;      //负责的课程
}