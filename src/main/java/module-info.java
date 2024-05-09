module com.example.huntertool {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.alibaba.fastjson2;


    opens com.huntertool to javafx.fxml;
    opens com.huntertool.bean to javafx.base;
    exports com.huntertool;
}