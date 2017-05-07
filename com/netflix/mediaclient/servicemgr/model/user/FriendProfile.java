// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.user;

public class FriendProfile
{
    private String firstName;
    private String id;
    private String imageUrl;
    private String lastName;
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getFullName() {
        return (this.getFirstName() + " " + this.getLastName()).trim();
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public String getLastName() {
        return this.lastName;
    }
}
