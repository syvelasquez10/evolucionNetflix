// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.model;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;

public class WPInteractiveMomentsModel extends InteractiveMomentsModel
{
    public static final String TYPE = "wordparty";
    private ArrayList<String> cacheableResources;
    private WPInteractiveMomentsModel$WPEpisodeTheme episodeTheme;
    private List<WPInteractiveMomentsModel$WPMoment> moments;
    
    public WPInteractiveMomentsModel() {
        this.cacheableResources = new ArrayList<String>();
    }
    
    private void addToCacheableResourceList(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.cacheableResources.add(s);
        }
    }
    
    public WPInteractiveMomentsModel$WPAudio getItemSelectAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.itemSelect;
    }
    
    public WPInteractiveMomentsModel$WPAudio getItemWiggleAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.itemWiggle;
    }
    
    public WPInteractiveMomentsModel$WPAudio getMomentTransitionInAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.momentTransitionIn;
    }
    
    public WPInteractiveMomentsModel$WPAudio getMomentTransitionOutAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.momentTransitionOut;
    }
    
    public List<WPInteractiveMomentsModel$WPMoment> getMoments() {
        return this.moments;
    }
    
    public WPInteractiveMomentsModel$WPAudio getPanelShuffleAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.panelShuffle;
    }
    
    public List<String> getPreCacheableResourcesList() {
        this.cacheableResources.clear();
        final WPInteractiveMomentsModel$WPAudio itemWiggleAudio = this.getItemWiggleAudio();
        if (itemWiggleAudio != null) {
            this.addToCacheableResourceList(itemWiggleAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio pugNotificationAudio = this.getPugNotificationAudio();
        if (pugNotificationAudio != null) {
            this.addToCacheableResourceList(pugNotificationAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio momentTransitionOutAudio = this.getMomentTransitionOutAudio();
        if (momentTransitionOutAudio != null) {
            this.addToCacheableResourceList(momentTransitionOutAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio momentTransitionInAudio = this.getMomentTransitionInAudio();
        if (momentTransitionInAudio != null) {
            this.addToCacheableResourceList(momentTransitionInAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio victoryAudio = this.getVictoryAudio();
        if (victoryAudio != null) {
            this.addToCacheableResourceList(victoryAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio pugSelectedAudio = this.getPugSelectedAudio();
        if (pugSelectedAudio != null) {
            this.addToCacheableResourceList(pugSelectedAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio itemSelectAudio = this.getItemSelectAudio();
        if (itemSelectAudio != null) {
            this.addToCacheableResourceList(itemSelectAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio tutorialBoingAudio = this.getTutorialBoingAudio();
        if (tutorialBoingAudio != null) {
            this.addToCacheableResourceList(tutorialBoingAudio.getUrl());
        }
        final WPInteractiveMomentsModel$WPAudio panelShuffleAudio = this.getPanelShuffleAudio();
        if (panelShuffleAudio != null) {
            this.addToCacheableResourceList(panelShuffleAudio.getUrl());
        }
        if (this.moments != null) {
            for (final WPInteractiveMomentsModel$WPMoment wpInteractiveMomentsModel$WPMoment : this.moments) {
                if (wpInteractiveMomentsModel$WPMoment != null) {
                    final WPInteractiveMomentsModel$WPAudio pugDefaultAudio = wpInteractiveMomentsModel$WPMoment.getPugDefaultAudio();
                    if (pugDefaultAudio != null) {
                        this.addToCacheableResourceList(pugDefaultAudio.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPImage pugDefaultImage = wpInteractiveMomentsModel$WPMoment.getPugDefaultImage();
                    if (pugDefaultImage != null) {
                        this.addToCacheableResourceList(pugDefaultImage.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPAudio pugSelectedAudio2 = wpInteractiveMomentsModel$WPMoment.getPugSelectedAudio();
                    if (pugSelectedAudio2 != null) {
                        this.addToCacheableResourceList(pugSelectedAudio2.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPImage pugSelectedImage = wpInteractiveMomentsModel$WPMoment.getPugSelectedImage();
                    if (pugSelectedImage != null) {
                        this.addToCacheableResourceList(pugSelectedImage.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPImage foregroundImage = wpInteractiveMomentsModel$WPMoment.getForegroundImage();
                    if (foregroundImage != null) {
                        this.addToCacheableResourceList(foregroundImage.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPImage backgroundImage = wpInteractiveMomentsModel$WPMoment.getBackgroundImage();
                    if (backgroundImage != null) {
                        this.addToCacheableResourceList(backgroundImage.getUrl());
                    }
                    final WPInteractiveMomentsModel$WPAudio backgroundAudio = wpInteractiveMomentsModel$WPMoment.getBackgroundAudio();
                    if (backgroundAudio != null) {
                        this.addToCacheableResourceList(backgroundAudio.getUrl());
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> introductionAudioList = wpInteractiveMomentsModel$WPMoment.getIntroductionAudioList();
                    if (introductionAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio : introductionAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> instructionAudioList = wpInteractiveMomentsModel$WPMoment.getInstructionAudioList();
                    if (instructionAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio2 : instructionAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio2 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio2.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> timeoutAudioList = wpInteractiveMomentsModel$WPMoment.getTimeoutAudioList();
                    if (timeoutAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio3 : timeoutAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio3 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio3.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> timeout2AudioList = wpInteractiveMomentsModel$WPMoment.getTimeout2AudioList();
                    if (timeout2AudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio4 : timeout2AudioList) {
                            if (wpInteractiveMomentsModel$WPAudio4 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio4.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> passiveExitAudioList = wpInteractiveMomentsModel$WPMoment.getPassiveExitAudioList();
                    if (passiveExitAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio5 : passiveExitAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio5 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio5.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> positiveLineAudioList = wpInteractiveMomentsModel$WPMoment.getPositiveLineAudioList();
                    if (positiveLineAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio6 : positiveLineAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio6 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio6.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> summaryAudioList = wpInteractiveMomentsModel$WPMoment.getSummaryAudioList();
                    if (summaryAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio7 : summaryAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio7 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio7.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPAudio> recapAudioList = wpInteractiveMomentsModel$WPMoment.getRecapAudioList();
                    if (recapAudioList != null) {
                        for (final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio8 : recapAudioList) {
                            if (wpInteractiveMomentsModel$WPAudio8 != null) {
                                this.addToCacheableResourceList(wpInteractiveMomentsModel$WPAudio8.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPItem> recapItems = wpInteractiveMomentsModel$WPMoment.getRecapItems();
                    if (recapItems != null) {
                        for (final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem : recapItems) {
                            if (wpInteractiveMomentsModel$WPItem != null) {
                                final WPInteractiveMomentsModel$WPImage cardOpenImage = wpInteractiveMomentsModel$WPItem.getCardOpenImage();
                                if (cardOpenImage != null) {
                                    this.addToCacheableResourceList(cardOpenImage.getUrl());
                                }
                                final WPInteractiveMomentsModel$WPImage cardClosedImage = wpInteractiveMomentsModel$WPItem.getCardClosedImage();
                                if (cardClosedImage != null) {
                                    this.addToCacheableResourceList(cardClosedImage.getUrl());
                                }
                                final WPInteractiveMomentsModel$WPAudio itemAudio = wpInteractiveMomentsModel$WPItem.getItemAudio();
                                if (itemAudio == null) {
                                    continue;
                                }
                                this.addToCacheableResourceList(itemAudio.getUrl());
                            }
                        }
                    }
                    final List<WPInteractiveMomentsModel$WPItem> items = wpInteractiveMomentsModel$WPMoment.getItems();
                    if (items == null) {
                        continue;
                    }
                    for (final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem2 : items) {
                        if (wpInteractiveMomentsModel$WPItem2 != null) {
                            final WPInteractiveMomentsModel$WPImage cardOpenImage2 = wpInteractiveMomentsModel$WPItem2.getCardOpenImage();
                            if (cardOpenImage2 != null) {
                                this.addToCacheableResourceList(cardOpenImage2.getUrl());
                            }
                            final WPInteractiveMomentsModel$WPImage cardClosedImage2 = wpInteractiveMomentsModel$WPItem2.getCardClosedImage();
                            if (cardClosedImage2 != null) {
                                this.addToCacheableResourceList(cardClosedImage2.getUrl());
                            }
                            final WPInteractiveMomentsModel$WPAudio itemAudio2 = wpInteractiveMomentsModel$WPItem2.getItemAudio();
                            if (itemAudio2 == null) {
                                continue;
                            }
                            this.addToCacheableResourceList(itemAudio2.getUrl());
                        }
                    }
                }
            }
        }
        return this.cacheableResources;
    }
    
    public WPInteractiveMomentsModel$WPAudio getPugNotificationAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.pugNotification;
    }
    
    public WPInteractiveMomentsModel$WPAudio getPugSelectedAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.pugSelected;
    }
    
    public WPInteractiveMomentsModel$WPAudio getTutorialBoingAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.tutorialBoing;
    }
    
    public WPInteractiveMomentsModel$WPAudio getVictoryAudio() {
        if (this.episodeTheme == null || this.episodeTheme.sfx == null) {
            return null;
        }
        return this.episodeTheme.sfx.victory;
    }
}
