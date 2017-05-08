// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.model;

public class WPInteractiveMomentsModel$WPAudio
{
    private String id;
    private String name;
    final /* synthetic */ WPInteractiveMomentsModel this$0;
    private String url;
    private float volume;
    
    public WPInteractiveMomentsModel$WPAudio(final WPInteractiveMomentsModel this$0) {
        this.this$0 = this$0;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    @Override
    public String toString() {
        return "WPAudio{name='" + this.name + '\'' + ", id='" + this.id + '\'' + ", url='" + this.url + '\'' + ", volume=" + this.volume + '}';
    }
}
