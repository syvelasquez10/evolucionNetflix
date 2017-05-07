// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.app.Dialog;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import android.app.AlertDialog;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.view.MenuItem;
import android.animation.Animator;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;

public final class TopPanel extends PlayerSection
{
    private static final String TAG = "screen";
    private final ActionBar mActionBar;
    private final View$OnClickListener mBackListener;
    private Animator mCurrentToolbarAnimation;
    private MenuItem mDebugMetadata;
    private String mDialogLanguageId;
    private MenuItem mEpisodeSelector;
    private boolean mEpisodeSelectorEnabled;
    private MenuItem mLanguage;
    private LanguageSelector mLanguageSelector;
    private final PlayScreen$Listeners mListeners;
    private boolean mMDXSelectorEnabled;
    protected MenuItem mMdxTarget;
    private MenuItem mSound;
    private final TextView mTitleLabel;
    private Toolbar mToolBar;
    private View mTopGradient;
    protected MdxTargetSelection mdxTargetSelector;
    
    public TopPanel(final PlayerActivity playerActivity, final PlayScreen$Listeners mListeners) {
        super(playerActivity);
        this.mBackListener = (View$OnClickListener)new TopPanel$1(this);
        this.mListeners = mListeners;
        (this.mActionBar = playerActivity.getSupportActionBar()).setTitle("");
        this.mTitleLabel = (TextView)playerActivity.findViewById(2131427711);
    }
    
    private void changeControlsVisibility(final boolean b) {
        if (b) {
            this.mCurrentToolbarAnimation = AnimationUtils.startViewAppearanceAnimation((View)this.mToolBar, b);
            if (this.showLanguageMenuItem()) {
                ViewUtils.setVisibility(this.mLanguage, true);
            }
        }
        else {
            if (this.mCurrentToolbarAnimation != null) {
                this.mCurrentToolbarAnimation.cancel();
            }
            ViewUtils.setVisibleOrInvisible((View)this.mToolBar, false);
        }
        AnimationUtils.startViewAppearanceAnimation(this.mTopGradient, b);
    }
    
    private AlertDialog createMdxTargetSelectionDialog(final PlayerActivity playerActivity) {
        final IPlayer player = playerActivity.getPlayer();
        final boolean b = player != null && player.isPlaying();
        final int localDevicePosition = this.mdxTargetSelector.getLocalDevicePosition();
        this.mdxTargetSelector.setTarget(localDevicePosition);
        final MdxTargetSelectionDialog$Builder mdxTargetSelectionDialog$Builder = new MdxTargetSelectionDialog$Builder(playerActivity);
        mdxTargetSelectionDialog$Builder.setCancelable(false);
        mdxTargetSelectionDialog$Builder.setTitle(2131493151);
        mdxTargetSelectionDialog$Builder.setAdapterData(this.mdxTargetSelector.getTargets((Context)playerActivity));
        mdxTargetSelectionDialog$Builder.setSelection(localDevicePosition, String.format(playerActivity.getString(2131493239), this.getCurrentTitle()));
        mdxTargetSelectionDialog$Builder.setOnItemClickListener((AdapterView$OnItemClickListener)new TopPanel$8(this, playerActivity, b));
        mdxTargetSelectionDialog$Builder.setOnCancelListener((DialogInterface$OnCancelListener)new TopPanel$9(this, playerActivity));
        return mdxTargetSelectionDialog$Builder.create();
    }
    
    private String getCurrentTitle() {
        if (this.mTitleLabel == null) {
            return null;
        }
        return this.mTitleLabel.getText().toString();
    }
    
    private void initBack() {
        this.mActionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    private void initGeneric(final Menu menu) {
        (this.mEpisodeSelector = menu.add(2131493236)).setVisible(this.mEpisodeSelectorEnabled);
        this.mEpisodeSelector.setIcon(2130837690);
        this.mEpisodeSelector.setShowAsAction(2);
        this.mEpisodeSelector.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$6(this));
        this.mTopGradient = this.context.findViewById(2131427707);
        this.mToolBar = (Toolbar)this.context.findViewById(2131427710);
    }
    
    private void initLanguages(final Menu menu) {
        this.mLanguageSelector = LanguageSelector.createInstance(this.context, this.context.isTablet(), new TopPanel$3(this));
        (this.mLanguage = menu.add(2131493159)).setVisible(this.showLanguageMenuItem());
        this.mLanguage.setIcon(2130837736);
        this.mLanguage.setShowAsAction(2);
        this.mLanguage.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$4(this));
    }
    
    private void initMDX(final Menu menu) {
        (this.mMdxTarget = menu.add(2131493157)).setIcon(2130837681);
        this.mMdxTarget.setVisible(this.mMDXSelectorEnabled);
        this.mMdxTarget.setShowAsAction(2);
        this.mMdxTarget.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$7(this));
    }
    
    private void initQa(final Menu menu) {
    }
    
    private void initSound(final Menu menu) {
        (this.mSound = menu.add(2131493190)).setIcon(2130837741);
        this.mSound.setShowAsAction(2);
        this.mSound.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$2(this));
    }
    
    private boolean isMdxTargetSelectionVisible() {
        return this.isMdxTargetSelectionVisible(this.mdxTargetSelector);
    }
    
    private boolean isMdxTargetSelectionVisible(final MdxTargetSelection mdxTargetSelection) {
        return mdxTargetSelection != null && mdxTargetSelection.getMdxTargets() != null && mdxTargetSelection.getMdxTargets().length > 1;
    }
    
    private boolean processLanguageChange(final Language language) {
        final boolean b = true;
        if (this.context.getScreen() == null) {
            return false;
        }
        final AudioSource selectedAudio = language.getSelectedAudio();
        final Subtitle selectedSubtitle = language.getSelectedSubtitle();
        boolean subtitleVisibility;
        if (selectedSubtitle == null) {
            Log.d("screen", "Selected subtitle is NONE");
            subtitleVisibility = false;
        }
        else {
            subtitleVisibility = true;
        }
        if (Log.isLoggable()) {
            Log.d("screen", "Selected subtitle " + selectedSubtitle);
            Log.d("screen", "Selected audio source " + selectedAudio);
        }
        this.context.getSubtitleManager().setSubtitleVisibility(subtitleVisibility);
        final int nccpOrderNumber = selectedAudio.getNccpOrderNumber();
        boolean b2;
        int n;
        if (nccpOrderNumber != language.getCurrentNccpAudioIndex()) {
            if (Log.isLoggable()) {
                Log.d("screen", "Audio source is switched from " + language.getCurrentNccpAudioIndex() + " to " + nccpOrderNumber + " NCCP index");
            }
            Log.d("screen", "Start change language, get awake clock");
            b2 = true;
            n = 1;
        }
        else {
            Log.d("screen", "No need to change audio.");
            b2 = false;
            n = 0;
        }
        if (selectedSubtitle != null) {
            final int nccpOrderNumber2 = selectedSubtitle.getNccpOrderNumber();
            if (nccpOrderNumber2 != language.getCurrentNccpSubtitleIndex()) {
                if (Log.isLoggable()) {
                    Log.d("screen", "Subtitle is now visible and it is switched from " + language.getCurrentNccpSubtitleIndex() + " to " + nccpOrderNumber2 + " NCCP index");
                }
                n = 1;
            }
            else {
                Log.d("screen", "No need to change subtitle.");
            }
        }
        else {
            Log.d("screen", "Subtitle is off");
            n = 1;
        }
        if (n != 0) {
            Log.d("screen", "Reloading tracks");
            this.context.changeLanguage(language, b2);
        }
        return !b2 && b;
    }
    
    private boolean showLanguageMenuItem() {
        final Language language = this.context.getLanguage();
        return this.mLanguage != null && language != null && language.isLanguageSwitchEnabled();
    }
    
    private void updateLastPanelInteractionTime() {
        final PlayerActivity context = this.context;
        if (context != null) {
            context.extendTimeoutTimer();
        }
    }
    
    @Override
    public void changeActionState(final boolean b) {
        synchronized (this) {
            this.enableButton(this.mLanguage, b);
            this.enableButton(this.mEpisodeSelector, b);
            this.enableButton(this.mMdxTarget, b);
            this.enableButton(this.mSound, b);
        }
    }
    
    @Override
    public void destroy() {
        synchronized (this) {
            if (this.mEpisodeSelector != null) {
                this.mEpisodeSelector.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)null);
            }
            if (this.mTitleLabel != null) {
                this.mTitleLabel.setOnClickListener((View$OnClickListener)null);
            }
        }
    }
    
    protected void displayMdxTargets() {
        if (this.mdxTargetSelector == null || this.mdxTargetSelector.getMdxTargets() == null || this.mdxTargetSelector.getMdxTargets().length < 2) {
            Log.d("screen", "Non local targets are not available!");
        }
        else {
            final PlayerActivity context = this.context;
            if (context != null) {
                Log.d("screen", "MDX target is reachable, display dialog");
                context.displayDialog((Dialog)this.createMdxTargetSelectionDialog(context));
            }
        }
    }
    
    LanguageSelector getLanguageSelector() {
        return this.mLanguageSelector;
    }
    
    public MdxTargetSelection getMdxTargetSelector() {
        return this.mdxTargetSelector;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            this.changeControlsVisibility(false);
        }
    }
    
    public void onCreateOptionsMenu(final Menu menu) {
        this.initMDX(menu);
        this.initGeneric(menu);
        this.initBack();
        this.initQa(menu);
        this.initLanguages(menu);
        this.initSound(menu);
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                Log.e("screen", "Unhandled menu action: " + (Object)menuItem.getTitle());
                return false;
            }
            case 16908332: {
                this.mBackListener.onClick((View)null);
                return true;
            }
        }
    }
    
    void setEpisodeSelectorVisibility(final boolean mEpisodeSelectorEnabled) {
        this.mEpisodeSelectorEnabled = mEpisodeSelectorEnabled;
        if (this.mEpisodeSelector == null) {
            return;
        }
        ViewUtils.setVisibility(this.mEpisodeSelector, mEpisodeSelectorEnabled);
    }
    
    public void setMdxTargetSelector(final MdxTargetSelection mdxTargetSelector) {
        synchronized (this) {
            this.mdxTargetSelector = mdxTargetSelector;
            final boolean mdxTargetSelectionVisible = this.isMdxTargetSelectionVisible();
            final boolean showing = this.mActionBar.isShowing();
            if (Log.isLoggable()) {
                Log.d("screen", "Bottom panel is visible: " + showing);
                Log.d("screen", "MDX target whould be visible: " + mdxTargetSelectionVisible);
            }
            if (showing) {
                if (this.context == null) {
                    Log.w("screen", "Player activity was destroyed, do nothing");
                }
                else {
                    ViewUtils.setVisibility(this.mMdxTarget, mdxTargetSelectionVisible);
                    this.mMDXSelectorEnabled = mdxTargetSelectionVisible;
                }
            }
        }
    }
    
    public void setTitle(final String s) {
        final PlayerActivity context = this.context;
        if (context != null && !context.isFinishing()) {
            context.runInUiThread(new TopPanel$10(this, s));
        }
    }
    
    @Override
    public void show() {
        synchronized (this) {
            this.changeControlsVisibility(true);
        }
    }
}
