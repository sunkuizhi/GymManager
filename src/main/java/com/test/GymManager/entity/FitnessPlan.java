package com.test.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FitnessPlan {
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Long planId;         // 计划ID
    private Long memberId;       // 所属会员ID
    private String target;       // 健身目标（减脂/增肌）
    private Integer duration;    // 周期（周）
    @TableField("plan_details")
    private String planDetails;  // 详细计划
}