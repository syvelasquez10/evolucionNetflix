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
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Bundle;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.support.v7.widget.SwitchCompat;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;

class BandwidthDialog$2 implements CompoundButton$OnCheckedChangeListener
{
    final /* synthetic */ BandwidthDialog this$0;
    
    BandwidthDialog$2(final BandwidthDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
        final Activity activity = this.this$0.getActivity();
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        PreferenceUtils.putIntPref((Context)activity, "user_bw_hd_override", n);
        Log.d("nf_bw", "hdSwitch toggled state: " + b);
        BandwidthLogging.reportBandwidthSettingChange((Context)this.this$0.getActivity(), BandwidthLogging$SettingType.HD_PLAYBACK, b, this.this$0.mInvokeLocation);
    }
}
