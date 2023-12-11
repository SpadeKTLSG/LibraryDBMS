package com.ldb.common.utils.uuid;


import java.nio.charset.StandardCharsets;

/**
 * @Author SpadeKTLSG & A
 */
public class MyUtils {

    /**
     * 将传入的String进行MD5加密, 返回Long类型的UUID
     *
     * @param args UUID参照对象, 例如书名+作者名
     * @return 完成ID赋值的对象
     * @Author SpadeKTLSG
     * @description 新增对象需要赋值对应的主键Id属性
     * @Note 控制大小量级: 千万级 {99999999~00000001} 避免万到十万级哈希冲突
     */
    public static long generateUUID(String args) { //注意考虑到重名的情况, 传入的args还要加上随机数

        byte[] bookNameBytes = (args + Math.random()).getBytes(StandardCharsets.UTF_8);//构造字节数组
        long uuid = (long) UUID.nameUUIDFromBytes(bookNameBytes).hashCode();//使用若依封装的字节数组UUID进行MD5加密, 生成HashCode
        
        // 如果生成的uuid长度不等于8位, 抛弃位数或者增加位数(补零)来保障万级别的hash冲突, 同时缓解存储展示压力
        uuid = uuid > 0 ? uuid : -uuid;
        if (String.valueOf(uuid).length() > 8) {// 使用length与8比较, 如果大于8, 则截取前8位; 如果小于8, 则补零
            uuid = Long.parseLong(String.valueOf(uuid).substring(0, 8)); // 截取前8位
        } else {
            uuid = Long.parseLong(uuid + "00000000".substring(0, 8 - String.valueOf(uuid).length())); // 补零
        }
        return uuid;
    }
}
