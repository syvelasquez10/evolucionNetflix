// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ServiceErrorsHandler$2(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.i("ServiceErrorsHandler", "User clicked Ok on prompt to update");
        final Intent updateSourceIntent = AppStoreHelper.getUpdateSourceIntent((Context)this.val$activity);
        if (updateSourceIntent == null) {
            return;
        }
        updateSourceIntent.addFlags(268435456);
        try {
            this.val$activity.startActivity(updateSourceIntent);
        }
        catch (ActivityNotFoundException ex) {
            Log.e("ServiceErrorsHandler", "Failed to start store Activity!", (Throwable)ex);
        }
        finally {
            this.val$activity.finish();
        }
    }
}
