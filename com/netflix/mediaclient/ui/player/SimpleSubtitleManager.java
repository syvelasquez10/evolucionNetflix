// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.Language;
import android.widget.TextView$BufferType;
import android.text.Html;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.view.View;

public final class SimpleSubtitleManager implements SubtitleManager
{
    private static final String TAG = "nf_subtitles";
    private final PlayerActivity mActivity;
    private final PlayScreen mScreen;
    private final View mSubtitleArea;
    private final TextView mSubtitleLabel;
    
    SimpleSubtitleManager(final PlayerActivity mActivity) {
        Log.d("nf_subtitles", "SimpleSubtitleManager created");
        this.mActivity = mActivity;
        this.mScreen = this.mActivity.getScreen();
        if (this.mScreen == null) {
            throw new IllegalArgumentException("Player screen is not initialized!");
        }
        this.mSubtitleArea = mActivity.findViewById(2131165496);
        this.mSubtitleLabel = (TextView)mActivity.findViewById(2131165405);
    }
    
    @Override
    public void clear() {
        Log.v("nf_subtitles", "NOOP");
    }
    
    @Override
    public void clearPendingUpdates() {
        Log.v("nf_subtitles", "NOOP");
    }
    
    @Override
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        Log.w("nf_subtitles", "SimpleSubtitleManager does not implement onSubtitleChange!");
    }
    
    @Override
    public void onSubtitleRemove() {
        Log.d("nf_subtitles", "Set subtitle to empty text to remove them");
        this.onSubtitleShow("");
    }
    
    @Override
    public void onSubtitleShow(final String s) {
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Set subtitle to : " + s);
        }
        final Language language = this.mActivity.getLanguage();
        if (language != null && language.isSubtitleVisible()) {
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Subtitle is visible : " + language.isSubtitleVisible());
            }
            this.setSubtitleVisibility(true);
        }
        if (this.mSubtitleLabel != null) {
            this.mSubtitleLabel.setText((CharSequence)Html.fromHtml(s), TextView$BufferType.SPANNABLE);
        }
    }
    
    @Override
    public void setSubtitleVisibility(final boolean b) {
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "setSubtitleVisibility to " + b);
        }
        if (this.mSubtitleArea == null) {
            Log.w("nf_subtitles", "subtitle area is NULL?!");
            return;
        }
        if (b) {
            Log.w("nf_subtitles", "subtitle area should be visible");
            this.mSubtitleArea.setVisibility(0);
            return;
        }
        Log.w("nf_subtitles", "subtitle area should be gone");
        this.mSubtitleArea.setVisibility(8);
    }
}
