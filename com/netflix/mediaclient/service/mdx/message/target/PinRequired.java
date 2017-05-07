// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PinRequired extends MdxMessage
{
    private static String PROPERTY_ancestorVideoId;
    private static String PROPERTY_ancestorVideoType;
    private static String PROPERTY_title;
    private static String PROPERTY_videoId;
    private int mAncestorVideoId;
    private String mAncestorVideoType;
    private String mTitle;
    private int mVideoId;
    
    static {
        PinRequired.PROPERTY_title = "title";
        PinRequired.PROPERTY_videoId = "videoId";
        PinRequired.PROPERTY_ancestorVideoId = "ancestorVideoId";
        PinRequired.PROPERTY_ancestorVideoType = "ancestorVideoType";
    }
    
    public PinRequired(final JSONObject jsonObject) {
        super("PIN_VERIFICATION_SHOW");
        this.mTitle = jsonObject.optString(PinRequired.PROPERTY_title);
        this.mVideoId = jsonObject.optInt(PinRequired.PROPERTY_videoId, -1);
        this.mAncestorVideoId = jsonObject.optInt(PinRequired.PROPERTY_ancestorVideoId, -1);
        this.mAncestorVideoType = jsonObject.optString(PinRequired.PROPERTY_ancestorVideoType);
    }
    
    public int getAncestorVideoId() {
        return this.mAncestorVideoId;
    }
    
    public String getAncestorVideoType() {
        return this.mAncestorVideoType;
    }
    
    public String getTitle() {
        return this.mTitle;
    }
    
    public int getVideoId() {
        return this.mVideoId;
    }
}
