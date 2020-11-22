package com.konglingzhan.manager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;

/**
 * 分页类封装
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {
    private List<T> data = Lists.newArrayList();

    private HashMap<String,?> obj;

    private int total = 0;

    private HashMap<String,?> result;

    public void setData(List<T> data) {
        this.data = data;
    }

}
