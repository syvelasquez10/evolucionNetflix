// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import android.app.AlertDialog;
import android.text.method.MovementMethod;
import android.view.View$OnKeyListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

@TargetApi(14)
public class PinDialog extends NetflixDialogFrag
{
    public static final int PIN_DIALOG_WIDTH_PHONE_DP = 320;
    public static final int PIN_DIALOG_WIDTH_TABLET_DP = 400;
    private static final String PIN_ERROR = "pin_error";
    private static final Integer PIN_LENGTH;
    private static final String PIN_PROGRESS = "pin_progress";
    private static final String TAG = "nf_pin";
    private boolean mActive;
    private int mDialogWidthInDp;
    private ImageView mErrorIcon;
    private boolean mInError;
    private boolean mInProgress;
    private EditText mPinEditText;
    private TextView mPinForgotView;
    private TextView mPinMessage;
    private ProgressBar mSpinner;
    private PlayVerifierVault mVault;
    
    static {
        PIN_LENGTH = 4;
    }
    
    protected static PinDialog createPinDialog(final PlayVerifierVault playVerifierVault) {
        Log.d("nf_pin", "creating dialog");
        final PinDialog pinDialog = new PinDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(PlayVerifierVault.NAME, (Parcelable)playVerifierVault);
        pinDialog.setArguments(arguments);
        pinDialog.setStyle(1, 2131558720);
        return pinDialog;
    }
    
    private void dismissAndNotifyCallers() {
        this.dismissDialog();
        this.notifyCallerPinCancelled();
    }
    
    private InputMethodManager getIMM(final ServiceManager serviceManager) {
        if (serviceManager == null || serviceManager.getActivity() == null) {
            return null;
        }
        return (InputMethodManager)serviceManager.getActivity().getSystemService("input_method");
    }
    
    private ServiceManager getServiceManager() {
        if (this.getActivity() == null) {
            return null;
        }
        return ((NetflixActivity)this.getActivity()).getServiceManager();
    }
    
    private void notifyCallerPinCancelled() {
        Log.d("nf_pin", String.format("%s onPinCancelled vault: %s", NetflixActivity.class.getSimpleName(), this.mVault));
        if (this.mVault == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else {
            if (PlayVerifierVault$PlayInvokedFrom.MDX.getValue().equals(this.mVault.getInvokeLocation()) && this.getActivity() != null) {
                this.getActivity().startService(MdxAgent$Utils.createIntent((Context)this.getActivity(), "com.netflix.mediaclient.intent.action.MDX_PINCANCELLED", this.mVault.getUuid()));
                return;
            }
            if (PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue().equals(this.mVault.getInvokeLocation())) {
                ((NetflixActivity)this.getActivity()).onPlayVerified(false, this.mVault);
            }
        }
    }
    
    protected static void notifyCallerPinVerified(final NetflixActivity netflixActivity, final PlayVerifierVault playVerifierVault) {
        if (playVerifierVault == null || netflixActivity == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else {
            Log.d("nf_pin", String.format("%s onVerified  , vault: %s", NetflixActivity.class.getSimpleName(), playVerifierVault));
            playVerifierVault.getAsset().setPinVerified(true);
            if (PlayVerifierVault$PlayInvokedFrom.PLAY_LAUNCHER.getValue().equals(playVerifierVault.getInvokeLocation())) {
                PlaybackLauncher.startPlaybackOnPINSuccess(netflixActivity, playVerifierVault.getAsset(), playVerifierVault.isRemotePlayback());
                return;
            }
            if (PlayVerifierVault$PlayInvokedFrom.MDX.getValue().equals(playVerifierVault.getInvokeLocation())) {
                netflixActivity.startService(MdxAgent$Utils.createIntent((Context)netflixActivity, "com.netflix.mediaclient.intent.action.MDX_PINCONFIRMED", playVerifierVault.getUuid()));
                return;
            }
            if (PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue().equals(playVerifierVault.getInvokeLocation())) {
                netflixActivity.onPlayVerified(true, playVerifierVault);
            }
        }
    }
    
    private void processUserInputPin(final String s) {
        this.showProgress(true);
        this.showErrorIcon(false);
        DeviceUtils.forceHideKeyboard(this.getIMM(this.getServiceManager()), this.mPinEditText);
        Log.d("nf_pin", "onEditorAction gotDone! password: " + s);
        this.getServiceManager().verifyPin(s, new PinDialog$OnPinVerifiedCallback(this));
    }
    
    private void setDialogWindowSize() {
        final WindowManager$LayoutParams attributes = new WindowManager$LayoutParams();
        try {
            attributes.copyFrom(this.getDialog().getWindow().getAttributes());
            attributes.width = (int)(TypedValue.applyDimension(1, (float)this.mDialogWidthInDp, this.getResources().getDisplayMetrics()) + 0.5f);
            this.getDialog().getWindow().setAttributes(attributes);
        }
        catch (Exception ex) {
            Log.e("nf_pin", "Could not set windowSize e:" + ex);
        }
    }
    
    private static void showConnectivityErrorDialog(final NetflixActivity netflixActivity, final Status status) {
        new AlertDialog$Builder((Context)netflixActivity).setCancelable(false).setMessage((CharSequence)String.format("%s (%d)", netflixActivity.getString(2131493266), status.getStatusCode().getValue())).setPositiveButton(2131493003, (DialogInterface$OnClickListener)new PinDialog$2()).show();
    }
    
    private void showErrorIcon(final boolean mInError) {
        this.mInError = mInError;
        final ImageView mErrorIcon = this.mErrorIcon;
        int visibility;
        if (mInError) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mErrorIcon.setVisibility(visibility);
    }
    
    private void showProgress(final boolean mInProgress) {
        final int n = 8;
        this.mInProgress = mInProgress;
        final ProgressBar mSpinner = this.mSpinner;
        int visibility;
        if (mInProgress) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mSpinner.setVisibility(visibility);
        final EditText mPinEditText = this.mPinEditText;
        int visibility2;
        if (mInProgress) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        mPinEditText.setVisibility(visibility2);
        final TextView mPinForgotView = this.mPinForgotView;
        int visibility3;
        if (mInProgress) {
            visibility3 = n;
        }
        else {
            visibility3 = 0;
        }
        mPinForgotView.setVisibility(visibility3);
        if (mInProgress) {
            this.mPinMessage.setText(2131493311);
        }
    }
    
    private void showSoftKeyboard(final ServiceManager serviceManager, final EditText editText) {
        final InputMethodManager imm = this.getIMM(serviceManager);
        if (imm != null) {
            imm.showSoftInput((View)editText, 1);
        }
    }
    
    private void showSoftKeyboardOnStart() {
        if (this.getDialog() != null && this.getDialog().getWindow() != null) {
            this.getDialog().getWindow().setSoftInputMode(5);
        }
    }
    
    protected void dismissDialog() {
        Log.d("nf_pin", "dismissing pin dialog");
        try {
            this.getDialog().dismiss();
            PinVerifier.getInstance().pinDialogDismissed();
        }
        catch (Exception ex) {
            Log.d("nf_pin", "app in backgound. cannot dismiss - retry on next start");
        }
    }
    
    public String getInvokeLocation() {
        if (this.mVault != null) {
            return this.mVault.getInvokeLocation();
        }
        return null;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("nf_pin", "onCancel");
        this.mActive = false;
        this.notifyCallerPinCancelled();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("nf_pin", String.format("onCreateDialog - mIsActive:%b,  restored=%b", this.mActive, bundle != null));
        if (bundle != null) {
            this.mInProgress = bundle.getBoolean("pin_progress");
            this.mInError = bundle.getBoolean("pin_error");
        }
        this.mVault = (PlayVerifierVault)this.getArguments().getParcelable(PlayVerifierVault.NAME);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903154, (ViewGroup)null);
        this.mSpinner = (ProgressBar)inflate.findViewById(2131427695);
        (this.mPinEditText = (EditText)inflate.findViewById(2131427692)).setOnEditorActionListener((TextView$OnEditorActionListener)new PinDialog$PinDialogOnDone(this, null));
        this.mPinEditText.setOnKeyListener((View$OnKeyListener)new PinDialog$PinDialogOnKeyPress(this, null));
        this.mPinMessage = (TextView)inflate.findViewById(2131427691);
        (this.mPinForgotView = (TextView)inflate.findViewById(2131427694)).setMovementMethod((MovementMethod)new PinDialog$1(this));
        this.mPinForgotView.setFocusable(false);
        this.mErrorIcon = (ImageView)inflate.findViewById(2131427693);
        int mDialogWidthInDp;
        if (DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            mDialogWidthInDp = 400;
        }
        else {
            mDialogWidthInDp = 320;
        }
        this.mDialogWidthInDp = mDialogWidthInDp;
        this.showErrorIcon(this.mInError);
        this.showProgress(this.mInProgress);
        alertDialog$Builder.setView(inflate);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setButton(-2, (CharSequence)this.getString(2131493123), (DialogInterface$OnClickListener)new PinDialog$PinDialogOnCancel(this, null));
        this.mActive = true;
        return (Dialog)create;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        Log.d("nf_pin", "onManagerReady");
        if (this.mInProgress) {
            Log.d("nf_pin", "doing pin validation again for restored dialog");
            this.processUserInputPin(this.mPinEditText.getText().toString());
        }
    }
    
    public void onResume() {
        super.onResume();
        Log.d("nf_pin", "onResume");
        this.mPinForgotView.setLinkTextColor(-1);
        this.setDialogWindowSize();
        if (!this.mInProgress) {
            this.showSoftKeyboardOnStart();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("nf_pin", "onSavedInstanceState");
        bundle.putBoolean("pin_progress", this.mInProgress);
        bundle.putBoolean("pin_error", this.mInError);
    }
    
    public void onStart() {
        super.onStart();
        Log.d("nf_pin", "onStart");
        if (PinVerifier.getInstance().toDismissDialog()) {
            Log.d("nf_pin", "onStart - dismissOnForeground");
            this.dismissAndNotifyCallers();
        }
    }
    
    public void retryOnPinFailure() {
        this.mPinMessage.setText(2131493313);
        this.mPinEditText.getText().clear();
        this.showErrorIcon(true);
        this.showSoftKeyboard(this.getServiceManager(), this.mPinEditText);
    }
}
