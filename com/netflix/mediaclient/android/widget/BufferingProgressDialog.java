// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.KeyEvent;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.ProgressDialog;

public class BufferingProgressDialog extends ProgressDialog
{
    private final NetflixActivity netflixActivity;
    
    public BufferingProgressDialog(final NetflixActivity netflixActivity) {
        super((Context)netflixActivity);
        this.netflixActivity = netflixActivity;
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
    
    public BufferingProgressDialog(final NetflixActivity netflixActivity, final int n) {
        super((Context)netflixActivity, n);
        this.netflixActivity = netflixActivity;
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.netflixActivity.onKeyDown(n, keyEvent);
        }
        else if (n != 84) {
            return super.onKeyDown(n, keyEvent);
        }
        return true;
    }
    
    public boolean onSearchRequested() {
        return false;
    }
}
