// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface LoMo extends BasicLoMo
{
    List<FriendProfile> getFacebookFriends();
    
    int getNumVideos();
    
    boolean isBillboard();
}
