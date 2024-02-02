package com.huntertool.bean;
public class JsonBean {
    private int id;
    private String is_risk;
    private String url;
    private String ip;
    private String port;
    private String web_title;
    private String domain;
    private String is_risk_protocol;
    private String protocol;
    private String base_protocol;
    private String status_code;
    private String component;
    private String os;
    private String company;
    private String number;
    private String country;
    private String province;
    private String city;
    private String updated_at;
    private String is_web;
    private String as_org;
    private String isp;
    private String banner;
    private String vul_list;
    public JsonBean(){
    }

    public JsonBean(int id,String is_risk, String url, String ip, String port, String web_title, String domain, String is_risk_protocol, String protocol, String base_protocol, String status_code, String component, String os, String company, String number, String country, String province, String city, String updated_at, String is_web, String as_org, String isp, String banner, String vul_list) {
        this.id = id;
        this.is_risk = is_risk;
        this.url = url;
        this.ip = ip;
        this.port = port;
        this.web_title = web_title;
        this.domain = domain;
        this.is_risk_protocol = is_risk_protocol;
        this.protocol = protocol;
        this.base_protocol = base_protocol;
        this.status_code = status_code;
        this.component = component;
        this.os = os;
        this.company = company;
        this.number = number;
        this.country = country;
        this.province = province;
        this.city = city;
        this.updated_at = updated_at;
        this.is_web = is_web;
        this.as_org = as_org;
        this.isp = isp;
        this.banner = banner;
        this.vul_list = vul_list;
    }
    public int getId() {
        return id;
    }
    // Getter 方法
    public String getIs_risk() {
        return is_risk;
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getWeb_title() {
        return web_title;
    }

    public String getDomain() {
        return domain;
    }

    public String getIs_risk_protocol() {
        return is_risk_protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBase_protocol() {
        return base_protocol;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getComponent() {
        return component;
    }

    public String getOs() {
        return os;
    }

    public String getCompany() {
        return company;
    }

    public String getNumber() {
        return number;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getIs_web() {
        return is_web;
    }

    public String getAs_org() {
        return as_org;
    }

    public String getIsp() {
        return isp;
    }

    public String getBanner() {
        return banner;
    }

    public String getVul_list() {
        return vul_list;
    }

    // Setter 方法
    public void setid(int id) { this.id = id; }
    public void setIs_risk(String is_risk) {
        this.is_risk = is_risk;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setWeb_title(String web_title) {
        this.web_title = web_title;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setIs_risk_protocol(String is_risk_protocol) {
        this.is_risk_protocol = is_risk_protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setBase_protocol(String base_protocol) {
        this.base_protocol = base_protocol;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setIs_web(String is_web) {
        this.is_web = is_web;
    }

    public void setAs_org(String as_org) {
        this.as_org = as_org;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void setVul_list(String vul_list) {
        this.vul_list = vul_list;
    }
}
