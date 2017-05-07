// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.android.app.Status;
import android.app.AlertDialog;
import android.view.View;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Bundle;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.widget.TextView;
import android.support.v7.widget.SwitchCompat;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class BandwidthDialog$BwDialogOnDone implements DialogInterface$OnClickListener
{
    final /* synthetic */ BandwidthDialog this$0;
    
    private BandwidthDialog$BwDialogOnDone(final BandwidthDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.d("nf_bw", "doneButton clicked");
        this.this$0.notifyCaller();
        this.this$0.dismissDialog();
    }
}
