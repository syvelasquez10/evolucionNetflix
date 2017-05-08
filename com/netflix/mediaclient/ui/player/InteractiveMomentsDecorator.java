// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsFactory;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;

public class InteractiveMomentsDecorator extends PlayScreenDecorator
{
    private static final String TAG = "InteractiveMomentsDecorator";
    private boolean bottomPanelVisible;
    private InteractiveMomentsModel data;
    private InteractiveMomentsManager interactiveMomentsManager;
    private VideoDetails mDetails;
    private PlayerFragment mFragment;
    private ServiceManager svcManager;
    
    protected InteractiveMomentsDecorator(final PlayScreen playScreen) {
        super(playScreen);
        this.init();
    }
    
    protected InteractiveMomentsDecorator(final PlayScreenDecorator playScreenDecorator) {
        super(playScreenDecorator);
        this.init();
    }
    
    private static String getAudioLanguageForAudioSource(final AudioSource audioSource) {
        String languageCodeIso639_1 = null;
        if (audioSource != null) {
            languageCodeIso639_1 = audioSource.getLanguageCodeIso639_1();
        }
        return languageCodeIso639_1;
    }
    
    private static String getCurrentAudioLanguage(final Language language) {
        String audioLanguageForAudioSource = null;
        if (language != null) {
            audioLanguageForAudioSource = getAudioLanguageForAudioSource(language.getCurrentAudioSource());
        }
        return audioLanguageForAudioSource;
    }
    
    private String getVideoId() {
        if (this.mFragment == null) {
            Log.d("InteractiveMomentsDecorator", "Player fragment is null. This should not happen");
        }
        else if (this.mFragment.getCurrentAsset() != null) {
            return this.mFragment.getCurrentAsset().getPlayableId();
        }
        return null;
    }
    
    private VideoType getVideoType() {
        if (this.mFragment == null) {
            Log.d("InteractiveMomentsDecorator", "Player fragment is null. This should not happen");
        }
        else if (this.mFragment.getCurrentAsset() != null) {
            if (this.mFragment.getCurrentAsset().isEpisode()) {
                return VideoType.EPISODE;
            }
            return VideoType.MOVIE;
        }
        return null;
    }
    
    private void init() {
        this.mFragment = this.getController();
        if (this.mFragment == null) {
            Log.e("InteractiveMomentsDecorator", "Player fragment is null. This should not happen");
            return;
        }
        this.svcManager = ServiceManager.getServiceManager(this.mFragment.getNetflixActivity());
    }
    
    private boolean isManagerReady() {
        return this.interactiveMomentsManager != null && this.interactiveMomentsManager.isManagerReady();
    }
    
    private void onMomentsFetched(final InteractivePlaybackMoments interactivePlaybackMoments) {
        if (interactivePlaybackMoments != null) {
            this.data = interactivePlaybackMoments.getData();
            if (this.data == null || StringUtils.isEmpty(this.data.getType())) {
                if (Log.isLoggable()) {
                    Log.d("InteractiveMomentsDecorator", "Interactive moments data is empty.");
                }
            }
            else {
                final String type = this.data.getType();
                if (this.interactiveMomentsManager != null) {
                    this.interactiveMomentsManager.onStop();
                    this.interactiveMomentsManager.onDestroy();
                }
                this.interactiveMomentsManager = InteractiveMomentsFactory.getManager(type);
                if (this.interactiveMomentsManager == null) {
                    Log.e("InteractiveMomentsDecorator", "Interactive moments manager is null");
                    return;
                }
                this.interactiveMomentsManager.init(this.mFragment);
                this.interactiveMomentsManager.onMomentsFetched(interactivePlaybackMoments);
                if (this.mDetails != null) {
                    this.interactiveMomentsManager.onVideoDetailsFetched(this.mDetails);
                }
            }
        }
    }
    
    private void requestInteractiveMoments(final String s) {
        if (Log.isLoggable()) {
            Log.d("InteractiveMomentsDecorator", "Fetching interactive moments with audioLanguage=" + s);
        }
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.hide();
        }
        this.svcManager.getBrowse().fetchInteractiveVideoMoments(this.getVideoType(), this.getVideoId(), s, new InteractiveMomentsDecorator$1(this));
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onDestroy();
        }
    }
    
    @Override
    public void onLanguageUpdated(final Language language) {
        super.onLanguageUpdated(language);
        final String currentAudioLanguage = getCurrentAudioLanguage(language);
        String audioLanguageForAudioSource = null;
        if (language != null) {
            audioLanguageForAudioSource = getAudioLanguageForAudioSource(language.getSelectedAudio());
        }
        if (audioLanguageForAudioSource != null && !audioLanguageForAudioSource.equals(currentAudioLanguage)) {
            if (Log.isLoggable()) {
                Log.d("InteractiveMomentsDecorator", "Updating audio language - selected=" + audioLanguageForAudioSource + " current=" + currentAudioLanguage);
            }
            this.requestInteractiveMoments(audioLanguageForAudioSource);
        }
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onPause();
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onResume();
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onStart();
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onStop();
        }
    }
    
    @Override
    public void onVideoDetailsFetched(final VideoDetails mDetails) {
        super.onVideoDetailsFetched(mDetails);
        this.mDetails = mDetails;
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onVideoDetailsFetched(mDetails);
        }
    }
    
    @Override
    public void playerOverlayVisibility(final boolean bottomPanelVisible) {
        super.playerOverlayVisibility(bottomPanelVisible);
        this.bottomPanelVisible = bottomPanelVisible;
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.playerOverlayVisibility(bottomPanelVisible);
        }
    }
    
    @Override
    public void setTimelineMaxProgress(final int timelineMaxProgress) {
        super.setTimelineMaxProgress(timelineMaxProgress);
        if (this.mFragment == null) {
            Log.d("InteractiveMomentsDecorator", "Not fetching interactive moments because mFragment is null");
            return;
        }
        this.requestInteractiveMoments(getCurrentAudioLanguage(this.mFragment.getLanguage()));
    }
    
    @Override
    public void setTimelineProgress(final int n, final boolean b) {
        super.setTimelineProgress(n, b);
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.setTimelineProgress(n, b);
        }
    }
}
