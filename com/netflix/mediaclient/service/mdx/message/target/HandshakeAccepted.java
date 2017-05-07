// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class HandshakeAccepted extends MdxMessage
{
    private static final String PROPERTY_accepted = "accepted";
    private static final String PROPERTY_contractVersion = "contractVersion";
    private static final String PROPERTY_volumeControl = "volumeControl";
    private static final String PROPERTY_volumeStep = "volumeStep";
    private boolean mAccepted;
    private int mContractVersion;
    private boolean mVolumeControl;
    private int mVolumeStep;
    
    public HandshakeAccepted(final JSONObject mJson) throws JSONException {
        super("HANDSHAKE_ACCEPTED");
        this.mJson = mJson;
        this.mContractVersion = mJson.getInt("contractVersion");
        this.mAccepted = mJson.getBoolean("accepted");
        if (mJson.has("volumeControl")) {
            this.mVolumeControl = mJson.getBoolean("volumeControl");
        }
        if (mJson.has("volumeStep")) {
            this.mVolumeStep = mJson.getInt("volumeStep");
        }
    }
    
    public int getContractVersion() {
        return this.mContractVersion;
    }
    
    public int getVolumeStep() {
        return this.mVolumeStep;
    }
    
    public boolean isAccepted() {
        return this.mAccepted;
    }
    
    public boolean isVolumeControl() {
        return this.mVolumeControl;
    }
    
    @Override
    public String toString() {
        return "HandshakeAccepted [contractVersion=" + this.mContractVersion + ", accepted=" + this.mAccepted + ", volumeControl=" + this.mVolumeControl + ", volumeStep=" + this.mVolumeStep + "]";
    }
}
