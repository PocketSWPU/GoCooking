package com.pocket.demo01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
class GoCookingApplicationTests {

    @Test
    void contextLoads() {
    }

    void test(){
        LinkedList<Integer> a = new LinkedList<>();
        a.removeFirst();
    }

}
