package com.inrech.jobs.youtube.model.channel;

import com.inrech.jobs.youtube.model.Page;

public class ChannelPage extends Page {
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
