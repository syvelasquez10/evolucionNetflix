// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Parcelable;
import java.util.Stack;
import android.os.Bundle;

public class RelatedTitlesHistorySaveInstanceState
{
    public static final String RELATED_TITLES_INSTANCE_STATE = "RELATED_TITLES_INSTANCE_STATE";
    private Bundle outState;
    private Stack<RelatedTitleState> relatedTitlesHistory;
    
    public RelatedTitlesHistorySaveInstanceState(final Bundle outState, final Stack<RelatedTitleState> relatedTitlesHistory) {
        this.relatedTitlesHistory = relatedTitlesHistory;
        this.outState = outState;
    }
    
    public void invoke() {
        this.outState.putParcelableArray("RELATED_TITLES_INSTANCE_STATE", (Parcelable[])this.relatedTitlesHistory.toArray(new Parcelable[this.relatedTitlesHistory.size()]));
    }
}
