package com.huntertool.bean;

public class yufaBean {
    private String content;
    private String illustrate;

    public yufaBean(String content,String illustrate){
        this.content = content;
        this.illustrate = illustrate;
    }
    public String getContent(){
        return content;
    }

    public String getIllustrate(){
        return illustrate;
    }

    public void setContent(String content){
        this.content = content;
    }

    public  void  setIllustrate(String illustrate){
        this.illustrate = illustrate;
    }

}
