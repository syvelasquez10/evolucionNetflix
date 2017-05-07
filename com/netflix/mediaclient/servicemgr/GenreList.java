// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.os.Parcelable;

public interface GenreList extends BasicLoMo, Parcelable
{
    int getNumVideos();
    
    boolean isKidsGenre();
}
