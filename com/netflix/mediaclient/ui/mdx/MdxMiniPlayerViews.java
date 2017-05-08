// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.ArrayList;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils$HideViewOnAnimatorEnd;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import java.util.Collection;
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

public class MdxMiniPlayerViews
{
    private static final float ALPHA_50 = 0.5f;
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
    protected final NetflixActivity activity;
    private final Interpolator animInterpolator;
    private AdvancedImageView artwork;
    protected View auxControlsGroup;
    private ImageView bifImage;
    private TextView bifSeekTime;
    private final IMdxMiniPlayerViewCallbacks callbacks;
    private List<View> collapsedViews;
    protected final ViewGroup content;
    private TextView currentTime;
    private TextView deviceName;
    protected View deviceNameGroup;
    private final MdxUtils$MdxTargetSelectionDialogInterface dialogCallbacks;
    private final View$OnClickListener dummyClickListener;
    private boolean episodesButtonVisible;
    private final View$OnClickListener episodesClickListener;
    private IconFontTextView episodesCollapsed;
    private View episodesDivider;
    private ImageView episodesExpanded;
    private Boolean isShowingViewForExpanded;
    private IconFontTextView languageCollapsed;
    private ImageView languageExpanded;
    protected int maxTitleTextYDelta;
    protected int maxTitleTextYMargin;
    private MdxMenu mdxMenu;
    private final SnappableSeekBar$OnSnappableSeekBarChangeListener onSeekBarChangeListener;
    private final String parentActivityClass;
    private final View$OnClickListener pauseOnClickListener;
    private ImageView playOrPauseCollapsed;
    private ImageView playOrPauseExpanded;
    protected ImageView playcardCaret;
    protected View playcardControlsGroup;
    private ImageView rating;
    private TextView remainingTime;
    private final View$OnClickListener resumeOnClickListener;
    private SnappableSeekBar seekBar;
    private final ViewTreeObserver$OnGlobalLayoutListener seekBarLayoutListener;
    private final View$OnClickListener showLanguageSelectorClickListener;
    private final View$OnClickListener showRatingClickListener;
    private final View$OnClickListener showTargetSelectionDialogListener;
    private final View$OnClickListener showVideoDetailsClickListener;
    private final View$OnClickListener showVolumeClickListener;
    private IconFontTextView skipBackCollapsed;
    private IconFontTextView skipBackExpanded;
    private final View$OnClickListener skipBackOnClickListener;
    private ImageView stop;
    private final View$OnClickListener stopOnClickListener;
    protected TextView subtitle;
    private TextView subtitleExpanded;
    protected TextView title;
    private TextView titleExpanded;
    protected View titleGroup;
    protected float titleGroupYPos;
    protected View titleTextGroup;
    private ImageView volume;
    
    static {
        timeFormatter = new TimeFormatterHelper();
    }
    
    public MdxMiniPlayerViews(final NetflixActivity activity, final IMdxMiniPlayerViewCallbacks callbacks, final MdxUtils$MdxTargetSelectionDialogInterface dialogCallbacks) {
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
        this.showVolumeClickListener = (View$OnClickListener)new MdxMiniPlayerViews$11(this);
        this.showTargetSelectionDialogListener = (View$OnClickListener)new MdxMiniPlayerViews$12(this);
        this.seekBarLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new MdxMiniPlayerViews$13(this);
        this.log("Creating");
        this.parentActivityClass = activity.getClass().getSimpleName();
        this.activity = activity;
        this.callbacks = callbacks;
        this.dialogCallbacks = dialogCallbacks;
        this.content = (ViewGroup)activity.getLayoutInflater().inflate(this.getLayoutId(), (ViewGroup)null);
        this.findViews();
        this.init();
    }
    
    private int computeTitleTextViewGroupWidth() {
        return DeviceUtils.getScreenWidthInPixels((Context)this.activity) - (this.activity.getResources().getDimensionPixelSize(2131361826) + 1) * 4;
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
    
    private void fadeOut(final Collection<View> collection, final float n) {
        for (final View view : collection) {
            if (view != null) {
                view.clearAnimation();
                view.animate().alpha(n).setDuration(100L).setListener((Animator$AnimatorListener)null).start();
            }
        }
    }
    
    private void fadeOutAndHide(final Collection<View> collection) {
        for (final View view : collection) {
            if (view != null) {
                view.clearAnimation();
                view.animate().alpha(0.0f).setDuration(100L).setListener((Animator$AnimatorListener)new AnimationUtils$HideViewOnAnimatorEnd(view)).start();
            }
        }
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
    
    private void initContentClicks() {
        if (this.content != null) {
            this.content.setOnClickListener(this.dummyClickListener);
        }
    }
    
    private void initDividersAndCollapsedViews(final boolean b) {
        Collection<View> viewsById = null;
        if (b) {
            viewsById = ViewUtils.getViewsById((View)this.content, 2131689995, 2131689996, 2131689997, 2131689998);
            ViewUtils.showViews(viewsById);
        }
        else {
            this.languageCollapsed = null;
            this.episodesCollapsed = null;
            this.episodesDivider = null;
        }
        if (this.languageCollapsed != null) {
            this.initCollapsedButton(this.languageCollapsed, 2131231468, 2131230883, 18);
        }
        if (this.episodesCollapsed != null) {
            this.initCollapsedButton(this.episodesCollapsed, 2131231470, 2131230882, 20);
        }
        if (this.skipBackCollapsed != null) {
            this.initCollapsedButton(this.skipBackCollapsed, 2131231486, 2131230888, 24);
        }
        this.collapsedViews = new ArrayList<View>();
        if (this.playOrPauseCollapsed != null) {
            this.collapsedViews.add((View)this.playOrPauseCollapsed);
        }
        if (this.skipBackCollapsed != null) {
            this.collapsedViews.add((View)this.skipBackCollapsed);
        }
        if (this.languageCollapsed != null) {
            this.collapsedViews.add((View)this.languageCollapsed);
        }
        if (this.episodesCollapsed != null) {
            this.collapsedViews.add((View)this.episodesCollapsed);
        }
        if (viewsById != null) {
            this.collapsedViews.addAll(viewsById);
        }
    }
    
    private void initOnClickListeners() {
        if (this.artwork != null) {
            this.artwork.setOnClickListener(this.showVideoDetailsClickListener);
        }
        if (this.stop != null) {
            this.stop.setOnClickListener(this.stopOnClickListener);
        }
        if (this.skipBackCollapsed != null) {
            this.skipBackCollapsed.setOnClickListener(this.skipBackOnClickListener);
        }
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
        if (this.volume != null) {
            this.volume.setOnClickListener(this.showVolumeClickListener);
        }
    }
    
    private void initPlaycardCaret() {
        if (this.playcardCaret != null) {
            this.playcardCaret.setRotation(180.0f);
        }
    }
    
    private void initRating() {
        if (this.rating != null) {
            ViewUtils.setVisibleOrGone((View)this.rating, !BrowseExperience.showKidsExperience());
        }
    }
    
    private void initSeekBar(final Resources resources, final boolean b) {
        if (this.seekBar != null) {
            this.seekBar.setSnappableOnSeekBarChangeListener(this.onSeekBarChangeListener);
            this.seekBar.getViewTreeObserver().addOnGlobalLayoutListener(this.seekBarLayoutListener);
            final int dimensionPixelSize = resources.getDimensionPixelSize(2131361832);
            this.seekBar.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
            if (b) {
                this.seekBar.setScrubberDentBitmap(2130837896);
                this.seekBar.setShouldSnapToTouchStartPosition(true);
            }
        }
    }
    
    private void initTitleDimens(final Resources resources) {
        this.maxTitleTextYDelta = (int)((resources.getDimensionPixelOffset(2131361829) - resources.getDimensionPixelOffset(2131361830)) * 0.75f);
        this.maxTitleTextYMargin = AndroidUtils.dipToPixels((Context)this.activity, 2);
    }
    
    private void initTitleText() {
        if (this.titleTextGroup != null) {
            this.titleTextGroup.getLayoutParams().width = this.computeTitleTextViewGroupWidth();
        }
    }
    
    private void log(final String s) {
        if (Log.isLoggable()) {
            Log.v("MdxMiniPlayerViews", this.parentActivityClass + ": " + s);
        }
    }
    
    private void setMaxWidths(final boolean b, final boolean b2) {
        int bifHeight;
        if (b) {
            final int min = Math.min(AndroidUtils.dipToPixels((Context)this.activity, 600), DeviceUtils.getScreenWidthInPixels((Context)this.activity));
            this.auxControlsGroup.getLayoutParams().width = min;
            this.setBifWidth(this.playcardControlsGroup.getLayoutParams().width = min);
            if (!b2 && this.artwork != null) {
                this.artwork.getLayoutParams().width = min;
            }
            bifHeight = min / 2;
        }
        else {
            bifHeight = DeviceUtils.getScreenWidthInPixels((Context)this.activity) / 2;
        }
        this.setBifHeight(bifHeight);
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
        if (this.seekBar != null) {
            final String stringForSeconds2 = MdxMiniPlayerViews.timeFormatter.getStringForSeconds(this.seekBar.getMax() - n);
            if (this.remainingTime != null) {
                this.remainingTime.setText((CharSequence)stringForSeconds2);
            }
        }
    }
    
    private void updateViewsForPanelSlide(final float n) {
        for (final View view : this.collapsedViews) {
            if (view != null) {
                view.setAlpha(n * n);
            }
        }
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
    
    protected void fadeInAndShow(final View... array) {
        this.fadeInAndShow(Arrays.asList(array));
    }
    
    protected void fadeOut(final View... array) {
        this.fadeOut(Arrays.asList(array), 0.0f);
    }
    
    protected void fadeOutAndHide(final View... array) {
        this.fadeOutAndHide(Arrays.asList(array));
    }
    
    protected void fadeOutTranslucent(final View... array) {
        this.fadeOut(Arrays.asList(array), 0.5f);
    }
    
    protected void findViews() {
        this.titleGroup = this.content.findViewById(2131689961);
        this.title = (TextView)this.content.findViewById(2131689964);
        this.subtitle = (TextView)this.content.findViewById(2131689965);
        this.titleExpanded = (TextView)this.content.findViewById(2131690019);
        this.subtitleExpanded = (TextView)this.content.findViewById(2131690020);
        this.titleTextGroup = this.content.findViewById(2131689962);
        this.playcardCaret = (ImageView)this.content.findViewById(2131689963);
        this.artwork = (AdvancedImageView)this.content.findViewById(2131689967);
        this.bifSeekTime = (TextView)this.content.findViewById(2131689969);
        this.bifImage = (ImageView)this.content.findViewById(2131689970);
        this.deviceNameGroup = this.content.findViewById(2131689960);
        this.deviceName = (TextView)this.content.findViewById(2131689968);
        this.playcardControlsGroup = this.content.findViewById(2131689986);
        this.playOrPauseExpanded = (ImageView)this.content.findViewById(2131689988);
        this.skipBackExpanded = (IconFontTextView)this.content.findViewById(2131689987);
        this.stop = (ImageView)this.content.findViewById(2131689989);
        this.auxControlsGroup = this.content.findViewById(2131689955);
        this.languageExpanded = (ImageView)this.content.findViewById(2131689956);
        this.episodesExpanded = (ImageView)this.content.findViewById(2131689959);
        this.rating = (ImageView)this.content.findViewById(2131689958);
        this.volume = (ImageView)this.content.findViewById(2131689957);
        this.currentTime = (TextView)this.content.findViewById(2131689984);
        this.remainingTime = (TextView)this.content.findViewById(2131689985);
        this.seekBar = (SnappableSeekBar)this.content.findViewById(2131689990);
        this.languageCollapsed = (IconFontTextView)this.content.findViewById(2131689991);
        this.episodesCollapsed = (IconFontTextView)this.content.findViewById(2131689992);
        this.skipBackCollapsed = (IconFontTextView)this.content.findViewById(2131689994);
        this.episodesDivider = this.content.findViewById(2131689996);
        if (DeviceUtils.isNotTabletByContext(this.content.getContext())) {
            this.skipBackCollapsed = (IconFontTextView)this.content.findViewById(2131689991);
        }
        this.playOrPauseCollapsed = (ImageView)this.content.findViewById(2131689993);
    }
    
    public View getContentView() {
        return (View)this.content;
    }
    
    protected int getLayoutId() {
        if (DeviceUtils.isPortrait((Context)this.activity)) {
            return 2130903187;
        }
        return 2130903193;
    }
    
    public int getProgress() {
        if (this.seekBar == null) {
            return 0;
        }
        return this.seekBar.getProgress();
    }
    
    public View getSlidingPanelDragView() {
        return this.titleTextGroup;
    }
    
    public void init() {
        final Resources resources = this.activity.getResources();
        final boolean tabletByContext = DeviceUtils.isTabletByContext((Context)this.activity);
        final boolean portrait = DeviceUtils.isPortrait((Context)this.activity);
        this.initContentClicks();
        this.initTitleText();
        this.initPlaycardCaret();
        this.initRating();
        this.initSeekBar(resources, tabletByContext);
        this.initTitleDimens(resources);
        this.setMaxWidths(tabletByContext, portrait);
        this.initDividersAndCollapsedViews(tabletByContext);
        ViewUtils.setLongTapListenersRecursivelyToShowContentDescriptionToast((View)this.content);
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
    
    public void rotateCaretTo(final int n) {
        if (this.playcardCaret != null) {
            this.playcardCaret.animate().rotation((float)n).setDuration(200L).setInterpolator((TimeInterpolator)this.animInterpolator).start();
        }
    }
    
    protected void setBifHeight(final int height) {
        if (this.bifImage != null) {
            this.bifImage.getLayoutParams().height = height;
        }
    }
    
    protected void setBifWidth(final int width) {
        if (this.bifImage != null) {
            this.bifImage.getLayoutParams().width = width;
        }
    }
    
    public void setControlsEnabled(final boolean b) {
        this.log("Set controls enabled: " + b);
        this.content.setEnabled(b);
        if (this.seekBar != null) {
            this.seekBar.setEnabled(b);
        }
        if (this.playOrPauseCollapsed != null) {
            this.enableView((View)this.playOrPauseCollapsed, b);
        }
        if (this.playOrPauseExpanded != null) {
            this.enableView((View)this.playOrPauseExpanded, b);
        }
        if (this.skipBackCollapsed != null) {
            this.enableView((View)this.skipBackCollapsed, b);
        }
        if (this.skipBackExpanded != null) {
            this.enableView((View)this.skipBackExpanded, b);
        }
        if (this.languageCollapsed != null) {
            this.enableView((View)this.languageCollapsed, b && this.callbacks.isLanguageReady());
        }
        if (this.languageExpanded != null) {
            this.enableView((View)this.languageExpanded, b && this.callbacks.isLanguageReady());
        }
        if (this.episodesCollapsed != null) {
            this.enableView((View)this.episodesCollapsed, b && this.callbacks.isEpisodeReady());
        }
        if (this.episodesExpanded != null) {
            this.enableView((View)this.episodesExpanded, b && this.callbacks.isEpisodeReady());
        }
        if (this.deviceNameGroup != null) {
            this.enableView(this.deviceNameGroup, b);
        }
        if (this.stop != null) {
            this.enableView((View)this.stop, b);
        }
        if (this.rating != null) {
            this.enableView((View)this.rating, b);
        }
        if (this.volume != null) {
            this.enableView((View)this.volume, b);
        }
        this.log(String.format("setControlsEnabled, enabled: %s", b));
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
        if (this.languageCollapsed != null) {
            this.enableView((View)this.languageCollapsed, b);
        }
        this.enableView((View)this.languageExpanded, b);
    }
    
    public void setProgress(final int progress) {
        if (this.seekBar != null) {
            this.seekBar.setProgress(progress);
            this.updateTimeViews(progress);
        }
    }
    
    public void setProgressMax(final int max) {
        if (this.seekBar != null) {
            this.seekBar.setMax(max);
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
        if (this.artwork != null) {
            this.fadeInAndShow(this.artwork);
        }
        if (this.currentTime != null) {
            this.fadeInAndShow(this.currentTime);
        }
        if (this.bifImage != null) {
            this.fadeOutAndHide(this.bifImage);
        }
        if (this.bifSeekTime != null) {
            this.fadeOutAndHide(this.bifSeekTime);
        }
    }
    
    protected void translateTitleGroup(float n) {
        if (this.titleTextGroup != null) {
            n = 1.0f - n;
            this.titleTextGroup.setY(this.titleGroupYPos + this.maxTitleTextYDelta * n);
            final int n2 = (int)(n * this.maxTitleTextYMargin);
            if (this.title != null) {
                this.title.setPadding(this.title.getPaddingLeft(), n2, this.title.getPaddingRight(), n2);
            }
        }
    }
    
    public void updateDeviceNameText(final String text) {
        if (this.deviceName != null) {
            this.deviceName.setText((CharSequence)text);
        }
    }
    
    public void updateImage(final VideoDetails videoDetails) {
        if (this.artwork == null) {
            return;
        }
        NetflixActivity.getImageLoader((Context)this.activity).showImg(this.artwork, videoDetails.getHorzDispUrl(), IClientLogging$AssetType.boxArt, videoDetails.getTitle(), ImageLoader$StaticImgConfig.DARK, true);
    }
    
    public void updateMdxMenu() {
        if (this.mdxMenu != null && this.callbacks != null) {
            Log.d("MdxMiniPlayerViews", "updateMdxMenu");
            this.mdxMenu.update();
        }
    }
    
    public void updatePlayPauseButton(final boolean b) {
        ImageView[] array;
        if (this.playOrPauseCollapsed != null) {
            array = new ImageView[] { this.playOrPauseCollapsed, this.playOrPauseExpanded };
        }
        else {
            array = new ImageView[] { this.playOrPauseExpanded };
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final ImageView imageView = array[i];
            if (imageView != null) {
                int imageResource;
                if (b) {
                    imageResource = 2130837781;
                }
                else {
                    imageResource = 2130837778;
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
    
    public void updateSubtitleText(final String s) {
        final boolean b = false;
        final boolean notEmpty = StringUtils.isNotEmpty(s);
        if (this.subtitle != null) {
            this.subtitle.setText((CharSequence)s);
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
        if (this.subtitleExpanded != null) {
            this.subtitleExpanded.setText((CharSequence)s);
            final TextView subtitleExpanded = this.subtitleExpanded;
            int visibility2;
            if (notEmpty) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 8;
            }
            subtitleExpanded.setVisibility(visibility2);
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
    
    public void updateTitleText(final String s) {
        if (this.title != null) {
            this.title.setText((CharSequence)s);
        }
        if (this.titleExpanded != null) {
            this.titleExpanded.setText((CharSequence)s);
        }
    }
    
    public void updateToEmptyState(final boolean controlsEnabled) {
        this.updateSubtitleText(null);
        final String string = this.activity.getString(2131231116);
        if (this.title != null) {
            this.title.setText((CharSequence)string);
        }
        if (this.deviceName != null) {
            this.deviceName.setText((CharSequence)string);
        }
        this.setControlsEnabled(controlsEnabled);
    }
    
    protected void updateViewsForPanelChange(final boolean b) {
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
    
    protected void updateViewsForSeekBarUsage(final boolean b) {
        if (b) {
            this.fadeOut(this.auxControlsGroup, this.playcardControlsGroup);
            if (this.deviceNameGroup != null) {
                this.fadeOutAndHide(this.deviceNameGroup);
            }
            return;
        }
        this.fadeInAndShow(this.auxControlsGroup, this.playcardControlsGroup, this.deviceNameGroup);
    }
}
