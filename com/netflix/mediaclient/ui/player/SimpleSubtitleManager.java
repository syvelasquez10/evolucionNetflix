// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.Language;
import android.widget.TextView$BufferType;
import android.text.Html;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.view.View;

public final class SimpleSubtitleManager implements SubtitleManager
{
    private static final int PLAYER_PADDING_PHONE = 46;
    private static final int PLAYER_PADDING_TABLET = 54;
    private static final String TAG = "nf_subtitles";
    private final PlayerActivity mActivity;
    private int mBottomPanelHeight;
    private int mPlayerPadding;
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
        this.mSubtitleArea = mActivity.findViewById(2131427725);
        this.mSubtitleLabel = (TextView)mActivity.findViewById(2131427634);
        int n;
        if (mActivity.isTablet()) {
            n = 54;
        }
        else {
            n = 46;
        }
        this.mPlayerPadding = AndroidUtils.dipToPixels((Context)mActivity, n);
        this.mBottomPanelHeight = mActivity.getResources().getDimensionPixelSize(2131296508);
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
    public void onPlayerOverlayVisibiltyChange(final boolean b) {
        final int n = this.mPlayerPadding + this.mBottomPanelHeight;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Player UI is now visible: " + b + ", push subtitles (if applicable) higher for " + this.mBottomPanelHeight + ", with padding " + this.mPlayerPadding + ", delta " + n);
        }
        if (this.mSubtitleArea != null) {
            final LinearLayout$LayoutParams layoutParams = (LinearLayout$LayoutParams)this.mSubtitleArea.getLayoutParams();
            if (b) {
                layoutParams.setMargins(0, 0, 0, n);
            }
            else {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            this.mSubtitleArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.mSubtitleArea.requestLayout();
        }
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
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Set subtitle to : " + s);
        }
        final Language language = this.mActivity.getLanguage();
        if (language != null && language.isSubtitleVisible()) {
            if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
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
