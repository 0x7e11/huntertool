package com.huntertool;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.huntertool.bean.JsonBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.BufferedWriter;
import java.util.UUID;


import java.util.Properties;

public class HunterController {
    private static String API_KEY = "";
    String response_string = "";
    String response_string_pl1 = "";
    String filename = "";
    int s = 0;

    int taskid = 0;
    ObservableList<JsonBean> result_list = FXCollections.observableArrayList();



    @FXML
    private Button Open_key_id;

    @FXML
    private Button chaxun_id;

    @FXML
    private Button daochu_id;

    @FXML
    private TextArea shuchu_id;


    @FXML
    private TextField key_id;

    @FXML
    private Button save_id;
    @FXML
    private TableView<JsonBean> TableView_id;

    @FXML
    private TableColumn<JsonBean, String> Column_ICP;

    @FXML
    private TableColumn<JsonBean, String> Column_ISP;

    @FXML
    private TableColumn<JsonBean, String> Column_domain;

    @FXML
    private TableColumn<JsonBean, String> Column_fingerprint;

    @FXML
    private TableColumn<JsonBean, Integer> Column_id;

    @FXML
    private TableColumn<JsonBean, String> Column_ip;

    @FXML
    private TableColumn<JsonBean, String> Column_port;

    @FXML
    private TableColumn<JsonBean, String> Column_position;

    @FXML
    private TableColumn<JsonBean, String> Column_service;

    @FXML
    private TableColumn<JsonBean, String> Column_statuscode;

    @FXML
    private TableColumn<JsonBean, String> Column_title;

    @FXML
    private TextField shuru_id;

    @FXML
    private TextArea pl_cx_1_TextArea;

    @FXML
    private Button pl_cx_1_but;

    @FXML
    private TextArea pl_cx_2_TextArea;

    @FXML
    private Button pl_cx_2_but;

    @FXML
    private Button pl_dc_1_but;

    @FXML
    private Button pl_scwj_1_but;

    @FXML
    private Button pl_scwj_2_but;


    @FXML
    void chaxun(ActionEvent event) {
        API_KEY = OtherTools.getkey();
        String originalText = this.shuru_id.getText().trim();
        String encodedText = Base64.getUrlEncoder().encodeToString(originalText.getBytes(StandardCharsets.UTF_8));

        try {
            response_string = NetworkTools.search(0, API_KEY, encodedText, "2023-12-01", "2024-01-22", 1, 100, 1, 200);
            System.out.println(response_string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int code = JSONObject.parseObject(response_string).getInteger("code");
        if (code == 200){
            JSONObject json_data = JSONObject.parseObject(response_string).getJSONObject("data");
            JSONArray json_data_arr = json_data.getJSONArray("arr");
            int total = json_data.getInteger("total");
            for (s = 0; s < json_data_arr.size(); s++) {
                JSONObject json_data_arr_content = (JSONObject) json_data_arr.get(s);
                JSONArray component_arr = json_data_arr_content.getJSONArray("component");
//            JSONArray component_arr = JSONArray.parseArray(json_data_arr_content.getString("component"));
                String component_arr_name =null;
                String component_arr_version = null;
                String component0 = null;
                String component = "";
                if (component_arr != null) {
                    for (int t = 0; t < component_arr.size(); t++) {
                        JSONObject component_arr_content = (JSONObject) component_arr.get(t);
//                    JSONObject component_arr_content = component_arr.getJSONObject(t);
                        component_arr_name = component_arr_content.getString("name");
                        component_arr_version = component_arr_content.getString("version");
                        component0 =  component_arr_name + "(" +component_arr_version +")"+"  "+"|"+"  ";
                        if (t == 0) {
                            component = component0; // 第一次循环时直接赋值
                        } else {
                            component += component0; // 累积每次循环的结果
                        }
                    }
                    if (component.endsWith("  |  ")){
                        component = component.substring(0, component.length() - 3);
                    }

                }
                result_list.add(new JsonBean(
                        s+1,
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
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(JSONObject.parseObject(response_string).getString("message"));
            alert.showAndWait();
        }

        //将表格和列设置为可编辑
        TableView_id.setEditable(true);
//        Column_id.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_ICP.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_ISP.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_domain.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_fingerprint.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_ip.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_port.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_position.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_service.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_statuscode.setCellFactory(TextFieldTableCell.forTableColumn());
        Column_title.setCellFactory(TextFieldTableCell.forTableColumn());

        Column_id.setCellValueFactory(new PropertyValueFactory<JsonBean,Integer>("id"));
        Column_ICP.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("company"));
        Column_ISP.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("isp"));
        Column_domain.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("domain"));
        Column_fingerprint.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("component"));
        Column_ip.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("ip"));
        Column_port.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("port"));
        Column_position.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("country"));
        Column_service.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("protocol"));
        Column_statuscode.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("status_code"));
        Column_title.setCellValueFactory(new PropertyValueFactory<JsonBean,String>("web_title"));

        TableView_id.setItems(result_list);

    }


    @FXML
    void Open_key(ActionEvent event) throws IOException {
        AnchorPane set_key = FXMLLoader.load(getClass().getResource("view_key.fxml"));
        Stage set_key_Stage = new Stage();
        set_key_Stage.setTitle("Set API-KEY");
        set_key_Stage.setScene(new Scene(set_key));
        set_key_Stage.show();
    }

    @FXML
    void key(MouseEvent event) {
    }

    @FXML
    void key_Clicked(MouseEvent event) {
        if(key_id.getText().equals("****************************************************************")){
            key_id.setText("");
        }
    }

    @FXML
    void save(ActionEvent event) {
        String save_key = key_id.getText().trim();
        if (save_key.length() > 0 && save_key.charAt(0) != '*') {
            API_KEY = save_key;
            Properties properties = new Properties();
            properties.setProperty("Hunter_Key", API_KEY);
            try (OutputStream output = new FileOutputStream("Huntertool.properties")){
                properties.store(output, "Huntertool Configuration");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Stage set_key_Stage = (Stage)save_id.getScene().getWindow();
        set_key_Stage.close();
    }

    @FXML
    void daochu(ActionEvent event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("export_" + UUID.randomUUID().toString() + ".csv"))) {
            // 写入表头
            writer.write("序号, IP, 域名, 端口, 服务, title, 组件（版本）, ICP, 地理位置, 状态码, ISP\n");
            for (int i = 0; i < result_list.size(); i++) {
                JsonBean jsonBean = result_list.get(i);
                writer.write((i+1) + "," + jsonBean.getIp() + "," + jsonBean.getDomain() + "," + jsonBean.getPort() + "," + jsonBean.getBase_protocol() + "," + jsonBean.getWeb_title() + "," + jsonBean.getProtocol() + "," + jsonBean.getCompany() + "," + jsonBean.getProvince() + "," + jsonBean.getStatus_code() + "," + jsonBean.getIsp() +"\n");
            }
            Alert informationAlert = new Alert(AlertType.INFORMATION);
            informationAlert.setTitle("Export Results");
            informationAlert.setHeaderText("导出成功");
            informationAlert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void pl_cx_1(ActionEvent event) {
        API_KEY = OtherTools.getkey();
        String originalText = this.pl_cx_1_TextArea.getText().trim();
        originalText = "1\r\n" + originalText; //从第二行开始读取数据
        try {
            response_string_pl1 = NetworkTools.search_pl1(0,API_KEY,originalText,"2023-12-01","2024-01-22",1);
            int code = JSONObject.parseObject(response_string_pl1).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_string_pl1).getJSONObject("data");
                taskid = json_data.getInteger("task_id");
                filename = json_data.getString("filename");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information");
                alert.setHeaderText("查询成功，请点击导出结果");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(JSONObject.parseObject(response_string_pl1).getString("message"));
                alert.showAndWait();
            }

        } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    void pl_cx_2(ActionEvent event) {
        API_KEY = OtherTools.getkey();
        String originalText = this.pl_cx_2_TextArea.getText().trim();
        NetworkTools.pl2(originalText,API_KEY);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText("查询完毕，导出成功");
        alert.showAndWait();
        }

    @FXML
    void pl_dc_1(ActionEvent event) {
        try {
            String response_task_progress = NetworkTools.task_progress(0,API_KEY,taskid);
            int code = JSONObject.parseObject(response_task_progress).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_task_progress).getJSONObject("data");
                String progress = json_data.getString("progress");
                if (progress.equals("100%")){
                    NetworkTools.download(0,API_KEY,taskid,filename);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("information");
                    alert.setHeaderText("导出成功");
                    alert.showAndWait();

                }else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("progress:"+progress);
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(JSONObject.parseObject(response_task_progress).getString("message"));
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void pl_scwj_1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("csv", "*.csv")
        );
        File csvfile = fileChooser.showOpenDialog(new Stage());
        if (csvfile == null) {
            return;
        }
        System.out.println(csvfile.getAbsolutePath());
        String csvText = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String Line = "";
            File file_csv = new File(csvfile.getAbsolutePath());
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file_csv), "GB2312");
            BufferedReader csv = new BufferedReader(isr);
//            BufferedReader csv = new BufferedReader(new FileReader(csvfile.getAbsolutePath()));
            while ((Line = csv.readLine()) != null) {
                stringBuilder.append(Line+"\r\n");
                System.out.println(Line);
                }
            csvText = stringBuilder.toString();
            System.out.println(csvText);
            } catch (IOException e) {
            e.printStackTrace();
        }
        API_KEY = OtherTools.getkey();
        try {
            response_string_pl1 = NetworkTools.search_pl1(0,API_KEY,csvText,"2023-12-01","2024-01-22",1);
            int code = JSONObject.parseObject(response_string_pl1).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_string_pl1).getJSONObject("data");
                taskid = json_data.getInteger("task_id");
                filename = json_data.getString("filename");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information");
                alert.setHeaderText("上传成功，请点击导出结果");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(JSONObject.parseObject(response_string_pl1).getString("message"));
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void pl_scwj_2(ActionEvent event) {
        API_KEY = OtherTools.getkey();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("csv", "*.csv")
        );
        File csvfile = fileChooser.showOpenDialog(new Stage());
        if (csvfile == null) {
            return;
        }
        System.out.println(csvfile.getAbsolutePath());
        String csvText = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String Line = "";
            File file_csv = new File(csvfile.getAbsolutePath());
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file_csv), "GB2312");
            BufferedReader csv = new BufferedReader(isr);
//            BufferedReader csv = new BufferedReader(new FileReader(csvfile.getAbsolutePath()));
            while ((Line = csv.readLine()) != null) {
                stringBuilder.append(Line+"\r\n");
                System.out.println(Line);
            }
            csvText = stringBuilder.toString();
            System.out.println(csvText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetworkTools.pl2(csvText,API_KEY);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText("查询完毕，导出成功");
        alert.showAndWait();
    }


    @FXML
    void pl_1_Clicked(MouseEvent event) {
        if(pl_cx_1_TextArea.getText().equals(
                "支持IP/域名/网段，例如：\n" +
                "192.168.10.1-255\n" +
                "192.168.0.1-192.168.0.255\n" +
                "192.168.0.0/24\n" +
                "192.168.0.0/255.255.255.0\n" +
                "baidu.com")){
            pl_cx_1_TextArea.setText("");
        }
    }

    @FXML
    void pl_2_Clicked(MouseEvent event) {
        if(pl_cx_2_TextArea.getText().equals(
                "支持所有hunter语法，每行一条，例如：\n" +
                        "domain==\"baidu.com\"\n" +
                        "domain==\"tabao.com\"")){
            pl_cx_2_TextArea.setText("");
        }


    }
}

