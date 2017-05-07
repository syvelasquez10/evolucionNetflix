// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Parcelable;
import android.os.Bundle;
import java.util.Stack;

public class RelatedTitlesHistoryRestoreInstanceState
{
    public static final String RELATED_TITLES_INSTANCE_STATE = "RELATED_TITLES_INSTANCE_STATE";
    private Stack<RelatedTitleState> relatedTitlesHistory;
    private Bundle savedInstanceState;
    
    public RelatedTitlesHistoryRestoreInstanceState(final Bundle savedInstanceState, final Stack<RelatedTitleState> relatedTitlesHistory) {
        this.relatedTitlesHistory = relatedTitlesHistory;
        this.savedInstanceState = savedInstanceState;
    }
    
    public void invoke() {
        final Parcelable[] parcelableArray = this.savedInstanceState.getParcelableArray("RELATED_TITLES_INSTANCE_STATE");
        if (parcelableArray != null && parcelableArray.length > 0) {
            this.relatedTitlesHistory.empty();
            for (int i = 0; i < parcelableArray.length; ++i) {
                this.relatedTitlesHistory.push((RelatedTitleState)parcelableArray[i]);
            }
        }
    }
}
