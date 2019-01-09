package com.worry.common.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * TODO:Http请求工具类
 * Created by 李小龙 on 17/8/1.
 */
public class HttpConnectionUtil {
    private HttpConnectionUtil(){};
    /**
     * 默认编码格式
     */
    public static String DEFAULT_CHARSET = "UTF-8";
    /**
     * 连接超时时间
     */
    private static int CONNECTION_TIMEOUT = 20000;
    private static String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
    
    /**
     * 执行Get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        HttpURLConnection connection = getConnection(url);
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        return readBuffered(inputStream);
    }

    /**
     * 执行Post请求
     * @param url
     * @param paramter
     * @return
     * @throws Exception
     */
    public static String doPost(String url,String paramter) throws Exception {
        HttpURLConnection connection = getConnection(url);
        connection.setRequestMethod("POST");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(paramter.getBytes(DEFAULT_CHARSET));
        outputStream.flush();
        outputStream.close();
        InputStream inputStream = connection.getInputStream();
        return readBuffered(inputStream);
    }
    /**
     * 获取Http连接
     * @param urlPath
     * @return
     * @throws Exception
     */
    private static HttpURLConnection getConnection(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        URLConnection urlConnection = url.openConnection();
        //设置超时时间
        urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        //设置请求不适用缓存
        urlConnection.setDefaultUseCaches(false);
        //打开流开关
        urlConnection.setDoOutput(true);
        //设置服务器返回JSON字符串
        urlConnection.setRequestProperty("Content-type",DEFAULT_CONTENT_TYPE);
        return (HttpURLConnection) urlConnection;

    }

    /**
     * 读取服务器返回内容
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readBuffered(InputStream inputStream) throws IOException {
        byte[] by = IOUtils.toByteArray(inputStream);
        return new String(by,DEFAULT_CHARSET);
    }
}