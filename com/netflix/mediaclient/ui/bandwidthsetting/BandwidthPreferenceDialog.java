// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.view.View$OnClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.preference.DialogPreference;

public class BandwidthPreferenceDialog extends DialogPreference
{
    private static final String TAG = "nf_bw";
    private TextView getmTextUnlimitedDetail;
    private SwitchCompat mBandwidthControlAuto;
    private RelativeLayout mRbGroupHigh;
    private RelativeLayout mRbGroupLow;
    private RelativeLayout mRbGroupMedium;
    private RelativeLayout mRbGroupOff;
    private RelativeLayout mRbGroupUnlimited;
    private RadioButton mRbHigh;
    private RadioButton mRbLow;
    private RadioButton mRbMedium;
    private RadioButton mRbOff;
    private RadioButton mRbUnlimited;
    private TextView mTextHigh;
    private TextView mTextHighDetail;
    private TextView mTextLow;
    private TextView mTextLowDetail;
    private TextView mTextMedium;
    private TextView mTextMediumDetail;
    private TextView mTextOff;
    private TextView mTextOffDetail;
    private TextView mTextUnlimited;
    
    public BandwidthPreferenceDialog(final Context context, final AttributeSet set) {
        super(context, set);
        this.setPersistent(false);
        this.setDialogLayoutResource(2130903239);
    }
    
    private void enableManualViews(final boolean enabled) {
        this.mRbOff.setEnabled(enabled);
        this.mRbLow.setEnabled(enabled);
        this.mRbMedium.setEnabled(enabled);
        this.mRbHigh.setEnabled(enabled);
        this.mRbUnlimited.setEnabled(enabled);
        this.mTextOff.setEnabled(enabled);
        this.mTextOffDetail.setEnabled(enabled);
        this.mTextLow.setEnabled(enabled);
        this.mTextLowDetail.setEnabled(enabled);
        this.mTextMedium.setEnabled(enabled);
        this.mTextMediumDetail.setEnabled(enabled);
        this.mTextHigh.setEnabled(enabled);
        this.mTextHighDetail.setEnabled(enabled);
        this.mTextUnlimited.setEnabled(enabled);
        this.getmTextUnlimitedDetail.setEnabled(enabled);
    }
    
    private BandwidthPreferenceDialog$ManualBwChoice getSelectedManualBwChoice() {
        if (this.mRbOff.isChecked()) {
            return BandwidthPreferenceDialog$ManualBwChoice.OFF;
        }
        if (this.mRbLow.isChecked()) {
            return BandwidthPreferenceDialog$ManualBwChoice.LOW;
        }
        if (this.mRbMedium.isChecked()) {
            return BandwidthPreferenceDialog$ManualBwChoice.MEDIUM;
        }
        if (this.mRbHigh.isChecked()) {
            return BandwidthPreferenceDialog$ManualBwChoice.HIGH;
        }
        if (this.mRbUnlimited.isChecked()) {
            return BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED;
        }
        return BandwidthPreferenceDialog$ManualBwChoice.LOW;
    }
    
    private void initClickListeners() {
        this.mRbOff.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbLow.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbMedium.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbHigh.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbUnlimited.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbGroupOff.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbGroupLow.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbGroupMedium.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbGroupHigh.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mRbGroupUnlimited.setOnClickListener((View$OnClickListener)new BandwidthPreferenceDialog$DataSelectorViewListener(this));
        this.mBandwidthControlAuto.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new BandwidthPreferenceDialog$1(this));
    }
    
    private void initViews(final View view, final boolean checked, final int n) {
        this.mRbOff = (RadioButton)view.findViewById(2131690173);
        this.mRbLow = (RadioButton)view.findViewById(2131690177);
        this.mRbMedium = (RadioButton)view.findViewById(2131690181);
        this.mRbHigh = (RadioButton)view.findViewById(2131690185);
        this.mRbUnlimited = (RadioButton)view.findViewById(2131690189);
        this.mTextOff = (TextView)view.findViewById(2131690174);
        this.mTextOffDetail = (TextView)view.findViewById(2131690175);
        this.mTextLow = (TextView)view.findViewById(2131690178);
        this.mTextLowDetail = (TextView)view.findViewById(2131690179);
        this.mTextMedium = (TextView)view.findViewById(2131690182);
        this.mTextMediumDetail = (TextView)view.findViewById(2131690183);
        this.mTextHigh = (TextView)view.findViewById(2131690186);
        this.mTextHighDetail = (TextView)view.findViewById(2131690187);
        this.mTextUnlimited = (TextView)view.findViewById(2131690190);
        this.getmTextUnlimitedDetail = (TextView)view.findViewById(2131690191);
        this.mRbGroupOff = (RelativeLayout)view.findViewById(2131690172);
        this.mRbGroupLow = (RelativeLayout)view.findViewById(2131690176);
        this.mRbGroupMedium = (RelativeLayout)view.findViewById(2131690180);
        this.mRbGroupHigh = (RelativeLayout)view.findViewById(2131690184);
        this.mRbGroupUnlimited = (RelativeLayout)view.findViewById(2131690188);
        (this.mBandwidthControlAuto = (SwitchCompat)view.findViewById(2131690168)).setChecked(checked);
        this.enableManualViews(!checked);
        if (!checked) {
            this.selectManualChoice(BandwidthPreferenceDialog$ManualBwChoice.create(n));
        }
    }
    
    private boolean isAutoSelectorViewOn() {
        return this.mBandwidthControlAuto.isChecked();
    }
    
    private void notifyCaller() {
        if (this.getContext() instanceof BandwidthPreferenceDialog$BandwidthSavingCallback) {
            ((BandwidthPreferenceDialog$BandwidthSavingCallback)this.getContext()).onBandwidthSettingsDone(this.getContext());
            return;
        }
        Log.d("nf_bw", "notifyCaller: no callback interface!! activity:" + this.getContext().getClass().getSimpleName());
    }
    
    private void selectManualChoice(final BandwidthPreferenceDialog$ManualBwChoice bandwidthPreferenceDialog$ManualBwChoice) {
        switch (BandwidthPreferenceDialog$2.$SwitchMap$com$netflix$mediaclient$ui$bandwidthsetting$BandwidthPreferenceDialog$ManualBwChoice[bandwidthPreferenceDialog$ManualBwChoice.ordinal()]) {
            default: {}
            case 1: {
                this.mRbOff.setChecked(true);
            }
            case 2: {
                this.mRbLow.setChecked(true);
            }
            case 3: {
                this.mRbMedium.setChecked(true);
            }
            case 4: {
                this.mRbHigh.setChecked(true);
            }
            case 5: {
                this.mRbUnlimited.setChecked(true);
            }
        }
    }
    
    private void unselectManualChoices() {
        this.mRbOff.setChecked(false);
        this.mRbLow.setChecked(false);
        this.mRbMedium.setChecked(false);
        this.mRbHigh.setChecked(false);
        this.mRbUnlimited.setChecked(false);
    }
    
    protected void dismissDialog() {
        Log.d("nf_bw", "dismissing bw dialog");
        this.getDialog().dismiss();
    }
    
    public void onBindDialogView(final View view) {
        this.initViews(view, BandwidthUtility.isAutomaticOn(this.getContext()), BandwidthUtility.getManualChoice(this.getContext()));
        this.initClickListeners();
        super.onBindDialogView(view);
    }
    
    protected void onDialogClosed(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_bw", String.format("onDialogClosed :%b", b));
        }
        if (b) {
            BandwidthUtility.saveUserSelections(this.getContext(), this.isAutoSelectorViewOn(), this.getSelectedManualBwChoice().getValue());
            this.notifyCaller();
        }
    }
}
