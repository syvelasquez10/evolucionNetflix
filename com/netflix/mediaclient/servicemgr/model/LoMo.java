// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import android.os.Parcelable;

public interface LoMo extends BasicLoMo, Parcelable
{
    List<FriendProfile> getFacebookFriends();
    
    List<String> getMoreImages();
    
    int getNumVideos();
    
    boolean isBillboard();
}