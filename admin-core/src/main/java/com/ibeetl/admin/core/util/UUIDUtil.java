package com.ibeetl.admin.core.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid() {
        long uuid = UUID.randomUUID().getMostSignificantBits();
        return Math.abs(uuid)+"";
    }
}
