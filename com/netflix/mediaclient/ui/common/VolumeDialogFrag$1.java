// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.DialogInterface$OnKeyListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class VolumeDialogFrag$1 extends BroadcastReceiver
{
    final /* synthetic */ VolumeDialogFrag this$0;
    
    VolumeDialogFrag$1(final VolumeDialogFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final int intExtra = intent.getIntExtra("volume", -1);
        if (intExtra >= 0) {
            this.this$0.seekBar.setProgress(intExtra);
            return;
        }
        Log.e("VolumeDialogFrag", "Volume value is missed from MDX_SETVOLUME Intent");
    }
}
