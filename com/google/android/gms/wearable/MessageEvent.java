// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

public interface MessageEvent
{
    byte[] getData();
    
    String getPath();
    
    int getRequestId();
    
    String getSourceNodeId();
}
