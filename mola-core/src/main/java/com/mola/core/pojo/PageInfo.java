package com.mola.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页信息
 *
 * @author hatim
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo {
    /**
     * 分页页码
     */
    @ApiModelProperty(value = "分页页码", example = "1")
    Integer pageNum;
    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小", example = "10")
    Integer pageSize;

    /**
     * 是否分页
     */
    @ApiModelProperty(value = "是否分页", example = "true")
    Boolean isPage;

    /**
     * 默认1,从第一页开始
     *
     * @return
     */
    public Integer getPageNum() {
        return (pageNum == null || pageNum == 0) ? 1 : pageNum;
    }

    /**
     * 默认20，最大不能超过100
     *
     * @return
     */
    public Integer getPageSize() {
        if (isPage == null || isPage) {
            pageSize = pageSize == null ? 20 : pageSize;
            return pageSize > 100 ? 100 : pageSize;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
