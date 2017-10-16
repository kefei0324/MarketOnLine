package org.feona.net;

import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class NetUtil {

    private static final String TAG = NetUtil.class.getSimpleName();
    private static final int THOUSAND = 1000;
    private static final int READ_TIMEOUT = 4 * THOUSAND;
    private static final int CONNECT_TIMEOUT = 4 * THOUSAND;

    public static void get(String spec, NetListener listener) {
        HttpURLConnection connection = null;
        Log.d(TAG, "get [spec " + spec + "]");
        try {
            connection = initConnection(spec, "GET");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = inputStream.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                listener.onResult(baos.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void post(String spec, Map<String, String> params, NetListener listener) {
        HttpURLConnection connection = null;
        Log.d(TAG, "post [spec " + spec + "]");
        try {
            connection = initConnection(spec, "POST");
            connection.setDoOutput(true);
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(createParams(params));
            printWriter.flush();
            // 开始获取数据
            if (connection.getResponseCode() == 200) {
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len;
                byte[] arr = new byte[1024];
                while ((len = bis.read(arr)) != -1) {
                    bos.write(arr, 0, len);
                    bos.flush();
                }
                bos.close();
                listener.onResult(bos.toString("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String createParams(Map<String, String> params) {
        Iterator<String> iterator = params.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = params.get(key);
            Log.d(TAG, "createParams [params key " + key + " value " + value + "]");
            sb.append(key).append("=").append(value).append("&");
        }
        return sb.toString();
    }

    private static HttpURLConnection initConnection(String spec, String method) throws IOException {
        Log.d(TAG, "initConnection [spec " + spec + " method " + method + " ]");
        URL url = new URL(spec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.215 Safari/535.1");
        connection.setRequestProperty("accept-language", "zh-CN");
        return connection;
    }

}
