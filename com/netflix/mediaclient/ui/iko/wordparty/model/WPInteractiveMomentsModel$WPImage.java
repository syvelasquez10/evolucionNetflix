// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.model;

public class WPInteractiveMomentsModel$WPImage
{
    private int height;
    private String id;
    private String size;
    final /* synthetic */ WPInteractiveMomentsModel this$0;
    private String url;
    private int width;
    
    public WPInteractiveMomentsModel$WPImage(final WPInteractiveMomentsModel this$0) {
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
    
    public String getUrl() {
        return this.url;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public String toString() {
        return "WPImage{id='" + this.id + '\'' + ", url='" + this.url + '\'' + ", width=" + this.width + ", height=" + this.height + ", size='" + this.size + '\'' + '}';
    }
}
