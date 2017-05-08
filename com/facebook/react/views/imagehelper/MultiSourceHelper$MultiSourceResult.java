// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.imagehelper;

public class MultiSourceHelper$MultiSourceResult
{
    private final ImageSource bestResult;
    private final ImageSource bestResultInCache;
    
    private MultiSourceHelper$MultiSourceResult(final ImageSource bestResult, final ImageSource bestResultInCache) {
        this.bestResult = bestResult;
        this.bestResultInCache = bestResultInCache;
    }
    
    public ImageSource getBestResult() {
        return this.bestResult;
    }
    
    public ImageSource getBestResultInCache() {
        return this.bestResultInCache;
    }
}
