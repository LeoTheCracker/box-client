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

    public ResultMessage(T data, String error, long timespan) {
        this.data = data;
        this.error = error;
        this.timespan = timespan;
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

    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error",error);
        jsonObject.put("data",data);
        jsonObject.put("timespan",timespan);

        return jsonObject;
    }
}
