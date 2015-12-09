package cn.liborange.message.transmit;

/**
 * Created by liborange on 15/10/2.
 */
public class Image {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    public String toString() {
        return "<MediaId><![CDATA["+mediaId+"]]></MediaId>";
    }
}
