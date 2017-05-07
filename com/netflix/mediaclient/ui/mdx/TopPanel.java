// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.ImageView;
import com.netflix.mediaclient.media.Language;
import android.app.Dialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.app.Activity;
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.widget.ImageButton;
import android.view.View;

public final class TopPanel extends PlaycardSection
{
    private static final String TAG = "playcard";
    private boolean episodePlaying;
    private View mBackArrow;
    private View mBackIcon;
    private View mBackPadding;
    private View mEpisodeContainer;
    private View mEpisodeDivider;
    private boolean mEpisodeReady;
    private ImageButton mEpisodeSelector;
    private ImageButton mLanguage;
    private View mLanguageContainer;
    private View mLanguageDivider;
    private LanguageSelector mLanguageSelector;
    private ImageButton mRatings;
    private final Social mSocial;
    private SeekBar mSound;
    private View mSoundPanel;
    private View mTopPanel;
    protected ImageButton mdxTarget;
    private MdxTargetSelection mdxTargetSelector;
    
    public TopPanel(final MdxPlaycardActivity mdxPlaycardActivity, final PlaycardScreen.Listeners listeners) {
        super(mdxPlaycardActivity);
        this.mSocial = new Social(mdxPlaycardActivity, mdxPlaycardActivity.getSocialProviderCallback());
        this.initGeneric(listeners);
        this.initBack();
        this.initLanguages();
        this.initSound(listeners.audioPositionListener);
    }
    
    private void initBack() {
        final View$OnTouchListener onTouchListener = (View$OnTouchListener)new View$OnTouchListener() {
            final /* synthetic */ Runnable val$upAction = TopPanel.this.context.createUpActionRunnable();
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final MdxPlaycardActivity context = TopPanel.this.context;
                if (context != null) {
                    this.val$upAction.run();
                    context.finish();
                }
                return true;
            }
        };
        this.mBackArrow = this.context.findViewById(2131230970);
        if (this.mBackArrow != null) {
            this.mBackArrow.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
        this.mBackIcon = this.context.findViewById(2131230971);
        if (this.mBackIcon != null) {
            this.mBackIcon.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
        this.mBackPadding = this.context.findViewById(2131230972);
        if (this.mBackPadding != null) {
            this.mBackPadding.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
    }
    
    private void initGeneric(final PlaycardScreen.Listeners listeners) {
        this.mTopPanel = this.context.findViewById(2131230969);
        if (this.mTopPanel == null) {
            Log.e("playcard", "========>top null!");
        }
        this.mEpisodeSelector = (ImageButton)this.context.findViewById(2131230994);
        if (this.mEpisodeSelector != null) {
            this.mEpisodeSelector.setOnClickListener(listeners.episodeSelectorListener);
        }
        this.mRatings = (ImageButton)this.context.findViewById(2131230974);
        if (this.mRatings != null) {
            this.mRatings.setOnClickListener(listeners.ratingListener);
        }
        final View$OnClickListener onClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final MdxPlaycardActivity context = TopPanel.this.context;
                if (context != null) {
                    context.extendTimeoutTimer();
                }
                Log.d("playcard", "MDX target is reachable, display dialog");
                TopPanel.this.context.displayDialog((Dialog)MdxUtils.createMdxTargetSelectionDialog(TopPanel.this.context, (MdxUtils.MdxTargetSelectionDialogInterface)TopPanel.this.context));
            }
        };
        this.mdxTarget = (ImageButton)this.context.findViewById(2131230973);
        if (this.mdxTarget != null) {
            this.mdxTarget.setOnClickListener((View$OnClickListener)onClickListener);
        }
        this.mEpisodeContainer = this.context.findViewById(2131230993);
        this.mEpisodeDivider = this.context.findViewById(2131230992);
        this.mLanguageContainer = this.context.findViewById(2131230996);
        this.mLanguageDivider = this.context.findViewById(2131230995);
    }
    
    private void initLanguages() {
        this.mLanguageSelector = LanguageSelector.createInstance(this.context, this.context.isTablet(), (LanguageSelector.LanguageSelectorCallback)new LanguageSelector.LanguageSelectorCallback() {
            @Override
            public void languageChanged(final Language language, final boolean b) {
                Log.d("playcard", "Language changed");
                TopPanel.this.context.getPlayer().changeLanguage(language);
            }
            
            @Override
            public void updateDialog(final Dialog dialog) {
                TopPanel.this.context.updateVisibleDialog(dialog);
            }
            
            @Override
            public void userCanceled() {
                if (Log.isLoggable("playcard", 3)) {
                    Log.d("playcard", "User canceled selection");
                }
                TopPanel.this.context.getScreen().setPausePlayButtonState(true);
                TopPanel.this.context.getPlayer().resume();
            }
            
            @Override
            public boolean wasPlaying() {
                return false;
            }
        });
        final View$OnClickListener onClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.d("playcard", "Display language dialog");
                final MdxPlaycardActivity context = TopPanel.this.context;
                if (context != null) {
                    context.extendTimeoutTimer();
                }
                if (TopPanel.this.context != null) {
                    TopPanel.this.mLanguageSelector.display(TopPanel.this.context.getLanguage());
                }
            }
        };
        final View viewById = this.context.findViewById(2131230997);
        if (viewById instanceof ImageView) {
            Log.d("playcard", "Add language button");
            (this.mLanguage = (ImageButton)viewById).setOnClickListener((View$OnClickListener)onClickListener);
        }
    }
    
    private void initSound(final SeekBar$OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSound = (SeekBar)this.context.findViewById(2131230977);
        this.mSoundPanel = this.context.findViewById(2131230976);
        if (this.mSound != null) {
            this.mSound.setOnSeekBarChangeListener(onSeekBarChangeListener);
            this.mSound.setMax(100);
            this.mSound.setKeyProgressIncrement(1);
            this.mSound.setProgress(100);
            return;
        }
        Log.e("playcard", "Sound seekbar was NOT found!");
    }
    
    private void setEpisodeSelectorVisibility(final boolean b) {
        if (this.mEpisodeSelector == null) {
            return;
        }
        ViewUtils.setVisibility(this.mEpisodeContainer, b);
        ViewUtils.setVisibility((View)this.mEpisodeSelector, b);
        ViewUtils.setVisibility(this.mEpisodeDivider, b);
    }
    
    @Override
    public void changeActionState(final boolean enabled) {
        synchronized (this) {
            this.enableButton((View)this.mdxTarget, enabled);
            this.enableButton((View)this.mRatings, enabled);
            this.enableButton((View)this.mEpisodeSelector, enabled && this.mEpisodeReady);
            this.enableButton((View)this.mLanguage, enabled);
            if (this.mSound != null) {
                this.mSound.setEnabled(enabled);
            }
            if (this.mSocial != null) {
                this.mSocial.changeActionState(enabled);
            }
        }
    }
    
    @Override
    public void destroy() {
        synchronized (this) {
            if (this.mSound != null) {
                this.mSound.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
            }
            this.mSocial.destroy();
            if (this.mBackArrow != null) {
                this.mBackArrow.setOnTouchListener((View$OnTouchListener)null);
            }
            if (this.mBackIcon != null) {
                this.mBackIcon.setOnTouchListener((View$OnTouchListener)null);
            }
            if (this.mBackPadding != null) {
                this.mBackPadding.setOnTouchListener((View$OnTouchListener)null);
            }
            if (this.mEpisodeSelector != null) {
                this.mEpisodeSelector.setOnTouchListener((View$OnTouchListener)null);
            }
            if (this.mdxTarget != null) {
                this.mdxTarget.setOnTouchListener((View$OnTouchListener)null);
            }
            if (this.mRatings != null) {
                this.mRatings.setOnTouchListener((View$OnTouchListener)null);
            }
        }
    }
    
    public LanguageSelector getLanguageSelector() {
        return this.mLanguageSelector;
    }
    
    public MdxTargetSelection getMdxTargetSelector() {
        return this.mdxTargetSelector;
    }
    
    Social getSocial() {
        return this.mSocial;
    }
    
    SeekBar getSound() {
        return this.mSound;
    }
    
    @Override
    public void hide() {
    }
    // monitorenter(this)
    // monitorexit(this)
    
    public boolean isEpisodePlaying() {
        return this.episodePlaying;
    }
    
    public int setAudioProgress(final int progress) {
        if (Log.isLoggable("playcard", 3)) {
            Log.d("playcard", "SetAudioProgress: pos " + progress);
        }
        if (this.mSound != null) {
            Log.d("playcard", "Audio: Updating seekbar");
            this.mSound.setProgress(progress);
        }
        return progress;
    }
    
    public void setEpisodePlaying(final boolean episodePlaying) {
        this.setEpisodeSelectorVisibility(this.episodePlaying = episodePlaying);
    }
    
    void setEpisodeReady(final boolean mEpisodeReady) {
        this.mEpisodeReady = mEpisodeReady;
    }
    
    public void setLanguage(final Language language) {
        if (this.mLanguage != null && language != null && language.isLanguageSwitchEnabled()) {
            final MdxPlaycardActivity context = this.context;
            if (context != null) {
                context.runInUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final ImageButton access$100 = TopPanel.this.mLanguage;
                        if (access$100 != null && !((ImageView)access$100).isShown()) {
                            ViewUtils.setVisibility((View)access$100, true);
                            ViewUtils.setVisibility(TopPanel.this.mLanguageContainer, true);
                            ViewUtils.setVisibility(TopPanel.this.mLanguageDivider, true);
                        }
                    }
                });
            }
        }
    }
    
    public void setMdxTargetSelector(final MdxTargetSelection mdxTargetSelector) {
        synchronized (this) {
            this.mdxTargetSelector = mdxTargetSelector;
        }
    }
    
    public void setVolumeChangeSupported(final boolean b, int visibility) {
        final int n = 0;
        if (this.mSound != null) {
            this.mSound.setKeyProgressIncrement(visibility);
            if (this.mSoundPanel != null) {
                final View mSoundPanel = this.mSoundPanel;
                if (b) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                mSoundPanel.setVisibility(visibility);
            }
            final SeekBar mSound = this.mSound;
            if (b) {
                visibility = n;
            }
            else {
                visibility = 8;
            }
            mSound.setVisibility(visibility);
        }
    }
    
    @Override
    public void show() {
    }
    // monitorenter(this)
    // monitorexit(this)
}
