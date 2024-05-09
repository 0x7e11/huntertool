package com.huntertool;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.huntertool.bean.JsonBean;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.UUID;


public class NetworkTools {
    public static String search(int version,String key,String search_base64url,String start_time,String end_time,int page,int page_size,int is_web,String port_filter,int status_code) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        String url = "";
        String response_content = "";
        try {
            if (version == 1) {
                url = "https://inner.hunter.qianxin-inc.cn/openApi/search?api-key=" + key + "&search=" + search_base64url + "&start_time=" + start_time + "&end_time=" + end_time + "&page=" + page + "&page_size=" + page_size + "&is_web=" + is_web + "&port_filter=" + port_filter + "&status_code=";//官方接口中status_code参数无作用
            } else {
                url = "https://hunter.qianxin.com/openApi/search?api-key=" + key + "&search=" + search_base64url + "&start_time=" + start_time + "&end_time=" + end_time + "&page=" + page + "&page_size=" + page_size + "&is_web=" + is_web + "&port_filter=" + port_filter + "&status_code=";
            }
            URL hunter_url = new URL(url);
            System.out.println(url);
            HttpsURLConnection connection = (HttpsURLConnection) hunter_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));//没有StandardCharsets.UTF_8则会导致需要指定编码启动
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            response_content = response.toString();
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response_content;
    }

    public static String search_pl1(int version,String key,String search_content,String start_time,String end_time,int is_web,String port_filter) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        String url = "";
        String response_content = "";
        String request_data = "";
        try{
            if(version == 1){
                url = "https://inner.hunter.qianxin-inc.cn/openApi/search/batch?api-key=" + key + "&start_time=" + start_time + "&end_time=" + end_time + "&is_web=" + is_web + "&port_filter=" + port_filter +"&status_code=";
            }else {
                url = "https://hunter.qianxin.com/openApi/search/batch?api-key=" + key + "&start_time=" + start_time + "&end_time=" + end_time + "&is_web=" + is_web + "&port_filter=" + port_filter +"&status_code=";
            }
            URL hunter_url = new URL(url);
            System.out.println(url);
            HttpsURLConnection connection = (HttpsURLConnection) hunter_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);//设置输出流为可用状态
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------219775075535691657702284372080");
            request_data = "-----------------------------219775075535691657702284372080" +
                    "\r\n"+
                    "Content-Disposition: form-data; name=\"file\"; filename=\"batch_file_template.csv\"" +
                    "\r\n"+
                    "Content-Type: application/vnd.ms-excel" +
                    "\r\n"+"\r\n"+
                    search_content+
                    "\r\n"+"\r\n"+
                    "-----------------------------219775075535691657702284372080--";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request_data.getBytes());
            outputStream.flush();
            outputStream.close();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String inputline;
            StringBuilder response = new StringBuilder();
            while ((inputline = in.readLine()) != null){
                response.append(inputline);
            }
            in.close();
            response_content = response.toString();
            System.out.println(response.toString());
            connection.disconnect();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response_content;
    }

    public static String task_progress(int version,String key,int taskid){
        String url = "";
        String response_content = "";
        String request_data = "";
        try{
            if(version == 1){
                url = "https://inner.hunter.qianxin-inc.cn/openApi/search/batch/" + taskid + "?api-key=" + key;
            }else {
                url = "https://hunter.qianxin.com/openApi/search/batch/" + taskid + "?api-key=" + key;
            }
            URL hunter_url = new URL(url);
            System.out.println(url);
            HttpsURLConnection connection = (HttpsURLConnection) hunter_url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            response_content = response.toString();
            connection.disconnect();
            System.out.println(response_content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response_content;
    }
    public static void download(int version,String key,int taskid,String filename)throws Exception{
        String url = "";
        String response_content = "";
        String request_data = "";
        if(version == 1){
            url = "https://inner.hunter.qianxin-inc.cn/openApi/search/download/" + taskid + "?api-key=" + key;
        }else {
            url = "https://hunter.qianxin.com/openApi/search/download/" + taskid + "?api-key=" + key;
        }
        URL hunter_url = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) hunter_url.openConnection();
        // 设置 User-Agent 避免被拦截
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
        InputStream inputStream = connection.getInputStream();
        byte[] buff = new byte[1024*10];
        OutputStream out = new FileOutputStream(new File(filename));
        int len;
        while((len = inputStream.read(buff)) != -1) {
            out.write(buff, 0, len);
            out.flush();
        }

        out.close();
        inputStream.close();
        connection.disconnect();

    }
    public static void pl2(int version,String API_KEY,String originalText,String start_time,String end_time,int is_web,String port_filter){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("export_pl_" + UUID.randomUUID().toString() + ".csv"))) {
            writer.write("IP, 域名, 端口, 服务, title, 组件（版本）, ICP, 地理位置, 状态码, ISP\n");
            try(BufferedReader reader = new BufferedReader(new StringReader(originalText))){
                String line;
                while ((line = reader.readLine()) != null ){
                    System.out.println(line);
                    String encodedText = Base64.getUrlEncoder().encodeToString(line.getBytes(StandardCharsets.UTF_8));
                    try {
                        String response_content_pl2 = NetworkTools.search(version, API_KEY, encodedText, start_time, end_time, 1, 100, is_web, port_filter,200);
                        System.out.println(response_content_pl2);
                        ObservableList<JsonBean> result_list_pl2 = OtherTools.data_processing(response_content_pl2);
                        for (int i = 0; i < result_list_pl2.size(); i++) {
                            JsonBean jsonBean = result_list_pl2.get(i);
                            writer.write(jsonBean.getIp() + "," + jsonBean.getDomain() + "," + jsonBean.getPort() + "," + jsonBean.getBase_protocol() + "," + jsonBean.getWeb_title() + "," + jsonBean.getComponent() + "," + jsonBean.getCompany() + "," + jsonBean.getCountry() + "," + jsonBean.getStatus_code() + "," + jsonBean.getIsp() +"\n");
                        }
                        Thread.sleep(2000);

                        JSONObject json_data = JSONObject.parseObject(response_content_pl2).getJSONObject("data");
                        int total = json_data.getInteger("total");
                        int page_total = total / 100 + 1;
                        if(page_total > 1){
                            for (int s = 2; s <= page_total; s++){
                                try {
                                    String response_content_pl2s = NetworkTools.search(version, API_KEY, encodedText, start_time, end_time, s, 100, is_web, port_filter,200);
                                    System.out.println(response_content_pl2s);
                                    ObservableList<JsonBean> result_list_pl2s = OtherTools.data_processing(response_content_pl2s);
                                    for (int i = 0; i < result_list_pl2s.size(); i++) {
                                        JsonBean jsonBean = result_list_pl2s.get(i);
                                        writer.write( jsonBean.getIp() + "," + jsonBean.getDomain() + "," + jsonBean.getPort() + "," + jsonBean.getBase_protocol() + "," + jsonBean.getWeb_title() + "," + jsonBean.getComponent() + "," + jsonBean.getCompany() + "," + jsonBean.getCountry() + "," + jsonBean.getStatus_code() + "," + jsonBean.getIsp() + "\n");
                                    }
                                    Thread.sleep(2000);
                                }catch (Exception e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        }



                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
