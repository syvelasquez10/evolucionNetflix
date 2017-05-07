// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.widget.SeekBar$OnSeekBarChangeListener;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.media.BifManager;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Activity;
import android.view.View$OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.Log;
import android.widget.ImageButton;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.Screen;

class PlaycardScreen implements Screen
{
    protected static final float DISABLED_ALPHA = 0.3f;
    private static final String TAG = "playcard";
    private final ImageView mBif;
    private final BottomPanel mBottomPanel;
    private final View mBufferingOverlay;
    private final MdxPlaycardActivity mController;
    private final TextView mDevice;
    private final AdvancedImageView mMainImage;
    private final ImageButton mPlayPauseButton;
    private final TextView mSubtitle;
    private final TextView mTitle;
    private final TopPanel mTopPanel;
    private final View mVideoMetadata;
    
    PlaycardScreen(final MdxPlaycardActivity mController, final Listeners listeners) {
        if (mController == null || listeners == null) {
            throw new IllegalArgumentException("Argument can not be null!");
        }
        this.mController = mController;
        this.mTopPanel = new TopPanel(mController, listeners);
        this.mBottomPanel = new BottomPanel(mController, listeners);
        (this.mMainImage = (AdvancedImageView)mController.findViewById(2131230966)).setPressedStateHandlerEnabled(false);
        this.mMainImage.setVisibility(0);
        this.mBufferingOverlay = mController.findViewById(2131231002);
        this.mBif = (ImageView)mController.findViewById(2131230967);
        this.mDevice = (TextView)mController.findViewById(2131230983);
        this.mTitle = (TextView)mController.findViewById(2131230984);
        this.mSubtitle = (TextView)mController.findViewById(2131230985);
        this.mVideoMetadata = mController.findViewById(2131230982);
        this.mPlayPauseButton = (ImageButton)mController.findViewById(2131230979);
        if (this.mPlayPauseButton != null) {
            Log.d("playcard", "Play/pause button found, sets listener, disable");
            this.mPlayPauseButton.setOnClickListener(listeners.playPauseListener);
            this.enableButton(this.mPlayPauseButton, false);
        }
        this.mBottomPanel.changeActionState(false);
        this.mTopPanel.changeActionState(false);
    }
    
    static String getTag() {
        return "playcard";
    }
    
    protected void animateView(final View view, final float n, final float n2) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        alphaAnimation.setFillAfter(true);
        view.startAnimation((Animation)alphaAnimation);
    }
    
    public void changeActionState(final boolean b, final boolean b2) {
        if (this.mTopPanel != null) {
            this.mTopPanel.changeActionState(b);
        }
        if (this.mBottomPanel != null) {
            if (b2) {
                this.mBottomPanel.enableButtons(b);
            }
            else {
                this.mBottomPanel.changeActionState(b);
            }
        }
        this.enableButton(this.mPlayPauseButton, b);
    }
    
    void destroy() {
        synchronized (this) {
            if (this.mTopPanel != null) {
                this.mTopPanel.destroy();
            }
            if (this.mPlayPauseButton != null) {
                this.mPlayPauseButton.setOnClickListener((View$OnClickListener)null);
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.destroy();
            }
        }
    }
    
    protected void enableButton(final ImageButton disableOverlayForImageButton, final boolean enabled) {
        if (disableOverlayForImageButton != null) {
            disableOverlayForImageButton.setEnabled(enabled);
            if (!enabled) {
                this.setDisableOverlayForImageButton(disableOverlayForImageButton);
                return;
            }
            disableOverlayForImageButton.clearAnimation();
        }
    }
    
    BottomPanel getBottomPanel() {
        return this.mBottomPanel;
    }
    
    @Override
    public Activity getController() {
        return this.mController;
    }
    
    public TextView getDeviceTextView() {
        return this.mDevice;
    }
    
    public AdvancedImageView getMainImage() {
        return this.mMainImage;
    }
    
    public TextView getSubtitleTextView() {
        return this.mSubtitle;
    }
    
    public TextView getTitleTextView() {
        return this.mTitle;
    }
    
    TopPanel getTopPanel() {
        return this.mTopPanel;
    }
    
    void moveCurrentTimeWithTimeline(final boolean b, final boolean b2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().move(b, b2);
        }
    }
    
    protected void playerOverlayVisibility(final boolean b) {
        if (b) {
            if (AndroidUtils.getAndroidVersion() < 14 || AndroidUtils.getAndroidVersion() >= 16) {
                this.mController.getWindow().clearFlags(1024);
            }
            return;
        }
        this.mController.getWindow().setFlags(1024, 1024);
    }
    
    public void setBufferingVisibility(final boolean b) {
        if (b) {
            this.mBufferingOverlay.setVisibility(0);
            return;
        }
        this.mBufferingOverlay.setVisibility(8);
    }
    
    protected void setDisableOverlayForImageButton(final ImageButton imageButton) {
        this.animateView((View)imageButton, 0.3f, 0.3f);
    }
    
    public void setEpisodePlaying(final boolean episodePlaying) {
        this.mTopPanel.setEpisodePlaying(episodePlaying);
    }
    
    public void setLanguage(final Language language) {
        final TopPanel mTopPanel = this.mTopPanel;
        if (mTopPanel != null) {
            mTopPanel.setLanguage(language);
        }
    }
    
    void setLastTimeState(final boolean lastTimeState) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getLastTime() != null) {
            mBottomPanel.getLastTime().setLastTimeState(lastTimeState);
        }
    }
    
    public void setPausePlayButtonState(final boolean b) {
        if (b) {
            Log.d("playcard", "State is now PAUSED, change Play/Pause button to PLAY");
            this.mPlayPauseButton.setImageResource(2130837804);
            return;
        }
        Log.d("playcard", "State is now PLAYING, change Play/Pause button to PAUSE");
        this.mPlayPauseButton.setImageResource(2130837803);
    }
    
    int setProgress(final int n, final int n2, final boolean b) {
        return this.setProgress(n, n2, b, false);
    }
    
    int setProgress(final int n, final int n2, final boolean b, final boolean b2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            mBottomPanel.show();
            return mBottomPanel.setProgress(n, n2, b, b2);
        }
        return 0;
    }
    
    void setVideoMatadataVisibility(final boolean b) {
        final View mVideoMetadata = this.mVideoMetadata;
        ViewUtils.Visibility visibility;
        if (b) {
            visibility = ViewUtils.Visibility.VISIBLE;
        }
        else {
            visibility = ViewUtils.Visibility.INVISIBLE;
        }
        ViewUtils.setVisibility(mVideoMetadata, visibility);
    }
    
    public void showBif(final ByteBuffer byteBuffer) {
        this.mBottomPanel.getCurrentTime().setBifDownloaded(byteBuffer != null);
        BifManager.Utils.showBifInView(byteBuffer, this.mBif);
    }
    
    void snapToPosition(final int n, final int n2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            mBottomPanel.snapToPosition(n, n2);
        }
    }
    
    public void startBif(final ByteBuffer byteBuffer) {
        this.showBif(byteBuffer);
    }
    
    public void startCurrentTime(final ByteBuffer byteBuffer) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().start(byteBuffer);
        }
    }
    
    public void stopBif() {
        if (this.mBif != null) {
            this.mBif.setVisibility(8);
        }
    }
    
    public void stopCurrentTime(final boolean b) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().stop(b);
        }
    }
    
    void updateScreenOnVideoSeekCompleted() {
        if (!this.mController.isTablet()) {
            if (DeviceUtils.isPortrait((Context)this.mController)) {
                Log.d("playcard", "Device is phone in portrait, restore background image and play/pause button");
                ViewUtils.setVisibility((View)this.mMainImage, true);
            }
            else {
                Log.d("playcard", "Device is phone in landscape, restore play/pause button");
            }
            ViewUtils.setVisibility((View)this.mPlayPauseButton, true);
            Log.d("playcard", "sets play/pause to visible on video seek complete ");
        }
    }
    
    void updateScreenOnVideoSeekStart() {
        if (!this.mController.isTablet()) {
            if (DeviceUtils.isPortrait((Context)this.mController)) {
                Log.d("playcard", "Device is phone in portrait, hide background imaage and play/pause button");
                ViewUtils.setVisibility((View)this.mMainImage, false);
            }
            else {
                Log.d("playcard", "Device is phone in landscape, hide play/pause button");
            }
            this.mPlayPauseButton.clearAnimation();
            ViewUtils.setVisibility((View)this.mPlayPauseButton, false);
        }
    }
    
    public static class Listeners
    {
        public SeekBar$OnSeekBarChangeListener audioPositionListener;
        public View$OnClickListener episodeSelectorListener;
        public View$OnClickListener playPauseListener;
        public View$OnClickListener ratingListener;
        public View$OnClickListener skipBackListener;
        public View$OnClickListener stopListener;
        public SeekBar$OnSeekBarChangeListener videoPositionListener;
    }
}
