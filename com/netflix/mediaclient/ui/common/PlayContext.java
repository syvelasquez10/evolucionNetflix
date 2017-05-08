// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.os.Parcelable;

public interface PlayContext extends Parcelable, Trackable
{
    public static final int DEFAULT_MDX_TRACKID = 13804431;
    public static final PlayContext DFLT_MDX_CONTEXT = new PlayContextImp(null, 13804431, 0, 0);
    public static final PlayContext EMPTY_CONTEXT = new PlayContextImp();
    public static final int NFLX_MDX_3RD_PARTY_LAUNCHER_TRACKID = 13747225;
    public static final PlayContext NFLX_MDX_CONTEXT = new PlayContextImp(null, 13747225, 0, 0);
    public static final PlayContext OFFLINE_MY_DOWNLOADS_CONTEXT = new PlayContextImp(null, 15233083, 0, 0);
    public static final PlayContext OFFLINE_NOTIFICATION_CONTEXT = new PlayContextImp(null, 15250315, 0, 0);
    
    boolean getBrowsePlay();
    
    PlayLocationType getPlayLocation();
    
    int getVideoPos();
    
    Bundle playContextToBundle();
    
    void setBrowsePlay(final boolean p0);
    
    void setPlayLocation(final PlayLocationType p0);
}
