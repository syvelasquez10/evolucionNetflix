// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.NativeModule$NativeMethod;
import java.util.Map;
import com.facebook.soloader.SoLoader;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeModule;

@DoNotStrip
public class CxxModuleWrapper implements NativeModule
{
    @DoNotStrip
    private HybridData mHybridData;
    
    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }
    
    protected CxxModuleWrapper(final HybridData mHybridData) {
        this.mHybridData = mHybridData;
    }
    
    public CxxModuleWrapper(final String s, final String s2) {
        SoLoader.loadLibrary(s);
        this.mHybridData = this.initHybrid(SoLoader.unpackLibraryAndDependencies(s).getAbsolutePath(), s2);
    }
    
    private native HybridData initHybrid(final String p0, final String p1);
    
    @Override
    public boolean canOverrideExistingModule() {
        return false;
    }
    
    public native String getConstantsJson();
    
    public native Map<String, NativeModule$NativeMethod> getMethods();
    
    @Override
    public native String getName();
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        this.mHybridData.resetNative();
    }
    
    public boolean supportsWebWorkers() {
        return false;
    }
}
