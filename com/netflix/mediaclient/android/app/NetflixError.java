// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

public interface NetflixError
{
    String getErrorCode();
    
    String getErrorMessage();
    
    String getStackTrace();
    
    long getTimestamp();
    
    boolean isFatal();
}
