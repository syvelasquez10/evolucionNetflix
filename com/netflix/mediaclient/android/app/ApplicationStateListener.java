// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import android.content.Intent;

public interface ApplicationStateListener
{
    void onBackground(final UserInputManager p0);
    
    void onFocusGain(final UserInputManager p0);
    
    void onFocusLost(final UserInputManager p0);
    
    void onForeground(final UserInputManager p0, final Intent p1);
    
    void onUiGone(final UserInputManager p0);
    
    void onUiStarted(final UserInputManager p0);
}
