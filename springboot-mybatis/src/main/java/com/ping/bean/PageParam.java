package com.ping.bean;

/**
 * @version $Id PageParam.java, v 1.0 2019-06-06 18:22 zsp $$
 * @author: zhangsp
 */

public class PageParam {
    /**
     *  起始行
     */
    private int beginLine;
    private Integer pageSize = 3;
    /**
     * / 当前页
     */
    private Integer currentPage=0;

    public int getBeginLine() {
        return pageSize*currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "beginLine=" + beginLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
