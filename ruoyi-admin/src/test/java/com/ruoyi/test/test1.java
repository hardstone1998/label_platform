package com.ruoyi.test;

import com.ruoyi.tool.LevenshteinDistance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author：乔石磊
 * @date：16:29 2024/1/12
 */
@SpringBootTest
public class test1 {

    @Test
    public void calculateDistance() {
        double result = LevenshteinDistance.calculateDistance("do", "dodge");
        System.out.println(result);
    }
}
