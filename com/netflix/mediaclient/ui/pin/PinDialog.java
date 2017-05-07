// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.app.AlertDialog;
import android.view.View$OnClickListener;
import android.widget.Button;
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
    private static final int PIN_DIALOG_WIDTH_PHONE_DP = 320;
    private static final int PIN_DIALOG_WIDTH_TABLET_DP = 400;
    private static final Integer PIN_LENGTH;
    private static final String TAG = "nf_pin";
    private int mDialogWidthInDp;
    private ImageView mErrorIcon;
    private EditText mPinEditText;
    private TextView mPinForgotView;
    private TextView mPinMessage;
    private ProgressBar mSpinner;
    private PinDialogVault mVault;
    
    static {
        PIN_LENGTH = 4;
    }
    
    protected static PinDialog createPinDialog(final NetflixActivity netflixActivity, final PinDialogVault pinDialogVault) {
        Log.d("nf_pin", "creating dialog");
        final PinDialog pinDialog = new PinDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(PinDialogVault.NAME, (Parcelable)pinDialogVault);
        pinDialog.setArguments(arguments);
        pinDialog.setStyle(1, 2131558713);
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
        return ((NetflixActivity)this.getActivity()).getServiceManager();
    }
    
    private void notifyCallerPinCancelled() {
        Log.d("nf_pin", String.format("%s onPinCancelled vault: %s", NetflixActivity.class.getSimpleName(), this.mVault));
        if (this.mVault == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else if (PinDialogVault$PinInvokedFrom.MDX.getValue().equals(this.mVault.getInvokeLocation())) {
            this.getActivity().startService(MdxAgent$Utils.createIntent((Context)this.getActivity(), "com.netflix.mediaclient.intent.action.MDX_PINCANCELLED", this.mVault.getUuid()));
        }
    }
    
    protected static void notifyCallerPinVerified(final NetflixActivity netflixActivity, final PinDialogVault pinDialogVault) {
        if (pinDialogVault == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else {
            Log.d("nf_pin", String.format("%s onPinVerified  , vault: %s", NetflixActivity.class.getSimpleName(), pinDialogVault));
            if (PinDialogVault$PinInvokedFrom.PLAY_LAUNCHER.getValue().equals(pinDialogVault.getInvokeLocation())) {
                PlaybackLauncher.startPlaybackOnPINSuccess(netflixActivity, pinDialogVault.getAsset(), pinDialogVault.isRemotePlayback());
                return;
            }
            if (PinDialogVault$PinInvokedFrom.MDX.getValue().equals(pinDialogVault.getInvokeLocation())) {
                netflixActivity.startService(MdxAgent$Utils.createIntent((Context)netflixActivity, "com.netflix.mediaclient.intent.action.MDX_PINCONFIRMED", pinDialogVault.getUuid()));
                return;
            }
            if (PinDialogVault$PinInvokedFrom.PLAYER.getValue().equals(pinDialogVault.getInvokeLocation())) {
                netflixActivity.onPinVerified(pinDialogVault);
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
        new AlertDialog$Builder((Context)netflixActivity).setCancelable(false).setMessage((CharSequence)String.format("%s (%d)", netflixActivity.getString(2131493234), status.getStatusCode().getValue())).setPositiveButton(2131492980, (DialogInterface$OnClickListener)new PinDialog$2()).show();
    }
    
    private void showErrorIcon(final boolean b) {
        final ImageView mErrorIcon = this.mErrorIcon;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mErrorIcon.setVisibility(visibility);
    }
    
    private void showProgress(final boolean b) {
        final int n = 8;
        final ProgressBar mSpinner = this.mSpinner;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mSpinner.setVisibility(visibility);
        final EditText mPinEditText = this.mPinEditText;
        int visibility2;
        if (b) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        mPinEditText.setVisibility(visibility2);
        final TextView mPinForgotView = this.mPinForgotView;
        int visibility3;
        if (b) {
            visibility3 = n;
        }
        else {
            visibility3 = 0;
        }
        mPinForgotView.setVisibility(visibility3);
        if (b) {
            this.mPinMessage.setText(2131493280);
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
        Log.d("nf_pin", "dismissing dialog");
        try {
            this.dismissAllowingStateLoss();
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
        this.notifyCallerPinCancelled();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        this.mVault = (PinDialogVault)this.getArguments().getParcelable(PinDialogVault.NAME);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903147, (ViewGroup)null);
        this.mSpinner = (ProgressBar)inflate.findViewById(2131165525);
        (this.mPinEditText = (EditText)inflate.findViewById(2131165522)).setOnEditorActionListener((TextView$OnEditorActionListener)new PinDialog$PinDialogOnDone(this, null));
        this.mPinEditText.setOnKeyListener((View$OnKeyListener)new PinDialog$PinDialogOnKeyPress(this, null));
        this.mPinMessage = (TextView)inflate.findViewById(2131165521);
        (this.mPinForgotView = (TextView)inflate.findViewById(2131165524)).setMovementMethod((MovementMethod)new PinDialog$1(this));
        this.mPinForgotView.setFocusable(false);
        ((Button)inflate.findViewById(2131165526)).setOnClickListener((View$OnClickListener)new PinDialog$PinDialogOnCancel(this, null));
        this.mErrorIcon = (ImageView)inflate.findViewById(2131165523);
        this.showErrorIcon(false);
        int mDialogWidthInDp;
        if (DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            mDialogWidthInDp = 400;
        }
        else {
            mDialogWidthInDp = 320;
        }
        this.mDialogWidthInDp = mDialogWidthInDp;
        this.showProgress(false);
        alertDialog$Builder.setView(inflate);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        return (Dialog)create;
    }
    
    public void onResume() {
        super.onResume();
        this.mPinForgotView.setLinkTextColor(-1);
        this.setDialogWindowSize();
        this.showSoftKeyboardOnStart();
    }
    
    public void onStart() {
        super.onStart();
        if (PinVerifier.getInstance().toDismissDialog()) {
            Log.d("nf_pin", "onStart - dismissOnForeground");
            this.dismissAndNotifyCallers();
        }
    }
    
    public void retryOnPinFailure() {
        this.mPinMessage.setText(2131493282);
        this.mPinEditText.getText().clear();
        this.showErrorIcon(true);
        this.showSoftKeyboard(this.getServiceManager(), this.mPinEditText);
    }
}
