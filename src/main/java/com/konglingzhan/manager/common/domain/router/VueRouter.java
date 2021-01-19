package com.konglingzhan.manager.common.domain.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String parentId;

    private String path;

    private String name;

    private String component;

    private String icon;

    private String redirect;

    private List<VueRouter<T>> children;

    @JsonIgnore
    private boolean hasParent = false;

    @JsonIgnore
    private boolean hasChildren = false;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
