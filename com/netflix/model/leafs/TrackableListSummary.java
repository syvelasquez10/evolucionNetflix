// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;

public class TrackableListSummary extends ListSummary implements Trackable, JsonPopulator
{
    private static final String TAG = "TrackableListSummary";
    private int listPos;
    private String requestId;
    private int trackId;
    
    public TrackableListSummary() {
        super(0);
    }
    
    protected TrackableListSummary(final int n, final int trackId, final int listPos, final String requestId) {
        super(n);
        this.trackId = trackId;
        this.listPos = listPos;
        this.requestId = requestId;
    }
    
    protected TrackableListSummary(final Parcel parcel) {
        super(parcel);
        this.trackId = parcel.readInt();
        this.listPos = parcel.readInt();
        this.requestId = parcel.readString();
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
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("TrackableListSummary", 2)) {
            Log.v("TrackableListSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0126: {
                switch (s.hashCode()) {
                    case -1067396154: {
                        if (s.equals("trackId")) {
                            n = 0;
                            break Label_0126;
                        }
                        break;
                    }
                    case 181951702: {
                        if (s.equals("listPos")) {
                            n = 1;
                            break Label_0126;
                        }
                        break;
                    }
                    case 693933066: {
                        if (s.equals("requestId")) {
                            n = 2;
                            break Label_0126;
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
                    this.trackId = entry.getValue().getAsInt();
                    continue;
                }
                case 1: {
                    this.listPos = entry.getValue().getAsInt();
                    continue;
                }
                case 2: {
                    this.requestId = entry.getValue().getAsString();
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
        parcel.writeInt(this.listPos);
        parcel.writeString(this.requestId);
    }
}
