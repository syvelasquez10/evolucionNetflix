// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.Log;
import android.app.Dialog;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SocialOptInDialogFrag extends NetflixDialogFrag
{
    private static final String TAG = "social";
    private final AtomicBoolean mClicked;
    
    public SocialOptInDialogFrag() {
        this.mClicked = new AtomicBoolean(false);
    }
    
    public static SocialOptInDialogFrag newInstance() {
        return new SocialOptInDialogFrag();
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        final Activity activity = this.getActivity();
        if (!(activity instanceof SocialOptInDialogFrag$OptInResponseHandler)) {
            Log.e("social", "Activity is not OptInResponseHandler! This should not happen!");
            return null;
        }
        final SocialOptInDialogFrag$OptInResponseHandler socialOptInDialogFrag$OptInResponseHandler = (SocialOptInDialogFrag$OptInResponseHandler)activity;
        return (Dialog)new AlertDialog$Builder((Context)this.getActivity(), 2131558720).setTitle(2131493248).setMessage(2131493249).setPositiveButton(2131493251, (DialogInterface$OnClickListener)new SocialOptInDialogFrag$2(this, socialOptInDialogFrag$OptInResponseHandler)).setNegativeButton(2131493250, (DialogInterface$OnClickListener)new SocialOptInDialogFrag$1(this, socialOptInDialogFrag$OptInResponseHandler)).create();
    }
}
