// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

public class AccessibilityRunnable implements Runnable
{
    private String accessibilityDescription;
    private final Runnable runnable;
    
    public AccessibilityRunnable(final Runnable runnable, final String accessibilityDescription) {
        this.runnable = runnable;
        this.accessibilityDescription = accessibilityDescription;
    }
    
    protected String getAccessibilityDescription() {
        return this.accessibilityDescription;
    }
    
    @Override
    public void run() {
        this.runnable.run();
    }
    
    protected void setAccessibilityDescription(final String accessibilityDescription) {
        this.accessibilityDescription = accessibilityDescription;
    }
}
