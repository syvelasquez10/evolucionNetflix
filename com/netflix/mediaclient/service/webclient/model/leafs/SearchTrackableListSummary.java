// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;

public class SearchTrackableListSummary extends TrackableListSummary implements SearchTrackable
{
    @SerializedName("reference")
    private String reference;
    
    @Override
    public String getReferenceId() {
        return this.reference;
    }
}
