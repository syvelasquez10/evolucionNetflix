// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import java.util.List;

public interface FriendProfilesProvider
{
    List<FriendProfile> getFacebookFriends();
}
