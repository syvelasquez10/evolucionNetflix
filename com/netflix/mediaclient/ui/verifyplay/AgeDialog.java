// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ProgressBar;
import android.app.AlertDialog;
import android.widget.TextView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class AgeDialog extends NetflixDialogFrag
{
    private static final int AGE_DIALOG_WIDTH_PHONE_DP = 320;
    private static final int AGE_DIALOG_WIDTH_TABLET_DP = 400;
    private static final String AGE_PROGRESS = "age_progress";
    private static final String AGE_VERIFY_URL = "http://www.netflix.com/verifyage";
    private static final String TAG = "nf_age";
    private boolean mActive;
    private TextView mAgeMessage;
    private AlertDialog mDialog;
    private int mDialogWidthInDp;
    private boolean mInProgress;
    private boolean mRestored;
    private ProgressBar mSpinner;
    private PlayVerifierVault mVault;
    
    protected static AgeDialog createAgeDialog(final PlayVerifierVault playVerifierVault) {
        Log.d("nf_age", "creating dialog");
        final AgeDialog ageDialog = new AgeDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(PlayVerifierVault.NAME, (Parcelable)playVerifierVault);
        ageDialog.setArguments(arguments);
        ageDialog.setStyle(1, 2131558720);
        return ageDialog;
    }
    
    private ServiceManager getServiceManager() {
        return ((NetflixActivity)this.getActivity()).getServiceManager();
    }
    
    private void notifyCallerAgeCancelled() {
        Log.d("nf_pin", String.format("%s onAgeCancelled vault: %s", NetflixActivity.class.getSimpleName(), this.mVault));
        if (this.mVault == null) {
            Log.d("nf_age", "mValut is null - cannot start playback");
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
    
    private void setDialogWindowSize() {
        final WindowManager$LayoutParams attributes = new WindowManager$LayoutParams();
        try {
            attributes.copyFrom(this.getDialog().getWindow().getAttributes());
            attributes.width = (int)(TypedValue.applyDimension(1, (float)this.mDialogWidthInDp, this.getResources().getDisplayMetrics()) + 0.5f);
            this.getDialog().getWindow().setAttributes(attributes);
        }
        catch (Exception ex) {
            Log.e("nf_age", "Could not set windowSize e:" + ex);
        }
    }
    
    private void showVerifyButton(final boolean b) {
        final Button button = this.mDialog.getButton(-1);
        if (button != null) {
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            button.setVisibility(visibility);
        }
    }
    
    public void ageVerifyDone() {
        this.showProgress(this.mActive = false);
    }
    
    protected void dismissDialog() {
        Log.d("nf_age", "dismissing age dialog");
        this.getDialog().dismiss();
        this.mActive = false;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("nf_age", "onCancel");
        this.mActive = false;
        this.notifyCallerAgeCancelled();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        this.mInProgress = true;
        this.mRestored = (bundle != null);
        Log.d("nf_age", String.format("onCreateDialog - mIsActive:%b,  restored=%b", this.mActive, this.mRestored));
        if (this.mRestored) {
            this.mInProgress = bundle.getBoolean("age_progress");
        }
        this.mVault = (PlayVerifierVault)this.getArguments().getParcelable(PlayVerifierVault.NAME);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903067, (ViewGroup)null);
        this.mSpinner = (ProgressBar)inflate.findViewById(2131427448);
        this.mAgeMessage = (TextView)inflate.findViewById(2131427447);
        int mDialogWidthInDp;
        if (DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            mDialogWidthInDp = 400;
        }
        else {
            mDialogWidthInDp = 320;
        }
        this.mDialogWidthInDp = mDialogWidthInDp;
        alertDialog$Builder.setView(inflate);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setButton(-2, (CharSequence)this.getString(2131493123), (DialogInterface$OnClickListener)new AgeDialog$AgeDialogOnCancel(this, null));
        create.setButton(-1, (CharSequence)this.getString(2131493316), (DialogInterface$OnClickListener)new AgeDialog$AgeDialogOnVerify(this, null));
        this.mActive = true;
        return (Dialog)(this.mDialog = create);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        Log.d("nf_age", "onManagerReady - starting age verification");
        serviceManager.verifyAge(new AgeDialog$OnAgeVerifiedCallback(this));
    }
    
    public void onResume() {
        super.onResume();
        Log.d("nf_age", "onResume");
        this.setDialogWindowSize();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("nf_age", "onSavedInstanceState");
        bundle.putBoolean("age_progress", this.mInProgress);
    }
    
    public void onStart() {
        Log.d("nf_age", "onStart");
        super.onStart();
        this.showProgress(this.mInProgress);
        if (!this.mRestored && this.getServiceManager() != null) {
            Log.d("nf_age", "starting age verification");
            this.getServiceManager().verifyAge(new AgeDialog$OnAgeVerifiedCallback(this));
        }
    }
    
    public void showProgress(final boolean mInProgress) {
        boolean b = false;
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
        final TextView mAgeMessage = this.mAgeMessage;
        int text;
        if (mInProgress) {
            text = 2131493315;
        }
        else {
            text = 2131493314;
        }
        mAgeMessage.setText(text);
        if (!mInProgress) {
            b = true;
        }
        this.showVerifyButton(b);
    }
}
