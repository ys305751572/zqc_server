package com.leoman.test.entity;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class TestEntity {
    private String request_no;
    private String plat_offer_id;
    private String activity_id;
    private String phone_id;
    private String contract_id;
    private String service_code;
    private String order_id;
    private String channel_id;

    public String getRequest_no() {
        return request_no;
    }

    public void setRequest_no(String request_no) {
        this.request_no = request_no;
    }

    public String getPlat_offer_id() {
        return plat_offer_id;
    }

    public void setPlat_offer_id(String plat_offer_id) {
        this.plat_offer_id = plat_offer_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    @Override
    public String toString() {
        return  "request_no:" + request_no + "==plat_offer_id:" + plat_offer_id + "==activity_id:" + activity_id + "==phone_id:" + phone_id
                + "==contract_id:" + contract_id + "==service_code:" + service_code + "==order_id:" + order_id + "==channel_id:" + channel_id;
    }
}
