package com.itheima.pojo;

import java.util.List;

/**
 * @author tuuli
 * @time Created in 2022/11/11 19:48
 * @description
 */
public class PageBean<T> {

    //总记录数
    private int totalCount;
    //当前页数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
