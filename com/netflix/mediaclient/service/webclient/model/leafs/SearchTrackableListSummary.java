// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;

public class SearchTrackableListSummary extends TrackableListSummary implements SearchTrackable
{
    @SerializedName("reference")
    private String reference;
    
    public SearchTrackableListSummary(final int n, final int n2, final int n3, final String s) {
        super(n, n2, n3, s);
    }
    
    public SearchTrackableListSummary(final Parcel parcel) {
        super(parcel);
    }
    
    @Override
    public String getReferenceId() {
        return this.reference;
    }
}
