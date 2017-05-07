// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum SessionState
{
    CLOSED(SessionState$Category.CLOSED_CATEGORY), 
    CLOSED_LOGIN_FAILED(SessionState$Category.CLOSED_CATEGORY), 
    CREATED(SessionState$Category.CREATED_CATEGORY), 
    CREATED_TOKEN_LOADED(SessionState$Category.CREATED_CATEGORY), 
    OPENED(SessionState$Category.OPENED_CATEGORY), 
    OPENED_TOKEN_UPDATED(SessionState$Category.OPENED_CATEGORY), 
    OPENING(SessionState$Category.CREATED_CATEGORY);
    
    private final SessionState$Category category;
    
    private SessionState(final SessionState$Category category) {
        this.category = category;
    }
    
    public boolean isClosed() {
        return this.category == SessionState$Category.CLOSED_CATEGORY;
    }
    
    public boolean isOpened() {
        return this.category == SessionState$Category.OPENED_CATEGORY;
    }
}
