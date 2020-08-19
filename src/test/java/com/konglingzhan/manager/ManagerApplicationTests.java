package com.konglingzhan.manager;

import com.konglingzhan.manager.param.TestVo;
import com.konglingzhan.manager.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.*;

@SpringBootTest
class ManagerApplicationTests {
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

    @Test
    public void f10001(){
        int[] arr = {1,2,3,4,5,6};
//        for(int i = 0;i<arr.length;i++){
//            System.out.println(arr[i]);
//        }
        for(int item: arr){
            System.out.println(item);
        }
        List list = new ArrayList();
        list.add("1");
        list.add("1");
        list.toArray();
        System.out.println(list.toString());
    }

    @Test
    public static void main ( String[] args )
    {

        int[] myArray = {1, 2, 3, 4, 5};
        ChangeIt.doIt( myArray );
        for(int j=0; j<myArray.length; j++)
            System.out.print( myArray[j] + " " );
    }
}

class ChangeIt
{
    static void doIt( int[] z )
    {
        z = new int[]{20,10}; // 开辟了新的内存空间（堆内存，栈内存），切断了与原来的地址联系
//        z = null;

    }
}