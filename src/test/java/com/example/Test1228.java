package com.example;

import com.example.pojo.UrlInput;
import com.example.testURL1228.BaseUrlReq;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Test1228 extends BaseUrlReq{
    public static String HttpPost (String api, String json) {
        String res = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL url = new URL("http://114.116.49.58:8080" + api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true); // 获取返回数据需要设置为true 默认false
            conn.setDoInput(true); // 发送数据需要设置为true 默认false
            conn.setRequestMethod("POST");
            conn.connect();
            out = new PrintWriter(conn.getOutputStream());
            out.write(json);
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
        return res;
    }

    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("starttime","2018-01-01 00:00:00");
//            obj.put("endtime","2018-01-01 03:00:00");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        // 建议启动Java时添加-Dfile.encoding=UTF-8命令行。
//        String res1 = HttpPost ("/runanalyze", "{}"); // 运行计算
//        System.out.println(res1);
//        System.out.println("-----------------");
////        String res2 = HttpPost ("/getanalyzeresult", "{\"starttime\":\"2018-01-01 00:00:00\",\"endtime\":\"2018-01-01 03:00:00\"}"); // 运行计算
//        String res2 = HttpPost ("/getanalyzeresult", obj.toString()); // 运行计算
//        System.out.println(res2);
//        new Test1228().dealFile();
//        UrlInput input = new UrlInput();
//        input.setUrl("/sendmetercurrent");
//        input.setCdata("dsfd");
//        HttpPost(input);

//        test allUri
        new Test1228().testAllUrl();
    }

    public void testUrl(){
        Map<String, Map<String,String>> mapUrl = new HashMap<>();
        mapUrl.put("/sendmetercurrent",new HashMap<String,String>(){{
            put("\"devmac\"","\"2018-01-01 00:00:00\"");
            put("\"metermac\"","\"2018-01-01 00:00:00\"");
            put("\"adata\"","\"2018-01-01 00:00:00\"");
            put("\"bdata\"","\"2018-01-01 00:00:00\"");
            put("\"cdata\"","\"2018-01-01 00:00:00\"");
            put("\"datetim\"","\"2018-12-28 00:00:00\"");
        }});
        JSONObject obj = new JSONObject();
        try {
            obj.put("devmac","2018-01-01 00:00:00");
            obj.put("metermac","2018-01-01 00:00:00");
            obj.put("adata","2018-01-01 00:00:00");
            obj.put("bdata","2018-01-01 00:00:00");
            obj.put("cdata","2018-01-01 03:00:00");
            obj.put("datetim","2018-12-01 03:00:00");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(obj.toString());

        for (Map.Entry<String, Map<String,String>> entry : mapUrl.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
            String res2 = HttpPost (entry.getKey(), entry.getValue().toString().replace("=",":")); // 运行计算
            System.out.println(res2);
        }
    }


    public void dealFile(){
        String path = "E:\\华云\\资料\\data-in.txt";
        String path_out = "E:\\华云\\资料\\data_out.txt";
        BufferedReader in = null;
        PrintWriter out = null;
        StringBuffer insb= new StringBuffer();
        StringBuffer outsb= new StringBuffer();
        int state = 0;
        String content = null;
        List<String> inList = new ArrayList<>();
        List<String> outList = new ArrayList<>();
        Set<String> inset = new HashSet<>();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            out = new PrintWriter(new FileOutputStream(path_out));
            String line;
            while ((line = in.readLine()) != null) {
                if("".equals(line)){
                    state=1;
                    continue;
                }
//                inList.add(line);
//                out.append(line);
                inset.add(line);
            }

            System.out.println(inset.toString());
            Iterator it = inset.iterator();
            while (it.hasNext()){
//                out.append("private String "+String.valueOf(it.next())+";\r\n");
                content = String.valueOf(it.next());
                char firstLetter = Character.toUpperCase(content.charAt(0));

                out.append("input.set"+firstLetter+content.substring(1,content.length())+"(\"2018-12-28 00:00:00\");\r\n");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out !=null)
                out.close();
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }


    public void testAllUrl(){
        UrlInput input = new UrlInput();
        input.setStealingparam18("2018-12-28 00:00:00");
        input.setStealingparam17("2018-12-28 00:00:00");
        input.setStealingparam19("2018-12-28 00:00:00");
        input.setData("2018-12-28 00:00:00");
        input.setPt("2018-12-28 00:00:00");
        input.setBabnormal("2018-12-28 00:00:00");
        input.setStarttime("2018-12-28 00:00:00");
        input.setAdata("2018-12-28 00:00:00");
        input.setFamilyid("2018-12-28 00:00:00");
        input.setDatetime("2018-12-28 00:00:00");
        input.setStealingparam21("2018-12-28 00:00:00");
        input.setStealingparam20("2018-12-28 00:00:00");
        input.setStealingparam23("2018-12-28 00:00:00");
        input.setStealingparam22("2018-12-28 00:00:00");
        input.setStealingparam25("2018-12-28 00:00:00");
        input.setStealingparam24("2018-12-28 00:00:00");
        input.setBdata("2018-12-28 00:00:00");
        input.setStealingparam26("2018-12-28 00:00:00");
        input.setThreeparam2("2018-12-28 00:00:00");
        input.setThreeparam1("2018-12-28 00:00:00");
        input.setCdata("2018-12-28 00:00:00");
        input.setElectrictype("2018-12-28 00:00:00");
        input.setPort("2018-12-28 00:00:00");
        input.setDistrict("2018-12-28 00:00:00");
        input.setStealingparam10("2018-12-28 00:00:00");
        input.setStealingparam12("2018-12-28 00:00:00");
        input.setStealingparam11("2018-12-28 00:00:00");
        input.setTwoparam1("2018-12-28 00:00:00");
        input.setStealingparam14("2018-12-28 00:00:00");
        input.setTwoparam2("2018-12-28 00:00:00");
        input.setStealingparam13("2018-12-28 00:00:00");
        input.setStealingparam16("2018-12-28 00:00:00");
        input.setStealingparam15("2018-12-28 00:00:00");
        input.setAabnormal("2018-12-28 00:00:00");
        input.setConnectmode("2018-12-28 00:00:00");
        input.setCity("2018-12-28 00:00:00");
        input.setOneparam1("2018-12-28 00:00:00");
        input.setFamilyname("2018-12-28 00:00:00");
        input.setOnelen("2018-12-28 00:00:00");
        input.setMetermac("2018-12-28 00:00:00");
        input.setResult("2018-12-28 00:00:00");
        input.setCabnormal("2018-12-28 00:00:00");
        input.setEtype("2018-12-28 00:00:00");
        input.setEvent("2018-12-28 00:00:00");
        input.setTable("2018-12-28 00:00:00");
        input.setThreelen("2018-12-28 00:00:00");
        input.setFourparam1("2018-12-28 00:00:00");
        input.setStealingparam3("2018-12-28 00:00:00");
        input.setFourparam2("2018-12-28 00:00:00");
        input.setStealingparam2("2018-12-28 00:00:00");
        input.setIndustrytype("2018-12-28 00:00:00");
        input.setStealingparam5("2018-12-28 00:00:00");
        input.setStealingparam4("2018-12-28 00:00:00");
        input.setFourlen("2018-12-28 00:00:00");
        input.setStealingparam7("2018-12-28 00:00:00");
        input.setStealingparam6("2018-12-28 00:00:00");
        input.setFourparam3("2018-12-28 00:00:00");
        input.setStealingparam9("2018-12-28 00:00:00");
        input.setStealingparam8("2018-12-28 00:00:00");
        input.setEndtime("2018-12-28 00:00:00");
        input.setStealingparam1("2018-12-28 00:00:00");
        input.setDevmac("2018-12-28 00:00:00");
        input.setCt("2018-12-28 00:00:00");
        input.setTwolen("2018-12-28 00:00:00");
        input.setRatio("2018-12-28 00:00:00");
        String[] uri = new String[]{
                "/sendmetercurrent",
                "/sendmeterenergy",
                "/senddevcurrent",
                "/sendmeterevent",
                "/senddevevent",
                "/sendmeterevent",
                "/sendusermsg",
                "/runanalyze",
                "/getanalyzeresult",
                "/getanalyzeraw",
                "/analyzeandgetresult",
                "/analyzeandgetraw",
                "/getstealinganalyzeresult",
                "/getstealinganalyzeresult",
                "/getconfigdata",
                "/setconfigdata"
                ,"/deletedata"
        };

        for (String str:uri) {
            input.setUrl(str);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method:"+str+"------------------");
            HttpPost(input);
        }

    }
}
