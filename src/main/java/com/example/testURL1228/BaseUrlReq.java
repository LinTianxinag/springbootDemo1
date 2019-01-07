package com.example.testURL1228;

import com.example.pojo.UrlInput;
import com.example.pojo.UrlOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseUrlReq {


    public static UrlOutput HttpPost (UrlInput input) {
        if(input.getUrl()==null){
            System.out.println("url cannot be null");
            return null;
        }
        String res = "";
        StringBuffer json = new StringBuffer();
        PrintWriter out = null;
        BufferedReader in = null;
        UrlOutput output = null;
        Field[] fields = input.getClass().getDeclaredFields();
        String[] strs = new String[fields.length];
        Map<String,String> mapUrl =  new HashMap<String,String>();
        for (Field field:fields){
//            System.out.print(field.getName());
            String value = String.valueOf(getValueByName(field.getName(),input));
//            System.out.println("  value: "+value);
            if(value==null||"null".equals(value)){
                continue;
            }
            mapUrl.put("\""+field.getName()+"\"","\""+value+"\"");
        }
        String content = mapUrl.toString().replace("=",":");
        System.out.println(content);

        try {
            URL url = new URL("http://114.116.49.58:8080" + input.getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true); // 获取返回数据需要设置为true 默认false
            conn.setDoInput(true); // 发送数据需要设置为true 默认false
            conn.setRequestMethod("POST");
            conn.connect();
            out = new PrintWriter(conn.getOutputStream());
            out.write(content);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out !=null)
                out.close();
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        System.out.println(res);
        return output;
    }

    public static Object getValueByName(String fileName, Object o){
        char firstLetter = Character.toUpperCase(fileName.charAt(0));
        try {
            Method method = o.getClass().getMethod(
                    ("get"+firstLetter+fileName.substring(1,fileName.length())),
                    new Class[]{});
            return method.invoke(o,new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        UrlInput input = new UrlInput();
        input.setUrl("/sendmetercurrent");
        input.setCdata("dsfd");
        BaseUrlReq.HttpPost(input);
    }
}
