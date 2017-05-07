// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.View$OnClickListener;

class SharingDialogFrag$1 implements View$OnClickListener
{
    final /* synthetic */ SharingDialogFrag this$0;
    
    SharingDialogFrag$1(final SharingDialogFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        ((NetflixActivity)this.this$0.getActivity()).getMdxMiniPlayerFrag().unshareVideo();
        this.this$0.dismiss();
    }
}
