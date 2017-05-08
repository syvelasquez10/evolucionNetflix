// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import org.json.JSONObject;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.media.Subtitle;

public class OfflineImageSubtitle extends OfflineSubtitle
{
    protected static final String ATTR_MASTER_INDEX_OFFSET = "masterIndexOffset";
    protected static final String ATTR_MASTER_INDEX_SIZE = "masterIndexSize";
    public static final int IMPL_VALUE = 4;
    private int mMasterIndexOffset;
    private int mMasterIndexSize;
    
    public OfflineImageSubtitle(final Subtitle subtitle, final SubtitleUrl subtitleUrl, final String s) {
        super(subtitle, subtitleUrl, s);
        this.mMasterIndexOffset = subtitleUrl.getMasterIndexOffset();
        this.mMasterIndexSize = subtitleUrl.getMasterIndexSize();
    }
    
    public OfflineImageSubtitle(final JSONObject jsonObject) {
        super(jsonObject);
        this.mMasterIndexSize = jsonObject.getInt("masterIndexSize");
        this.mMasterIndexOffset = jsonObject.getInt("masterIndexOffset");
    }
    
    @Override
    protected int getImplementation() {
        return 4;
    }
    
    public int getMasterIndexOffset() {
        return this.mMasterIndexOffset;
    }
    
    public int getMasterIndexSize() {
        return this.mMasterIndexSize;
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = super.toJson();
        json.put("masterIndexOffset", this.mMasterIndexOffset);
        json.put("masterIndexSize", this.mMasterIndexSize);
        return json;
    }
}
