package com.example.common.utils;

/**
 * @author moc
 * @date 2018/11/26 3:44 PM
 */
public class StringHelper {

    public static Boolean isEmpty(Object param) {
        if (null == param || "".equals(param.toString().trim()) || "null".equalsIgnoreCase(param.toString().trim()))
            return true;
        return false;
    }

    public static boolean equal(Object o1, Object o2) {
        if (isEmpty(o1) && isEmpty(o2))
            return true;
        else if (isEmpty(o1) || isEmpty(o2))
            return false;
        else if (valueOf(o1).equals(valueOf(o2)))
            return true;
        else
            return false;
    }

    public static String valueOf(Object object) {
        if (isEmpty(object))
            return null;
        String returnStr = object.toString();
        if (isEmpty(returnStr))
            return null;
        return returnStr.trim();
    }

    public static String concatToString(Object... arrays) {
        StringBuffer sb = new StringBuffer();
        for (Object obj : arrays) {
            if (isEmpty(obj)) {
                continue;
            } else {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

}
