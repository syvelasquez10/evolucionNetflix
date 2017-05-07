// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import java.util.List;
import android.os.Parcelable;

public interface LoMo extends Parcelable, BasicLoMo, FriendProfilesProvider
{
    List<String> getMoreImages();
    
    int getNumVideos();
    
    boolean isBillboard();
    
    void setId(final String p0);
    
    void setListPos(final int p0);
}
