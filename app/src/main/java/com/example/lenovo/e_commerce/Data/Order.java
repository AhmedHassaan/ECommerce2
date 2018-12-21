package com.example.lenovo.e_commerce.Data;

import java.util.HashMap;
import java.util.Map;

public class Order {
    String odate, address;
    Map<String,Integer> oproducts;
    int oid, ouid;

    public Order(){
        oproducts = new HashMap<>();
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Map<String, Integer> getOproducts() {
        return oproducts;
    }

    public void setOproducts(Map<String, Integer> oproducts) {
        this.oproducts = oproducts;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public int getOuid() {
        return ouid;
    }

    public void setOuid(int ouid) {
        this.ouid = ouid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addProduct(String pid, Integer quantity){
        oproducts.put(pid,quantity);
    }
}
