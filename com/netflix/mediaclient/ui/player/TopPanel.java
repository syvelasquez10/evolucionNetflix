// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import android.media.AudioManager;
import android.widget.ImageView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Dialog;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.widget.ImageButton;
import android.widget.Button;
import android.view.View;

public final class TopPanel extends PlayerSection
{
    private static final String TAG = "screen";
    private View mBackArrow;
    private View mBackIcon;
    private View mBackPadding;
    private Button mBtnLog;
    private Button mBtnMetadata;
    private String mDialogLanguageId;
    private ImageButton mLanguage;
    private LanguageSelector mLanguageSelector;
    private final Social mSocial;
    private SeekBar mSound;
    private TextView mTitleLabel;
    private View mTopPanel;
    
    public TopPanel(final PlayerActivity playerActivity, final SeekBar$OnSeekBarChangeListener seekBar$OnSeekBarChangeListener) {
        super(playerActivity);
        this.mSocial = new Social(playerActivity, playerActivity.getSocialProviderCallback());
        this.initGeneric();
        this.initBack();
        this.initQa();
        this.initLanguages();
        this.initSound(seekBar$OnSeekBarChangeListener);
    }
    
    private void initBack() {
        final View$OnTouchListener onTouchListener = (View$OnTouchListener)new View$OnTouchListener() {
            final /* synthetic */ Runnable val$upAction = TopPanel.this.context.createUpActionRunnable();
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final PlayerActivity context = TopPanel.this.context;
                if (context != null) {
                    this.val$upAction.run();
                    context.cleanupAndExit();
                }
                return true;
            }
        };
        this.mBackArrow = this.context.findViewById(2131099836);
        if (this.mBackArrow != null) {
            this.mBackArrow.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
        this.mBackIcon = this.context.findViewById(2131099837);
        if (this.mBackIcon != null) {
            this.mBackIcon.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
        this.mBackPadding = this.context.findViewById(2131099838);
        if (this.mBackPadding != null) {
            this.mBackPadding.setOnTouchListener((View$OnTouchListener)onTouchListener);
        }
    }
    
    private void initGeneric() {
        this.mTopPanel = this.context.findViewById(2131099835);
        if (this.mTopPanel == null) {
            Log.e("screen", "========>top null!");
        }
        this.mTitleLabel = (TextView)this.context.findViewById(2131099888);
    }
    
    private void initLanguages() {
        this.mLanguageSelector = LanguageSelector.createInstance(this.context, this.context.isTablet(), (LanguageSelector.LanguageSelectorCallback)new LanguageSelector.LanguageSelectorCallback() {
            @Override
            public void languageChanged(final Language language, final boolean b) {
                Log.d("screen", "Language changed");
                if (TopPanel.this.processLanguageChange(language) && b) {
                    TopPanel.this.context.doUnpause();
                }
                TopPanel.this.context.startScreenUpdateTask();
                TopPanel.this.context.reportUiModelessViewSessionEnded(IClientLogging.ModalView.audioSubtitlesSelector, TopPanel.this.mDialogLanguageId);
            }
            
            @Override
            public void updateDialog(final Dialog dialog) {
                TopPanel.this.context.updateVisibleDialog(dialog);
            }
            
            @Override
            public void userCanceled() {
                Log.d("screen", "User canceled selection");
                TopPanel.this.context.doUnpause();
                TopPanel.this.context.startScreenUpdateTask();
                TopPanel.this.context.reportUiModelessViewSessionEnded(IClientLogging.ModalView.audioSubtitlesSelector, TopPanel.this.mDialogLanguageId);
            }
            
            @Override
            public boolean wasPlaying() {
                return TopPanel.this.context.getPlayer().isPlaying();
            }
        });
        final View$OnClickListener onClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.d("screen", "Display language dialog");
                final PlayerActivity context = TopPanel.this.context;
                if (context != null) {
                    context.extendTimeoutTimer();
                }
                TopPanel.this.mLanguageSelector.display(TopPanel.this.context.getLanguage());
                TopPanel.this.context.stopScreenUpdateTask();
                TopPanel.this.mDialogLanguageId = TopPanel.this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.audioSubtitlesSelector);
            }
        };
        final View viewById = this.context.findViewById(2131099863);
        if (viewById instanceof ImageView) {
            Log.d("screen", "Add language button");
            (this.mLanguage = (ImageButton)viewById).setOnClickListener((View$OnClickListener)onClickListener);
            this.mLanguage.setBackgroundColor(this.transpColor);
        }
    }
    
    private void initQa() {
    }
    
    private void initSound(final SeekBar$OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSound = (SeekBar)this.context.findViewById(2131099843);
        if (this.mSound == null) {
            Log.e("screen", "Sound seekbar was NOT found!");
            return;
        }
        this.mSound.setOnSeekBarChangeListener(onSeekBarChangeListener);
        final AudioManager audioManager = (AudioManager)this.context.getSystemService("audio");
        if (audioManager != null) {
            final int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "Maximal audio volume for music stream is: " + streamMaxVolume);
            }
            this.mSound.setMax(streamMaxVolume);
            this.mSound.setKeyProgressIncrement(1);
            this.mSound.setProgress(audioManager.getStreamVolume(3));
            return;
        }
        Log.e("screen", "Audio manager is not available, can not set max volume");
    }
    
    private boolean processLanguageChange(final Language language) {
        if (this.context.getScreen() != null) {
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
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "Selected subtitle " + selectedSubtitle);
                Log.d("screen", "Selected audio source " + selectedAudio);
            }
            this.context.getSubtitleManager().setSubtitleVisibility(subtitleVisibility);
            int n = 0;
            boolean b = false;
            final int nccpOrderNumber = selectedAudio.getNccpOrderNumber();
            if (nccpOrderNumber != language.getCurrentNccpAudioIndex()) {
                if (Log.isLoggable("screen", 3)) {
                    Log.d("screen", "Audio source is switched from " + language.getCurrentNccpAudioIndex() + " to " + nccpOrderNumber + " NCCP index");
                }
                n = 1;
                b = true;
                Log.d("screen", "Start change language, get awake clock");
            }
            else {
                Log.d("screen", "No need to change audio.");
            }
            if (selectedSubtitle != null) {
                final int nccpOrderNumber2 = selectedSubtitle.getNccpOrderNumber();
                if (nccpOrderNumber2 != language.getCurrentNccpSubtitleIndex()) {
                    if (Log.isLoggable("screen", 3)) {
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
                this.context.changeLanguage(language, b);
            }
            if (!b) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void changeActionState(final boolean b) {
        synchronized (this) {
            this.enableButton((View)this.mLanguage, b);
            if (this.mBtnLog != null) {
                this.mBtnLog.setEnabled(b);
            }
            if (this.mBtnMetadata != null) {
                this.mBtnMetadata.setEnabled(b);
            }
            this.mSocial.changeActionState(b);
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
        }
    }
    
    public String getCurrentTitle() {
        if (this.mTitleLabel == null) {
            return null;
        }
        return this.mTitleLabel.getText().toString();
    }
    
    LanguageSelector getLanguageSelector() {
        return this.mLanguageSelector;
    }
    
    Social getSocial() {
        return this.mSocial;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            if (this.mTopPanel != null) {
                this.mTopPanel.setVisibility(8);
            }
            if (this.mLanguage != null) {
                this.mLanguage.setVisibility(8);
            }
            if (this.mBtnLog != null) {
                this.mBtnLog.setVisibility(8);
            }
            if (this.mBtnMetadata != null) {
                this.mBtnMetadata.setVisibility(8);
            }
            this.mSocial.hide();
        }
    }
    
    public void hideSoundSection() {
        synchronized (this) {
            if (this.mTopPanel != null) {
                this.mTopPanel.setBackgroundResource(this.context.getUiResources().topBackground);
                this.mTopPanel.setVisibility(8);
            }
            if (this.mBackArrow != null) {
                this.mBackArrow.setVisibility(0);
            }
            if (this.mBackIcon != null) {
                this.mBackIcon.setVisibility(0);
            }
            if (this.mTitleLabel != null) {
                this.mTitleLabel.setVisibility(0);
            }
        }
    }
    
    public void initAudioProgress(final int progress) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "InitAudioProgress: pos " + progress);
        }
        if (this.mSound != null) {
            Log.d("screen", "Audio: Updating seekbar");
            this.mSound.setProgress(progress);
        }
    }
    
    public int setAudioProgress(final int progress) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "SetAudioProgress: pos " + progress);
        }
        if (this.mSound != null) {
            Log.d("screen", "Audio: Updating seekbar");
            this.mSound.setProgress(progress);
        }
        return progress;
    }
    
    public void setLanguage(final Language language) {
        if (this.mLanguage != null && language != null && language.isLanguageSwitchEnabled()) {
            final PlayerActivity context = this.context;
            if (context != null) {
                context.runInUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final ImageButton access$300 = TopPanel.this.mLanguage;
                        if (access$300 != null && !((ImageView)access$300).isShown()) {
                            ((ImageView)access$300).setVisibility(0);
                        }
                    }
                });
            }
        }
    }
    
    public void setTitles(String string, final String s) {
        final PlayerActivity context = this.context;
        final StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(s)) {
            sb.append(s).append(" : ");
        }
        string = sb.append(string).toString();
        if (context != null && !context.isFinishing()) {
            context.runInUiThread(new Runnable() {
                @Override
                public void run() {
                    final TextView access$400 = TopPanel.this.mTitleLabel;
                    if (access$400 == null) {
                        return;
                    }
                    if (StringUtils.isEmpty(string)) {
                        access$400.setText((CharSequence)"");
                        return;
                    }
                    access$400.setText((CharSequence)string);
                }
            });
        }
    }
    
    @Override
    public void show() {
        while (true) {
            synchronized (this) {
                if (this.mTopPanel != null) {
                    this.mTopPanel.setBackgroundResource(this.context.getUiResources().topBackground);
                    this.mTopPanel.setVisibility(0);
                }
                if (this.mBackArrow != null) {
                    this.mBackArrow.setVisibility(0);
                }
                if (this.mBackIcon != null) {
                    this.mBackIcon.setVisibility(0);
                }
                if (this.mTitleLabel != null) {
                    this.mTitleLabel.setVisibility(0);
                }
                final Language language = this.context.getLanguage();
                if (this.mLanguage != null && language != null && language.isLanguageSwitchEnabled()) {
                    this.mLanguage.setVisibility(0);
                }
                if (this.mBtnLog != null) {
                    this.mBtnLog.setVisibility(0);
                }
                if (this.mBtnMetadata != null) {
                    this.mBtnMetadata.setVisibility(0);
                }
                this.mSocial.show();
                if (this.mSound != null) {
                    this.mSound.setVisibility(0);
                    final AudioManager audioManager = (AudioManager)this.context.getSystemService("audio");
                    if (audioManager != null) {
                        Log.d("screen", "Audio manager is available, update volume to " + audioManager.getStreamVolume(3));
                        audioManager.setStreamMute(3, false);
                        this.mSound.setProgress(audioManager.getStreamVolume(3));
                    }
                    else {
                        Log.e("screen", "Audio manager is not available, can not update volume");
                    }
                    return;
                }
            }
            Log.e("screen", "Soundbar not found!");
        }
    }
    
    public void showSoundSection() {
        synchronized (this) {
            final SeekBar mSound = this.mSound;
            if (mSound != null) {
                mSound.setVisibility(0);
            }
            if (this.mBackArrow != null) {
                this.mBackArrow.setVisibility(4);
            }
            if (this.mBackIcon != null) {
                this.mBackIcon.setVisibility(4);
            }
            if (this.mLanguage != null) {
                this.mLanguage.setVisibility(8);
            }
            if (this.mBtnLog != null) {
                this.mBtnLog.setVisibility(8);
            }
            if (this.mBtnMetadata != null) {
                this.mBtnMetadata.setVisibility(8);
            }
            this.mSocial.hide();
            if (this.mTitleLabel != null) {
                this.mTitleLabel.setVisibility(4);
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.setBackgroundResource(0);
                this.mTopPanel.setVisibility(0);
            }
        }
    }
}
