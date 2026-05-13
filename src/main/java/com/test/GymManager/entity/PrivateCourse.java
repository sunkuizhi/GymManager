package com.test.GymManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PrivateCourse{
    private Long privateId;      // 私教ID
    private Long memberId;       // 学员ID
    private Long coachId;        // 教练ID
    private LocalDateTime startTime; // 开始时间
    private Integer totalSessions; // 总课时
    private Integer completed;   // 已完成课时
}

