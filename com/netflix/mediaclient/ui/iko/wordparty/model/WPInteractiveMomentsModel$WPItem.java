// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.model;

public class WPInteractiveMomentsModel$WPItem
{
    private String name;
    private WPInteractiveMomentsModel$WPItemStates states;
    final /* synthetic */ WPInteractiveMomentsModel this$0;
    
    public WPInteractiveMomentsModel$WPItem(final WPInteractiveMomentsModel this$0) {
        this.this$0 = this$0;
    }
    
    public WPInteractiveMomentsModel$WPImage getCardClosedImage() {
        if (this.states == null || this.states.DEFAULT == null) {
            return null;
        }
        return this.states.DEFAULT.image;
    }
    
    public WPInteractiveMomentsModel$WPImage getCardOpenImage() {
        if (this.states == null || this.states.SELECTED == null) {
            return null;
        }
        return this.states.SELECTED.image;
    }
    
    public WPInteractiveMomentsModel$WPAudio getItemAudio() {
        if (this.states == null || this.states.SELECTED == null) {
            return null;
        }
        return this.states.SELECTED.VO;
    }
    
    public String getItemName() {
        return this.name;
    }
    
    public WPInteractiveMomentsModel$WPAudio getRecapAudio() {
        if (this.states == null || this.states.DEFAULT == null) {
            return null;
        }
        return this.states.DEFAULT.VO;
    }
}
