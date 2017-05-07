// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerPlay extends MdxMessage
{
    private static final String ORIGIN_USER = "USER";
    private static final String PROPERTY_catalogId = "catalogId";
    private static final String PROPERTY_enablePostPlay = "enablePostPlay";
    private static final String PROPERTY_episodeId = "episodeId";
    private static final String PROPERTY_esn = "esn";
    private static final String PROPERTY_isDial = "isDial";
    private static final String PROPERTY_isPinVerified = "isPinVerified";
    private static final String PROPERTY_isThirdParty = "isThirdParty";
    private static final String PROPERTY_originator = "originator";
    private static final String PROPERTY_startTime = "startTime";
    private static final String PROPERTY_trackId = "trackId";
    private final String mCatalogId;
    private String mEpisodeId;
    private final String mEsn;
    private Boolean mIsDial;
    private Boolean mIsThirdParty;
    private String mOrigin;
    private Integer mStartTime;
    private final int mTrackId;
    
    public PlayerPlay(final String mCatalogId, final int mTrackId, final String mEsn) {
        super("PLAYER_PLAY");
        this.mOrigin = "USER";
        this.mCatalogId = mCatalogId;
        this.mTrackId = mTrackId;
        this.mEsn = mEsn;
        this.createObj();
    }
    
    public PlayerPlay(final String mCatalogId, final int mTrackId, final String mEsn, final String mEpisodeId, final Integer mStartTime) {
        super("PLAYER_PLAY");
        this.mOrigin = "USER";
        this.mCatalogId = mCatalogId;
        this.mTrackId = mTrackId;
        this.mEsn = mEsn;
        this.mEpisodeId = mEpisodeId;
        this.mStartTime = mStartTime;
        this.createObj();
    }
    
    public PlayerPlay(final String s, final String mCatalogId, final int mTrackId, final String mEsn, final String mEpisodeId, final Integer mStartTime, final String mOrigin, final Boolean mIsDial, final Boolean mIsThirdParty) {
        super("PLAYER_PLAY");
        this.mOrigin = "USER";
        this.mCatalogId = mCatalogId;
        this.mTrackId = mTrackId;
        this.mEsn = mEsn;
        this.mEpisodeId = mEpisodeId;
        this.mStartTime = mStartTime;
        this.mOrigin = mOrigin;
        this.mIsDial = mIsDial;
        this.mIsThirdParty = mIsThirdParty;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("catalogId", (Object)this.mCatalogId);
            this.mJson.put("trackId", this.mTrackId);
            this.mJson.put("esn", (Object)this.mEsn);
            this.mJson.put("enablePostPlay", true);
            if (PinVerifier.isPinSessionActive()) {
                Log.d("nf_mdx", "nf_pin  sessionActive adding isPInVerified to true");
                this.mJson.put("isPinVerified", true);
            }
            if (this.mEpisodeId != null) {
                this.mJson.put("episodeId", (Object)this.mEpisodeId);
            }
            if (this.mStartTime != null && this.mStartTime >= 0) {
                this.mJson.put("startTime", (int)this.mStartTime);
            }
            if (this.mOrigin != null) {
                this.mJson.put("originator", (Object)this.mOrigin);
            }
            if (this.mIsDial != null) {
                this.mJson.put("isDial", (Object)this.mIsDial);
            }
            if (this.mIsThirdParty != null) {
                this.mJson.put("isThirdParty", (Object)this.mIsThirdParty);
            }
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
