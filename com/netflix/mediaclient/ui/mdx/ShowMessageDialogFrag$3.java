// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

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

class ShowMessageDialogFrag$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ ShowMessageDialogFrag this$0;
    final /* synthetic */ String[] val$codes;
    
    ShowMessageDialogFrag$3(final ShowMessageDialogFrag this$0, final String[] val$codes) {
        this.this$0 = this$0;
        this.val$codes = val$codes;
    }
    
    public void onClick(DialogInterface dialogInterface, final int n) {
        dialogInterface = (DialogInterface)this.this$0.mClicked;
        synchronized (dialogInterface) {
            if (this.this$0.mClicked.get()) {
                Log.w("mdxui", "Already clicked!");
                return;
            }
            this.this$0.mClicked.set(true);
            // monitorexit(dialogInterface)
            this.this$0.dismissAllowingStateLoss();
            this.this$0.getFragmentManager().beginTransaction().remove((Fragment)this.this$0).commit();
            dialogInterface = (DialogInterface)this.this$0.getActivity();
            if (dialogInterface instanceof ShowMessageDialogFrag$MessageResponseProvider) {
                ((ShowMessageDialogFrag$MessageResponseProvider)dialogInterface).onResponse(this.val$codes[2]);
            }
        }
    }
}
