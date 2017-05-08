// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.yoga.YogaNode;
import com.facebook.react.common.ClearableSynchronizedPool;

public class YogaNodePool
{
    private static final Object sInitLock;
    private static ClearableSynchronizedPool<YogaNode> sPool;
    
    static {
        sInitLock = new Object();
    }
    
    public static ClearableSynchronizedPool<YogaNode> get() {
        if (YogaNodePool.sPool != null) {
            return YogaNodePool.sPool;
        }
        synchronized (YogaNodePool.sInitLock) {
            if (YogaNodePool.sPool == null) {
                YogaNodePool.sPool = new ClearableSynchronizedPool<YogaNode>(1024);
            }
            return YogaNodePool.sPool;
        }
    }
}
