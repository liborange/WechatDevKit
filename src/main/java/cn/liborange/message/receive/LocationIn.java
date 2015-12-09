package cn.liborange.message.receive;

import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class LocationIn extends InMessage {
    private String location_X;  //	地理位置维度
    private String location_Y;  //	地理位置经度
    private String  scale;      //	地图缩放大小
    private String  label;      //	地理位置信息


    //getter setter
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScale() {

        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLocation_Y() {

        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getLocation_X() {

        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }
}
