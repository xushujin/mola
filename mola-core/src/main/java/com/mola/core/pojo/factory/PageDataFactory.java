package com.mola.core.pojo.factory;


import com.mola.core.pojo.PageData;
import com.github.pagehelper.PageInfo;

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
