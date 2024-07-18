package com.huntertool;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.huntertool.bean.JsonBean;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.BufferedWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.time.LocalTime;


public class HunterController implements Initializable {
    private static String API_KEY = "";
    String response_string = "";
    String response_string_pl1 = "";
    String filename = "";

    int s = 0;

    int taskid = 0;

    int total = 0;
    ObservableList<JsonBean> result_list = FXCollections.observableArrayList();



    @FXML
    private Button Open_key_id;

    @FXML
    private Button Open_proxy_id;

    @FXML
    private Button chaxun_id;

    @FXML
    private Button daochu_id;

    @FXML
    private Button ico_but;

    @FXML
    private Button yufa_but;

    @FXML
    private TextArea shuchu_id;

    @FXML
    private ChoiceBox<String> is_web;

    @FXML
    private ChoiceBox<String> is_key;

    @FXML
    private ChoiceBox<String> time;

    @FXML
    private ChoiceBox<String> is_web_pl1;

    @FXML
    private ChoiceBox<String> is_web_pl2;

    @FXML
    private ChoiceBox<String> time_pl1;

    @FXML
    private ChoiceBox<String> time_pl2;

    @FXML
    private ChoiceBox<String> filter;

    @FXML
    private ChoiceBox<String> filter_pl1;

    @FXML
    private ChoiceBox<String> filter_pl2;


    @FXML
    private TextField key_id_outer;

    @FXML
    private TextField key_id_inner;

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
    private TextField page_id;

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
    private Text text1;


    @FXML
    private TextField ip_id;

    @FXML
    private ChoiceBox<String> is_proxy;

    @FXML
    private TextField port_id;

    @FXML
    private Button proxysave_id;

    @FXML
    void ip(ActionEvent event) {

    }

    @FXML
    void key_Clicked_0(MouseEvent event) {

    }

    @FXML
    void key_Clicked_1(MouseEvent event) {

    }

    @FXML
    void port(ActionEvent event) {

    }

    @FXML
    private TextArea log_TextArea;

    @FXML
    private Button clear_log_but;






    @FXML
    void chaxun(ActionEvent event) {
        TableView_id.getItems().clear();//清空内容显示
        TableView_id.setPlaceholder(new Label("查询中"));//清空查询显示
        text1.setText("");//清空统计显示

        String originalText = this.shuru_id.getText().trim();
        String encodedText = Base64.getUrlEncoder().encodeToString(originalText.getBytes(StandardCharsets.UTF_8));
        String get_web_S = is_web.getValue();//获取ChoiceBox选项
        int get_web_i;
        if(get_web_S.equals("web资产")){
            get_web_i = 1;
        }else if(get_web_S.equals("非web资产")){
            get_web_i = 2;
        }else {
            get_web_i = 3;
        }

        String filter_s = filter.getValue();
        String filter_i;
        if(filter_s.equals("开启")){
            filter_i = "true";
        }else {
            filter_i = "false";
        }



        String time_S = time.getValue();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date_m;
        String Date_p = Date.format(LocalDateTime.now());
        if(time_S.equals("最近一月")){
            Date_m = Date.format(LocalDateTime.now().minusMonths(1));
        } else if (time_S.equals("最近半年")) {
            Date_m = Date.format(LocalDateTime.now().minusMonths(6));
        }else {
            Date_m = Date.format(LocalDateTime.now().minusMonths(12));
        }



        ExecutorService executorService = Executors.newSingleThreadExecutor();//创建线程池,异步线程避免卡顿
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    response_string = NetworkTools.search(OtherTools.getveriosn(), OtherTools.getkey(OtherTools.getveriosn()), encodedText, Date_m, Date_p, Integer.parseInt(page_id.getText()), 100, get_web_i,filter_i,200);
                    System.out.println(response_string);
                } catch (Exception e) {
                    e.printStackTrace();
                    response_string = "Error";

                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (response_string.equals("Error")){
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle(" Error");
                            alert.setHeaderText("发生错误，请检查网络环境或代理设置");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                            alert.showAndWait();
                            TableView_id.setPlaceholder(new Label("发生错误，请检查网络环境或代理设置"));
                        }
                        int code = JSONObject.parseObject(response_string).getInteger("code");
                        if (code == 200){
                            JSONObject json_data = JSONObject.parseObject(response_string).getJSONObject("data");
                            System.out.println(json_data.getString("arr"));
                            if (json_data.getString("arr") == null){
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle(" INFORMATION");
                                alert.setHeaderText("未查询到相关内容");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                                alert.showAndWait();
                                TableView_id.setPlaceholder(new Label("未查询到相关内容"));
                            }else {
                                JSONArray json_data_arr = json_data.getJSONArray("arr");
                                total = json_data.getInteger("total");
                                text1.setText("共" + total + "条资产,每页最多显示100条资产");
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
                                            json_data_arr_content.getString("country")+"-"+json_data_arr_content.getString("province")+"-"+json_data_arr_content.getString("city"),
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
                        else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle(" Error");
                            alert.setHeaderText(JSONObject.parseObject(response_string).getString("message"));
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                            alert.showAndWait();
                            TableView_id.setPlaceholder(new Label(JSONObject.parseObject(response_string).getString("message")));
                        }
                    }
                });
                return null;
            }
        });
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
        set_key_Stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
        set_key_Stage.show();


//        Alert informationAlert = new Alert(AlertType.NONE);
//        informationAlert.setTitle("Set API-KEY");
//        informationAlert.getDialogPane().setContent(set_key);
////        informationAlert.setHeaderText("导出成功");
//        informationAlert.showAndWait();
    }

    @FXML
    void Open_proxy(ActionEvent event) throws IOException {
        AnchorPane set_key = FXMLLoader.load(getClass().getResource("view_proxy.fxml"));
        Stage set_key_Stage = new Stage();
        set_key_Stage.setTitle("Set proxy");
        set_key_Stage.setScene(new Scene(set_key));
        set_key_Stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
        set_key_Stage.show();
    }

    @FXML
    void key_outer(MouseEvent event) {
    }

    @FXML
    void key_inner(MouseEvent event) {
    }

    @FXML
    void ico(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("ico", "*.ico")
        );
        File icofile = fileChooser.showOpenDialog(new Stage());
        if (icofile == null) {
            return;
        }
        System.out.println(icofile.getAbsolutePath());
        try{
            FileInputStream file = new FileInputStream(icofile.getAbsolutePath());
            byte[] data = file.readAllBytes();

            shuru_id.setText("web.icon=\"" + OtherTools.getHash(data) + "\"");
            TableView_id.getItems().clear();
            TableView_id.setPlaceholder(new Label("请点击查询"));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(" INFORMATION");
            alert.setHeaderText("请点击查询");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
            alert.showAndWait();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void yufa(ActionEvent event) throws IOException {
        TabPane set_key = FXMLLoader.load(getClass().getResource("view_yufa.fxml"));
        Stage set_key_Stage = new Stage();
        set_key_Stage.setTitle("语法参考");
        set_key_Stage.setScene(new Scene(set_key));
        set_key_Stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
        set_key_Stage.show();
    }


    @FXML
    void save(ActionEvent event) {  //保存apikey设置
        String save_key_outer = key_id_outer.getText().trim();
        String save_key_inner = key_id_inner.getText().trim();
        String is_key_s = is_key.getValue();
        String is_key_i;
        if (is_key_s.equals("内网hunter")){
            is_key_i = "inner";
        }else {
            is_key_i = "outer";
        }
        Properties properties = new Properties();
        properties.setProperty("Hunter_mode",is_key_i);
        properties.setProperty("Hunter_Key_outer", save_key_outer);
        properties.setProperty("Hunter_Key_inner", save_key_inner);


        String is_proxy_i;
        if (OtherTools.get_is_proxy().equals("开启")){
            is_proxy_i = "Y";
        }else {
            is_proxy_i = "N";
        }
        properties.setProperty("proxy",is_proxy_i);//刷一遍配置文件
        properties.setProperty("proxy_ip", OtherTools.getip());  //OtherTools.getip()可为""，而不可为null
        properties.setProperty("proxy_port", OtherTools.getport());




        try (OutputStream output = new FileOutputStream("Huntertool.properties")) {
            properties.store(output, "Huntertool Configuration");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage set_key_Stage = (Stage) save_id.getScene().getWindow();
        set_key_Stage.close();
    }

    @FXML
    void proxysave(ActionEvent event) {  //保存代理设置
        String proxy_ip =ip_id.getText().trim();
        String proxy_port = port_id.getText().trim();
        String proxy = "N";
        if (is_proxy.getValue().equals("开启")){
            proxy = "Y";
        }
        Properties properties = new Properties();
        properties.setProperty("proxy",proxy);
        properties.setProperty("proxy_ip", proxy_ip);
        properties.setProperty("proxy_port", proxy_port);

        String is_key_i;
        if (OtherTools.get_is_key().equals("内网hunter")){
            is_key_i = "inner";
        }else {
            is_key_i = "outer";
        }
        properties.setProperty("Hunter_mode",is_key_i);//刷一遍配置文件
        properties.setProperty("Hunter_Key_outer", OtherTools.getkey("outer"));
        properties.setProperty("Hunter_Key_inner", OtherTools.getkey("inner"));
        try (OutputStream output = new FileOutputStream("Huntertool.properties")) {
            properties.store(output, "Huntertool Configuration");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(proxy == "Y"){
            System.getProperties().setProperty("https.proxyHost", proxy_ip);
            System.getProperties().setProperty("https.proxyPort", proxy_port);
        }else {
            System.getProperties().clear();
        }




        Stage set_proxy_Stage = (Stage) proxysave_id.getScene().getWindow();
        set_proxy_Stage.close();



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
            informationAlert.setTitle(" Export Results");
            informationAlert.setHeaderText("导出成功");
            Stage stage = (Stage) informationAlert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
            informationAlert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void pl_cx_1(ActionEvent event) {
        log_TextArea.appendText("------------------------------HUNTERT TOOL------------------------------"+"\n");
        String get_web_pl1_S = is_web_pl1.getValue();//获取ChoiceBox选项
        int get_web_pl1_i;
        if(get_web_pl1_S.equals("web资产")){
            get_web_pl1_i = 1;
        }else if(get_web_pl1_S.equals("非web资产")){
            get_web_pl1_i = 2;
        }else {
            get_web_pl1_i = 3;
        }

        String filter_s = filter_pl1.getValue();
        String filter_i;
        if(filter_s.equals("开启")){
            filter_i = "true";
        }else {
            filter_i = "false";
        }

        String time_pl1_S = time_pl1.getValue();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date_m;
        String Date_p = Date.format(LocalDateTime.now());
        if(time_pl1_S.equals("最近一月")){
            Date_m = Date.format(LocalDateTime.now().minusMonths(1));
        } else if (time_pl1_S.equals("最近半年")) {
            Date_m = Date.format(LocalDateTime.now().minusMonths(6));
        }else {
            Date_m = Date.format(LocalDateTime.now().minusMonths(12));
        }

        API_KEY = OtherTools.getkey(OtherTools.getveriosn());
        String originalText = this.pl_cx_1_TextArea.getText().trim();
        originalText = "1\r\n" + originalText; //从第二行开始读取数据
        try {
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询任务开始"+ " [批量方式1] "+"["+OtherTools.getveriosn()+"]"+"\n");
            try{
                response_string_pl1 = NetworkTools.search_pl1(OtherTools.getveriosn(),OtherTools.getkey(OtherTools.getveriosn()),originalText,Date_m, Date_p,get_web_pl1_i,filter_i);
            } catch (Exception e) {
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式1]"+" 任务中断(请检查网络环境或代理设置)"+"\n");
                throw new RuntimeException(e);
            }
            int code = JSONObject.parseObject(response_string_pl1).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_string_pl1).getJSONObject("data");
                taskid = json_data.getInteger("task_id");
                filename = json_data.getString("filename");
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询成功[批量方式1] 等待导出\n");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(" information");
                alert.setHeaderText("查询成功，请点击导出结果");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                alert.showAndWait();
            }
            else {
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式1] " + "message:"+JSONObject.parseObject(response_string_pl1).getString("message")+"\n");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(" Error");
                alert.setHeaderText(JSONObject.parseObject(response_string_pl1).getString("message"));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                alert.showAndWait();
            }

        } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    void pl_cx_2(ActionEvent event) {
        log_TextArea.appendText("------------------------------HUNTERT TOOL------------------------------"+"\n");
        API_KEY = OtherTools.getkey(OtherTools.getveriosn());
        String originalText = this.pl_cx_2_TextArea.getText().trim();

        String get_web_pl2_S = is_web_pl2.getValue();//获取ChoiceBox选项
        int get_web_pl2_i;
        if(get_web_pl2_S.equals("web资产")){
            get_web_pl2_i = 1;
        }else if(get_web_pl2_S.equals("非web资产")){
            get_web_pl2_i = 2;
        }else {
            get_web_pl2_i = 3;
        }

        String filter_s = filter_pl2.getValue();
        String filter_i;
        if(filter_s.equals("开启")){
            filter_i = "true";
        }else {
            filter_i = "false";
        }

        String time_pl2_S = time_pl2.getValue();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date_m;
        String Date_p = Date.format(LocalDateTime.now());
        if(time_pl2_S.equals("最近一月")){
            Date_m = Date.format(LocalDateTime.now().minusMonths(1));
        } else if (time_pl2_S.equals("最近半年")) {
            Date_m = Date.format(LocalDateTime.now().minusMonths(6));
        }else {
            Date_m = Date.format(LocalDateTime.now().minusMonths(12));
        }

        if(API_KEY.equals("") | OtherTools.getveriosn().equals("-1")){
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式2]\n");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(" ERROR");
            alert.setHeaderText("请检查设置内容");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
            alert.showAndWait();
            return;
        }else {
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询任务开始"+ " [批量方式2] "+"["+OtherTools.getveriosn()+"]"+"\n");
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(" information");
//            alert.setHeaderText("开始查询，请稍后");
//            alert.setContentText("网络通畅的情况下执行速度为2s/条");
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
//            alert.show();//不阻塞主线程
        }


        ExecutorService executorService = Executors.newSingleThreadExecutor();//创建线程池,异步线程避免卡顿
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                pl2(OtherTools.getveriosn(),OtherTools.getkey(OtherTools.getveriosn()),originalText,Date_m, Date_p,get_web_pl2_i,filter_i);


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {   //弹窗的显示将在主线程中进行，不会阻塞任务的执行
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(" information");
                        alert.setHeaderText("查询完毕，导出成功");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                        alert.showAndWait();
                    }
                });


                return null;
            }
        });








        }

    public void pl2(String version,String API_KEY,String originalText,String start_time,String end_time,int is_web,String port_filter){
        String export_csv = "export_pl_" + UUID.randomUUID().toString() + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(export_csv))) {
            writer.write("IP, 域名, 端口, 服务, title, 组件（版本）, ICP, 地理位置, 状态码, ISP\n");
            try(BufferedReader reader = new BufferedReader(new StringReader(originalText))){
                String line;
                while ((line = reader.readLine()) != null ){
                    log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 开始查询[批量方式2] "+"查询内容:"+line+"\n");
                    System.out.println(line);
                    String encodedText = Base64.getUrlEncoder().encodeToString(line.getBytes(StandardCharsets.UTF_8));
                    try {
                        String response_content_pl2 = NetworkTools.search(version, API_KEY, encodedText, start_time, end_time, 1, 100, is_web, port_filter,200);


                        int code = JSONObject.parseObject(response_content_pl2).getInteger("code");
                        if (code != 200){
                            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式2] 任务中断 " + "message:"+JSONObject.parseObject(response_content_pl2).getString("message")+"\n");
                            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询完毕的项目已成功保存："+export_csv+"\n");
                            return;
                        }


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

                                    int codes = JSONObject.parseObject(response_content_pl2s).getInteger("code");
                                    if (codes != 200){
                                        log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式2] 任务中断 " + "message:"+JSONObject.parseObject(response_content_pl2).getString("message")+"\n");
                                        log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询完毕的项目已成功保存："+export_csv+"\n");
                                        return;
                                    }

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
                        log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询成功[批量方式2] 结果已写入表格"+"("+"查询内容:"+line+")"+"\n");




                    } catch (Exception e) {
                        log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式2]"+" 任务中断(请检查网络环境或代理设置)"+ " 查询完毕的项目已成功保存："+export_csv+"\n");
                        throw new RuntimeException(e);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询完毕[批量方式2] 查询结果已成功保存："+export_csv+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void pl_dc_1(ActionEvent event) {
        try {
            String response_task_progress = NetworkTools.task_progress(OtherTools.getveriosn(),API_KEY,taskid);
            int code = JSONObject.parseObject(response_task_progress).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_task_progress).getJSONObject("data");
                String progress = json_data.getString("progress");
                if (progress.equals("100%")){
                    NetworkTools.download(OtherTools.getveriosn(),OtherTools.getkey(OtherTools.getveriosn()),taskid,filename);
                    log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 导出成功[批量方式1] 查询结果见当前目录"+"\n");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle(" information");
                    alert.setHeaderText("导出成功");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                    alert.showAndWait();

                }else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle(" Error");
                    alert.setHeaderText("progress:"+progress);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                    alert.showAndWait();
                }
            }
            else {
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式1] 任务中断 " + "message:"+JSONObject.parseObject(response_task_progress).getString("message")+"\n");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(" Error");
                alert.setHeaderText(JSONObject.parseObject(response_task_progress).getString("message"));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
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
        log_TextArea.appendText("------------------------------HUNTERT TOOL------------------------------"+"\n");
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
//                System.out.println(Line);
                }

            if (stringBuilder.length() >= 2) {  //去除最后冗余的\r\n
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }

            csvText = stringBuilder.toString();
            System.out.println(csvText);
            } catch (IOException e) {
            e.printStackTrace();
        }
        API_KEY = OtherTools.getkey(OtherTools.getveriosn());

        String get_web_pl1_S = is_web_pl1.getValue();//获取ChoiceBox选项
        int get_web_pl1_i;
        if(get_web_pl1_S.equals("web资产")){
            get_web_pl1_i = 1;
        }else if(get_web_pl1_S.equals("非web资产")){
            get_web_pl1_i = 2;
        }else {
            get_web_pl1_i = 3;
        }

        String filter_s = filter_pl1.getValue();
        String filter_i;
        if(filter_s.equals("开启")){
            filter_i = "true";
        }else {
            filter_i = "false";
        }

        String time_pl1_S = time_pl1.getValue();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date_m;
        String Date_p = Date.format(LocalDateTime.now());
        if(time_pl1_S.equals("最近一月")){
            Date_m = Date.format(LocalDateTime.now().minusMonths(1));
        } else if (time_pl1_S.equals("最近半年")) {
            Date_m = Date.format(LocalDateTime.now().minusMonths(6));
        }else {
            Date_m = Date.format(LocalDateTime.now().minusMonths(12));
        }

        try {
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询任务开始"+ " [批量方式1] "+"["+OtherTools.getveriosn()+"]"+"\n");
            try{
                response_string_pl1 = NetworkTools.search_pl1(OtherTools.getveriosn(),OtherTools.getkey(OtherTools.getveriosn()),csvText,Date_m, Date_p, get_web_pl1_i,filter_i);
            } catch (Exception e) {
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式1]"+" 任务中断(请检查网络环境或代理设置)"+"\n");
                throw new RuntimeException(e);
            }

            int code = JSONObject.parseObject(response_string_pl1).getInteger("code");
            if (code == 200){
                JSONObject json_data = JSONObject.parseObject(response_string_pl1).getJSONObject("data");
                taskid = json_data.getInteger("task_id");
                filename = json_data.getString("filename");
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询成功[批量方式1] 等待导出\n");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(" information");
                alert.setHeaderText("上传成功，请点击导出结果");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                alert.showAndWait();
            }
            else {
                log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询失败[批量方式1] 任务中断 " + "message:"+JSONObject.parseObject(response_string_pl1).getString("message")+"\n");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(" Error");
                alert.setHeaderText(JSONObject.parseObject(response_string_pl1).getString("message"));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void pl_scwj_2(ActionEvent event) {
        API_KEY = OtherTools.getkey(OtherTools.getveriosn());
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
        log_TextArea.appendText("------------------------------HUNTERT TOOL------------------------------"+"\n");
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
//                System.out.println(Line);
            }
            if (stringBuilder.length() >= 2) {  //去除最后冗余的\r\n
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }

            csvText = stringBuilder.toString();
            System.out.println(csvText);
        } catch (IOException e) {
            e.printStackTrace();
        }



        String get_web_pl2_S = is_web_pl2.getValue();//获取ChoiceBox选项
        int get_web_pl2_i;
        if(get_web_pl2_S.equals("web资产")){
            get_web_pl2_i = 1;
        }else if(get_web_pl2_S.equals("非web资产")){
            get_web_pl2_i = 2;
        }else {
            get_web_pl2_i = 3;
        }

        String filter_s = filter_pl2.getValue();
        String filter_i;
        if(filter_s.equals("开启")){
            filter_i = "true";
        }else {
            filter_i = "false";
        }

        String time_pl2_S = time_pl2.getValue();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date_m;
        String Date_p = Date.format(LocalDateTime.now());
        if(time_pl2_S.equals("最近一月")){
            Date_m = Date.format(LocalDateTime.now().minusMonths(1));
        } else if (time_pl2_S.equals("最近半年")) {
            Date_m = Date.format(LocalDateTime.now().minusMonths(6));
        }else {
            Date_m = Date.format(LocalDateTime.now().minusMonths(12));
        }

        if(API_KEY.equals("") | OtherTools.getveriosn().equals("-1")){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(" ERROR");
            alert.setHeaderText("请检查设置内容");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
            alert.showAndWait();
            return;
        }else {
            log_TextArea.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 查询任务开始"+ " [批量方式2] "+"["+OtherTools.getveriosn()+"]"+"\n");
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(" information");
//            alert.setHeaderText("开始查询，请稍后");
//            alert.setContentText("网络通畅的情况下执行速度为2s/条");
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//            stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
//            alert.show();//不阻塞主线程
        }

        String originalText = csvText;
        ExecutorService executorService = Executors.newSingleThreadExecutor();//创建线程池,异步线程避免卡顿
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                pl2(OtherTools.getveriosn(),OtherTools.getkey(OtherTools.getveriosn()),originalText,Date_m, Date_p,get_web_pl2_i,filter_i);


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {   //弹窗的显示将在主线程中进行，不会阻塞任务的执行
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(" information");
                        alert.setHeaderText("查询完毕，导出成功");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
                        alert.showAndWait();
                    }
                });


                return null;
            }
        });



    }

    @FXML
    void clear_log(ActionEvent event) {
        log_TextArea.clear();

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

    public void initialize(URL location, ResourceBundle resource){//自动获取参数，不需要手动赋值
        if (location.toString().contains("view_main.fxml")){  //打开新的fxml文件时，会再次调用initialize，需要对fxml文件进行判断避免造成空指针报错
            is_web.setItems(FXCollections.observableArrayList("web资产","非web资产","全部"));
            is_web.setValue("全部");
            is_web_pl1.setItems(FXCollections.observableArrayList("web资产","非web资产","全部"));
            is_web_pl1.setValue("全部");
            is_web_pl2.setItems(FXCollections.observableArrayList("web资产","非web资产","全部"));
            is_web_pl2.setValue("全部");

            time.setItems(FXCollections.observableArrayList("最近一月","最近半年","最近一年"));
            time.setValue("最近一年");
            time_pl1.setItems(FXCollections.observableArrayList("最近一月","最近半年","最近一年"));
            time_pl1.setValue("最近一年");
            time_pl2.setItems(FXCollections.observableArrayList("最近一月","最近半年","最近一年"));
            time_pl2.setValue("最近一年");

            filter.setItems(FXCollections.observableArrayList("开启","关闭"));
            filter.setValue("关闭");
            filter_pl1.setItems(FXCollections.observableArrayList("开启","关闭"));
            filter_pl1.setValue("关闭");
            filter_pl2.setItems(FXCollections.observableArrayList("开启","关闭"));
            filter_pl2.setValue("关闭");

            page_id.setText("1");


        }
        if (location.toString().contains("view_key.fxml")){
            is_key.setItems(FXCollections.observableArrayList("内网hunter","外网hunter"));
            is_key.setValue(OtherTools.get_is_key());
            key_id_outer.setText(OtherTools.getkey("outer"));//读取配置文件内容并显示
            key_id_inner.setText(OtherTools.getkey("inner"));

            if (OtherTools.get_is_key().equals("-1")){//为-1时表示文件不存在或者其他读取失败的情况，设置为空
                is_key.setValue("");
            }
        }

        if (location.toString().contains("view_proxy.fxml")){
            is_proxy.setItems(FXCollections.observableArrayList("开启","关闭"));
            is_proxy.setValue(OtherTools.get_is_proxy());
            ip_id.setText(OtherTools.getip());
            port_id.setText(OtherTools.getport());
            }
        }




}

