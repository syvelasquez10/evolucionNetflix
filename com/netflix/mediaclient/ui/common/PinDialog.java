// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.app.FragmentManager;
import android.app.Fragment;
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
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.View;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.view.inputmethod.InputMethodManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class PinDialog extends NetflixDialogFrag
{
    private static final String DATA_FRAG_TAG = "PIN_DATA";
    private static final int PIN_DIALOG_WIDTH_PHONE_DP = 320;
    private static final int PIN_DIALOG_WIDTH_TABLET_DP = 400;
    private static final Integer PIN_LENGTH;
    private static final String TAG = "nf_pin";
    private PinDialogCallback mCallback;
    private DataFragment mDataFragment;
    private int mDialogWidthInDp;
    private ImageView mErrorIcon;
    private EditText mPinEditText;
    private TextView mPinForgotView;
    private TextView mPinMessage;
    private ServiceManager mServiceMan;
    private ProgressBar mSpinner;
    
    static {
        PIN_LENGTH = 4;
    }
    
    public static PinDialog createPinDialog(final ServiceManager serviceManager, final PinDialogCallback callback) {
        final PinDialog pinDialog = new PinDialog();
        pinDialog.setStyle(1, 2131558603);
        pinDialog.setServiceManager(serviceManager);
        pinDialog.setCallback(callback);
        return pinDialog;
    }
    
    private void dismissDialog() {
        this.dismissAllowingStateLoss();
    }
    
    private InputMethodManager getIMM(final ServiceManager serviceManager) {
        if (serviceManager == null || serviceManager.getActivity() == null) {
            return null;
        }
        return (InputMethodManager)serviceManager.getActivity().getSystemService("input_method");
    }
    
    private ServiceManager getServiceManager() {
        return this.mServiceMan;
    }
    
    private void hideSoftKeyboard(final ServiceManager serviceManager, final EditText editText) {
        final InputMethodManager imm = this.getIMM(serviceManager);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
    
    private void notifyCallerPinCancelled() {
        if (this.mCallback != null) {
            this.mCallback.pinCancelled();
            this.mCallback = null;
        }
    }
    
    private void notifyCallerPinVerified(final boolean b) {
        if (this.mCallback != null) {
            this.mCallback.pinVerified(b);
            this.mCallback = null;
        }
    }
    
    private void processUserInputPin(final String s) {
        this.showProgress(true);
        this.showErrorIcon(false);
        this.hideSoftKeyboard(this.getServiceManager(), this.mPinEditText);
        Log.d("nf_pin", "onEditorAction gotDone! password: " + s);
        this.getServiceManager().verifyPin(s, new OnPinVerifiedCallback());
    }
    
    private void setCallback(final PinDialogCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    private void setDialogWindowSize() {
        final WindowManager$LayoutParams attributes = new WindowManager$LayoutParams();
        attributes.copyFrom(this.getDialog().getWindow().getAttributes());
        attributes.width = (int)(0.5f + TypedValue.applyDimension(1, (float)this.mDialogWidthInDp, this.getResources().getDisplayMetrics()));
        this.getDialog().getWindow().setAttributes(attributes);
    }
    
    private void setServiceManager(final ServiceManager mServiceMan) {
        this.mServiceMan = mServiceMan;
    }
    
    private static void showConnectivityErrorDialog(final NetflixActivity netflixActivity, final int n) {
        new AlertDialog$Builder((Context)netflixActivity).setCancelable(false).setMessage((CharSequence)String.format("%s (%d)", netflixActivity.getString(2131493270), n)).setPositiveButton(2131492976, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
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
            this.mPinMessage.setText(2131493324);
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
    
    protected static void showVerifyPinDialog(final NetflixActivity netflixActivity, final PinDialogCallback pinDialogCallback) {
        netflixActivity.showDialog(createPinDialog(netflixActivity.getServiceManager(), pinDialogCallback));
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("nf_pin", "onCancel");
        this.notifyCallerPinCancelled();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("nf_pin", " onCreateDialog ");
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903139, (ViewGroup)null);
        this.mSpinner = (ProgressBar)inflate.findViewById(2131165496);
        (this.mPinEditText = (EditText)inflate.findViewById(2131165493)).setOnEditorActionListener((TextView$OnEditorActionListener)new PinDialogOnDone());
        this.mPinEditText.setOnKeyListener((View$OnKeyListener)new PinDialogOnKeyPress());
        this.mPinMessage = (TextView)inflate.findViewById(2131165492);
        (this.mPinForgotView = (TextView)inflate.findViewById(2131165495)).setMovementMethod((MovementMethod)new LinkMovementMethod() {
            public boolean onTouchEvent(final TextView textView, final Spannable spannable, final MotionEvent motionEvent) {
                PinDialog.this.mPinForgotView.setLinkTextColor(PinDialog.this.getResources().getColor(2131296383));
                return super.onTouchEvent(textView, spannable, motionEvent);
            }
        });
        this.mPinForgotView.setFocusable(false);
        ((Button)inflate.findViewById(2131165497)).setOnClickListener((View$OnClickListener)new PinDialogOnCancel());
        this.mErrorIcon = (ImageView)inflate.findViewById(2131165494);
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
        this.saveDialogData();
        return (Dialog)create;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
        super.onManagerReady(serviceManager, n);
        this.setServiceManager(serviceManager);
    }
    
    public void onResume() {
        super.onResume();
        this.mPinForgotView.setLinkTextColor(-1);
        this.setDialogWindowSize();
        this.showSoftKeyboardOnStart();
    }
    
    public void retryOnPinFailure() {
        this.mPinMessage.setText(2131493327);
        this.mPinEditText.getText().clear();
        this.showErrorIcon(true);
        this.showSoftKeyboard(this.getServiceManager(), this.mPinEditText);
    }
    
    public void saveDialogData() {
        final FragmentManager fragmentManager = this.getFragmentManager();
        this.mDataFragment = (DataFragment)fragmentManager.findFragmentByTag("PIN_DATA");
        if (this.mDataFragment == null) {
            this.mDataFragment = new DataFragment();
            fragmentManager.beginTransaction().add((Fragment)this.mDataFragment, "PIN_DATA").commit();
            this.mDataFragment.setData(this.mCallback);
            return;
        }
        this.setCallback(this.mDataFragment.getData());
    }
    
    public class DataFragment extends Fragment
    {
        private PinDialogCallback _callback;
        
        public PinDialogCallback getData() {
            return this._callback;
        }
        
        public void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            this.setRetainInstance(true);
        }
        
        public void setData(final PinDialogCallback callback) {
            this._callback = callback;
        }
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
            }
            else {
                PinDialog.this.dismissDialog();
                if (PinDialog.this.mCallback != null) {
                    if (n == 0) {
                        PinDialog.this.notifyCallerPinVerified(b);
                        return;
                    }
                    PinDialog.this.notifyCallerPinCancelled();
                    showConnectivityErrorDialog(PinDialog.this.getServiceManager().getActivity(), n);
                }
            }
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
            PinDialog.this.dismissDialog();
            PinDialog.this.notifyCallerPinCancelled();
        }
    }
    
    private class PinDialogOnDone implements TextView$OnEditorActionListener
    {
        public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
            if (n == 6) {
                final String string = textView.getText().toString();
                if (string.length() < PinDialog.PIN_LENGTH) {
                    return true;
                }
                if (PinDialog.this.getServiceManager() != null) {
                    PinDialog.this.processUserInputPin(string);
                    return false;
                }
            }
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
                if (string.length() >= PinDialog.PIN_LENGTH && PinDialog.this.getServiceManager() != null) {
                    PinDialog.this.processUserInputPin(string);
                    return false;
                }
            }
            return false;
        }
    }
}
