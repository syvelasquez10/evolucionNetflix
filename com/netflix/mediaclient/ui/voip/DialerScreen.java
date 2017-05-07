// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.Log;
import android.media.ToneGenerator;
import android.view.View$OnTouchListener;
import android.widget.ImageView;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.media.AudioManager;

class DialerScreen
{
    private static final int DELAY_POST = 1000;
    private static final String TAG = "VoipActivity";
    private AudioManager mAudioManager;
    private View[] mButtons;
    private TextView mCallStatus;
    private boolean mConnected;
    private View mDialer;
    protected TableLayout mDialpad;
    private View mDivider;
    protected FloatingActionButton mFab;
    private TimeFormatterHelper mFormatter;
    private Handler mHandler;
    private View mHeader;
    private ImageView mMicIcon;
    private boolean mMuted;
    private View$OnTouchListener mOnTouchListener;
    private ContactUsActivity mOwner;
    private ImageView mSpeakerIcon;
    private TextView mSpeakerLabel;
    private boolean mSpeakerOn;
    private TextView mTimer;
    private ToneGenerator mToneGenerator;
    private final Runnable onEverySecond;
    
    DialerScreen(final ContactUsActivity mOwner) {
        this.mHandler = new Handler();
        this.mOnTouchListener = (View$OnTouchListener)new DialerScreen$1(this);
        this.onEverySecond = new DialerScreen$2(this);
        this.mOwner = mOwner;
        this.mCallStatus = (TextView)this.mOwner.findViewById(2131624094);
        this.mSpeakerIcon = (ImageView)this.mOwner.findViewById(2131624095);
        this.mSpeakerLabel = (TextView)this.mOwner.findViewById(2131624096);
        this.mTimer = (TextView)this.mOwner.findViewById(2131624100);
        this.mMicIcon = (ImageView)this.mOwner.findViewById(2131624098);
        this.mDivider = this.mOwner.findViewById(2131624097);
        this.mDialer = this.mOwner.findViewById(2131624092);
        this.mHeader = this.mOwner.findViewById(2131624091);
        this.mFab = (FloatingActionButton)this.mOwner.findViewById(2131624126);
        this.mDialpad = (TableLayout)this.mOwner.findViewById(2131624101);
        this.mToneGenerator = new ToneGenerator(8, 100);
        this.setupButtons();
        this.setupScreen();
        this.mAudioManager = (AudioManager)this.mOwner.getSystemService("audio");
        this.mSpeakerOn = this.mAudioManager.isSpeakerphoneOn();
        this.mMuted = this.mAudioManager.isMicrophoneMute();
        this.mFormatter = new TimeFormatterHelper();
        this.setupSpeaker();
    }
    
    private void cancelCall() {
        Log.d("VoipActivity", "Cancel call by user");
        this.mOwner.getVoip().terminate();
        this.mOwner.callEnded(null);
    }
    
    private void handleButtonTounch(final char c, final boolean b, final int n) {
        if (b) {
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "Button pressed " + c);
            }
            this.mOwner.getVoip().startDTMF(c);
            this.mToneGenerator.startTone(n);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Button released " + c);
        }
        this.mOwner.getVoip().stopDTMF();
        this.mToneGenerator.stopTone();
    }
    
    private void handleButtonTounch(final View view, final boolean b) {
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("VoipActivity", "Uknown view, unable to handle: " + view.getId());
                }
            }
            case 2131624102: {
                this.handleButtonTounch('1', b, 1);
            }
            case 2131624104: {
                this.handleButtonTounch('2', b, 2);
            }
            case 2131624106: {
                this.handleButtonTounch('3', b, 3);
            }
            case 2131624108: {
                this.handleButtonTounch('4', b, 4);
            }
            case 2131624110: {
                this.handleButtonTounch('5', b, 5);
            }
            case 2131624112: {
                this.handleButtonTounch('6', b, 6);
            }
            case 2131624114: {
                this.handleButtonTounch('7', b, 7);
            }
            case 2131624116: {
                this.handleButtonTounch('8', b, 8);
            }
            case 2131624118: {
                this.handleButtonTounch('9', b, 9);
            }
            case 2131624122: {
                this.handleButtonTounch('0', b, 0);
            }
            case 2131624120: {
                this.handleButtonTounch('*', b, 10);
            }
            case 2131624124: {
                this.handleButtonTounch('#', b, 11);
            }
        }
    }
    
    private void repostOnEverySecondRunnable(final int n) {
        this.mHandler.removeCallbacks(this.onEverySecond);
        this.mHandler.postDelayed(this.onEverySecond, (long)n);
    }
    
    private void setButtonsStateToEnabled(final boolean enabled) {
        if (this.mButtons != null && this.mButtons.length >= 1) {
            final View[] mButtons = this.mButtons;
            for (int length = mButtons.length, i = 0; i < length; ++i) {
                mButtons[i].setEnabled(enabled);
            }
        }
    }
    
    private void setProgress() {
        synchronized (this) {
            final int n = (int)(System.currentTimeMillis() - this.mOwner.getVoip().getCallStartTimeInMs());
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "Elapsed [sec] " + n / 1000);
            }
            this.mTimer.setText((CharSequence)this.mFormatter.getStringForMs(n));
        }
    }
    
    private void setupButtons() {
        int i = 0;
        final boolean connected = this.mOwner.getServiceManager().getVoip().isConnected();
        (this.mButtons = new View[12])[0] = this.mOwner.findViewById(2131624122);
        this.mButtons[1] = this.mOwner.findViewById(2131624102);
        this.mButtons[2] = this.mOwner.findViewById(2131624104);
        this.mButtons[3] = this.mOwner.findViewById(2131624106);
        this.mButtons[4] = this.mOwner.findViewById(2131624108);
        this.mButtons[5] = this.mOwner.findViewById(2131624110);
        this.mButtons[6] = this.mOwner.findViewById(2131624112);
        this.mButtons[7] = this.mOwner.findViewById(2131624114);
        this.mButtons[8] = this.mOwner.findViewById(2131624116);
        this.mButtons[9] = this.mOwner.findViewById(2131624118);
        this.mButtons[10] = this.mOwner.findViewById(2131624120);
        this.mButtons[11] = this.mOwner.findViewById(2131624124);
        for (View[] mButtons = this.mButtons; i < mButtons.length; ++i) {
            final View view = mButtons[i];
            view.setOnTouchListener(this.mOnTouchListener);
            view.setEnabled(connected);
        }
    }
    
    private void setupLandscape(final int n, final int n2) {
        if (DeviceUtils.isDeviceSmallestWidthGreaterOrEqualThan(this.mOwner, 700)) {
            this.setupLandscapeWith700dp(n, n2);
            return;
        }
        this.setupLandscapeWithLess700dp(n, n2);
    }
    
    private void setupLandscapeWith700dp(int n, final int n2) {
        Log.d("VoipActivity", "Setup for landscape, sw greater or equal than 700dp");
        n = n2 * 3 / 10;
        final int dipToPixels = AndroidUtils.dipToPixels((Context)this.mOwner, 2);
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Sets header to w match parent, h " + n);
            Log.d("VoipActivity", "Sets dialer to w match parent, h " + (n2 - n - dipToPixels));
        }
        this.mHeader.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, n));
        Log.d("VoipActivity", "Sets dialer");
        this.mDialer.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, 0, 1.0f));
    }
    
    private void setupLandscapeWithLess700dp(int n, final int n2) {
        Log.d("VoipActivity", "Setup for landscape, sw less than 700dp");
        n = n - n2 - AndroidUtils.dipToPixels((Context)this.mOwner, 2);
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Sets header to  w " + n + ", h match parent");
            Log.d("VoipActivity", "Sets dialer to  w 0, h match parent, weight 1");
        }
        this.mHeader.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, -1));
        Log.d("VoipActivity", "Sets dialer");
        this.mDialer.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n2, -1));
    }
    
    private void setupPortrait(int n, final int n2) {
        Log.d("VoipActivity", "Setup for portrait");
        n = n2 * 3 / 10;
        final int dipToPixels = AndroidUtils.dipToPixels((Context)this.mOwner, 2);
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Sets header to w match parent, h " + n);
            Log.d("VoipActivity", "Sets dialer to w match parent, h " + (n2 - n - dipToPixels));
        }
        this.mHeader.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, n));
        Log.d("VoipActivity", "Sets dialer");
        this.mDialer.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, 0, 1.0f));
    }
    
    private void setupScreen() {
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels((Context)this.mOwner);
        final int screenHeightInPixels = DeviceUtils.getScreenHeightInPixels((Context)this.mOwner);
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Screen w " + screenWidthInPixels + ", h " + screenHeightInPixels);
        }
        if (DeviceUtils.isLandscape((Context)this.mOwner)) {
            this.setupLandscape(screenWidthInPixels, screenHeightInPixels);
            return;
        }
        this.setupPortrait(screenWidthInPixels, screenHeightInPixels);
    }
    
    private void setupSpeaker() {
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Speaker is on " + this.mSpeakerOn + ", muted " + this.mMuted);
        }
        if (this.mOwner.isTablet()) {
            Log.d("VoipActivity", "Tablet: hide speaker option");
            if (this.mSpeakerIcon != null) {
                this.mSpeakerIcon.setVisibility(8);
            }
            if (this.mSpeakerLabel != null) {
                this.mSpeakerLabel.setVisibility(8);
            }
            if (this.mDivider != null) {
                this.mDivider.setVisibility(8);
            }
        }
    }
    
    private void toggleMic() {
        if (this.mMuted) {
            Log.d("VoipActivity", "Unmute");
        }
        else {
            Log.d("VoipActivity", "Mute");
        }
        this.mMuted = !this.mMuted;
        this.mOwner.getVoip().setMicrophoneMute(this.mMuted);
        if (this.mAudioManager != null) {
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "mute " + this.mMuted);
            }
            this.mAudioManager.setMode(2);
            this.mAudioManager.setMicrophoneMute(this.mMuted);
        }
        this.updateMic();
    }
    
    private void toggleSpeaker() {
        if (this.mSpeakerOn) {
            Log.d("VoipActivity", "Set speaker from on to off");
        }
        else {
            Log.d("VoipActivity", "Set speaker from off to on");
        }
        this.mSpeakerOn = !this.mSpeakerOn;
        this.mAudioManager.setSpeakerphoneOn(this.mSpeakerOn);
        this.updateSpeaker();
    }
    
    private void updateCallStatus() {
        Log.d("VoipActivity", "Update call status...");
        if (this.mCallStatus == null) {
            return;
        }
        if (this.mConnected) {
            this.mCallStatus.setText((CharSequence)this.mOwner.getString(2131165462));
            this.mTimer.setVisibility(0);
            return;
        }
        this.mCallStatus.setText((CharSequence)this.mOwner.getString(2131165463));
        this.mTimer.setVisibility(4);
    }
    
    private void updateMic() {
        if (this.mMicIcon == null) {
            return;
        }
        this.mMuted = this.mAudioManager.isMicrophoneMute();
        if (this.mMuted) {
            this.mMicIcon.setImageResource(2130837691);
            return;
        }
        this.mMicIcon.setImageResource(2130837690);
    }
    
    private void updateSpeaker() {
        if (this.mOwner.isTablet() || this.mSpeakerIcon == null) {
            return;
        }
        this.mSpeakerOn = this.mAudioManager.isSpeakerphoneOn();
        if (this.mSpeakerOn) {
            this.mSpeakerIcon.setImageResource(2130837734);
            return;
        }
        this.mSpeakerIcon.setImageResource(2130837735);
    }
    
    public void callConnected() {
        Log.d("VoipActivity", "Call is connected");
        this.mConnected = true;
        this.updateCallStatus();
        this.setButtonsStateToEnabled(true);
        this.startTimer();
    }
    
    public void callRinging() {
    }
    
    ContactUsActivity getActivity() {
        return this.mOwner;
    }
    
    public boolean performAction(final View view) {
        if (view == null) {
            Log.e("VoipActivity", "DialScreen:: null view? This should never happen!");
            return true;
        }
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("VoipActivity", "Uknown view, unable to handle: " + view.getId());
                }
                return false;
            }
            case 2131624095:
            case 2131624096: {
                this.toggleSpeaker();
                return true;
            }
            case 2131624098:
            case 2131624099: {
                this.toggleMic();
                return true;
            }
            case 2131624126: {
                this.cancelCall();
                return true;
            }
            case 2131624142:
            case 2131624143: {
                Log.d("VoipActivity", "Up action from dial");
                this.mOwner.performUpAction();
                return true;
            }
        }
    }
    
    public void restartTimer() {
        this.stopTimer();
        this.startTimer();
    }
    
    public void startCall() {
        Log.d("VoipActivity", "Call started!");
        this.update(false);
        this.mConnected = false;
        if (this.mOwner.getVoip().dial()) {
            Log.d("VoipActivity", "Success on starting call");
            return;
        }
        Log.e("VoipActivity", "Failed to start call");
        this.mOwner.callFailed(null, null, -1);
    }
    
    public void startTimer() {
        Log.d("VoipActivity", "Start timer...");
        this.mHandler.post(this.onEverySecond);
    }
    
    public void stopTimer() {
        Log.d("VoipActivity", "Stop timer...");
        this.mHandler.removeCallbacks(this.onEverySecond);
    }
    
    public void update(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Update, connected: " + b);
        }
        this.updateMic();
        this.updateSpeaker();
        this.mConnected = b;
        this.updateCallStatus();
        if (this.mConnected) {
            this.restartTimer();
        }
        this.setButtonsStateToEnabled(b);
    }
}
