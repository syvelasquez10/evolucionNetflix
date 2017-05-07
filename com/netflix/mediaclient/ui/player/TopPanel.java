// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Dialog;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthSaving;
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
import android.animation.Animator;
import android.view.MenuItem;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;

public final class TopPanel extends PlayerSection
{
    private static final String TAG = "screen";
    private final ActionBar mActionBar;
    private final View$OnClickListener mBackListener;
    private MenuItem mBandwidth;
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
    
    public TopPanel(final PlayerFragment playerFragment, final PlayScreen$Listeners mListeners) {
        super(playerFragment);
        this.mBackListener = (View$OnClickListener)new TopPanel$1(this);
        this.mListeners = mListeners;
        (this.mActionBar = playerFragment.getNetflixActivity().getSupportActionBar()).setTitle("");
        this.mTitleLabel = (TextView)playerFragment.getView().findViewById(2131624382);
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
    
    private AlertDialog createMdxTargetSelectionDialog(final PlayerFragment playerFragment) {
        final IPlayer player = playerFragment.getPlayer();
        final boolean b = player != null && player.isPlaying();
        final int localDevicePosition = this.mdxTargetSelector.getLocalDevicePosition();
        this.mdxTargetSelector.setTarget(localDevicePosition);
        final MdxTargetSelectionDialog$Builder mdxTargetSelectionDialog$Builder = new MdxTargetSelectionDialog$Builder(playerFragment.getActivity());
        mdxTargetSelectionDialog$Builder.setCancelable(false);
        mdxTargetSelectionDialog$Builder.setTitle(2131165513);
        mdxTargetSelectionDialog$Builder.setAdapterData(this.mdxTargetSelector.getTargets((Context)playerFragment.getActivity()));
        mdxTargetSelectionDialog$Builder.setSelection(localDevicePosition, String.format(playerFragment.getString(2131165640), this.getCurrentTitle()));
        mdxTargetSelectionDialog$Builder.setOnItemClickListener((AdapterView$OnItemClickListener)new TopPanel$9(this, playerFragment, b));
        mdxTargetSelectionDialog$Builder.setOnCancelListener((DialogInterface$OnCancelListener)new TopPanel$10(this, playerFragment));
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
    
    private void initBandwidth(final Menu menu) {
        final DataSaveConfigData bwSaveConfigData = this.playerFragment.getNetflixActivity().getServiceManager().getConfiguration().getBWSaveConfigData();
        if (!BandwidthSaving.isBWSavingEnabledForPlay((Context)this.playerFragment.getActivity(), bwSaveConfigData, this.playerFragment.getNetflixActivity().getServiceManager().getConfiguration().shouldForceBwSettingsInWifi())) {
            return;
        }
        this.mBandwidth = menu.add(2131165707);
        int icon;
        if (BandwidthSaving.isDataSavingEnabled((Context)this.playerFragment.getNetflixActivity(), bwSaveConfigData)) {
            icon = 2130837648;
        }
        else {
            icon = 2130837647;
        }
        this.mBandwidth.setIcon(icon);
        this.mBandwidth.setShowAsAction(2);
        this.mBandwidth.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$3(this, bwSaveConfigData));
    }
    
    private void initGeneric(final Menu menu) {
        (this.mEpisodeSelector = menu.add(2131165341)).setVisible(this.mEpisodeSelectorEnabled);
        this.mEpisodeSelector.setIcon(2130837671);
        this.mEpisodeSelector.setShowAsAction(2);
        this.mEpisodeSelector.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$7(this));
        this.mTopGradient = this.playerFragment.getView().findViewById(2131624379);
        this.mToolBar = (Toolbar)this.playerFragment.getView().findViewById(2131624381);
    }
    
    private void initLanguages(final Menu menu) {
        this.mLanguageSelector = LanguageSelector.createInstance(this.playerFragment.getNetflixActivity(), this.playerFragment.getNetflixActivity().isTablet(), new TopPanel$4(this));
        (this.mLanguage = menu.add(2131165342)).setVisible(this.showLanguageMenuItem());
        this.mLanguage.setIcon(2130837738);
        this.mLanguage.setShowAsAction(2);
        this.mLanguage.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$5(this));
    }
    
    private void initMDX(final Menu menu) {
        (this.mMdxTarget = menu.add(2131165343)).setIcon(2130837653);
        this.mMdxTarget.setVisible(this.mMDXSelectorEnabled);
        this.mMdxTarget.setShowAsAction(2);
        this.mMdxTarget.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new TopPanel$8(this));
    }
    
    private void initQa(final Menu menu) {
    }
    
    private void initSound(final Menu menu) {
        (this.mSound = menu.add(2131165360)).setIcon(2130837743);
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
    
    public MdxTargetSelection getMdxTargetSelector() {
        return this.mdxTargetSelector;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            this.changeControlsVisibility(false);
        }
    }
    
    public void onBandwidthSettingsDone(final ServiceManager serviceManager) {
        if (serviceManager == null) {
            Log.e("screen", "serviceManager null");
        }
        else {
            final DataSaveConfigData bwSaveConfigData = serviceManager.getConfiguration().getBWSaveConfigData();
            final boolean dataSavingEnabled = BandwidthSaving.isDataSavingEnabled((Context)serviceManager.getActivity(), bwSaveConfigData);
            int icon;
            if (dataSavingEnabled) {
                icon = 2130837648;
            }
            else {
                icon = 2130837647;
            }
            Log.d("nf_bw_saving", String.format("onBandwidthSettingsDone called - dataSavingEnabled: %b, icon: %d", dataSavingEnabled, icon));
            if (this.mBandwidth != null) {
                this.mBandwidth.setIcon(icon);
            }
            final int maxBandwidth = BandwidthSaving.getMaxBandwidth((Context)serviceManager.getActivity(), bwSaveConfigData);
            final IPlayer player = serviceManager.getPlayer();
            if (player != null && maxBandwidth > 0) {
                player.setVideoBitrateRange(0, maxBandwidth);
            }
            if (this.playerFragment != null) {
                this.playerFragment.doUnpause();
            }
        }
    }
    
    public void onCreateOptionsMenu(final Menu menu) {
        this.initMDX(menu);
        this.initGeneric(menu);
        this.initBack();
        this.initQa(menu);
        this.initLanguages(menu);
        this.initSound(menu);
        this.initBandwidth(menu);
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
                if (!this.playerFragment.isActivityValid()) {
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
        if (this.playerFragment.isActivityValid()) {
            this.playerFragment.runOnUiThread(new TopPanel$11(this, s));
        }
    }
    
    @Override
    public void show() {
        synchronized (this) {
            this.changeControlsVisibility(true);
        }
    }
}
