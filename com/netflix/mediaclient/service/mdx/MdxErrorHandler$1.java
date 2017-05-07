// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class MdxErrorHandler$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ MdxErrorHandler this$0;
    
    MdxErrorHandler$1(final MdxErrorHandler this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("https://help.netflix.com/en/node/12407"));
        if (this.this$0.activity != null && setData.resolveActivity(this.this$0.activity.getPackageManager()) != null) {
            this.this$0.activity.startActivity(setData);
            return;
        }
        Log.e(this.this$0.tag, "Unable to launchHelp");
    }
}
