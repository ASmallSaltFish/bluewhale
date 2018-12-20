package com.qs.bluewhale.utils;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

public class CommonUtil {

    /**
     * 将set、list字符串集合元素转换为字符串数组，使用指定分割符分割
     */
    public static String collectionToString(Collection<String> collection, String split) {
        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            sb.append(str).append(",");
        }

        String result = sb.toString();
        return result.substring(0, result.lastIndexOf(split));
    }

    /**
     * 将指定路径图片文件转化为base64编码字符串
     */
    public static String imageToBase64String(String imageFilePath) {
        File file = new File(imageFilePath);
        FileImageInputStream inputStream = null;
        try {
            inputStream = new FileImageInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
