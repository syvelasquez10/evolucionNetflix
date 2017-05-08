// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.genre;

import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import android.os.Parcelable;

public interface GenreList extends Parcelable, BasicLoMo
{
    GenreList$GenreType getGenreType();
    
    int getNumVideos();
    
    boolean isKidsGenre();
}
