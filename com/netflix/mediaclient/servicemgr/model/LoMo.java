// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import java.util.List;
import android.os.Parcelable;

public interface LoMo extends BasicLoMo, Parcelable, FriendProfilesProvider
{
    List<String> getMoreImages();
    
    int getNumVideos();
    
    boolean isBillboard();
    
    void setListPos(final int p0);
}
