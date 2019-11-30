package com.whitenight.gate.base;

import java.io.Serializable;
import java.util.List;

public class Page<E> implements Serializable {

    /**
     * 当前页数
     */
    private int currentPage = 0;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总记录数
     */
    private long totalNumber;
    /**
     * 数据集
     */
    private List<E> data;

}
