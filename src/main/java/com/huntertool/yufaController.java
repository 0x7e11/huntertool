package com.huntertool;


import com.huntertool.bean.yufaBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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

        tableView1.setEditable(true);//设置为可编辑
        column1_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column1_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_1 = FXCollections.observableArrayList();

        data_1.add(new yufaBean("web.tag=\"登录页面\" ","查询包含资产标签\"登录页面\"的资产"));
        data_1.add(new yufaBean("domain.suffix=\"qianxin.com\"","搜索主域为\"qianxin.com\"的网站"));
        data_1.add(new yufaBean("web.similar_id=\"3322dfb483ea6fd250b29de488969b35\"","查询与该网页相似的资产"));
        data_1.add(new yufaBean("web.similar=\"baidu.com:443\"","查询与baidu.com:443网站的特征相似的资产"));
        data_1.add(new yufaBean("web.similar_icon==\"17262739310191283300\"","查询网站icon与该icon相似的资产"));
        data_1.add(new yufaBean("ip=\"1.1.1.1\"","搜索IP为 ”1.1.1.1”的资产"));
        data_1.add(new yufaBean("ip=\"220.181.111.0/24\"","搜索网段为\"220.181.111.0\"的C段资产"));
        data_1.add(new yufaBean("domain=\"qianxin.com\"","搜索域名包含\"qianxin.com\"的网站"));
        data_1.add(new yufaBean("domain.suffix=\"qianxin.com\" ","搜索主域为\"qianxin.com\"的网站"));
        data_1.add(new yufaBean("web.body=\"网络空间测绘\"","搜索网站正文包含”网络空间测绘“的资产"));
        data_1.add(new yufaBean("web.title=\"北京\"","从网站标题中搜索“北京”"));
        data_1.add(new yufaBean("app=\"Hikvision 海康威视 Firmware 5.0+\" && ip.ports=\"8000\"","检索使用了Hikvision且ip开放8000端口的资产"));
        data_1.add(new yufaBean("icp.web_name=\"奇安信\"","搜索ICP备案网站名中含有“奇安信”的资产"));
        tableView1.setItems(data_1);




        column2_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column2_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView2.setEditable(true);
        column2_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column2_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_2 = FXCollections.observableArrayList();
        data_2.add(new yufaBean("web.title=\"北京\"","从网站标题中搜索“北京”"));
        data_2.add(new yufaBean("web.tag=\"登录页面\"","查询包含资产标签\"登录页面\"的资产"));
        data_2.add(new yufaBean("web.body=\"网络空间测绘\"","搜索网站正文包含”网络空间测绘“的资产"));
        data_2.add(new yufaBean("icp.number=\"京ICP备16020626号-8\"","搜索通过域名关联的ICP备案号为”京ICP备16020626号-8”的网站资产"));
        data_2.add(new yufaBean("icp.web_name=\"奇安信\"","搜索ICP备案网站名中含有“奇安信”的资产"));
        data_2.add(new yufaBean("web.similar_icon==\"17262739310191283300\"","查询网站icon与该icon相似的资产"));
        data_2.add(new yufaBean("web.icon=\"22eeab765346f14faf564a4709f98548\"","查询网站icon与该icon相同的资产"));
        data_2.add(new yufaBean("web.similar_id=\"3322dfb483ea6fd250b29de488969b35\"","查询与该网页相似的资产"));
        tableView2.setItems(data_2);

        column3_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column3_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView3.setEditable(true);
        column3_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column3_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_3 = FXCollections.observableArrayList();
        data_3.add(new yufaBean("domain=\"qianxin.com\"","搜索域名包含\"qianxin.com\"的网站"));
        data_3.add(new yufaBean("domain.suffix=\"qianxin.com\" ","搜索主域为\"qianxin.com\"的网站"));
        data_3.add(new yufaBean("domain.created_date=\"2022-06-01\"","搜索域名创建时间为\"2022-06-01\"的网站"));
        data_3.add(new yufaBean("domain.expires_date=\"2022-06-01\"","搜索域名到期时间为\"2022-06-01\"的网站"));
        data_3.add(new yufaBean("domain.updated_date=\"2022-06-01\"","搜索域名更新时间为\"2022-06-01\"的网站"));
        data_3.add(new yufaBean("is_domain.cname=true","搜索含有cname解析记录的网站"));
        data_3.add(new yufaBean("ip=\"1.1.1.1\"","搜索IP为 ”1.1.1.1”的资产"));
        data_3.add(new yufaBean("ip=\"220.181.111.0/24\"","搜索网段为\"220.181.111.0\"的C段资产"));
        data_3.add(new yufaBean("ip.port=\"80\"","搜索开放端口为”80“的资产"));
        data_3.add(new yufaBean("ip.province=\"江苏\"","搜索IP对应主机在江苏省的资产"));
        data_3.add(new yufaBean("ip.city=\"北京\"","搜索IP对应主机所在城市为”北京“市的资产"));
        data_3.add(new yufaBean("ip.isp=\"电信\"","搜索运营商为”中国电信”的资产"));
        tableView3.setItems(data_3);

        column4_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column4_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView4.setEditable(true);
        column4_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column4_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_4 = FXCollections.observableArrayList();
        data_4.add(new yufaBean("icp.number=\"京ICP备16020626号-8\"","搜索通过域名关联的ICP备案号为”京ICP备16020626号-8”的网站资产"));
        data_4.add(new yufaBean("icp.web_name=\"奇安信\"","搜索ICP备案网站名中含有“奇安信”的资产"));
        data_4.add(new yufaBean("icp.name=\"奇安信\"","搜索ICP备案单位名中含有“奇安信”的资产"));
        data_4.add(new yufaBean("icp.type=\"企业\"","搜索ICP备案主体为“企业”的资产"));
        data_4.add(new yufaBean("icp.industry=\"软件和信息技术服务业\"","搜索ICP备案行业为“软件和信息技术服务业”的资产"));
        tableView4.setItems(data_4);

        column5_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column5_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView5.setEditable(true);
        column5_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column5_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_5 = FXCollections.observableArrayList();
        data_5.add(new yufaBean("app.name=\"小米 Router\"","搜索标记为”小米 Router“的资产"));
        data_5.add(new yufaBean("app.type=\"开发与运维\"","查询包含组件分类为\"开发与运维\"的资产"));
        data_5.add(new yufaBean("app.vendor=\"PHP\"","查询包含组件厂商为\"PHP\"的资产"));
        data_5.add(new yufaBean("app.version=\"1.8.1\"","查询包含组件版本为\"1.8.1\"的资产"));
        data_5.add(new yufaBean("app=\"Hikvision 海康威视 Firmware 5.0+\" && ip.ports=\"8000\"","检索使用了Hikvision且ip开放8000端口的资产"));
        tableView5.setItems(data_5);

        column6_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column6_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView6.setEditable(true);
        column6_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column6_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_6 = FXCollections.observableArrayList();
        data_6.add(new yufaBean("web.similar=\"baidu.com:443\"","通过网络特征搜索资产"));
        data_6.add(new yufaBean("web.similar_id=\"3322dfb483ea6fd250b29de488969b35\"","搜索相似网站"));
        data_6.add(new yufaBean("web.similar_icon==\"17262739310191283300\"","搜索相似网站icon搜索资产"));
        data_6.add(new yufaBean("web.tag=\"登录页面\"","搜索网站标签搜索资产"));
        data_6.add(new yufaBean("ip.tag=\"CDN\"","ip.tag=\"CDN\""));
        tableView6.setItems(data_6);

        column7_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        column7_illustrate.setCellValueFactory(new PropertyValueFactory<>("illustrate"));
        tableView7.setEditable(true);
        column7_content.setCellFactory(TextFieldTableCell.forTableColumn());
        column7_illustrate.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<yufaBean> data_7 = FXCollections.observableArrayList();
        data_7.add(new yufaBean("cert=\"baidu\"","搜索证书中带有baidu的资产"));
        data_7.add(new yufaBean("cert.subject=\"qianxin.com\"","搜索证书使用者包含qianxin.com的资产"));
        data_7.add(new yufaBean("cert.subject.suffix=\"qianxin.com\" ","搜索证书使用者为qianxin.com的资产"));
        data_7.add(new yufaBean("cert.subject_org=\"奇安信科技集团股份有限公司\"","搜索证书使用者组织是奇安信科技集团股份有限公司的资产"));
        data_7.add(new yufaBean("cert.sha-1=\"be7605a3b72b60fcaa6c58b6896b9e2e7442ec50\"","搜索证书签名哈希算法sha1为be7605a3b72b60fcaa6c58b6896b9e2e7442ec50的资产"));
        data_7.add(new yufaBean("cert.is_trust=true ","搜索证书可信的资产"));
        tableView7.setItems(data_7);

    }

}