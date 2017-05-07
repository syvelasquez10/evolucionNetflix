// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import android.content.Context;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;

public class SafeSubtitleManager implements SubtitleManager
{
    protected static final String TAG = "nf_subtitles";
    private PlayerActivity mActivity;
    private IMedia$SubtitleProfile mProfile;
    private SubtitleManager mSubtitleManager;
    
    public SafeSubtitleManager(final PlayerActivity mActivity) {
        this.mActivity = mActivity;
    }
    
    private SubtitleManager getRealSubtitleManager() {
        synchronized (this) {
            if (this.mSubtitleManager == null) {
                IMedia$SubtitleProfile subtitleProfileFromMetadata = null;
                if (this.mActivity.getPlayer() != null) {
                    subtitleProfileFromMetadata = this.mActivity.getPlayer().getSubtitleProfileFromMetadata();
                }
                if (subtitleProfileFromMetadata != null && subtitleProfileFromMetadata != this.mProfile) {
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "Previous subtitle profile: " + this.mProfile + ", new subtitle profile " + subtitleProfileFromMetadata);
                    }
                    if (this.mSubtitleManager != null) {
                        Log.d("nf_subtitles", "Remove existing subtitles from screen");
                        this.mSubtitleManager.clear();
                    }
                    this.mSubtitleManager = SubtitleManagerFactory.createInstance(subtitleProfileFromMetadata, this.mActivity);
                }
            }
            return this.mSubtitleManager;
        }
    }
    
    @Override
    public void clear() {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.clear();
        }
    }
    
    @Override
    public void clearPendingUpdates() {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.clearPendingUpdates();
        }
    }
    
    @Override
    public Context getContext() {
        return (Context)this.mActivity;
    }
    
    @Override
    public void onPlayerOverlayVisibiltyChange(final boolean b) {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.onPlayerOverlayVisibiltyChange(b);
        }
    }
    
    @Override
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.onSubtitleChange(subtitleScreen);
        }
    }
    
    @Override
    public void onSubtitleRemove() {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.onSubtitleRemove();
        }
    }
    
    @Override
    public void setSubtitleVisibility(final boolean subtitleVisibility) {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            realSubtitleManager.setSubtitleVisibility(subtitleVisibility);
        }
    }
}
