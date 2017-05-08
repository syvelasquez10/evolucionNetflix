// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView$LayoutManager;

public interface ILayoutManager
{
    RecyclerView$LayoutManager getLayoutManager();
    
    void setLayoutManagerSavedState(final Parcelable p0);
}
