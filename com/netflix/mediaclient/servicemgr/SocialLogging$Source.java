// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum SocialLogging$Source
{
    BOB("BOB"), 
    MDP("MDP"), 
    NotificationCenter("NotificationCenter"), 
    OnBoarding("On-boarding"), 
    Play("Play"), 
    PostPlay("PostPlay"), 
    Postcard("Postcard"), 
    Signup("Signup"), 
    SocialFriendGallery("SocialFriendGallery"), 
    SocialRow("SocialRow");
    
    private String mValue;
    
    private SocialLogging$Source(final String mValue) {
        this.mValue = mValue;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
