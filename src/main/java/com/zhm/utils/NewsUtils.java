package com.zhm.utils;

import java.io.*;
import java.net.URL;

/**
 * Created by zhm on 16-12-30.
 */
public enum NewsUtils {
    INSTANCE;

    /**
     * @param httpUrl
     *            :请求接口
     * @return 返回结果
     */
    public  String request(String httpUrl) {
        try {
            URL u=new URL(httpUrl);
            InputStream in=u.openStream();
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            try {
                byte buf[]=new byte[1024];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    out.write(buf, 0, read);
                }
            }  finally {
                if (in != null) {
                    in.close();
                }
            }
            byte b[]=out.toByteArray( );
            return new String(b,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
