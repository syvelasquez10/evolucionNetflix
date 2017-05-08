// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.util.Property;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.AnimatorSet;
import android.animation.Animator$AnimatorListener;
import android.net.Uri;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.text.TextUtils;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.log.IkoLogUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.view.TextureView;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPVideo;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.widget.FrameLayout;

public abstract class WPCardLayout extends FrameLayout
{
    public static final String TAG = "WPCardLayout";
    protected final int CAMERA_DISTANCE;
    protected WPInteractiveMomentsModel$WPAudio audio;
    protected int cardHeight;
    protected CardListener cardListener;
    protected float cardRotationValue;
    protected int cardWidth;
    private boolean completedAudioPlaybackForReveal;
    private boolean completedVideoPlaybackForReveal;
    protected ObjectAnimator currentVideoTextureAnimator;
    protected WPCardImageView imageView;
    protected MediaPlayerWrapper mediaPlayerWrapper;
    protected int padding;
    protected Interpolator quintOutInterpolator;
    protected WPInteractiveMomentsModel$WPVideo video;
    protected BitmapDrawable videoMaskDrawable;
    protected ImageView videoMaskImageView;
    protected TextureView videoTexture;
    private WPCardLayout$VideoTextureState videoTextureState;
    protected WPCardVOPlayer voPlayer;
    
    public WPCardLayout(final Context context) {
        this(context, null);
    }
    
    public WPCardLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.CAMERA_DISTANCE = 8000;
        this.init(context);
    }
    
    private void createTextureView() {
        final TextureView videoTexture = new TextureView(this.getContext());
        videoTexture.setOpaque(false);
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(this.getWidth(), this.getHeight());
        frameLayout$LayoutParams.gravity = 17;
        videoTexture.setVisibility(8);
        this.addView((View)videoTexture, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.videoTexture = videoTexture;
    }
    
    private void downloadVideo(final String s) {
        if (Log.isLoggable()) {
            Log.d("WPCardLayout", "Downloading video - url: " + s);
        }
        IkoLogUtils.reportIkoVideoLoadStarted(this.getContext(), s);
        ((NetflixActivity)this.getContext()).getServiceManager().fetchAndCacheResource(s, IClientLogging$AssetType.wordPartyVideoCard, new WPCardLayout$5(this, s));
    }
    
    private void endCurrentVideoTextureAnimator() {
        final ObjectAnimator currentVideoTextureAnimator = this.currentVideoTextureAnimator;
        if (currentVideoTextureAnimator != null && ((Animator)currentVideoTextureAnimator).isStarted()) {
            ((Animator)currentVideoTextureAnimator).end();
        }
    }
    
    private float getCardRotationValue(final int n, int n2) {
        if (n2 % 2 != 0 && n == n2 - 1) {
            return 0.0f;
        }
        float cardRotationValue;
        final float n3 = cardRotationValue = this.cardRotationValue;
        if (n2 == 4) {
            cardRotationValue = n3;
            if (n >= n2 / 2) {
                cardRotationValue = n3 / 2.0f;
            }
        }
        if (n / 2 <= 0) {
            n2 = -1;
        }
        else {
            n2 = 1;
        }
        final float n4 = n2;
        if (n % 2 > 0) {
            cardRotationValue = -cardRotationValue;
        }
        return cardRotationValue * n4;
    }
    
    private float getCardSpacingMultiplier(final int n) {
        return (float)Math.pow(1.2000000476837158, 4 - n + 1);
    }
    
    private float getCardXPosition(final int n, final int n2) {
        float n3;
        if (n2 % 2 != 0 && n == n2 - 1) {
            n3 = 0.0f;
        }
        else {
            n3 = this.cardWidth / 2 + this.padding;
            if (n % 2 == 0) {
                return -n3;
            }
        }
        return n3;
    }
    
    private float getCardYPosition(final int n, final int n2) {
        float n3;
        if (n2 <= 2) {
            n3 = 0.0f;
        }
        else {
            n3 = this.cardHeight / 2 + this.padding;
            if (n / 2 <= 0) {
                return -n3;
            }
        }
        return n3;
    }
    
    private float getRecapCardXPosition(final int n, final int n2) {
        if (n == 0) {
            return 0.0f;
        }
        return this.getRecapExitCardXPosition(n - 1, n2 - 1);
    }
    
    private float getRecapCardYPosition(final int n, int n2) {
        n2 = this.getHeight() / 2;
        if (n == 0) {
            return -(n2 - this.padding);
        }
        return n2 * 3.0f * (1.0f / DeviceUtils.getScreenAspectRatio(this.getContext()));
    }
    
    private float getRecapExitCardXPosition(final int n, final int n2) {
        float n3 = n - n2 / 2;
        if (n2 % 2 == 0) {
            n3 += 0.5f;
        }
        return n3 * (this.getWidth() / 2) * this.getCardSpacingMultiplier(n2);
    }
    
    private float getRecapExitCardYPosition(final int n, final int n2) {
        return 0.0f;
    }
    
    private void init(final Context context) {
        Log.v("WPCardLayout", "init");
        this.initProperties();
        this.storeViews(context);
    }
    
    private void onMediaPlaybackComplete() {
        if (this.completedAudioPlaybackForReveal && this.completedVideoPlaybackForReveal && this.cardListener != null) {
            this.cardListener.onCardRevealComplete(this);
        }
    }
    
    private void onVideoPlaybackComplete() {
        this.completedVideoPlaybackForReveal = true;
        this.onMediaPlaybackComplete();
    }
    
    private void playVO() {
        final WPCardLayout$3 wpCardLayout$3 = new WPCardLayout$3(this);
        if (this.voPlayer != null) {
            this.voPlayer.playVO(this.audio, wpCardLayout$3);
            return;
        }
        wpCardLayout$3.onComplete(null);
    }
    
    private void playVideo() {
        if (this.video != null && this.video.getUrl() != null) {
            this.setUpVideo(this.video.getUrl());
            return;
        }
        this.onVideoPlaybackComplete();
    }
    
    private void removeViewFromParent(final View view) {
        if (view != null && view.getParent() != null) {
            this.removeView(view);
        }
    }
    
    private void setUpVideo(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (Log.isLoggable()) {
                Log.v("WPCardLayout", "setUpVideo, video url: " + s);
            }
            this.removeViewFromParent((View)this.videoTexture);
            if (this.mediaPlayerWrapper != null) {
                this.mediaPlayerWrapper.releaseResources(true);
                this.mediaPlayerWrapper = null;
            }
            this.removeViewFromParent((View)this.videoMaskImageView);
            this.createTextureView();
            this.createVideoMask();
            this.mediaPlayerWrapper = new MediaPlayerWrapper(this.videoTexture, false, 0, 1.0f, IClientLogging$AssetType.wordPartyVideoCard, new WPCardLayout$4(this));
            this.downloadVideo(Uri.parse(s).buildUpon().clearQuery().build().toString());
            return;
        }
        this.hideVideo();
        this.onVideoPlaybackComplete();
    }
    
    private void startVideoPlaybackRevealAnimation() {
        Log.v("WPCardLayout", "startVideoPlaybackRevealAnimation");
        if (this.videoTextureState != WPCardLayout$VideoTextureState.SHOWING && this.videoTextureState != WPCardLayout$VideoTextureState.SHOWN) {
            this.videoTextureState = WPCardLayout$VideoTextureState.SHOWING;
            this.endCurrentVideoTextureAnimator();
            if (this.videoTexture != null) {
                final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.videoTexture, View.ALPHA, new float[] { 0.5f, 1.0f });
                ofFloat.setDuration(500L);
                ofFloat.addListener((Animator$AnimatorListener)new WPCardLayout$6(this));
                (this.currentVideoTextureAnimator = ofFloat).start();
            }
        }
    }
    
    public AnimatorSet calculateRecapAnimation(final int n, final int n2) {
        float n3;
        if (n == 0) {
            n3 = 1.5f;
        }
        else {
            n3 = 0.5f;
        }
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapCardXPosition(n, n2), this.getRecapCardYPosition(n, n2), 0.0f, n3);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRecapExitAnimation(final int n, final int n2) {
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapExitCardXPosition(n, n2), this.getRecapExitCardYPosition(n, n2), 0.0f, 0.5f);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRecapInitAnimation(final int n, final int n2) {
        this.setScaleX(0.5f);
        this.setScaleY(0.5f);
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapExitCardXPosition(n, n2), this.getRecapExitCardYPosition(n, n2), 0.0f, 0.5f);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRevealAnimation(final int n, final int n2) {
        final float n3 = 1.0f;
        final float cardXPosition = this.getCardXPosition(n, n2);
        final float cardYPosition = this.getCardYPosition(n, n2);
        final float cardRotationValue = this.getCardRotationValue(n, n2);
        float n4 = n3;
        if (n2 == 1) {
            if (this.imageView.isOpen()) {
                n4 = n3;
            }
            else {
                n4 = 1.5f;
            }
        }
        return this.getCardAnimation(cardXPosition, cardYPosition, cardRotationValue, n4);
    }
    
    protected abstract void createVideoMask();
    
    public void flip() {
        this.setPivotX((float)(this.getWidth() / 2));
        final Property rotation_Y = View.ROTATION_Y;
        float n;
        if (this.imageView.isOpen()) {
            n = 0.0f;
        }
        else {
            n = -180.0f;
        }
        final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)this, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(rotation_Y, new float[] { n }), PropertyValuesHolder.ofFloat(View.ROTATION, new float[] { -1.0f * this.getRotation() }), PropertyValuesHolder.ofFloat(View.ALPHA, WPConstants.CARD_FLIP_ALPHA_VALUE_LIST) });
        ofPropertyValuesHolder.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new WPCardLayout$1(this, ofPropertyValuesHolder));
        final AnimatorSet set = new AnimatorSet();
        set.playSequentially(new Animator[] { ofPropertyValuesHolder });
        set.setDuration(500L);
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.addListener((Animator$AnimatorListener)new WPCardLayout$2(this));
        if (this.cardListener != null) {
            this.cardListener.onCardClickStart(this);
        }
        set.start();
    }
    
    public AnimatorSet getCardAnimation(final float n, final float n2, final float n3, final float n4) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, View.TRANSLATION_X, new float[] { n });
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)this, View.TRANSLATION_Y, new float[] { n2 });
        final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this, View.ROTATION, new float[] { n3 });
        final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this, View.SCALE_X, new float[] { n4 });
        final ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat((Object)this, View.SCALE_Y, new float[] { n4 });
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(new Animator[] { ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5 });
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return set;
    }
    
    public ObjectAnimator getWiggleAnimation(final int n) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, View.ROTATION, new float[] { this.getRotation(), -1.0f, 1.0f, this.getRotation() });
        ofFloat.setStartDelay((long)(n % 2 * 100));
        ofFloat.setRepeatCount(3);
        ofFloat.setRepeatMode(2);
        ofFloat.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return ofFloat;
    }
    
    protected final void hideVideo() {
        Log.v("WPCardLayout", "Hiding video");
        if (this.videoTextureState != WPCardLayout$VideoTextureState.HIDING && this.videoTextureState != WPCardLayout$VideoTextureState.HIDDEN && this.videoTexture != null && this.videoTexture.getVisibility() == 0) {
            this.videoTextureState = WPCardLayout$VideoTextureState.HIDING;
            this.endCurrentVideoTextureAnimator();
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.videoTexture, View.ALPHA, new float[] { 1.0f, 0.0f });
            ofFloat.setDuration(500L);
            ofFloat.addListener((Animator$AnimatorListener)new WPCardLayout$7(this));
            (this.currentVideoTextureAnimator = ofFloat).start();
            this.onHideVideo();
        }
    }
    
    protected void initProperties() {
        this.setCameraDistance(8000.0f);
        this.cardRotationValue = 3.0f;
        this.padding = this.getContext().getResources().getDimensionPixelSize(2131427931);
        this.quintOutInterpolator = WPConstants.getQuintOutInterpolator();
        this.videoTextureState = WPCardLayout$VideoTextureState.HIDDEN;
    }
    
    protected abstract void onHideVideo();
    
    protected abstract void onVideoPlaybackStarted();
    
    public void releaseResources() {
        Log.d("WPCardLayout", "releaseResources");
        this.imageView.releaseResources();
        if (this.mediaPlayerWrapper != null) {
            this.mediaPlayerWrapper.releaseResources();
        }
        this.endCurrentVideoTextureAnimator();
        this.currentVideoTextureAnimator = null;
        this.video = null;
        this.audio = null;
    }
    
    public void reset(final boolean b) {
        this.imageView.reset(b);
        this.hideVideo();
        this.setScaleX(1.0f);
        this.setScaleY(1.0f);
        this.setRotationY(0.0f);
        this.setRotation(0.0f);
    }
    
    public void revealCard() {
        if (Log.isLoggable()) {
            Log.d("WPCardLayout", "revealCard: playing VO for card - " + this);
        }
        this.completedAudioPlaybackForReveal = false;
        this.completedVideoPlaybackForReveal = false;
        this.playVideo();
        this.playVO();
    }
    
    public void setAudio(final WPInteractiveMomentsModel$WPAudio audio) {
        this.audio = audio;
    }
    
    public void setCardListener(final CardListener cardListener) {
        this.cardListener = cardListener;
    }
    
    public void setDrawables(final BitmapDrawable bitmapDrawable, final BitmapDrawable bitmapDrawable2, final BitmapDrawable videoMaskDrawable) {
        this.imageView.setDrawables(bitmapDrawable, bitmapDrawable2);
        this.videoMaskDrawable = videoMaskDrawable;
    }
    
    public void setVOPlayer(final WPCardVOPlayer voPlayer) {
        this.voPlayer = voPlayer;
    }
    
    public void setVideo(final WPInteractiveMomentsModel$WPVideo video) {
        this.video = video;
    }
    
    protected void showVideo() {
        Log.v("WPCardLayout", "Showing video");
        final TextureView videoTexture = this.videoTexture;
        if (videoTexture != null) {
            ((View)videoTexture).setAlpha(0.0f);
            ((View)videoTexture).setVisibility(0);
        }
        final ImageView videoMaskImageView = this.videoMaskImageView;
        if (videoMaskImageView != null) {
            ((View)videoMaskImageView).setVisibility(0);
        }
    }
    
    protected abstract void storeViews(final Context p0);
    
    public String toString() {
        return "WPCardLayout{, currentVideoTextureAnimator=" + this.currentVideoTextureAnimator + ", audio=" + this.audio + ", video=" + this.video + ", cardWidth=" + this.cardWidth + ", cardHeight=" + this.cardHeight + ", completedAudioPlaybackForReveal=" + this.completedAudioPlaybackForReveal + ", completedVideoPlaybackForReveal=" + this.completedVideoPlaybackForReveal + ", imageView=" + this.imageView + ", videoTextureState=" + this.videoTextureState + '}';
    }
}
