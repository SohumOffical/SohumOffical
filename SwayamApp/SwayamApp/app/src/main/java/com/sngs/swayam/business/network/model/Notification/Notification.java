
package com.sngs.swayam.business.network.model.Notification;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Notification {

    @SerializedName("notiId")
    private String mNotiId;
    @SerializedName("notiMessage")
    private String mNotiMessage;
    @SerializedName("notiType")
    private String mNotiType;

    public String getNotiId() {
        return mNotiId;
    }

    public void setNotiId(String notiId) {
        mNotiId = notiId;
    }

    public String getNotiMessage() {
        return mNotiMessage;
    }

    public void setNotiMessage(String notiMessage) {
        mNotiMessage = notiMessage;
    }

    public String getNotiType() {
        return mNotiType;
    }

    public void setNotiType(String notiType) {
        mNotiType = notiType;
    }

}
