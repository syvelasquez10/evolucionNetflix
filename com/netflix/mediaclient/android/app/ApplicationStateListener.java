// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

public interface ApplicationStateListener
{
    void onBackground(final UserInputManager p0);
    
    void onForeground(final UserInputManager p0);
    
    void onUiGone(final UserInputManager p0);
    
    void onUiStarted(final UserInputManager p0);
}
