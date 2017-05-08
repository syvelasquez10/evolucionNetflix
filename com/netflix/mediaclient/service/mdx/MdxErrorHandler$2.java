// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class MdxErrorHandler$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ MdxErrorHandler this$0;
    
    MdxErrorHandler$2(final MdxErrorHandler this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("https://help.netflix.com/en/node/13590"));
        if (this.this$0.activity != null && setData.resolveActivity(this.this$0.activity.getPackageManager()) != null) {
            this.this$0.activity.startActivity(setData);
            return;
        }
        Log.e(this.this$0.tag, "Unable to launchHelp");
    }
}
