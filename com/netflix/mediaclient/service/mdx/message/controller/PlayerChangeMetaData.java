// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerChangeMetaData extends MdxMessage
{
    private static final String PROPERTY_catalogId = "catalogId";
    private static final String PROPERTY_episodeId = "episodeId";
    private static final String PROPERTY_type = "type";
    private String mCatalogId;
    private String mEpisodeId;
    private String mType;
    
    public PlayerChangeMetaData(final String mCatalogId, final String mEpisodeId, final String mType) {
        super("META_DATA_CHANGED");
        this.mCatalogId = mCatalogId;
        this.mEpisodeId = mEpisodeId;
        this.mType = mType;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("catalogId", (Object)this.mCatalogId);
            this.mJson.put("episodeId", (Object)this.mEpisodeId);
            this.mJson.put("type", (Object)this.mType);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
