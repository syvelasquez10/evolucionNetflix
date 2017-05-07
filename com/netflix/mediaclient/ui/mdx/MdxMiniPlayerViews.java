// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.AnimationUtils$HideViewOnAnimatorEnd;
import java.util.Arrays;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.view.LayoutInflater;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Collection;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.android.widget.SnappableSeekBar;
import com.netflix.mediaclient.android.widget.SnappableSeekBar$OnSnappableSeekBarChangeListener;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import java.util.List;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeFormatterHelper;

class MdxMiniPlayerViews
{
    private static final boolean DISABLED = false;
    private static final float DISABLED_ALPHA = 0.4f;
    private static final float FULL_ALPHA = 0.0f;
    private static final long LONG_VIEW_ANIMATION_DURATION_MS = 200L;
    private static final int MAX_CONTROL_GROUP_WIDTH_DP = 600;
    private static final float NO_ALPHA = 1.0f;
    private static final long PROGRESS_CHANGE_EVENT_FILTER_TIME_MS = -1L;
    private static final long SHORT_VIEW_ANIMATION_DURATION_MS = 100L;
    private static final String TAG = "MdxMiniPlayerViews";
    private static final int TOTAL_NUMBER_OF_COLLAPSED_BUTTONS = 4;
    private static final TimeFormatterHelper timeFormatter;
    private final NetflixActivity activity;
    private final Interpolator animInterpolator;
    private final AdvancedImageView artwork;
    private final View auxControlsGroup;
    private final ImageView bifImage;
    private final TextView bifSeekTime;
    private final MdxMiniPlayerViews$MdxMiniPlayerViewCallbacks callbacks;
    private final List<View> collapsedViews;
    private final ViewGroup content;
    private final TextView currentTime;
    private final TextView deviceName;
    private final View deviceNameGroup;
    private final MdxUtils$MdxTargetSelectionDialogInterface dialogCallbacks;
    private final View$OnClickListener dummyClickListener;
    private boolean episodesButtonVisible;
    private final View$OnClickListener episodesClickListener;
    private final IconFontTextView episodesCollapsed;
    private final View episodesDivider;
    private final ImageView episodesExpanded;
    private Boolean isShowingViewForExpanded;
    private final boolean isTablet;
    private final IconFontTextView languageCollapsed;
    private final ImageView languageExpanded;
    private final int maxTitleTextYDelta;
    private final int maxTitleTextYMargin;
    private MdxMenu mdxMenu;
    private final SnappableSeekBar$OnSnappableSeekBarChangeListener onSeekBarChangeListener;
    private final String parentActivityClass;
    private final View$OnClickListener pauseOnClickListener;
    private final ImageView playOrPauseCollapsed;
    private final ImageView playOrPauseExpanded;
    private final ImageView playcardCaret;
    private final View playcardControlsGroup;
    private final ImageView rating;
    private final TextView remainingTime;
    private final View$OnClickListener resumeOnClickListener;
    private final SnappableSeekBar seekBar;
    private final ViewTreeObserver$OnGlobalLayoutListener seekBarLayoutListener;
    private final ImageView sharing;
    private final View$OnClickListener sharingClickListener;
    private final View$OnClickListener showLanguageSelectorClickListener;
    private final View$OnClickListener showRatingClickListener;
    private final View$OnClickListener showTargetSelectionDialogListener;
    private final View$OnClickListener showVideoDetailsClickListener;
    private final View$OnClickListener showVolumeClickListener;
    private final IconFontTextView skipBackCollapsed;
    private final IconFontTextView skipBackExpanded;
    private final View$OnClickListener skipBackOnClickListener;
    private final ImageView stop;
    private final View$OnClickListener stopOnClickListener;
    private final TextView subtitle;
    private final TextView title;
    private final View titleGroup;
    private float titleGroupYPos;
    private final View titleTextGroup;
    private final ImageView volume;
    
    static {
        timeFormatter = new TimeFormatterHelper();
    }
    
    public MdxMiniPlayerViews(final NetflixActivity activity, final MdxMiniPlayerViews$MdxMiniPlayerViewCallbacks callbacks, final MdxUtils$MdxTargetSelectionDialogInterface dialogCallbacks) {
        this.animInterpolator = (Interpolator)new AccelerateDecelerateInterpolator();
        this.episodesButtonVisible = true;
        this.isShowingViewForExpanded = null;
        this.onSeekBarChangeListener = new MdxMiniPlayerViews$1(this);
        this.dummyClickListener = (View$OnClickListener)new MdxMiniPlayerViews$2(this);
        this.pauseOnClickListener = (View$OnClickListener)new MdxMiniPlayerViews$3(this);
        this.showVideoDetailsClickListener = (View$OnClickListener)new MdxMiniPlayerViews$4(this);
        this.resumeOnClickListener = (View$OnClickListener)new MdxMiniPlayerViews$5(this);
        this.skipBackOnClickListener = (View$OnClickListener)new MdxMiniPlayerViews$6(this);
        this.stopOnClickListener = (View$OnClickListener)new MdxMiniPlayerViews$7(this);
        this.episodesClickListener = (View$OnClickListener)new MdxMiniPlayerViews$8(this);
        this.showLanguageSelectorClickListener = (View$OnClickListener)new MdxMiniPlayerViews$9(this);
        this.showRatingClickListener = (View$OnClickListener)new MdxMiniPlayerViews$10(this);
        this.sharingClickListener = (View$OnClickListener)new MdxMiniPlayerViews$11(this);
        this.showVolumeClickListener = (View$OnClickListener)new MdxMiniPlayerViews$12(this);
        this.showTargetSelectionDialogListener = (View$OnClickListener)new MdxMiniPlayerViews$13(this);
        this.seekBarLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new MdxMiniPlayerViews$14(this);
        this.parentActivityClass = activity.getClass().getSimpleName();
        this.log("Creating");
        this.activity = activity;
        this.callbacks = callbacks;
        this.dialogCallbacks = dialogCallbacks;
        final Resources resources = activity.getResources();
        this.isTablet = DeviceUtils.isTabletByContext((Context)activity);
        final boolean portrait = DeviceUtils.isPortrait((Context)activity);
        final LayoutInflater layoutInflater = activity.getLayoutInflater();
        int n;
        if (this.isTablet || portrait) {
            n = 2130903142;
        }
        else {
            n = 2130903146;
        }
        (this.content = (ViewGroup)layoutInflater.inflate(n, (ViewGroup)null)).setOnClickListener(this.dummyClickListener);
        this.titleGroup = this.content.findViewById(2131427674);
        this.title = (TextView)this.content.findViewById(2131427685);
        this.subtitle = (TextView)this.content.findViewById(2131427686);
        this.titleTextGroup = this.content.findViewById(2131427683);
        this.titleTextGroup.getLayoutParams().width = this.computeTitleTextViewGroupWidth();
        this.playcardCaret = (ImageView)this.content.findViewById(2131427684);
        if (this.playcardCaret != null) {
            this.playcardCaret.setRotation(180.0f);
        }
        this.artwork = (AdvancedImageView)this.content.findViewById(2131427659);
        this.bifSeekTime = (TextView)this.content.findViewById(2131427662);
        this.bifImage = (ImageView)this.content.findViewById(2131427663);
        this.deviceNameGroup = this.content.findViewById(2131427660);
        this.deviceName = (TextView)this.content.findViewById(2131427661);
        this.playcardControlsGroup = this.content.findViewById(2131427669);
        this.playOrPauseExpanded = (ImageView)this.content.findViewById(2131427671);
        this.skipBackExpanded = (IconFontTextView)this.content.findViewById(2131427670);
        this.stop = (ImageView)this.content.findViewById(2131427672);
        this.auxControlsGroup = this.content.findViewById(2131427652);
        this.languageExpanded = (ImageView)this.content.findViewById(2131427653);
        this.episodesExpanded = (ImageView)this.content.findViewById(2131427657);
        this.rating = (ImageView)this.content.findViewById(2131427656);
        this.volume = (ImageView)this.content.findViewById(2131427654);
        this.sharing = (ImageView)this.content.findViewById(2131427655);
        if (this.sharing != null) {
            final ImageView sharing = this.sharing;
            int visibility;
            if (callbacks.isVideoUnshared()) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            sharing.setVisibility(visibility);
        }
        this.currentTime = (TextView)this.content.findViewById(2131427667);
        this.remainingTime = (TextView)this.content.findViewById(2131427668);
        (this.seekBar = (SnappableSeekBar)this.content.findViewById(2131427673)).setSnappableOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.seekBar.getViewTreeObserver().addOnGlobalLayoutListener(this.seekBarLayoutListener);
        final int dimensionPixelSize = resources.getDimensionPixelSize(2131296360);
        this.seekBar.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        if (this.isTablet) {
            this.seekBar.setScrubberDentBitmap(2130837784);
            this.seekBar.setShouldSnapToTouchStartPosition(true);
        }
        this.maxTitleTextYDelta = (int)((resources.getDimensionPixelOffset(2131296354) - resources.getDimensionPixelOffset(2131296352)) * 0.75f);
        this.maxTitleTextYMargin = AndroidUtils.dipToPixels((Context)activity, 2);
        int height;
        if (this.isTablet) {
            final int min = Math.min(AndroidUtils.dipToPixels((Context)activity, 600), DeviceUtils.getScreenWidthInPixels((Context)activity));
            this.auxControlsGroup.getLayoutParams().width = min;
            this.playcardControlsGroup.getLayoutParams().width = min;
            this.bifImage.getLayoutParams().width = min;
            if (!portrait) {
                this.artwork.getLayoutParams().width = min;
            }
            height = min / 2;
        }
        else {
            height = DeviceUtils.getScreenWidthInPixels((Context)activity) / 2;
        }
        this.bifImage.getLayoutParams().height = height;
        List<View> viewsById;
        if (this.isTablet) {
            this.languageCollapsed = (IconFontTextView)this.content.findViewById(2131427675);
            this.episodesCollapsed = (IconFontTextView)this.content.findViewById(2131427676);
            this.skipBackCollapsed = (IconFontTextView)this.content.findViewById(2131427678);
            this.episodesDivider = this.content.findViewById(2131427680);
            viewsById = ViewUtils.getViewsById((View)this.content, 2131427679, 2131427680, 2131427681, 2131427682);
            ViewUtils.showViews(viewsById);
        }
        else {
            this.languageCollapsed = null;
            this.episodesCollapsed = null;
            this.episodesDivider = null;
            this.skipBackCollapsed = (IconFontTextView)this.content.findViewById(2131427675);
            viewsById = null;
        }
        this.playOrPauseCollapsed = (ImageView)this.content.findViewById(2131427677);
        this.initCollapsedButton(this.languageCollapsed, 2131492961, 2131493159, 18);
        this.initCollapsedButton(this.episodesCollapsed, 2131492962, 2131493236, 20);
        this.initCollapsedButton(this.skipBackCollapsed, 2131492960, 2131493158, 24);
        (this.collapsedViews = new ArrayList<View>()).add((View)this.playOrPauseCollapsed);
        this.collapsedViews.add((View)this.skipBackCollapsed);
        this.collapsedViews.add((View)this.languageCollapsed);
        this.collapsedViews.add((View)this.episodesCollapsed);
        if (viewsById != null) {
            this.collapsedViews.addAll(viewsById);
        }
        ViewUtils.setLongTapListenersRecursivelyToShowContentDescriptionToast((View)this.content);
    }
    
    private int computeTitleTextViewGroupWidth() {
        return DeviceUtils.getScreenWidthInPixels((Context)this.activity) - (this.activity.getResources().getDimensionPixelSize(2131296355) + 1) * 4;
    }
    
    private void enableView(final View view, final boolean enabled) {
        if (view == null) {
            return;
        }
        view.setEnabled(enabled);
        view.clearAnimation();
        float alpha;
        if (enabled) {
            alpha = 1.0f;
        }
        else {
            alpha = 0.4f;
        }
        view.setAlpha(alpha);
    }
    
    private void fadeInAndShow(final Collection<View> collection) {
        for (final View view : collection) {
            if (view != null) {
                view.clearAnimation();
                view.animate().alpha(1.0f).setDuration(100L).setListener((Animator$AnimatorListener)null).start();
                view.setVisibility(0);
            }
        }
    }
    
    private void fadeInAndShow(final View... array) {
        this.fadeInAndShow(Arrays.asList(array));
    }
    
    private void fadeOut(final Collection<View> collection) {
        for (final View view : collection) {
            if (view != null) {
                view.clearAnimation();
                view.animate().alpha(0.0f).setDuration(100L).setListener((Animator$AnimatorListener)null).start();
            }
        }
    }
    
    private void fadeOut(final View... array) {
        this.fadeOut(Arrays.asList(array));
    }
    
    private void fadeOutAndHide(final Collection<View> collection) {
        for (final View view : collection) {
            if (view != null) {
                view.clearAnimation();
                view.animate().alpha(0.0f).setDuration(100L).setListener((Animator$AnimatorListener)new AnimationUtils$HideViewOnAnimatorEnd(view)).start();
            }
        }
    }
    
    private void fadeOutAndHide(final View... array) {
        this.fadeOutAndHide(Arrays.asList(array));
    }
    
    private void initCollapsedButton(final IconFontTextView iconFontTextView, final int text, final int n, final int n2) {
        if (iconFontTextView == null) {
            return;
        }
        iconFontTextView.setContentDescription((CharSequence)this.activity.getString(n));
        iconFontTextView.setText(text);
        iconFontTextView.setTextSize((float)n2);
        iconFontTextView.setVisibility(0);
    }
    
    private void initOnClickListeners() {
        this.artwork.setOnClickListener(this.showVideoDetailsClickListener);
        this.stop.setOnClickListener(this.stopOnClickListener);
        this.skipBackCollapsed.setOnClickListener(this.skipBackOnClickListener);
        if (this.skipBackExpanded != null) {
            this.skipBackExpanded.setOnClickListener(this.skipBackOnClickListener);
        }
        if (this.episodesCollapsed != null) {
            this.episodesCollapsed.setOnClickListener(this.episodesClickListener);
        }
        if (this.episodesExpanded != null) {
            this.episodesExpanded.setOnClickListener(this.episodesClickListener);
        }
        if (this.languageCollapsed != null) {
            this.languageCollapsed.setOnClickListener(this.showLanguageSelectorClickListener);
        }
        if (this.languageExpanded != null) {
            this.languageExpanded.setOnClickListener(this.showLanguageSelectorClickListener);
        }
        if (this.deviceNameGroup != null) {
            this.deviceNameGroup.setOnClickListener(this.showTargetSelectionDialogListener);
        }
        if (this.rating != null) {
            this.rating.setOnClickListener(this.showRatingClickListener);
        }
        if (this.sharing != null) {
            this.sharing.setOnClickListener(this.sharingClickListener);
        }
        if (this.volume != null) {
            this.volume.setOnClickListener(this.showVolumeClickListener);
        }
    }
    
    private void log(final String s) {
        if (Log.isLoggable()) {
            Log.v("MdxMiniPlayerViews", this.parentActivityClass + ": " + s);
        }
    }
    
    private void rotateCaretTo(final int n) {
        if (this.playcardCaret != null) {
            this.playcardCaret.animate().rotation((float)n).setDuration(200L).setInterpolator((TimeInterpolator)this.animInterpolator).start();
        }
    }
    
    private void translateTitleGroup(float n) {
        if (this.titleTextGroup != null) {
            n = 1.0f - n;
            this.titleTextGroup.setY(this.titleGroupYPos + this.maxTitleTextYDelta * n);
            final int n2 = (int)(n * this.maxTitleTextYMargin);
            this.title.setPadding(this.title.getPaddingLeft(), n2, this.title.getPaddingRight(), n2);
        }
    }
    
    private void updateEpisodeButtonVisibility() {
        final int n = 4;
        final boolean b = false;
        final boolean b2 = this.isShowingViewForExpanded != null && !this.isShowingViewForExpanded;
        if (Log.isLoggable()) {
            Log.v("MdxMiniPlayerViews", "Updating episode button visibility, isPanelCollapsed: " + b2 + ", episodesButtonVisible: " + this.episodesButtonVisible);
        }
        if (this.episodesCollapsed != null) {
            final IconFontTextView episodesCollapsed = this.episodesCollapsed;
            int visibility;
            if (b2 && this.episodesButtonVisible) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            episodesCollapsed.setVisibility(visibility);
        }
        if (this.episodesDivider != null) {
            final View episodesDivider = this.episodesDivider;
            int visibility2 = n;
            if (b2) {
                visibility2 = n;
                if (this.episodesButtonVisible) {
                    visibility2 = 0;
                }
            }
            episodesDivider.setVisibility(visibility2);
        }
        if (this.episodesExpanded != null) {
            final ImageView episodesExpanded = this.episodesExpanded;
            int visibility3;
            if (this.episodesButtonVisible) {
                visibility3 = (b ? 1 : 0);
            }
            else {
                visibility3 = 8;
            }
            episodesExpanded.setVisibility(visibility3);
        }
    }
    
    private void updateTimeViews(final int n) {
        final String stringForSeconds = MdxMiniPlayerViews.timeFormatter.getStringForSeconds(n);
        if (this.currentTime != null) {
            this.currentTime.setText((CharSequence)stringForSeconds);
        }
        if (this.bifSeekTime != null) {
            this.bifSeekTime.setText((CharSequence)stringForSeconds);
        }
        final String stringForSeconds2 = MdxMiniPlayerViews.timeFormatter.getStringForSeconds(this.seekBar.getMax() - n);
        if (this.remainingTime != null) {
            this.remainingTime.setText((CharSequence)stringForSeconds2);
        }
    }
    
    private void updateViewsForPanelChange(final boolean b) {
        if (this.isShowingViewForExpanded == null) {
            this.isShowingViewForExpanded = !b;
        }
        if (this.isShowingViewForExpanded == b) {
            Log.v("MdxMiniPlayerViews", "Views already updated for panel expansion - skipping");
            return;
        }
        this.isShowingViewForExpanded = b;
        Log.v("MdxMiniPlayerViews", "Updating views for panel expansion, expanded: " + this.isShowingViewForExpanded);
        if (this.isShowingViewForExpanded) {
            this.fadeOutAndHide(this.collapsedViews);
            this.rotateCaretTo(0);
            return;
        }
        this.fadeInAndShow(this.collapsedViews);
        this.rotateCaretTo(180);
        this.updateEpisodeButtonVisibility();
    }
    
    private void updateViewsForPanelSlide(final float n) {
        for (final View view : this.collapsedViews) {
            if (view != null) {
                view.setAlpha(n * n);
            }
        }
    }
    
    private void updateViewsForSeekBarUsage(final boolean b) {
        if (b) {
            this.fadeOut(this.auxControlsGroup, this.playcardControlsGroup);
            this.fadeOutAndHide(this.deviceNameGroup);
            return;
        }
        this.fadeInAndShow(this.auxControlsGroup, this.playcardControlsGroup, this.deviceNameGroup);
    }
    
    public void attachMenuItem(final MdxMenu mdxMenu) {
        this.mdxMenu = mdxMenu;
    }
    
    public boolean computeMdxMenuEnabled(final boolean b) {
        if (this.callbacks.isPlayingRemotely()) {
            Log.d("MdxMiniPlayerViews", "mdx is playing remotely - mdx menu enabled: " + b);
            return b;
        }
        Log.d("MdxMiniPlayerViews", "mdx is not playing remotely - mdx menu enabled");
        return true;
    }
    
    public void enableMdxMenu() {
        if (this.mdxMenu != null) {
            this.mdxMenu.setEnabled(true);
        }
    }
    
    public View getContentView() {
        return (View)this.content;
    }
    
    public int getProgress() {
        return this.seekBar.getProgress();
    }
    
    public View getSlidingPanelDragView() {
        return this.titleTextGroup;
    }
    
    public void onManagerReady(final ServiceManager serviceManager) {
        this.initOnClickListeners();
    }
    
    public void onPanelCollapsed() {
        Log.v("MdxMiniPlayerViews", "onPanelCollapsed()");
        this.showArtworkAndHideBif();
        this.updateViewsForSeekBarUsage(false);
        this.updateViewsForPanelChange(false);
        this.updateEpisodeButtonVisibility();
    }
    
    public void onPanelExpanded() {
        Log.v("MdxMiniPlayerViews", "onPanelExpanded()");
        this.updateViewsForPanelChange(true);
    }
    
    public void onPanelSlide(final float n) {
        this.translateTitleGroup(n);
        this.updateViewsForPanelSlide(n);
    }
    
    public void onResume() {
        this.updateMdxMenu();
        if (this.titleGroup != null) {
            this.titleGroupYPos = this.titleGroup.getY();
        }
        this.updateViewsForPanelChange(this.callbacks.isPanelExpanded());
    }
    
    public void setControlsEnabled(final boolean b) {
        final boolean b2 = true;
        this.log("Set controls enabled: " + b);
        this.content.setEnabled(b);
        this.seekBar.setEnabled(b);
        this.enableView((View)this.playOrPauseCollapsed, b);
        this.enableView((View)this.playOrPauseExpanded, b);
        this.enableView((View)this.skipBackCollapsed, b);
        this.enableView((View)this.skipBackExpanded, b);
        this.enableView((View)this.languageCollapsed, b && this.callbacks.isLanguageReady());
        this.enableView((View)this.languageExpanded, b && this.callbacks.isLanguageReady());
        this.enableView((View)this.episodesCollapsed, b && this.callbacks.isEpisodeReady());
        this.enableView((View)this.episodesExpanded, b && this.callbacks.isEpisodeReady());
        this.enableView(this.deviceNameGroup, b);
        this.enableView((View)this.stop, b);
        this.enableView((View)this.rating, b);
        this.enableView((View)this.volume, b);
        this.log(String.format("setControlsEnabled, enabled: %s, unshare state: %s", b, this.callbacks.isVideoUnshared()));
        this.enableView((View)this.sharing, b && !this.callbacks.isVideoUnshared() && b2);
        if (this.mdxMenu != null) {
            this.mdxMenu.setEnabled(this.computeMdxMenuEnabled(b));
            if (this.callbacks != null) {
                Log.d("MdxMiniPlayerViews", "setControlsEnabled");
                this.mdxMenu.update();
            }
        }
        this.updateViewsForPanelChange(this.callbacks.isPanelExpanded());
        this.callbacks.notifyControlsEnabled(b);
    }
    
    public void setEpisodesButtonVisibile(final boolean episodesButtonVisible) {
        this.episodesButtonVisible = episodesButtonVisible;
        this.updateEpisodeButtonVisibility();
    }
    
    public void setLanguageButtonEnabled(final boolean b) {
        this.enableView((View)this.languageCollapsed, b);
        this.enableView((View)this.languageExpanded, b);
    }
    
    public void setProgress(final int progress) {
        this.seekBar.setProgress(progress);
        this.updateTimeViews(progress);
    }
    
    public void setProgressMax(final int max) {
        this.seekBar.setMax(max);
    }
    
    public void setSharingButtonEnabled(final boolean b) {
        this.enableView((View)this.sharing, b);
    }
    
    public void setSharingButtonVisibility(final boolean b) {
        if (this.sharing != null) {
            final ImageView sharing = this.sharing;
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            sharing.setVisibility(visibility);
        }
    }
    
    public void setVolumeButtonVisibility(final boolean b) {
        if (this.volume != null) {
            final ImageView volume = this.volume;
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            volume.setVisibility(visibility);
        }
    }
    
    protected void showArtworkAndHideBif() {
        this.fadeInAndShow(this.artwork, this.currentTime);
        this.fadeOutAndHide(this.bifImage, this.bifSeekTime);
    }
    
    public void updateDeviceNameText(final String text) {
        this.deviceName.setText((CharSequence)text);
    }
    
    public void updateImage(final VideoDetails videoDetails) {
        NetflixActivity.getImageLoader((Context)this.activity).showImg(this.artwork, videoDetails.getHorzDispUrl(), IClientLogging$AssetType.boxArt, videoDetails.getTitle(), false, true);
    }
    
    public void updateMdxMenu() {
        if (this.mdxMenu != null && this.callbacks != null) {
            Log.d("MdxMiniPlayerViews", "updateMdxMenu");
            this.mdxMenu.update();
        }
    }
    
    public void updatePlayPauseButton(final boolean b) {
        final ImageView[] array = { this.playOrPauseCollapsed, this.playOrPauseExpanded };
        for (int length = array.length, i = 0; i < length; ++i) {
            final ImageView imageView = array[i];
            if (imageView != null) {
                int imageResource;
                if (b) {
                    imageResource = 2130837699;
                }
                else {
                    imageResource = 2130837698;
                }
                imageView.setImageResource(imageResource);
                View$OnClickListener onClickListener;
                if (b) {
                    onClickListener = this.resumeOnClickListener;
                }
                else {
                    onClickListener = this.pauseOnClickListener;
                }
                imageView.setOnClickListener(onClickListener);
            }
        }
    }
    
    public void updateSubtitleText(final String text) {
        final boolean notEmpty = StringUtils.isNotEmpty(text);
        if (this.subtitle != null) {
            this.subtitle.setText((CharSequence)text);
            final TextView subtitle = this.subtitle;
            int visibility;
            if (notEmpty) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            subtitle.setVisibility(visibility);
        }
        if (this.playcardCaret != null) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.playcardCaret.getLayoutParams();
            final NetflixActivity activity = this.activity;
            int n;
            if (notEmpty) {
                n = 1;
            }
            else {
                n = 5;
            }
            viewGroup$MarginLayoutParams.bottomMargin = AndroidUtils.dipToPixels((Context)activity, n);
        }
    }
    
    public void updateTitleText(final String text) {
        this.title.setText((CharSequence)text);
    }
    
    public void updateToEmptyState(final boolean controlsEnabled) {
        this.updateSubtitleText(null);
        final String string = this.activity.getString(2131493174);
        this.title.setText((CharSequence)string);
        this.deviceName.setText((CharSequence)string);
        this.setControlsEnabled(controlsEnabled);
    }
}
