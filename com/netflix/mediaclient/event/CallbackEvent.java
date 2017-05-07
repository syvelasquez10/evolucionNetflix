// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event;

public interface CallbackEvent extends UIEvent
{
    int getCallerId();
    
    boolean isFailed();
}
