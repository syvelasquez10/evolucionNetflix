// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

public abstract class BuffetBar$Callback
{
    public static final int DISMISS_EVENT_ACTION = 1;
    public static final int DISMISS_EVENT_CONSECUTIVE = 4;
    public static final int DISMISS_EVENT_MANUAL = 3;
    public static final int DISMISS_EVENT_SWIPE = 0;
    public static final int DISMISS_EVENT_TIMEOUT = 2;
    
    public void onDismissed(final BuffetBar buffetBar, final int n) {
    }
    
    public void onShown(final BuffetBar buffetBar) {
    }
}
