package com.konglingzhan.manager.util;

import com.konglingzhan.manager.common.domain.router.VueRouter;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    /**
     * 构造前端路由
     **/
    public static <T> ArrayList<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes){
        if(routes == null) return null;

        ArrayList<VueRouter<T>> list = new ArrayList<>();
        List<VueRouter<T>> topRoutes = new ArrayList<>();

        routes.forEach(route -> {
            String parentId = route.getParentId();
            if(parentId == null || parentId.equals("0")){
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null)
                        parent.initChildren();
                    parent.getChildren().add(route);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });

        VueRouter<T> router = new VueRouter<>();
        router.setName("系统主页");
        router.setComponent("Layout");
        router.setPath("/");
        router.setRedirect("/home");
        router.setIcon("home");
        router.setChildren(topRoutes);
        list.add(router);

        VueRouter<T> root = new VueRouter<>();
        root.setName("404");
        root.setComponent("error/404");
        root.setPath("*");
        list.add(root);

        return list;
    }
}
