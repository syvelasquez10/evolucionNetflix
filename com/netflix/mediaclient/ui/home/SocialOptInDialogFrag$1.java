// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.Fragment;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class SocialOptInDialogFrag$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ SocialOptInDialogFrag this$0;
    final /* synthetic */ SocialOptInDialogFrag$OptInResponseHandler val$handler;
    
    SocialOptInDialogFrag$1(final SocialOptInDialogFrag this$0, final SocialOptInDialogFrag$OptInResponseHandler val$handler) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.d("social", "User opted out!");
        synchronized (this.this$0.mClicked) {
            if (this.this$0.mClicked.get()) {
                Log.w("social", "Already clicked!");
                return;
            }
            this.this$0.mClicked.set(true);
            // monitorexit(SocialOptInDialogFrag.access$000(this.this$0))
            this.this$0.dismiss();
            this.this$0.getFragmentManager().beginTransaction().remove((Fragment)this.this$0).commit();
            this.val$handler.onDecline();
        }
    }
}
