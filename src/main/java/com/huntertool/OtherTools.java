package com.huntertool;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.huntertool.bean.JsonBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Properties;

public class OtherTools {
    public static String getkey(int id){
        String API_KEY = "";
        File file = new File("Huntertool.properties");
        if (file.exists()) {
            Properties properties = new Properties();
            try {
                InputStream input = new FileInputStream("Huntertool.properties");
                properties.load(input);
                input.close();
                if (id == 1){
                    API_KEY = properties.getProperty("Hunter_Key_1");//读取key
                }else {
                    API_KEY = properties.getProperty("Hunter_Key_0");//读取key
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return API_KEY;
    }


    public static String get_is_key(){
        String mode = "";
        File file = new File("Huntertool.properties");
        if (file.exists()) {
            Properties properties = new Properties();
            try {
                InputStream input = new FileInputStream("Huntertool.properties");
                properties.load(input);
                input.close();
                if (properties.getProperty("mode").equals("0")){
                    mode = "外网hunter";//读取key
                }else {
                    mode = "内网hunter";//读取key
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mode;
    }



    public static String getveriosn(){
        File file = new File("Huntertool.properties");
        String id = "";
        if (file.exists()) {
            Properties properties = new Properties();
            try {
                InputStream input = new FileInputStream("Huntertool.properties");
                properties.load(input);
                input.close();
                id = properties.getProperty("mode");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return id;
    }

    public static ObservableList<JsonBean> data_processing(String response_string) {
        int code = JSONObject.parseObject(response_string).getInteger("code");
        int s;
        ObservableList<JsonBean> result_list = FXCollections.observableArrayList();
        if (code == 200) {
            JSONObject json_data = JSONObject.parseObject(response_string).getJSONObject("data");
            if (json_data.getString("arr") != null){
                JSONArray json_data_arr = json_data.getJSONArray("arr");
                for (s = 0; s < json_data_arr.size(); s++) {
                    JSONObject json_data_arr_content = (JSONObject) json_data_arr.get(s);
                    JSONArray component_arr = json_data_arr_content.getJSONArray("component");
//            JSONArray component_arr = JSONArray.parseArray(json_data_arr_content.getString("component"));
                    String component_arr_name = null;
                    String component_arr_version = null;
                    String component0 = null;
                    String component = "";
                    if (component_arr != null) {
                        for (int t = 0; t < component_arr.size(); t++) {
                            JSONObject component_arr_content = (JSONObject) component_arr.get(t);
//                    JSONObject component_arr_content = component_arr.getJSONObject(t);
                            component_arr_name = component_arr_content.getString("name");
                            component_arr_version = component_arr_content.getString("version");
                            component0 = component_arr_name + "(" + component_arr_version + ")" + "  " + "|" + "  ";
                            if (t == 0) {
                                component = component0; // 第一次循环时直接赋值
                            } else {
                                component += component0; // 累积每次循环的结果
                            }
                        }
                        if (component.endsWith("  |  ")) {
                            component = component.substring(0, component.length() - 3);
                        }

                    }
                    result_list.add(new JsonBean(
                            s + 1,
                            json_data_arr_content.getString("is_risk"),
                            json_data_arr_content.getString("url"),
                            json_data_arr_content.getString("ip"),
                            json_data_arr_content.getString("port"),
                            json_data_arr_content.getString("web_title"),
                            json_data_arr_content.getString("domain"),
                            json_data_arr_content.getString("is_risk_protocol"),
                            json_data_arr_content.getString("protocol"),
                            json_data_arr_content.getString("base_protocol"),
                            json_data_arr_content.getString("status_code"),
                            component,
                            json_data_arr_content.getString("os"),
                            json_data_arr_content.getString("company"),
                            json_data_arr_content.getString("number"),
                            json_data_arr_content.getString("country"),
                            json_data_arr_content.getString("province"),
                            json_data_arr_content.getString("city"),
                            json_data_arr_content.getString("updated_at"),
                            json_data_arr_content.getString("is_web"),
                            json_data_arr_content.getString("as_org"),
                            json_data_arr_content.getString("isp"),
                            json_data_arr_content.getString("banner"),
                            json_data_arr_content.getString("vul_list")
                    ));
                }
            }

        }
        return result_list;
    }
}
