// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum SessionState
{
    CLOSED(Category.CLOSED_CATEGORY), 
    CLOSED_LOGIN_FAILED(Category.CLOSED_CATEGORY), 
    CREATED(Category.CREATED_CATEGORY), 
    CREATED_TOKEN_LOADED(Category.CREATED_CATEGORY), 
    OPENED(Category.OPENED_CATEGORY), 
    OPENED_TOKEN_UPDATED(Category.OPENED_CATEGORY), 
    OPENING(Category.CREATED_CATEGORY);
    
    private final Category category;
    
    private SessionState(final Category category) {
        this.category = category;
    }
    
    public boolean isClosed() {
        return this.category == Category.CLOSED_CATEGORY;
    }
    
    public boolean isOpened() {
        return this.category == Category.OPENED_CATEGORY;
    }
    
    private enum Category
    {
        CLOSED_CATEGORY, 
        CREATED_CATEGORY, 
        OPENED_CATEGORY;
    }
}
