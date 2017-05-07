// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public class SubtitleData extends BaseMediaEvent
{
    private static final String ATTR_DATA = "data";
    private static final String ATTR_HEIGHT = "height";
    private static final String ATTR_LANGUAGE = "language";
    private static final String ATTR_MASTER_INDEX_OFFSET = "masterIndexOffset";
    private static final String ATTR_MASTER_INDEX_SIZE = "masterIndexSize";
    private static final String ATTR_PROFILE = "profile";
    private static final String ATTR_WIDTH = "width";
    public static final String TYPE = "subtitledata";
    private int mHeight;
    private String mLanguage;
    private int mMasterIndexOffset;
    private int mMasterIndexSize;
    private IMedia$SubtitleProfile mProfile;
    private String mUrl;
    private int mWidth;
    
    public SubtitleData(final JSONObject jsonObject) {
        super("subtitledata", jsonObject);
    }
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public String getLanguage() {
        return this.mLanguage;
    }
    
    public int getMasterIndexOffset() {
        return this.mMasterIndexOffset;
    }
    
    public int getMasterIndexSize() {
        return this.mMasterIndexSize;
    }
    
    public IMedia$SubtitleProfile getProfile() {
        return this.mProfile;
    }
    
    public String getUrl() {
        return this.mUrl;
    }
    
    public int getWidth() {
        return this.mWidth;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.mUrl = BaseNccpEvent.getString(jsonObject, "data", null);
        this.mProfile = IMedia$SubtitleProfile.fromNccpCode(BaseNccpEvent.getString(jsonObject, "profile", null));
        this.mLanguage = BaseNccpEvent.getString(jsonObject, "language", null);
        if (this.mProfile == IMedia$SubtitleProfile.IMAGE) {
            this.mHeight = BaseNccpEvent.getInt(jsonObject, "height", 0);
            this.mWidth = BaseNccpEvent.getInt(jsonObject, "width", 0);
            this.mMasterIndexSize = BaseNccpEvent.getInt(jsonObject, "masterIndexSize", 0);
            this.mMasterIndexOffset = BaseNccpEvent.getInt(jsonObject, "masterIndexOffset", 0);
        }
    }
    
    @Override
    public String toString() {
        return "SubtitleData [mUrl=" + this.mUrl + ", mLanguage=" + this.mLanguage + ", mHeight=" + this.mHeight + ", mWidth=" + this.mWidth + ", mProfile=" + this.mProfile + ", mMasterIndexSize=" + this.mMasterIndexSize + ", mMasterIndexOffset=" + this.mMasterIndexOffset + "]";
    }
}
