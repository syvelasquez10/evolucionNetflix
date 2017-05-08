// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongImage
{
    int height;
    String id;
    String size;
    String sourceId;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    String url;
    int width;
    
    public KongInteractivePostPlayModel$KongImage(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getSize() {
        return this.size;
    }
    
    public String getSourceId() {
        return this.sourceId;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public String toString() {
        return "KongImage{id='" + this.id + '\'' + ", url='" + this.url + '\'' + ", sourceId='" + this.sourceId + '\'' + ", width=" + this.width + ", height=" + this.height + ", size='" + this.size + '\'' + '}';
    }
}
