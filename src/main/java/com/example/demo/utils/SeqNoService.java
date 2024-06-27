package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SeqNoService {
    public static String createPlanCode(String planId) {

        // 将名称转换为字节数组
        byte[] nameBytes = planId.getBytes(StandardCharsets.UTF_8);
        // 生成名称基UUID
        String nameBasedUUID = String.valueOf(UUID.nameUUIDFromBytes(nameBytes));
        // 打印生成的UUID
        return nameBasedUUID;
    }
}
