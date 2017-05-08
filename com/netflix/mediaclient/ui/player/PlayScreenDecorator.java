// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.media.Language;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.Asset;

public abstract class PlayScreenDecorator
{
    private PlayScreenDecorator decorator;
    protected final PlayScreen playerScreen;
    
    protected PlayScreenDecorator(final PlayScreen playerScreen) {
        this.playerScreen = playerScreen;
    }
    
    protected PlayScreenDecorator(final PlayScreenDecorator decorator) {
        this.playerScreen = decorator.getPlayScreen();
        this.decorator = decorator;
    }
    
    public PlayerFragment getController() {
        return this.playerScreen.getPlayerFragment();
    }
    
    public PlayScreen getPlayScreen() {
        return this.playerScreen;
    }
    
    public void onAssetUpdated(final Asset asset) {
        if (this.decorator != null) {
            this.decorator.onAssetUpdated(asset);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.decorator != null) {
            this.decorator.onConfigurationChanged(configuration);
        }
    }
    
    public void onDestroy() {
        if (this.decorator != null) {
            this.decorator.onDestroy();
        }
    }
    
    public void onLanguageUpdated(final Language language) {
        if (this.decorator != null) {
            this.decorator.onLanguageUpdated(language);
        }
    }
    
    public void onPause() {
        if (this.decorator != null) {
            this.decorator.onPause();
        }
    }
    
    public void onResume() {
        if (this.decorator != null) {
            this.decorator.onResume();
        }
    }
    
    public void onStart() {
        if (this.decorator != null) {
            this.decorator.onStart();
        }
    }
    
    public void onStop() {
        if (this.decorator != null) {
            this.decorator.onStop();
        }
    }
    
    public void onVideoDetailsFetched(final VideoDetails videoDetails) {
        if (this.decorator != null) {
            this.decorator.onVideoDetailsFetched(videoDetails);
        }
    }
    
    public void playExtraHandlerAnimation(final int n, final Runnable runnable) {
        if (this.decorator != null) {
            this.decorator.playExtraHandlerAnimation(n, runnable);
        }
    }
    
    public void playerOverlayVisibility(final boolean b) {
        if (this.decorator != null) {
            this.decorator.playerOverlayVisibility(b);
        }
    }
    
    public void setDraggingState(final boolean draggingState) {
        if (this.decorator != null) {
            this.decorator.setDraggingState(draggingState);
        }
    }
    
    public void setSeekbarEnabled(final boolean seekbarEnabled) {
        if (this.decorator != null) {
            this.decorator.setSeekbarEnabled(seekbarEnabled);
        }
    }
    
    public void setTimelineMaxProgress(final int timelineMaxProgress) {
        if (this.decorator != null) {
            this.decorator.setTimelineMaxProgress(timelineMaxProgress);
        }
    }
    
    public void setTimelineProgress(final int n, final boolean b) {
        if (this.decorator != null) {
            this.decorator.setTimelineProgress(n, b);
        }
    }
    
    public void updatePlaybackStatus(final boolean b) {
        if (this.decorator != null) {
            this.decorator.updatePlaybackStatus(b);
        }
    }
}
