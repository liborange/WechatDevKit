package cn.liborange.message.receive;

import cn.liborange.message.InMessage;


/**
 * Created by liborange on 15/10/7.
 */
public class EventIn extends InMessage {
    private String event;
    private String eventKey;
    private String ticket;
    private String latitude;    //	地理位置纬度
    private String longitude;   //	地理位置经度
    private String precision;   //	地理位置精度

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getLongitude() {

        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {

        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTicket() {

        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getEventKey() {

        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
