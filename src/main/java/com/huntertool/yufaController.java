package com.huntertool;


import com.huntertool.bean.yufaBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;

public class yufaController {
    @FXML
    private TableView<yufaBean> tableView1;

    @FXML
    private TableColumn<String,String> column1_content;

    @FXML
    private TableColumn<String,String> column1_illustrate;



    @FXML
    private TableView<yufaBean> tableView2;

    @FXML
    private TableColumn<String,String> column2_content;

    @FXML
    private TableColumn<String,String> column2_illustrate;



    @FXML
    private TableView<yufaBean> tableView3;

    @FXML
    private TableColumn<String,String> column3_content;

    @FXML
    private TableColumn<String,String> column3_illustrate;



    @FXML
    private TableView<yufaBean> tableView4;

    @FXML
    private TableColumn<String,String> column4_content;

    @FXML
    private TableColumn<String,String> column4_illustrate;



    @FXML
    private TableView<yufaBean> tableView5;

    @FXML
    private TableColumn<String,String> column5_content;

    @FXML
    private TableColumn<String,String> column5_illustrate;




    @FXML
    private TableView<yufaBean> tableView6;

    @FXML
    private TableColumn<String,String> column6_content;

    @FXML
    private TableColumn<String,String> column6_illustrate;



    @FXML
    private TableView<yufaBean> tableView7;

    @FXML
    private TableColumn<String,String> column7_content;

    @FXML
    private TableColumn<String,String> column7_illustrate;



    public void initialize(){
        column1_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column1_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_1 = FXCollections.observableArrayList();
        data_1.add(new yufaBean("web.tag=\"登录页面\" ","查询包含资产标签\"登录页面\"的资产"));
        data_1.add(new yufaBean("domain.suffix=\"qianxin.com\"","搜索主域为\"qianxin.com\"的网站"));
        tableView1.setItems(data_1);

        column2_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column2_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_2 = FXCollections.observableArrayList();
        data_2.add(new yufaBean("web.title=\"北京\"","从网站标题中搜索“北京”"));
        data_2.add(new yufaBean("web.body=\"网络空间测绘\"","搜索网站正文包含”网络空间测绘“的资产"));
        data_2.add(new yufaBean("icp.number=\"京ICP备16020626号-8\"","搜索通过域名关联的ICP备案号为”京ICP备16020626号-8”的网站资产"));
        data_2.add(new yufaBean("icp.web_name=\"奇安信\"","搜索ICP备案网站名中含有“奇安信”的资产"));
        tableView2.setItems(data_2);

        column3_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column3_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_3 = FXCollections.observableArrayList();
        data_3.add(new yufaBean("domain=\"qianxin.com\"","搜索域名包含\"qianxin.com\"的网站"));
        data_3.add(new yufaBean("domain.suffix=\"qianxin.com\" ","搜索主域为\"qianxin.com\"的网站"));
        data_3.add(new yufaBean("p=\"1.1.1.1\"","搜索IP为 ”1.1.1.1”的资产"));
        data_3.add(new yufaBean("ip=\"220.181.111.0/24\"","搜索网段为\"220.181.111.0\"的C段资产"));
        data_3.add(new yufaBean("ip.port=\"80\"","搜索开放端口为”80“的资产"));
        data_3.add(new yufaBean("ip.city=\"北京\"","搜索IP对应主机所在城市为”北京“市的资产"));
        data_3.add(new yufaBean("ip.isp=\"电信\"","搜索运营商为”中国电信”的资产"));
        tableView3.setItems(data_3);

        column4_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column4_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_4 = FXCollections.observableArrayList();
        data_4.add(new yufaBean("",""));
        data_4.add(new yufaBean("",""));
        tableView4.setItems(data_4);

        column5_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column5_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_5 = FXCollections.observableArrayList();
        data_5.add(new yufaBean("",""));
        data_5.add(new yufaBean("",""));
        tableView5.setItems(data_5);

        column6_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column6_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_6 = FXCollections.observableArrayList();
        data_6.add(new yufaBean("",""));
        data_6.add(new yufaBean("",""));
        tableView6.setItems(data_6);

        column7_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column7_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        ObservableList<yufaBean> data_7 = FXCollections.observableArrayList();
        data_7.add(new yufaBean("",""));
        data_7.add(new yufaBean("",""));
        tableView7.setItems(data_7);

    }

}