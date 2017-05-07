// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.List;

public class AccountData
{
    private User user;
    private List<UserProfile> userProfiles;
    
    public User getUser() {
        return this.user;
    }
    
    public List<UserProfile> getUserProfiles() {
        return this.userProfiles;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public void setUserProfiles(final List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
