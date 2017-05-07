// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

public enum ImageResolutionClass
{
    HIGH("high"), 
    LOW("low");
    
    public final String urlParamValue;
    
    private ImageResolutionClass(final String urlParamValue) {
        this.urlParamValue = urlParamValue;
    }
}
