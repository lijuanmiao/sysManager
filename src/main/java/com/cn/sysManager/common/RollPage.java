package com.cn.sysManager.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lijm on 2017-12-15.
 * 分页对象
 */
public class RollPage<T> implements Serializable{

  private static final Integer DefaultPageSize = 20;
  private Integer pageSize = 0;
  private Integer pageNum = 1;
  private Integer pageSum = 0;
  private Integer recordSum = 0;

  private List<T> recordList;

  /*private List<T> footer;

    public List<T> getFooter() {
        return footer;
    }

    public void setFooter(List<T> footer) {
        this.footer = footer;
    }*/

    public RollPage() {
        pageSize = DefaultPageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSum() {
        if (this.pageSum == 0) {
            this.pageSum = (int) Math.ceil(this.recordSum / this.pageSize)
                    + ((this.recordSum % this.pageSize == 0) ? 0 : 1);
        }
        return pageSum;
    }

    public void setPageSum(Integer pageSum) {
        this.pageSum = pageSum;
    }

    public Integer getRecordSum() {
        return recordSum;
    }

    public void setRecordSum(Integer recordSum) {
        this.recordSum = recordSum;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }
}