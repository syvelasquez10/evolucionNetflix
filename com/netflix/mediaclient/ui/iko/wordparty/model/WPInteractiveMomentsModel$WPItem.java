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
    
    public WPInteractiveMomentsModel$WPImage getCardOpenVideoMask() {
        WPInteractiveMomentsModel$WPImage access$4100 = null;
        WPInteractiveMomentsModel$WPImage wpInteractiveMomentsModel$WPImage = null;
        if (this.states != null) {
            final WPInteractiveMomentsModel$WPItemState access$4101 = this.states.DEFAULT;
            if (access$4101 != null) {
                access$4100 = access$4101.videoMask;
            }
            if ((wpInteractiveMomentsModel$WPImage = access$4100) == null) {
                final WPInteractiveMomentsModel$WPItemState access$4102 = this.states.SELECTED;
                wpInteractiveMomentsModel$WPImage = access$4100;
                if (access$4102 != null) {
                    return access$4102.videoMask;
                }
            }
        }
        return wpInteractiveMomentsModel$WPImage;
    }
    
    public WPInteractiveMomentsModel$WPVideo getCardVideo() {
        WPInteractiveMomentsModel$WPVideo access$4200 = null;
        WPInteractiveMomentsModel$WPVideo wpInteractiveMomentsModel$WPVideo = null;
        if (this.states != null) {
            final WPInteractiveMomentsModel$WPItemState access$4201 = this.states.DEFAULT;
            if (access$4201 != null) {
                access$4200 = access$4201.video;
            }
            if ((wpInteractiveMomentsModel$WPVideo = access$4200) == null) {
                final WPInteractiveMomentsModel$WPItemState access$4202 = this.states.SELECTED;
                wpInteractiveMomentsModel$WPVideo = access$4200;
                if (access$4202 != null) {
                    return access$4202.video;
                }
            }
        }
        return wpInteractiveMomentsModel$WPVideo;
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
