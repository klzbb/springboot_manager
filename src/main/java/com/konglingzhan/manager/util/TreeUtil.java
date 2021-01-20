package com.konglingzhan.manager.util;

import com.konglingzhan.manager.common.domain.router.RouterMeta;
import com.konglingzhan.manager.common.domain.router.VueRouter;
import org.apache.ibatis.reflection.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    /**
     * 构造前端路由
     **/
    public static <T> ArrayList<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes){
        if(routes == null) return null;

        ArrayList<VueRouter<T>> list = new ArrayList<>();
        routes.forEach(route -> {
            String parentId = route.getParentId();
            if(parentId == null || parentId.equals("0")){
                list.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null) parent.initChildren();
                    parent.getChildren().add(route);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });
        return list;
    }
}
