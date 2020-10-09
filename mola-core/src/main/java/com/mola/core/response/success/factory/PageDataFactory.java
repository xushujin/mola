package com.mola.core.response.success.factory;


import com.github.pagehelper.PageInfo;
import com.mola.core.response.success.pojo.PageData;

/**
 * PageDataFactory
 *
 * @author hatim
 */
public class PageDataFactory {
    public static <T> PageData<T> create(PageInfo<T> pageInfo) {
        if (pageInfo == null) {
            return PageData.<T>builder().build();
        }
        return PageData.<T>builder()
                .list(pageInfo.getList())
                .total(pageInfo.getTotal())
                .pageNum(pageInfo.getPageNum())
                .pageSize(pageInfo.getPageSize())
                .pages(pageInfo.getPages()).build();
    }
}
