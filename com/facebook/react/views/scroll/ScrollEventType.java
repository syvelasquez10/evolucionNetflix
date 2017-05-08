// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

public enum ScrollEventType
{
    ANIMATION_END("topScrollAnimationEnd"), 
    BEGIN_DRAG("topScrollBeginDrag"), 
    END_DRAG("topScrollEndDrag"), 
    MOMENTUM_BEGIN("topMomentumScrollBegin"), 
    MOMENTUM_END("topMomentumScrollEnd"), 
    SCROLL("topScroll");
    
    private final String mJSEventName;
    
    private ScrollEventType(final String mjsEventName) {
        this.mJSEventName = mjsEventName;
    }
    
    public String getJSEventName() {
        return this.mJSEventName;
    }
}
