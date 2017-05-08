// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.Coppola1Utils;
import android.app.Dialog;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
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
import android.view.ViewPropertyAnimator;
import android.view.MenuItem;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;

public final class TopPanel extends PlayerSection
{
    private static final String TAG = "screen";
    private final ActionBar mActionBar;
    private final View$OnClickListener mBackListener;
    private MenuItem mBandwidth;
    private ViewPropertyAnimator mCurrentToolbarAnimation;
    private MenuItem mDebugMetadata;
    private String mDialogLanguageId;
    private MenuItem mEpisodeSelector;
    private boolean mEpisodeSelectorEnabled;
    private MenuItem mLanguage;
    private LanguageSelector mLanguageSelector;
    private final PlayScreen$Listeners mListeners;
    private boolean mMDXSelectorEnabled;
    protected MenuItem mMdxTarget;
    private PlayScreen mScreen;
    private MenuItem mSound;
    private final TextView mTitleLabel;
    private Toolbar mToolBar;
    private View mTopGradient;
    protected MdxTargetSelection mdxTargetSelector;
    
    public TopPanel(final PlayerFragment playerFragment, final PlayScreen$Listeners mListeners) {
        super(playerFragment);
        this.mBackListener = (View$OnClickListener)new TopPanel$1(this);
        this.mListeners = mListeners;
        (this.mActionBar = playerFragment.getNetflixActivity().getSupportActionBar()).setTitle("");
        this.mTitleLabel = (TextView)playerFragment.getView().findViewById(2131690088);
        this.mTopGradient = playerFragment.getView().findViewById(2131690085);
        this.mToolBar = (Toolbar)playerFragment.getView().findViewById(2131690087);
    }
    
    private void changeControlsVisibility(final boolean b) {
        boolean b2 = true;
        if (b) {
            this.mCurrentToolbarAnimation = AnimationUtils.startViewAppearanceAnimation((View)this.mToolBar, b);
            this.hideContentAdvisory();
            if (this.showLanguageMenuItem()) {
                ViewUtils.setVisibility(this.mLanguage, true);
            }
        }
        else {
            if (this.mCurrentToolbarAnimation != null) {
                this.mCurrentToolbarAnimation.cancel();
            }
            ViewUtils.setVisibleOrInvisible((View)this.mToolBar, false);
            this.showContentAdvisory();
        }
        boolean gradientVisibility = b;
        if (this.mScreen != null) {
            if (this.mScreen.isAdvisoryDisabled()) {
                b2 = false;
            }
            gradientVisibility = (b | b2);
        }
        this.setGradientVisibility(gradientVisibility);
    }
    
    private AlertDialog createMdxTargetSelectionDialog(final PlayerFragment playerFragment) {
        final IPlayer player = playerFragment.getPlayer();
        final boolean b = player != null && player.isPlaying();
        final int localDevicePosition = this.mdxTargetSelector.getLocalDevicePosition();
        this.mdxTargetSelector.setTarget(localDevicePosition);
        final MdxTargetSelectionDialog$Builder mdxTargetSelectionDialog$Builder = new MdxTargetSelectionDialog$Builder(playerFragment.getActivity());
        mdxTargetSelectionDialog$Builder.setCancelable(false);
        mdxTargetSelectionDialog$Builder.setTitle(2131231092);
        mdxTargetSelectionDialog$Builder.setAdapterData(this.mdxTargetSelector.getTargets((Context)playerFragment.getActivity()));
        mdxTargetSelectionDialog$Builder.setSelection(localDevicePosition, String.format(playerFragment.getString(2131231242), this.getCurrentTitle()));
        mdxTargetSelectionDialog$Builder.setOnItemClickListener((AdapterView$OnItemClickListener)new TopPanel$8(this, playerFragment, b));
        mdxTargetSelectionDialog$Builder.setOnCancelListener((DialogInterface$OnCancelListener)new TopPanel$9(this, playerFragment));
        return mdxTargetSelectionDialog$Builder.create();
    }
    
    private String getCurrentTitle() {
        if (this.mTitleLabel == null) {
            return null;
        }
        return this.mTitleLabel.getText().toString();
    }
    
    private void hideContentAdvisory() {
        if (this.mScreen != null) {
            this.mScreen.hideContentAdvisory();
        }
    }
    
    private void initBack() {
        this.mActionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    private void initGeneric(final Menu menu) {
        (this.mEpisodeSelector = menu.add(2131230878)).setVisible(this.mEpisodeSelectorEnabled);
        this.mEpisodeSelector.setIcon(2130837700);
        this.mEpisodeSelector.setShowAsAction(2);
        this.mEpisodeSelector.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$6(this));
    }
    
    private void initLanguages(final Menu menu) {
        this.mLanguageSelector = LanguageSelector.createInstance(this.playerFragment.getNetflixActivity(), this.playerFragment.getNetflixActivity().isTablet(), new TopPanel$3(this));
        (this.mLanguage = menu.add(2131230879)).setVisible(this.showLanguageMenuItem());
        this.mLanguage.setIcon(2130837775);
        this.mLanguage.setShowAsAction(2);
        this.mLanguage.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$4(this));
    }
    
    private void initMDX(final Menu menu) {
        (this.mMdxTarget = menu.add(2131230880)).setIcon(2130837681);
        this.mMdxTarget.setVisible(this.mMDXSelectorEnabled);
        this.mMdxTarget.setShowAsAction(2);
        this.mMdxTarget.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$7(this));
    }
    
    private void initQa(final Menu menu) {
    }
    
    private void initSound(final Menu menu) {
        (this.mSound = menu.add(2131230898)).setIcon(2130837780);
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
        if (this.playerFragment.getScreen() == null) {
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
        this.playerFragment.getSubtitleManager().setSubtitleVisibility(subtitleVisibility);
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
            this.playerFragment.changeLanguage(language, b2);
        }
        return !b2 && b;
    }
    
    private void showContentAdvisory() {
        if (this.mScreen != null) {
            this.mScreen.showContentAdvisory();
        }
    }
    
    private boolean showLanguageMenuItem() {
        final Language language = this.playerFragment.getLanguage();
        return this.mLanguage != null && language != null && language.isLanguageSwitchEnabled();
    }
    
    private void updateLastPanelInteractionTime() {
        if (this.playerFragment.isActivityValid()) {
            this.playerFragment.extendTimeoutTimer();
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
        else if (this.playerFragment.isActivityValid()) {
            Log.d("screen", "MDX target is reachable, display dialog");
            this.playerFragment.getNetflixActivity().displayDialog((Dialog)this.createMdxTargetSelectionDialog(this.playerFragment));
        }
    }
    
    LanguageSelector getLanguageSelector() {
        return this.mLanguageSelector;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            this.changeControlsVisibility(false);
        }
    }
    
    public void onCreateOptionsMenu(final Menu menu) {
        this.mScreen = this.playerFragment.getScreen();
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
    
    void setGradientVisibility(final boolean b) {
        if (ViewUtils.isVisible(this.mTopGradient) != b) {
            AnimationUtils.startViewAppearanceAnimation(this.mTopGradient, b);
        }
    }
    
    public void setMdxTargetSelector(final MdxTargetSelection mdxTargetSelector) {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.mdxTargetSelector = mdxTargetSelector;
                    final boolean mdxTargetSelectionVisible = this.isMdxTargetSelectionVisible();
                    if (!Coppola1Utils.isCoppolaContext((Context)this.playerFragment.getActivity())) {
                        if (!this.mActionBar.isShowing()) {
                            final boolean b = false;
                            if (Log.isLoggable()) {
                                Log.d("screen", "Bottom panel is visible: " + b);
                                Log.d("screen", "MDX target whould be visible: " + mdxTargetSelectionVisible);
                            }
                            if (b) {
                                if (!this.playerFragment.isActivityValid()) {
                                    Log.w("screen", "Player activity was destroyed, do nothing");
                                }
                                else {
                                    ViewUtils.setVisibility(this.mMdxTarget, mdxTargetSelectionVisible);
                                    this.mMDXSelectorEnabled = mdxTargetSelectionVisible;
                                }
                            }
                            return;
                        }
                    }
                }
                final boolean b = true;
                continue;
            }
        }
    }
    
    public void setTitle(final String s) {
        if (this.playerFragment.isActivityValid()) {
            this.playerFragment.runOnUiThread(new TopPanel$10(this, s));
        }
    }
    
    @Override
    public void show() {
        synchronized (this) {
            this.changeControlsVisibility(true);
        }
    }
}
