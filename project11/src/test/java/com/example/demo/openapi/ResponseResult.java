package com.example.demo.openapi;

import java.util.ArrayList;

import lombok.ToString;

@ToString
class Body{
    public String dataType;
    public Items items;
    public int pageNo;
    public int numOfRows;
    public int totalCount;
}

@ToString
class Header{
    public String resultCode;
    public String resultMsg;
}

@ToString
class Item{
    public Object announceTime;
    public int numEf;
    public String regId;
    public int rnSt;
    public int rnYn;
    public String ta;
    public String wd1;
    public String wd2;
    public String wdTnd;
    public String wf;
    public String wfCd;
    public String wsIt;
}

@ToString
class Items{
    public ArrayList<Item> item;
}

@ToString
class Response{
    public Header header;
    public Body body;
}

@ToString
class Root{
    public Response response;
}