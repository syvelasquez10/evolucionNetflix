// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

public enum ImageLoader$Type
{
    CACHE, 
    NETWORK, 
    PLACEHOLDER;
    
    public boolean isImmediate() {
        return this != ImageLoader$Type.NETWORK;
    }
}
