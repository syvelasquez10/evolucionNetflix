// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONObject;

public final class RecommendImplicitFeedbackReadEvent extends BaseSocialDiscreteEvent
{
    private static final String MSG_ID = "msgId";
    protected static final String NAME = "recommendImplicitFeedbackRead";
    private static final String VIDEO_ID = "videoId";
    private String mMsgId;
    private int mTrackId;
    private String mVideoId;
    
    public RecommendImplicitFeedbackReadEvent(final String mMsgId, final String mVideoId, final int mTrackId) {
        super("recommendImplicitFeedbackRead");
        this.mTrackId = mTrackId;
        this.mMsgId = mMsgId;
        this.mVideoId = mVideoId;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("trackId", this.mTrackId);
        if (this.mMsgId != null) {
            data.put("msgId", (Object)this.mMsgId);
        }
        if (this.mVideoId != null) {
            data.put("videoId", (Object)this.mVideoId);
        }
        return data;
    }
}
