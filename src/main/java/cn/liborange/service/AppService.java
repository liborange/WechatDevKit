package cn.liborange.service;

import cn.liborange.backstage.WeXClient;
import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;

/**
 * Created by liborange on 15/9/30.
 */
public abstract class AppService {
    protected WeXClient client;

    public void setClient(WeXClient client) {
        this.client = client;
    }

    /**
     * 每个处理请求的应用都应该实现这个接口
     * 对InMessage处理后返回处理结果
     * @param inMessage
     * @return
     */
    protected abstract OutMessage process(InMessage inMessage);
}
