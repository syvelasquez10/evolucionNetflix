// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class TrackableListSummary extends ListSummary implements JsonPopulator, Trackable
{
    private static final String TAG = "TrackableListSummary";
    private int heroTrackId;
    private int listPos;
    private String requestId;
    private int trackId;
    
    public TrackableListSummary() {
    }
    
    protected TrackableListSummary(final Parcel parcel) {
        super(parcel);
        this.trackId = parcel.readInt();
        this.heroTrackId = parcel.readInt();
        this.listPos = parcel.readInt();
        this.requestId = parcel.readString();
    }
    
    @Override
    public int getHeroTrackId() {
        return this.heroTrackId;
    }
    
    @Override
    public int getListPos() {
        return this.listPos;
    }
    
    @Override
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public boolean isHero() {
        return false;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("TrackableListSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0130: {
                switch (s.hashCode()) {
                    case -1067396154: {
                        if (s.equals("trackId")) {
                            n = 0;
                            break Label_0130;
                        }
                        break;
                    }
                    case 311922284: {
                        if (s.equals("heroTrackId")) {
                            n = 1;
                            break Label_0130;
                        }
                        break;
                    }
                    case 181951702: {
                        if (s.equals("listPos")) {
                            n = 2;
                            break Label_0130;
                        }
                        break;
                    }
                    case 693933066: {
                        if (s.equals("requestId")) {
                            n = 3;
                            break Label_0130;
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
                    this.trackId = JsonUtils.getAsIntSafe(entry.getValue());
                    continue;
                }
                case 1: {
                    this.heroTrackId = JsonUtils.getAsIntSafe(entry.getValue());
                    continue;
                }
                case 2: {
                    this.listPos = JsonUtils.getAsIntSafe(entry.getValue());
                    continue;
                }
                case 3: {
                    this.requestId = JsonUtils.getAsStringSafe(entry.getValue());
                    continue;
                }
            }
        }
    }
    
    public void setListPos(final int listPos) {
        this.listPos = listPos;
    }
    
    @Override
    public String toString() {
        return "TrackableListSummary [trackId=" + this.trackId + ", listPos=" + this.listPos + ", requestId=" + this.requestId + "]";
    }
    
    @Override
    protected void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.trackId);
        parcel.writeInt(this.heroTrackId);
        parcel.writeInt(this.listPos);
        parcel.writeString(this.requestId);
    }
}
