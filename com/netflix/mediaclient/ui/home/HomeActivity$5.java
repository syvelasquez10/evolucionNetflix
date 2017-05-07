// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class HomeActivity$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$5(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (n == -1) {
            Log.e("HomeActivity", "Invalid country error dialog displayed - killing self");
            this.this$0.finish();
            return;
        }
        Log.e("HomeActivity", "Unhandled dialog button was clicked");
    }
}
