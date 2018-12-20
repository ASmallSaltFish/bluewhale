package com.qs.bluewhale.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommonUtil {

    public static String collectionToString(Collection<String> collection, String split) {
        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            sb.append(str).append(",");
        }

        String result = sb.toString();
        return result.substring(0, result.lastIndexOf(split));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3");
        System.out.println(collectionToString(list,","));
    }
}
