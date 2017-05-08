// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

public abstract class Snackbar$Callback
{
    public static final int DISMISS_EVENT_ACTION = 1;
    public static final int DISMISS_EVENT_CONSECUTIVE = 4;
    public static final int DISMISS_EVENT_MANUAL = 3;
    public static final int DISMISS_EVENT_SWIPE = 0;
    public static final int DISMISS_EVENT_TIMEOUT = 2;
    
    public void onDismissed(final Snackbar snackbar, final int n) {
    }
    
    public void onShown(final Snackbar snackbar) {
    }
}
