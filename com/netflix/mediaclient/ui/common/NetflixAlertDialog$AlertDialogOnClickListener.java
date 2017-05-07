// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import android.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class NetflixAlertDialog$AlertDialogOnClickListener implements View$OnClickListener
{
    private final String mActionId;
    final /* synthetic */ NetflixAlertDialog this$0;
    
    public NetflixAlertDialog$AlertDialogOnClickListener(final NetflixAlertDialog this$0, final String mActionId) {
        this.this$0 = this$0;
        this.mActionId = mActionId;
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable("dialog", 3)) {
            Log.d("dialog", "Clicked on " + this.mActionId);
        }
        if (this.this$0.getActivity() == null) {
            Log.e("dialog", "Activity is NULL, we can update rating!");
            return;
        }
        synchronized (this.this$0.mClicked) {
            if (this.this$0.mClicked.get()) {
                Log.w("dialog", "Already clicked!");
                return;
            }
        }
        this.this$0.mClicked.set(true);
        // monitorexit(atomicBoolean)
        final Intent intent = new Intent("nflx_button_selected");
        intent.putExtra("nflx_action_selected", this.mActionId);
        intent.putExtra("nflx_dialog_id", this.this$0.mDialogId);
        intent.addCategory("LocalIntentNflxUi");
        final Context context;
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        this.this$0.dismissAllowingStateLoss();
        this.this$0.getFragmentManager().beginTransaction().remove((Fragment)this.this$0).commit();
    }
}
