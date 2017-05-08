// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;

public class SafeSubtitleManager implements SubtitleManager
{
    protected static final String TAG = "nf_subtitles";
    private PlayerFragment mPlayerFrag;
    private SubtitleManager mSubtitleManager;
    
    public SafeSubtitleManager(final PlayerFragment mPlayerFrag) {
        this.mPlayerFrag = mPlayerFrag;
    }
    
    private SubtitleManager getRealSubtitleManager() {
        synchronized (this) {
            if (this.mSubtitleManager == null) {
                IMedia$SubtitleProfile subtitleProfileFromMetadata = null;
                if (this.mPlayerFrag.getPlayer() != null) {
                    subtitleProfileFromMetadata = this.mPlayerFrag.getPlayer().getSubtitleProfileFromMetadata();
                }
                if (subtitleProfileFromMetadata != null) {
                    this.mSubtitleManager = SubtitleManagerFactory.createInstance(subtitleProfileFromMetadata, this.mPlayerFrag);
                }
            }
            return this.mSubtitleManager;
        }
    }
    
    private boolean shouldCreateNewSubtitleManager(final IMedia$SubtitleProfile media$SubtitleProfile) {
        final IMedia$SubtitleProfile subtitleProfile = this.getSubtitleProfile();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Old profile " + subtitleProfile + ", newProfile " + media$SubtitleProfile);
        }
        final boolean b = this.mSubtitleManager != null && this.mSubtitleManager.canHandleSubtitleProfile(media$SubtitleProfile);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Existing manager can handle new profile " + media$SubtitleProfile + ", yes? " + b);
        }
        return !b;
    }
    
    @Override
    public boolean canHandleSubtitleProfile(final IMedia$SubtitleProfile media$SubtitleProfile) {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        return realSubtitleManager != null && realSubtitleManager.canHandleSubtitleProfile(media$SubtitleProfile);
    }
    
    @Override
    public void clear() {
        if (this.mSubtitleManager != null) {
            final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
            if (realSubtitleManager != null) {
                realSubtitleManager.clear();
            }
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
    public NetflixActivity getContext() {
        return this.mPlayerFrag.getNetflixActivity();
    }
    
    @Override
    public IMedia$SubtitleProfile getSubtitleProfile() {
        final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
        if (realSubtitleManager != null) {
            return realSubtitleManager.getSubtitleProfile();
        }
        return null;
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
        synchronized (this) {
            final IMedia$SubtitleProfile subtitleProfile = subtitleScreen.getParser().getSubtitleProfile();
            if (this.shouldCreateNewSubtitleManager(subtitleProfile)) {
                Log.d("nf_subtitles", "========> Create new subtitle manager!");
                if (this.mSubtitleManager != null) {
                    this.mSubtitleManager.clear();
                }
                this.mSubtitleManager = SubtitleManagerFactory.createInstance(subtitleProfile, this.mPlayerFrag);
            }
            final SubtitleManager realSubtitleManager = this.getRealSubtitleManager();
            if (realSubtitleManager != null) {
                realSubtitleManager.onSubtitleChange(subtitleScreen);
            }
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
