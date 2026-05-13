package com.test.GymManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 封装控制层的数据响应到前端
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pages<T> {
    private List<T> content;       // 分页数据
    private long totalElements;    // 总记录数
    private int totalPages;        // 总页数
    private int pageNumber;        // 当前页码
    private int pageSize;          // 每页大小
    private boolean hasNext;       // 是否有下一页
    private boolean hasPrevious;   // 是否有上一页
}

