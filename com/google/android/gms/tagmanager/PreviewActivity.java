// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Activity;

public class PreviewActivity extends Activity
{
    private void d(final String title, final String message, final String s) {
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        create.setTitle((CharSequence)title);
        create.setMessage((CharSequence)message);
        create.setButton(-1, (CharSequence)s, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        create.show();
    }
    
    public void onCreate(final Bundle bundle) {
        try {
            super.onCreate(bundle);
            bh.U("Preview activity");
            final Uri data = this.getIntent().getData();
            if (!TagManager.getInstance((Context)this).i(data)) {
                final String string = "Cannot preview the app with the uri: " + data + ". Launching current version instead.";
                bh.W(string);
                this.d("Preview failure", string, "Continue");
            }
            final Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
            if (launchIntentForPackage != null) {
                bh.U("Invoke the launch activity for package name: " + this.getPackageName());
                this.startActivity(launchIntentForPackage);
                return;
            }
            bh.U("No launch activity found for package name: " + this.getPackageName());
        }
        catch (Exception ex) {
            bh.T("Calling preview threw an exception: " + ex.getMessage());
        }
    }
}
