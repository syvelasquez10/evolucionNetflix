// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.app.AlertDialog;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View$OnKeyListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.view.View;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.app.Activity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.Log;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
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
        final PinDialog pinDialog = new PinDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(PinDialogVault.NAME, (Parcelable)pinDialogVault);
        pinDialog.setArguments(arguments);
        pinDialog.setStyle(1, 2131558603);
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
    
    private void hideSoftKeyboard(final ServiceManager serviceManager, final EditText editText) {
        final InputMethodManager imm = this.getIMM(serviceManager);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
    
    private void notifyCallerPinCancelled() {
        Log.d("nf_pin", String.format("%s onPinCancelled vault: %s", NetflixActivity.class.getSimpleName(), this.mVault));
        if (this.mVault == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else if (PinDialogVault.PinInvokedFrom.MDX.getValue().equals(this.mVault.getInvokeLocation())) {
            this.getActivity().startService(MdxAgent.Utils.createIntent(this.getActivity(), "com.netflix.mediaclient.intent.action.MDX_PINCANCELLED", this.mVault.getUuid()));
        }
    }
    
    protected static void notifyCallerPinVerified(final NetflixActivity netflixActivity, final PinDialogVault pinDialogVault) {
        if (pinDialogVault == null) {
            Log.d("nf_pin", "mValut is null - cannot start playback");
        }
        else {
            Log.d("nf_pin", String.format("%s onPinVerified  , vault: %s", NetflixActivity.class.getSimpleName(), pinDialogVault));
            if (PinDialogVault.PinInvokedFrom.PLAY_LAUNCHER.getValue().equals(pinDialogVault.getInvokeLocation())) {
                PlaybackLauncher.startPlaybackOnPINSuccess(netflixActivity, pinDialogVault.getAsset(), pinDialogVault.isRemotePlayback());
                return;
            }
            if (PinDialogVault.PinInvokedFrom.MDX.getValue().equals(pinDialogVault.getInvokeLocation())) {
                netflixActivity.startService(MdxAgent.Utils.createIntent(netflixActivity, "com.netflix.mediaclient.intent.action.MDX_PINCONFIRMED", pinDialogVault.getUuid()));
                return;
            }
            if (PinDialogVault.PinInvokedFrom.PLAYER.getValue().equals(pinDialogVault.getInvokeLocation())) {
                netflixActivity.onPinVerified(pinDialogVault);
            }
        }
    }
    
    private void processUserInputPin(final String s) {
        this.showProgress(true);
        this.showErrorIcon(false);
        this.hideSoftKeyboard(this.getServiceManager(), this.mPinEditText);
        Log.d("nf_pin", "onEditorAction gotDone! password: " + s);
        this.getServiceManager().verifyPin(s, new OnPinVerifiedCallback());
    }
    
    private void setDialogWindowSize() {
        final WindowManager$LayoutParams attributes = new WindowManager$LayoutParams();
        attributes.copyFrom(this.getDialog().getWindow().getAttributes());
        attributes.width = (int)(0.5f + TypedValue.applyDimension(1, (float)this.mDialogWidthInDp, this.getResources().getDisplayMetrics()));
        this.getDialog().getWindow().setAttributes(attributes);
    }
    
    private static void showConnectivityErrorDialog(final NetflixActivity netflixActivity, final int n) {
        new AlertDialog$Builder((Context)netflixActivity).setCancelable(false).setMessage((CharSequence)String.format("%s (%d)", netflixActivity.getString(2131493271), n)).setPositiveButton(2131492977, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).show();
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
            this.mPinMessage.setText(2131493325);
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
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("nf_pin", "onCancel");
        this.notifyCallerPinCancelled();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        this.mVault = (PinDialogVault)this.getArguments().getParcelable(PinDialogVault.NAME);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903138, (ViewGroup)null);
        this.mSpinner = (ProgressBar)inflate.findViewById(2131165502);
        (this.mPinEditText = (EditText)inflate.findViewById(2131165499)).setOnEditorActionListener((TextView$OnEditorActionListener)new PinDialogOnDone());
        this.mPinEditText.setOnKeyListener((View$OnKeyListener)new PinDialogOnKeyPress());
        this.mPinMessage = (TextView)inflate.findViewById(2131165498);
        (this.mPinForgotView = (TextView)inflate.findViewById(2131165501)).setMovementMethod((MovementMethod)new LinkMovementMethod() {
            public boolean onTouchEvent(final TextView textView, final Spannable spannable, final MotionEvent motionEvent) {
                PinDialog.this.mPinForgotView.setLinkTextColor(PinDialog.this.getResources().getColor(2131296383));
                return super.onTouchEvent(textView, spannable, motionEvent);
            }
        });
        this.mPinForgotView.setFocusable(false);
        ((Button)inflate.findViewById(2131165503)).setOnClickListener((View$OnClickListener)new PinDialogOnCancel());
        this.mErrorIcon = (ImageView)inflate.findViewById(2131165500);
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
        this.mPinMessage.setText(2131493328);
        this.mPinEditText.getText().clear();
        this.showErrorIcon(true);
        this.showSoftKeyboard(this.getServiceManager(), this.mPinEditText);
    }
    
    private class OnPinVerifiedCallback extends LoggingManagerCallback
    {
        public OnPinVerifiedCallback() {
            super("nf_pin");
        }
        
        @Override
        public void onPinVerified(final boolean b, final int n) {
            super.onPinVerified(b, n);
            PinDialog.this.showProgress(false);
            Log.d("nf_pin", String.format("onPinVerified isPinValid:%b, statusCode:%d", b, n));
            if (n == 0 && !b) {
                PinDialog.this.retryOnPinFailure();
                return;
            }
            PinDialog.this.dismissDialog();
            if (n == 0) {
                PinVerifier.getInstance().registerPinVerifyEvent();
                PinDialog.notifyCallerPinVerified((NetflixActivity)PinDialog.this.getActivity(), PinDialog.this.mVault);
                return;
            }
            showConnectivityErrorDialog((NetflixActivity)PinDialog.this.getActivity(), n);
        }
    }
    
    public interface PinDialogCallback
    {
        void pinCancelled();
        
        void pinVerified(final boolean p0);
    }
    
    private class PinDialogOnCancel implements View$OnClickListener
    {
        public void onClick(final View view) {
            PinDialog.this.dismissAndNotifyCallers();
        }
    }
    
    private class PinDialogOnDone implements TextView$OnEditorActionListener
    {
        public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
            if (n != 6) {
                return false;
            }
            final String string = textView.getText().toString();
            if (string.length() < PinDialog.PIN_LENGTH) {
                return true;
            }
            if (PinDialog.this.getServiceManager() == null) {
                Log.d("nf_pin", "serviceManager is null");
                return false;
            }
            PinDialog.this.processUserInputPin(string);
            return false;
        }
    }
    
    private class PinDialogOnKeyPress implements View$OnKeyListener
    {
        public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
            if (n != 67) {
                PinDialog.this.mPinEditText.setError((CharSequence)null, (Drawable)null);
                PinDialog.this.showErrorIcon(false);
                final String string = ((EditText)view).getText().toString();
                if (string.length() >= PinDialog.PIN_LENGTH) {
                    if (PinDialog.this.getServiceManager() == null) {
                        Log.d("nf_pin", "serviceManager is null");
                        return false;
                    }
                    PinDialog.this.processUserInputPin(string);
                    return false;
                }
            }
            return false;
        }
    }
}
