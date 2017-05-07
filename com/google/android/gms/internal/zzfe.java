// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;

public interface zzfe extends IInterface
{
    void onBackPressed();
    
    void onCreate(final Bundle p0);
    
    void onDestroy();
    
    void onPause();
    
    void onRestart();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
    
    void onStart();
    
    void onStop();
    
    void zzaE();
    
    boolean zzez();
}
