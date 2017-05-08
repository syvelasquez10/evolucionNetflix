// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;

public interface ExecutorSupplier
{
    Executor forBackgroundTasks();
    
    Executor forDecode();
    
    Executor forLightweightBackgroundTasks();
    
    Executor forLocalStorageRead();
    
    Executor forLocalStorageWrite();
}
