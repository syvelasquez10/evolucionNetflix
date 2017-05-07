// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum FriendPickerFragment$FriendPickerType
{
    FRIENDS("/friends", true), 
    INVITABLE_FRIENDS("/invitable_friends", false), 
    TAGGABLE_FRIENDS("/taggable_friends", false);
    
    private final boolean requestIsCacheable;
    private final String requestPath;
    
    private FriendPickerFragment$FriendPickerType(final String requestPath, final boolean requestIsCacheable) {
        this.requestPath = requestPath;
        this.requestIsCacheable = requestIsCacheable;
    }
    
    String getRequestPath() {
        return this.requestPath;
    }
    
    boolean isCacheable() {
        return this.requestIsCacheable;
    }
}
