// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

public enum TouchEventType
{
    CANCEL("topTouchCancel"), 
    END("topTouchEnd"), 
    MOVE("topTouchMove"), 
    START("topTouchStart");
    
    private final String mJSEventName;
    
    private TouchEventType(final String mjsEventName) {
        this.mJSEventName = mjsEventName;
    }
    
    public String getJSEventName() {
        return this.mJSEventName;
    }
}
