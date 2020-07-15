package com.konglingzhan.manager;

import com.konglingzhan.manager.param.TestVo;
import com.konglingzhan.manager.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@SpringBootTest
class ManagerApplicationTests {

    @Test
    void contextLoads() {
        Dog d = new Dog();
        System.out.println(d);
        change(d);
        System.out.println(d);

    }

    void change (Dog dog){
        System.out.println("kkkk=" + dog);
        dog = new Dog();
        System.out.println(dog);
    }

    @Test
    void test1(){
        Set<Integer> test = new HashSet<>();
        int a = 1;
        int b = 8;
        int c = 3;

        test.add(a);
        test.add(b);
        test.add(c);

        //遍历集合test   利用foreach遍历          //输出结果：1   3   8
        for (Integer value : test) {
            System.out.print(value+" ");
        }
    }

    @Test
    public void test2(){
        // 创建 HashMap 对象 Sites
        HashMap<Integer, String> Sites = new HashMap<Integer, String>();
        // 添加键值对
        Sites.put(1, "Google");
        Sites.put(2, "Runoob");
        Sites.put(3, "Taobao");
        Sites.put(4, "Zhihu");
        Sites.put(4, "konglll");
        System.out.println(Sites.get(4));
    }
}
class Dog{

}