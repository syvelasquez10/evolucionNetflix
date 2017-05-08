// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.systrace.SystraceMessage$Builder;
import com.facebook.systrace.Systrace;
import com.facebook.infer.annotation.Assertions;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.ReactMarker;
import java.util.concurrent.ExecutionException;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.module.model.ReactModuleInfo;
import javax.inject.Provider;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.module.model.Info;

public class ModuleHolder
{
    private final Info mInfo;
    private boolean mInitializeNeeded;
    private NativeModule mModule;
    private Provider<? extends NativeModule> mProvider;
    
    public ModuleHolder(final Class<? extends NativeModule> clazz, final ReactModuleInfo reactModuleInfo, final Provider<? extends NativeModule> mProvider) {
        Info mInfo = reactModuleInfo;
        if (reactModuleInfo == null) {
            mInfo = new ModuleHolder$LegacyModuleInfo(clazz);
        }
        this.mInfo = mInfo;
        this.mProvider = mProvider;
        if (this.mInfo.needsEagerInit()) {
            this.mModule = this.doCreate();
        }
    }
    
    private static void callInitializeOnUiThread(final NativeModule ex) {
        if (UiThreadUtil.isOnUiThread()) {
            ((NativeModule)ex).initialize();
            return;
        }
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        UiThreadUtil.runOnUiThread(new ModuleHolder$1((NativeModule)ex, simpleSettableFuture));
        try {
            simpleSettableFuture.get();
        }
        catch (InterruptedException ex2) {}
        catch (ExecutionException ex) {
            goto Label_0040;
        }
    }
    
    private NativeModule create() {
        final boolean b = this.mInfo instanceof ModuleHolder$LegacyModuleInfo;
        String s;
        if (b) {
            s = ((ModuleHolder$LegacyModuleInfo)this.mInfo).mType.getSimpleName();
        }
        else {
            s = this.mInfo.name();
        }
        if (!b) {
            ReactMarker.logMarker("CREATE_MODULE_START");
        }
        SystraceMessage.beginSection(0L, "createModule").arg("name", s).flush();
        final NativeModule nativeModule = (NativeModule)Assertions.assertNotNull(this.mProvider).get();
        if (this.mInitializeNeeded) {
            this.doInitialize(nativeModule);
            this.mInitializeNeeded = false;
        }
        Systrace.endSection(0L);
        if (!b) {
            ReactMarker.logMarker("CREATE_MODULE_END");
        }
        return nativeModule;
    }
    
    private NativeModule doCreate() {
        final NativeModule create = this.create();
        this.mProvider = null;
        return create;
    }
    
    private void doInitialize(final NativeModule nativeModule) {
        final SystraceMessage$Builder beginSection = SystraceMessage.beginSection(0L, "initialize");
        if (nativeModule instanceof CxxModuleWrapper) {
            beginSection.arg("className", nativeModule.getClass().getSimpleName());
        }
        else {
            beginSection.arg("name", this.mInfo.name());
        }
        beginSection.flush();
        callInitializeOnUiThread(nativeModule);
        Systrace.endSection(0L);
    }
    
    public void destroy() {
        synchronized (this) {
            if (this.mModule != null) {
                this.mModule.onCatalystInstanceDestroy();
            }
        }
    }
    
    public Info getInfo() {
        return this.mInfo;
    }
    
    public NativeModule getModule() {
        synchronized (this) {
            if (this.mModule == null) {
                this.mModule = this.doCreate();
            }
            return this.mModule;
        }
    }
    
    public void initialize() {
        synchronized (this) {
            if (this.mModule != null) {
                this.doInitialize(this.mModule);
            }
            else {
                this.mInitializeNeeded = true;
            }
        }
    }
}
