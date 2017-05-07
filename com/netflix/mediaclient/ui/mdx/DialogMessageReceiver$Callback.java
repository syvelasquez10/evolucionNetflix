// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

public interface DialogMessageReceiver$Callback
{
    void handleDialogButton(final String p0, final String p1);
    
    void handleDialogCancel(final String p0);
    
    void handleUserRatingChange(final String p0, final float p1);
}
