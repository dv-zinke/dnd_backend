package com.dnd.jachwirus.write.utils;

import java.util.UUID;

public class UuidUtil {
    public String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
