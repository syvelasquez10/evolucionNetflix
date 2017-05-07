// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;

public interface ds extends IInterface
{
    void U();
    
    void onCreate(final Bundle p0);
    
    void onDestroy();
    
    void onPause();
    
    void onRestart();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
    
    void onStart();
    
    void onStop();
}
