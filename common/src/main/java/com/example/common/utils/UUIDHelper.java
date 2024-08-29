package com.example.common.utils;

import java.util.UUID;

/**
 * @author: moc
 * @date: 2018/11/8 5:57 PM
 */
public class UUIDHelper {

    /**
     * 功能描述: 获得一个32位的UUID
     * @return: java.lang.String UUID
     * @auther: WangJJ
     * @date: 2018/9/15 上午11:32
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 获得指定数目的UUID数组
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     * @auther: WangJJ
     * @date: 2018/9/15 上午11:33
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID();
        }
        return retArray;
    }

}
