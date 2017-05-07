// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;

public interface FriendProfilesProvider
{
    List<FriendProfile> getFacebookFriends();
}
