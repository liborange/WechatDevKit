package cn.liborange.message.transmit;

import cn.liborange.message.OutMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class ImageOut extends OutMessage {
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;
}
