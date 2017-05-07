// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnKeyListener;

class VolumeDialogFrag$2 implements DialogInterface$OnKeyListener
{
    final /* synthetic */ VolumeDialogFrag this$0;
    
    VolumeDialogFrag$2(final VolumeDialogFrag this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
        return this.this$0.getActivity() != null && ((NetflixActivity)this.this$0.getActivity()).dispatchKeyEvent(keyEvent);
    }
}
