package com.test.GymManager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;    // 状态码：200成功，500失败
    private String message;  // 返回消息
    private T data;          // 返回数据

    // 成功静态方法（无数据）
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    // 成功静态方法（有数据）
    public static <T> Result<T> success(T data) {
        return new Result(200, "操作成功", data);
    }

    // 失败静态方法（自定义消息）
    public static Result error(String message) {
        return new Result(500, message, null);
    }

    // 失败静态方法（自定义状态码和消息）
    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }
}