// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

enum LayoutAnimationType
{
    CREATE("create"), 
    DELETE("delete"), 
    UPDATE("update");
    
    private final String mName;
    
    private LayoutAnimationType(final String mName) {
        this.mName = mName;
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
}
