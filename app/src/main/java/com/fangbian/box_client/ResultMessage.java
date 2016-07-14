package com.fangbian.box_client;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zt on 16/7/13.
 */
public class ResultMessage<T> {
    private T data;
    private String error;
    private long timespan;
    private String ip;

    public ResultMessage(T data, String error, long timespan,String ip) {
        this.data = data;
        this.error = error;
        this.timespan = timespan;
        this.ip=ip;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimespan() {
        return timespan;
    }

    public void setTimespan(long timespan) {
        this.timespan = timespan;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error",error);
        jsonObject.put("data",data);
        jsonObject.put("timespan",timespan);
        jsonObject.put("ip",ip);

        return jsonObject;
    }
}
