// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.genre;

import android.os.Parcelable;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;

public interface GenreList extends BasicLoMo, Parcelable
{
    int getNumVideos();
    
    boolean isKidsGenre();
}
