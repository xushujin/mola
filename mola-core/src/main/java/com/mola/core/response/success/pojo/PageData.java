package com.mola.core.response.success.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author hatim
 */
@Data
@Builder
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageData<T> {

    /**
     * 查询列表数据
     */
    @Builder.Default
    private List<T> list = new ArrayList<>();
    /**
     * 查询条件
     */
    @JsonIgnore
    private T query;
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 定义初始值
     *
     * @return
     */
    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    /**
     * 定义初始值
     *
     * @return
     */
    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }
}
