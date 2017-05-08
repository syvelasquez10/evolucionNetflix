// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongSound
{
    String id;
    String sourceId;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    String url;
    float volume;
    
    public KongInteractivePostPlayModel$KongSound(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getSourceId() {
        return this.sourceId;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public float getVolume() {
        if (this.volume > 0.0f) {
            return this.volume;
        }
        return 1.0f;
    }
    
    @Override
    public String toString() {
        return "KongSound{id='" + this.id + '\'' + ", url='" + this.url + '\'' + ", sourceId='" + this.sourceId + '\'' + ", volume=" + this.volume + '}';
    }
}
