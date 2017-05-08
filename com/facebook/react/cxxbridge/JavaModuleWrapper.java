// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.BaseJavaModule$SyncJavaHook;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.BaseJavaModule;
import java.util.Iterator;
import com.facebook.react.bridge.NativeModule$NativeMethod;
import java.util.List;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.Map;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.BaseJavaModule$JavaMethod;
import java.util.ArrayList;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
class JavaModuleWrapper
{
    private final CatalystInstance mCatalystInstance;
    private final ArrayList<BaseJavaModule$JavaMethod> mMethods;
    private final ModuleHolder mModuleHolder;
    
    public JavaModuleWrapper(final CatalystInstance mCatalystInstance, final ModuleHolder mModuleHolder) {
        this.mCatalystInstance = mCatalystInstance;
        this.mModuleHolder = mModuleHolder;
        this.mMethods = new ArrayList<BaseJavaModule$JavaMethod>();
    }
    
    @DoNotStrip
    public NativeArray getConstants() {
        SystraceMessage.beginSection(0L, "Map constants").arg("moduleName", this.getName()).flush();
        final Map<String, Object> constants = this.getModule().getConstants();
        Systrace.endSection(0L);
        SystraceMessage.beginSection(0L, "WritableNativeMap constants").arg("moduleName", this.getName()).flush();
        try {
            final WritableNativeMap nativeMap = Arguments.makeNativeMap(constants);
            Systrace.endSection(0L);
            final WritableNativeArray writableNativeArray = new WritableNativeArray();
            writableNativeArray.pushMap(nativeMap);
            return writableNativeArray;
        }
        finally {
            Systrace.endSection(0L);
        }
    }
    
    @DoNotStrip
    public List<JavaModuleWrapper$MethodDescriptor> getMethodDescriptors() {
        final ArrayList<JavaModuleWrapper$MethodDescriptor> list = new ArrayList<JavaModuleWrapper$MethodDescriptor>();
        for (final Map.Entry<String, NativeModule$NativeMethod> entry : this.getModule().getMethods().entrySet()) {
            final JavaModuleWrapper$MethodDescriptor javaModuleWrapper$MethodDescriptor = new JavaModuleWrapper$MethodDescriptor(this);
            javaModuleWrapper$MethodDescriptor.name = entry.getKey();
            javaModuleWrapper$MethodDescriptor.type = entry.getValue().getType();
            this.mMethods.add(entry.getValue());
            list.add(javaModuleWrapper$MethodDescriptor);
        }
        return list;
    }
    
    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule)this.mModuleHolder.getModule();
    }
    
    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getInfo().name();
    }
    
    @DoNotStrip
    public void invoke(final ExecutorToken executorToken, final int n, final ReadableNativeArray readableNativeArray) {
        if (this.mMethods == null || n >= this.mMethods.size()) {
            return;
        }
        this.mMethods.get(n).invoke(this.mCatalystInstance, executorToken, readableNativeArray);
    }
    
    @DoNotStrip
    public List<JavaModuleWrapper$MethodDescriptor> newGetMethodDescriptors() {
        final ArrayList<JavaModuleWrapper$MethodDescriptor> list = new ArrayList<JavaModuleWrapper$MethodDescriptor>();
        for (final Map.Entry<String, NativeModule$NativeMethod> entry : this.getModule().getMethods().entrySet()) {
            final JavaModuleWrapper$MethodDescriptor javaModuleWrapper$MethodDescriptor = new JavaModuleWrapper$MethodDescriptor(this);
            javaModuleWrapper$MethodDescriptor.name = entry.getKey();
            javaModuleWrapper$MethodDescriptor.type = entry.getValue().getType();
            final BaseJavaModule$JavaMethod baseJavaModule$JavaMethod = entry.getValue();
            javaModuleWrapper$MethodDescriptor.method = baseJavaModule$JavaMethod.getMethod();
            javaModuleWrapper$MethodDescriptor.signature = baseJavaModule$JavaMethod.getSignature();
            list.add(javaModuleWrapper$MethodDescriptor);
        }
        for (final Map.Entry<String, Object> entry2 : this.getModule().getSyncHooks().entrySet()) {
            final JavaModuleWrapper$MethodDescriptor javaModuleWrapper$MethodDescriptor2 = new JavaModuleWrapper$MethodDescriptor(this);
            javaModuleWrapper$MethodDescriptor2.name = entry2.getKey();
            javaModuleWrapper$MethodDescriptor2.type = "sync";
            final BaseJavaModule$SyncJavaHook baseJavaModule$SyncJavaHook = entry2.getValue();
            javaModuleWrapper$MethodDescriptor2.method = baseJavaModule$SyncJavaHook.getMethod();
            javaModuleWrapper$MethodDescriptor2.signature = baseJavaModule$SyncJavaHook.getSignature();
            list.add(javaModuleWrapper$MethodDescriptor2);
        }
        return list;
    }
    
    @DoNotStrip
    public boolean supportsWebWorkers() {
        return this.getModule().supportsWebWorkers();
    }
}
