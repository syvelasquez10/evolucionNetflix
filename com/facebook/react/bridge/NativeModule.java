// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface NativeModule
{
    boolean canOverrideExistingModule();
    
    String getName();
    
    void initialize();
    
    void onCatalystInstanceDestroy();
}
