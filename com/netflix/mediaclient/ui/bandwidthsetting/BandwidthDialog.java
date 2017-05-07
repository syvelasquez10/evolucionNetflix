// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.android.app.Status;
import android.app.AlertDialog;
import android.view.View;
import android.view.LayoutInflater;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Bundle;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.widget.TextView;
import android.support.v7.widget.SwitchCompat;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class BandwidthDialog extends NetflixDialogFrag
{
    private static final int BW_DIALOG_WIDTH_PHONE_DP = 400;
    private static final int BW_DIALOG_WIDTH_TABLET_DP = 600;
    private static final String DATA_SAVING_ENABLED = "dataSavingEnabled";
    private static final String HD_ENABLED_CELL = "isHdEnabledCell";
    private static final String HD_ENABLED_DEVICE = "isHdEnabledDevice";
    private static final String INVOKE_LOCATION = "invokeLocation";
    private static final String TAG = "nf_bw";
    private SwitchCompat mBandwidthSwitch;
    private int mDialogWidthInDp;
    private TextView mHdDescription;
    private boolean mHdEnabledDevice;
    private SwitchCompat mHdSwitch;
    private TextView mHdText;
    private BandwidthLogging$InvokeLocation mInvokeLocation;
    
    public static BandwidthDialog createBwDialog(final NetflixActivity netflixActivity, final DataSaveConfigData dataSaveConfigData, final BandwidthLogging$InvokeLocation bandwidthLogging$InvokeLocation) {
        Log.d("nf_bw", "creating dialog");
        final BandwidthDialog bandwidthDialog = new BandwidthDialog();
        final boolean dataSavingEnabled = BandwidthSaving.isDataSavingEnabled((Context)netflixActivity, dataSaveConfigData);
        final boolean equals = dataSaveConfigData.abTestConfig_6733.getCell().equals(ABTestConfigData$Cell.CELL_FIVE);
        final boolean hdEnabledDevice = BandwidthSaving.isHdEnabledDevice(netflixActivity.getServiceManager());
        final Bundle arguments = new Bundle();
        arguments.putBoolean("dataSavingEnabled", dataSavingEnabled);
        arguments.putBoolean("isHdEnabledCell", equals);
        arguments.putBoolean("isHdEnabledDevice", hdEnabledDevice);
        arguments.putString("invokeLocation", bandwidthLogging$InvokeLocation.getValue());
        bandwidthDialog.setArguments(arguments);
        bandwidthDialog.setStyle(1, 2131361924);
        return bandwidthDialog;
    }
    
    private ServiceManager getServiceManager() {
        if (this.getActivity() != null) {
            return ((NetflixActivity)this.getActivity()).getServiceManager();
        }
        return null;
    }
    
    private boolean isHdEnabled(final Context context, final boolean b) {
        return b && PreferenceUtils.getIntPref(context, "user_bw_hd_override", -1) > 0;
    }
    
    private void notifyCaller() {
        if (this.getActivity() instanceof BandwidthDialog$BandwidthSavingCallback) {
            ((BandwidthDialog$BandwidthSavingCallback)this.getActivity()).onBandwidthSettingsDone(this.getServiceManager());
            return;
        }
        Log.d("nf_bw", "notifyCaller: no callback interface!! activity:" + this.getActivity().getClass().getSimpleName());
    }
    
    private void setDialogWindowSize() {
        final WindowManager$LayoutParams attributes = new WindowManager$LayoutParams();
        try {
            attributes.copyFrom(this.getDialog().getWindow().getAttributes());
            attributes.width = (int)(TypedValue.applyDimension(1, (float)this.mDialogWidthInDp, this.getResources().getDisplayMetrics()) + 0.5f);
            this.getDialog().getWindow().setAttributes(attributes);
        }
        catch (Exception ex) {
            Log.e("nf_bw", "Could not set windowSize e:" + ex);
        }
    }
    
    private void setHdViewsEnableState(final boolean enabled) {
        if (this.mHdSwitch != null) {
            this.mHdSwitch.setEnabled(enabled);
        }
        if (this.mHdText != null) {
            this.mHdText.setEnabled(enabled);
        }
        if (this.mHdDescription != null) {
            this.mHdDescription.setEnabled(enabled);
        }
    }
    
    protected void dismissDialog() {
        Log.d("nf_bw", "dismissing bw dialog");
        this.getDialog().dismiss();
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("nf_bw", "onCancel");
        this.notifyCaller();
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        final boolean b = false;
        super.onCreate(bundle);
        final AlertDialog$Builder setTitle = new AlertDialog$Builder((Context)this.getActivity(), 2131361923).setTitle(2131165711);
        final boolean boolean1 = this.getArguments().getBoolean("dataSavingEnabled");
        final boolean boolean2 = this.getArguments().getBoolean("isHdEnabledCell");
        this.mHdEnabledDevice = this.getArguments().getBoolean("isHdEnabledDevice");
        this.mInvokeLocation = BandwidthLogging$InvokeLocation.create(this.getArguments().getString("invokeLocation"));
        final LayoutInflater layoutInflater = this.getActivity().getLayoutInflater();
        boolean b2;
        if (this.mHdEnabledDevice && boolean2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        int n;
        if (b2) {
            n = 2130903071;
        }
        else {
            n = 2130903072;
        }
        final View inflate = layoutInflater.inflate(n, (ViewGroup)null);
        (this.mBandwidthSwitch = (SwitchCompat)inflate.findViewById(2131624069)).setChecked(boolean1);
        this.mBandwidthSwitch.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new BandwidthDialog$1(this));
        if (b2) {
            this.mHdSwitch = (SwitchCompat)inflate.findViewById(2131624072);
            this.mHdText = (TextView)inflate.findViewById(2131624071);
            this.mHdDescription = (TextView)inflate.findViewById(2131624073);
            this.mHdSwitch.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new BandwidthDialog$2(this));
            this.mHdSwitch.setChecked(!boolean1 && this.isHdEnabled((Context)this.getActivity(), this.mHdEnabledDevice));
            boolean hdViewsEnableState = b;
            if (!boolean1) {
                hdViewsEnableState = true;
            }
            this.setHdViewsEnableState(hdViewsEnableState);
        }
        int mDialogWidthInDp;
        if (DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            mDialogWidthInDp = 600;
        }
        else {
            mDialogWidthInDp = 400;
        }
        this.mDialogWidthInDp = mDialogWidthInDp;
        setTitle.setView(inflate);
        final AlertDialog create = setTitle.create();
        create.setCanceledOnTouchOutside(true);
        create.setButton(-1, (CharSequence)this.getString(2131165720), (DialogInterface$OnClickListener)new BandwidthDialog$BwDialogOnDone(this, null));
        return (Dialog)create;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        Log.d("nf_bw", "onManagerReady");
    }
    
    public void onResume() {
        super.onResume();
        Log.d("nf_bw", "onResume");
        if (DeviceUtils.isLandscape((Context)this.getActivity())) {
            this.setDialogWindowSize();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("nf_bw", "onSavedInstanceState");
    }
    
    public void onStart() {
        Log.d("nf_bw", "onStart");
        super.onStart();
    }
}
