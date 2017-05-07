// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class MetaDataChanged extends MdxMessage
{
    private static final String PROPERTY_catalogId = "catalogId";
    private static final String PROPERTY_episodeId = "episodeId";
    private static final String PROPERTY_type = "type";
    private String mCatalogId;
    private String mEpisodeId;
    private String mType;
    
    public MetaDataChanged(final JSONObject mJson) {
        super("META_DATA_CHANGED");
        this.mJson = mJson;
        this.mCatalogId = mJson.getString("catalogId");
        this.mEpisodeId = mJson.optString("episodeId", (String)null);
        this.mType = mJson.getString("type");
    }
    
    public String getCatalogId() {
        return this.mCatalogId;
    }
    
    public String getEpisodeId() {
        return this.mEpisodeId;
    }
    
    public String getType() {
        return this.mType;
    }
}
