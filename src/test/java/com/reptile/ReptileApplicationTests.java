package com.reptile;

import com.reptile.util.GeneratIdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReptileApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 0; i <100 ; i++) {
            System.out.println(GeneratIdUtil.getGeneratID());
        }
    }

}
