// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.model;

import java.util.List;

public class WPInteractiveMomentsModel$WPMoment
{
    private WPInteractiveMomentsModel$WPScene interactiveScene;
    private WPInteractiveMomentsModel$WPPug notification;
    final /* synthetic */ WPInteractiveMomentsModel this$0;
    
    public WPInteractiveMomentsModel$WPMoment(final WPInteractiveMomentsModel this$0) {
        this.this$0 = this$0;
    }
    
    public WPInteractiveMomentsModel$WPAudio getBackgroundAudio() {
        if (this.interactiveScene == null || this.interactiveScene.momentTheme == null || this.interactiveScene.momentTheme.background == null) {
            return null;
        }
        return this.interactiveScene.momentTheme.background.audio;
    }
    
    public WPInteractiveMomentsModel$WPColor getBackgroundColor() {
        if (this.interactiveScene == null || this.interactiveScene.momentTheme == null || this.interactiveScene.momentTheme.background == null) {
            return null;
        }
        return this.interactiveScene.momentTheme.background.color;
    }
    
    public WPInteractiveMomentsModel$WPImage getBackgroundImage() {
        if (this.interactiveScene == null || this.interactiveScene.momentTheme == null || this.interactiveScene.momentTheme.background == null) {
            return null;
        }
        return this.interactiveScene.momentTheme.background.image;
    }
    
    public WPInteractiveMomentsModel$WPImage getForegroundImage() {
        if (this.interactiveScene == null || this.interactiveScene.momentTheme == null || this.interactiveScene.momentTheme.foreground == null) {
            return null;
        }
        return this.interactiveScene.momentTheme.foreground.image;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getInstructionAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.INSTRUCTION == null) {
            return null;
        }
        return this.interactiveScene.states.INSTRUCTION.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getIntroductionAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.INTRODUCTION == null) {
            return null;
        }
        return this.interactiveScene.states.INTRODUCTION.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPItem> getItems() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.ITEM_SELECTION == null) {
            return null;
        }
        return this.interactiveScene.states.ITEM_SELECTION.items;
    }
    
    public int getMomentExpectedVideoOffset() {
        if (this.interactiveScene == null || this.interactiveScene.trackingInfo == null) {
            return -1;
        }
        return this.interactiveScene.trackingInfo.expectedVideoOffset;
    }
    
    public String getMomentId() {
        if (this.interactiveScene == null || this.interactiveScene.trackingInfo == null) {
            return "ikoMomentId";
        }
        return this.interactiveScene.trackingInfo.momentId;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getPassiveExitAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.PASSIVE_EXIT == null) {
            return null;
        }
        return this.interactiveScene.states.PASSIVE_EXIT.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getPositiveLineAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.POSITIVE_LINE == null) {
            return null;
        }
        return this.interactiveScene.states.POSITIVE_LINE.VO;
    }
    
    public WPInteractiveMomentsModel$WPAudio getPugDefaultAudio() {
        if (this.notification == null || this.notification.states == null || this.notification.states.DEFAULT == null) {
            return null;
        }
        return this.notification.states.DEFAULT.VO;
    }
    
    public WPInteractiveMomentsModel$WPImage getPugDefaultImage() {
        if (this.notification == null || this.notification.states == null || this.notification.states.DEFAULT == null) {
            return null;
        }
        return this.notification.states.DEFAULT.image;
    }
    
    public Integer getPugEndTimeMS() {
        if (this.notification == null) {
            return null;
        }
        return this.notification.endTimeMS;
    }
    
    public int getPugNotificationExpectedVideoOffset() {
        if (this.notification == null || this.notification.trackingInfo == null) {
            return -1;
        }
        return this.notification.trackingInfo.expectedVideoOffset;
    }
    
    public String getPugNotificationId() {
        if (this.notification == null || this.notification.trackingInfo == null) {
            return "ikoNotificationId";
        }
        return this.notification.trackingInfo.notificationId;
    }
    
    public WPInteractiveMomentsModel$WPAudio getPugSelectedAudio() {
        if (this.notification == null || this.notification.states == null || this.notification.states.SELECTED == null) {
            return null;
        }
        return this.notification.states.SELECTED.VO;
    }
    
    public WPInteractiveMomentsModel$WPImage getPugSelectedImage() {
        if (this.notification == null || this.notification.states == null || this.notification.states.SELECTED == null) {
            return null;
        }
        return this.notification.states.SELECTED.image;
    }
    
    public Integer getPugStartTimeMS() {
        if (this.notification == null) {
            return null;
        }
        return this.notification.startTimeMS;
    }
    
    public String getPugType() {
        if (this.notification == null) {
            return null;
        }
        return this.notification.type;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getRecapAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.RECAP == null) {
            return null;
        }
        return this.interactiveScene.states.RECAP.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPItem> getRecapItems() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.RECAP == null) {
            return null;
        }
        return this.interactiveScene.states.RECAP.items;
    }
    
    public String getSceneInitialState() {
        if (this.interactiveScene == null) {
            return null;
        }
        return this.interactiveScene.initialState;
    }
    
    public Integer getSceneTriggerEndMS() {
        if (this.interactiveScene == null) {
            return null;
        }
        return this.interactiveScene.triggerEndMS;
    }
    
    public Integer getSceneTriggerStartMS() {
        if (this.interactiveScene == null) {
            return null;
        }
        return this.interactiveScene.triggerStartMS;
    }
    
    public String getSceneType() {
        if (this.interactiveScene == null) {
            return null;
        }
        return this.interactiveScene.type;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getSummaryAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.SUMMARY == null) {
            return null;
        }
        return this.interactiveScene.states.SUMMARY.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getTimeout2AudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.TIMEOUT2 == null) {
            return null;
        }
        return this.interactiveScene.states.TIMEOUT2.VO;
    }
    
    public List<WPInteractiveMomentsModel$WPAudio> getTimeoutAudioList() {
        if (this.interactiveScene == null || this.interactiveScene.states == null || this.interactiveScene.states.TIMEOUT == null) {
            return null;
        }
        return this.interactiveScene.states.TIMEOUT.VO;
    }
    
    public Integer getVideoReturnOffsetMS() {
        if (this.interactiveScene == null) {
            return null;
        }
        int n;
        if ((n = this.interactiveScene.triggerEndMS) > 0) {
            n = this.interactiveScene.videoReturnOffsetMS;
        }
        return n;
    }
    
    public Boolean isPugAutoOptIn() {
        if (this.notification == null) {
            return null;
        }
        return this.notification.autoOptIn;
    }
}
