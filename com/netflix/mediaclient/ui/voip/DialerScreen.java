// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.ToneGenerator;
import android.view.View$OnTouchListener;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.media.AudioManager;

class DialerScreen
{
    private static final int DELAY_POST = 1000;
    private static final String TAG = "VoipActivity";
    private AudioManager mAudioManager;
    private int mButtonSelectedColor;
    private View[] mButtons;
    private View mButtonsContainer;
    private TextView mCallStatus1;
    private TextView mCallStatus2;
    private boolean mConnected;
    private View mDialerContainer;
    protected TableLayout mDialpad;
    private ImageView mDialpadIcon;
    private View mDialpadIconLabel;
    protected FloatingActionButton mFab;
    private TimeFormatterHelper mFormatter;
    private Handler mHandler;
    private View mLoadingView;
    private ImageView mMicIcon;
    private boolean mMuted;
    private int mNumberButtonColor;
    private View$OnTouchListener mOnTouchListener;
    private int mOtherButtonColor;
    private ContactUsActivity mOwner;
    private int mRippleAnimationInMs;
    private View mSpeakerButtonContainer;
    private ImageView mSpeakerIcon;
    private TextView mSpeakerLabel;
    private boolean mSpeakerOn;
    private View mStatusContainer;
    private TextView mTimer;
    private ToneGenerator mToneGenerator;
    private final Runnable onEverySecond;
    
    DialerScreen(final ContactUsActivity mOwner) {
    Label_0393_Outer:
        while (true) {
            this.mHandler = new Handler();
            this.mOnTouchListener = (View$OnTouchListener)new DialerScreen$1(this);
            this.onEverySecond = new DialerScreen$6(this);
            this.mOwner = mOwner;
            this.mRippleAnimationInMs = this.mOwner.getResources().getInteger(2131427347);
            this.mCallStatus1 = (TextView)this.mOwner.findViewById(2131624134);
            this.mCallStatus2 = (TextView)this.mOwner.findViewById(2131624135);
            this.mSpeakerIcon = (ImageView)this.mOwner.findViewById(2131624100);
            this.mSpeakerLabel = (TextView)this.mOwner.findViewById(2131624101);
            this.mTimer = (TextView)this.mOwner.findViewById(2131624137);
            this.mMicIcon = (ImageView)this.mOwner.findViewById(2131624103);
            this.mDialpadIcon = (ImageView)this.mOwner.findViewById(2131624106);
            this.mDialpadIconLabel = this.mOwner.findViewById(2131624107);
            this.setEnableDialpad(false);
            this.mDialerContainer = this.mOwner.findViewById(2131624095);
            this.mStatusContainer = this.mOwner.findViewById(2131624094);
            this.mButtonsContainer = this.mOwner.findViewById(2131624096);
            this.mLoadingView = this.mOwner.findViewById(2131624136);
            this.mSpeakerButtonContainer = this.mOwner.findViewById(2131624099);
            this.mFab = (FloatingActionButton)this.mOwner.findViewById(2131624108);
            this.mDialpad = (TableLayout)this.mOwner.findViewById(2131624109);
            while (true) {
                while (true) {
                    try {
                        this.mToneGenerator = new ToneGenerator(8, 100);
                        this.setupButtons();
                        this.mAudioManager = (AudioManager)this.mOwner.getSystemService("audio");
                        this.mSpeakerOn = this.mAudioManager.isSpeakerphoneOn();
                        this.mMuted = this.mAudioManager.isMicrophoneMute();
                        this.mFormatter = new TimeFormatterHelper();
                        if (AndroidUtils.getAndroidVersion() > 22) {
                            this.mNumberButtonColor = mOwner.getColor(2131558447);
                            this.mOtherButtonColor = mOwner.getColor(2131558446);
                            this.mButtonSelectedColor = mOwner.getColor(2131558450);
                            this.setupSpeaker();
                            return;
                        }
                    }
                    catch (Throwable t) {
                        Log.e("VoipActivity", "Failed to initiate tone generator", t);
                        continue Label_0393_Outer;
                    }
                    break;
                }
                this.mNumberButtonColor = mOwner.getResources().getColor(2131558447);
                this.mOtherButtonColor = mOwner.getResources().getColor(2131558446);
                this.mButtonSelectedColor = mOwner.getResources().getColor(2131558450);
                continue;
            }
        }
    }
    
    private void cancelCall() {
        Log.d("VoipActivity", "Cancel call by user");
        this.mOwner.getVoip().terminate();
        this.mOwner.callEnded(null);
    }
    
    public static Drawable convertDrawableToGrayScale(Drawable mutate) {
        if (mutate == null) {
            return null;
        }
        mutate = mutate.mutate();
        mutate.setColorFilter(-1, PorterDuff$Mode.SRC_ATOP);
        return mutate;
    }
    
    private void executeAfterRippleAnimation(final Runnable runnable, final int n) {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            this.mHandler.postDelayed(runnable, (long)n);
            return;
        }
        runnable.run();
    }
    
    private void handleButtonTounch(final int n, final char c, final boolean b, final int n2) {
        final TextView textView = (TextView)this.mOwner.findViewById(n);
        if (b) {
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "Button pressed " + c);
            }
            this.mOwner.getVoip().startDTMF(c);
            if (this.mToneGenerator != null) {
                this.mToneGenerator.startTone(n2);
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "Button released " + c);
            }
            this.mOwner.getVoip().stopDTMF();
            if (this.mToneGenerator != null) {
                this.mToneGenerator.stopTone();
            }
        }
        this.updateDiaPadState(n, c, b);
        if (textView == null) {
            Log.w("VoipActivity", "Button label not found!");
        }
    }
    
    private void handleButtonTounch(final View view, final boolean b) {
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("VoipActivity", "Uknown view for button, unable to handle: " + view.getId());
                }
            }
            case 2131624111: {
                this.handleButtonTounch(2131624111, '1', b, 1);
            }
            case 2131624113: {
                this.handleButtonTounch(2131624113, '2', b, 2);
            }
            case 2131624115: {
                this.handleButtonTounch(2131624115, '3', b, 3);
            }
            case 2131624117: {
                this.handleButtonTounch(2131624117, '4', b, 4);
            }
            case 2131624119: {
                this.handleButtonTounch(2131624119, '5', b, 5);
            }
            case 2131624121: {
                this.handleButtonTounch(2131624121, '6', b, 6);
            }
            case 2131624123: {
                this.handleButtonTounch(2131624123, '7', b, 7);
            }
            case 2131624125: {
                this.handleButtonTounch(2131624125, '8', b, 8);
            }
            case 2131624127: {
                this.handleButtonTounch(2131624127, '9', b, 9);
            }
            case 2131624131: {
                this.handleButtonTounch(2131624131, '0', b, 0);
            }
            case 2131624129: {
                this.handleButtonTounch(2131624129, '*', b, 10);
            }
            case 2131624133: {
                this.handleButtonTounch(2131624133, '#', b, 11);
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
                final View view = mButtons[i];
                view.setEnabled(enabled);
                float alpha;
                if (enabled) {
                    alpha = 1.0f;
                }
                else {
                    alpha = 0.25f;
                }
                view.setAlpha(alpha);
            }
        }
    }
    
    private void setDialPadColor(final int n, final int textColor) {
        final TextView textView = (TextView)this.mOwner.findViewById(n);
        if (textView != null) {
            textView.setTextColor(textColor);
        }
    }
    
    private void setEnableDialpad(final boolean enabled) {
        setImageViewEnabled((Context)this.mOwner, enabled, this.mDialpadIcon, 2130837690);
        this.mDialpadIconLabel.setEnabled(enabled);
    }
    
    public static void setImageViewEnabled(final Context context, final boolean enabled, final ImageView imageView, final int n) {
        imageView.setEnabled(enabled);
        Drawable imageDrawable = context.getResources().getDrawable(n);
        if (!enabled) {
            imageDrawable = convertDrawableToGrayScale(imageDrawable);
        }
        imageView.setImageDrawable(imageDrawable);
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
        (this.mButtons = new View[12])[0] = this.mOwner.findViewById(2131624131);
        this.mButtons[1] = this.mOwner.findViewById(2131624111);
        this.mButtons[2] = this.mOwner.findViewById(2131624113);
        this.mButtons[3] = this.mOwner.findViewById(2131624115);
        this.mButtons[4] = this.mOwner.findViewById(2131624117);
        this.mButtons[5] = this.mOwner.findViewById(2131624119);
        this.mButtons[6] = this.mOwner.findViewById(2131624121);
        this.mButtons[7] = this.mOwner.findViewById(2131624123);
        this.mButtons[8] = this.mOwner.findViewById(2131624125);
        this.mButtons[9] = this.mOwner.findViewById(2131624127);
        this.mButtons[10] = this.mOwner.findViewById(2131624129);
        this.mButtons[11] = this.mOwner.findViewById(2131624133);
        for (View[] mButtons = this.mButtons; i < mButtons.length; ++i) {
            mButtons[i].setOnTouchListener(this.mOnTouchListener);
        }
        this.setButtonsStateToEnabled(this.mOwner.getServiceManager().getVoip().isConnected());
    }
    
    private void setupSpeaker() {
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Speaker is on " + this.mSpeakerOn + ", muted " + this.mMuted);
        }
        if (this.mOwner.isTablet()) {
            Log.d("VoipActivity", "Tablet: hide speaker option");
            if (this.mSpeakerButtonContainer != null) {
                this.mSpeakerButtonContainer.setVisibility(8);
            }
        }
    }
    
    private void toggleDialpad() {
        final boolean b = this.mDialerContainer.getVisibility() == 0;
        if (b) {
            Log.d("VoipActivity", "Dialpad was visible, remove it!");
            this.mStatusContainer.setVisibility(0);
            this.mDialerContainer.setVisibility(8);
        }
        else {
            Log.d("VoipActivity", "Dialpad was NOT visible, show it!");
            this.mStatusContainer.setVisibility(8);
            this.mDialerContainer.setVisibility(0);
        }
        this.executeAfterRippleAnimation(new DialerScreen$5(this, b), this.mRippleAnimationInMs);
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
        if (this.mConnected) {
            this.setEnableDialpad(true);
            this.mCallStatus1.setText((CharSequence)this.mOwner.getString(2131165497));
            this.mCallStatus2.setText((CharSequence)this.mOwner.getString(2131165498));
            this.mTimer.setVisibility(0);
            this.mLoadingView.setVisibility(8);
            return;
        }
        this.setEnableDialpad(false);
        this.mCallStatus1.setText((CharSequence)this.mOwner.getString(2131165500));
        this.mCallStatus2.setText((CharSequence)this.mOwner.getString(2131165501));
        this.mTimer.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }
    
    private void updateDiaPadState(final int n, final char c, final boolean b) {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            return;
        }
        Log.d("VoipActivity", "Sets color to label for pre L devices...");
        if (b) {
            this.setDialPadColor(n, this.mButtonSelectedColor);
            return;
        }
        int n2;
        if (Character.isDigit(c)) {
            n2 = this.mNumberButtonColor;
        }
        else {
            n2 = this.mOtherButtonColor;
        }
        this.setDialPadColor(n, n2);
    }
    
    private void updateMic() {
        if (this.mMicIcon == null) {
            return;
        }
        this.mMuted = this.mAudioManager.isMicrophoneMute();
        this.executeAfterRippleAnimation(new DialerScreen$3(this), this.mRippleAnimationInMs);
    }
    
    private void updateSpeaker() {
        if (!this.mOwner.isTablet() && this.mSpeakerIcon != null) {
            this.mSpeakerOn = this.mAudioManager.isSpeakerphoneOn();
            this.executeAfterRippleAnimation(new DialerScreen$4(this), this.mRippleAnimationInMs);
        }
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
    
    void initUi() {
        this.mStatusContainer.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new DialerScreen$2(this));
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
            case 2131624100:
            case 2131624101: {
                this.toggleSpeaker();
                return true;
            }
            case 2131624103:
            case 2131624104: {
                this.toggleMic();
                return true;
            }
            case 2131624108: {
                this.cancelCall();
                return true;
            }
            case 2131624106:
            case 2131624107: {
                this.toggleDialpad();
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
        }
        else {
            Log.e("VoipActivity", "Failed to start call");
            this.mOwner.callFailed(null, null, -1);
        }
        this.mDialerContainer.setVisibility(8);
        this.mStatusContainer.setVisibility(0);
        this.setEnableDialpad(false);
    }
    
    public void startTimer() {
        Log.d("VoipActivity", "Start timer...");
        this.mHandler.post(this.onEverySecond);
    }
    
    public void stopTimer() {
        Log.d("VoipActivity", "Stop timer...");
        this.mHandler.removeCallbacks(this.onEverySecond);
    }
    
    void update(final boolean b) {
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
