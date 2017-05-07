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

public class ListSummary implements JsonPopulator
{
    private static final String TAG = "ListSummary";
    private int length;
    
    public ListSummary(final int length) {
        this.length = length;
    }
    
    protected ListSummary(final Parcel parcel) {
        this.length = parcel.readInt();
    }
    
    public int getLength() {
        return this.length;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("ListSummary", 2)) {
            Log.v("ListSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0106: {
                switch (s.hashCode()) {
                    case -1106363674: {
                        if (s.equals("length")) {
                            n = 0;
                            break Label_0106;
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
                    this.length = entry.getValue().getAsInt();
                    continue;
                }
            }
        }
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    protected void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.length);
    }
}
