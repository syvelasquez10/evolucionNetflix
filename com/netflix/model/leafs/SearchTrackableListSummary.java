// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;

public class SearchTrackableListSummary extends TrackableListSummary implements SearchTrackable
{
    private static final String TAG = "SearchTrackableListSummary";
    private String reference;
    
    public SearchTrackableListSummary() {
    }
    
    protected SearchTrackableListSummary(final Parcel parcel) {
        super(parcel);
        this.reference = parcel.readString();
    }
    
    @Override
    public String getReferenceId() {
        return this.reference;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SearchTrackableListSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0106: {
                switch (s.hashCode()) {
                    case -925155509: {
                        if (s.equals("reference")) {
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
                    this.reference = JsonUtils.getAsStringSafe(entry.getValue());
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        super.set(s, jsonParser);
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SearchTrackableListSummary", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "reference": {
                this.reference = jsonParser.getValueAsString();
                return true;
            }
        }
    }
    
    @Override
    protected void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.reference);
    }
}
