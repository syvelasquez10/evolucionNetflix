// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ExecutorToken;
import java.util.Iterator;
import com.facebook.react.bridge.BaseJavaModule$JavaMethod;
import java.util.List;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.Map;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.NativeModuleLogger;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule$NativeMethod;
import java.util.ArrayList;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
class JavaModuleWrapper
{
    private final CatalystInstance mCatalystInstance;
    private final ArrayList<NativeModule$NativeMethod> mMethods;
    private final ModuleHolder mModuleHolder;
    
    public JavaModuleWrapper(final CatalystInstance mCatalystInstance, final ModuleHolder mModuleHolder) {
        this.mCatalystInstance = mCatalystInstance;
        this.mModuleHolder = mModuleHolder;
        this.mMethods = new ArrayList<NativeModule$NativeMethod>();
    }
    
    @DoNotStrip
    public NativeArray getConstants() {
        SystraceMessage.beginSection(0L, "Map constants").arg("moduleName", this.getName()).flush();
        final BaseJavaModule module = this.getModule();
        final Map<String, Object> constants = module.getConstants();
        Systrace.endSection(0L);
        SystraceMessage.beginSection(0L, "WritableNativeMap constants").arg("moduleName", this.getName()).flush();
        if (module instanceof NativeModuleLogger) {
            ((NativeModuleLogger)module).startConstantsMapConversion();
        }
        try {
            final WritableNativeMap nativeMap = Arguments.makeNativeMap(constants);
            Systrace.endSection(0L);
            final WritableNativeArray writableNativeArray = new WritableNativeArray();
            writableNativeArray.pushMap(nativeMap);
            if (module instanceof NativeModuleLogger) {
                ((NativeModuleLogger)module).endConstantsMapConversion();
            }
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
            final BaseJavaModule$JavaMethod baseJavaModule$JavaMethod = entry.getValue();
            if (javaModuleWrapper$MethodDescriptor.type == "sync") {
                javaModuleWrapper$MethodDescriptor.signature = baseJavaModule$JavaMethod.getSignature();
                javaModuleWrapper$MethodDescriptor.method = baseJavaModule$JavaMethod.getMethod();
            }
            this.mMethods.add(baseJavaModule$JavaMethod);
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
    public boolean supportsWebWorkers() {
        return this.getModule().supportsWebWorkers();
    }
}
