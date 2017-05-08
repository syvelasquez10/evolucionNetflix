// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import android.os.Parcelable;

public class IrisNotificationsListSummary implements Parcelable, JsonMerger, JsonPopulator
{
    public static final Parcelable$Creator<IrisNotificationsListSummary> CREATOR;
    private static final String TAG = "SocialNotificationsListSummary";
    @SerializedName("baseTrackId")
    private int baseTrackId;
    @SerializedName("length")
    private long length;
    @SerializedName("mdpTrackId")
    private int mdpTrackId;
    @SerializedName("playerTrackId")
    private int playerTrackId;
    @SerializedName("requestId")
    private String requestId;
    
    static {
        CREATOR = (Parcelable$Creator)new IrisNotificationsListSummary$1();
    }
    
    public IrisNotificationsListSummary() {
    }
    
    protected IrisNotificationsListSummary(final Parcel parcel) {
        final String[] array = new String[5];
        parcel.readStringArray(array);
        this.length = Long.valueOf(array[0]);
        this.requestId = array[1];
        this.baseTrackId = Integer.valueOf(array[2]);
        this.mdpTrackId = Integer.valueOf(array[3]);
        this.playerTrackId = Integer.valueOf(array[4]);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null && o instanceof IrisNotificationsListSummary) {
            final IrisNotificationsListSummary irisNotificationsListSummary = (IrisNotificationsListSummary)o;
            if (this.getLength() == irisNotificationsListSummary.getLength() && this.getBaseTrackId() == irisNotificationsListSummary.getBaseTrackId() && this.getMDPTrackId() == irisNotificationsListSummary.getMDPTrackId() && this.getPlayerTrackId() == irisNotificationsListSummary.getPlayerTrackId()) {
                return true;
            }
        }
        return false;
    }
    
    public int getBaseTrackId() {
        return this.baseTrackId;
    }
    
    public long getLength() {
        return this.length;
    }
    
    public int getMDPTrackId() {
        return this.mdpTrackId;
    }
    
    public int getPlayerTrackId() {
        return this.playerTrackId;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int hashCode() {
        return (int)this.getLength();
    }
    
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationsListSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0146: {
                switch (s.hashCode()) {
                    case -1106363674: {
                        if (s.equals("length")) {
                            n = 0;
                            break Label_0146;
                        }
                        break;
                    }
                    case 693933066: {
                        if (s.equals("requestId")) {
                            n = 1;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1234061237: {
                        if (s.equals("baseTrackId")) {
                            n = 2;
                            break Label_0146;
                        }
                        break;
                    }
                    case -2078436403: {
                        if (s.equals("mdpTrackId")) {
                            n = 3;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1585578405: {
                        if (s.equals("playerTrackId")) {
                            n = 4;
                            break Label_0146;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.length = jsonElement2.getAsInt();
                    continue;
                }
                case 1: {
                    this.requestId = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.baseTrackId = jsonElement2.getAsInt();
                    continue;
                }
                case 3: {
                    this.mdpTrackId = jsonElement2.getAsInt();
                    continue;
                }
                case 4: {
                    this.playerTrackId = jsonElement2.getAsInt();
                    continue;
                }
            }
        }
    }
    
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationsListSummary", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "length": {
                this.length = jsonParser.getValueAsInt();
                break;
            }
            case "requestId": {
                this.requestId = jsonParser.getValueAsString();
                break;
            }
            case "baseTrackId": {
                this.baseTrackId = jsonParser.getValueAsInt();
                break;
            }
            case "mdpTrackId": {
                this.mdpTrackId = jsonParser.getValueAsInt();
                break;
            }
            case "playerTrackId": {
                this.playerTrackId = jsonParser.getValueAsInt();
                break;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "FalkorSocialNotificationsListSummary [mLength=" + this.length + ", mRequestId=" + this.requestId + ", mBaseTrackId=" + this.baseTrackId + ", mMDPTrackId=" + this.mdpTrackId + ", mPlayerTrackId=" + this.playerTrackId + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStringArray(new String[] { String.valueOf(this.length), this.requestId, String.valueOf(this.baseTrackId), String.valueOf(this.mdpTrackId), String.valueOf(this.playerTrackId) });
    }
}
