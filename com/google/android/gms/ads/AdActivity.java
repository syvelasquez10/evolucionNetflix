// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.internal.dr;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.gs;
import com.google.android.gms.internal.ds;
import android.app.Activity;

public final class AdActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private ds lc;
    
    private void U() {
        if (this.lc == null) {
            return;
        }
        try {
            this.lc.U();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward setContentViewSet to ad overlay:", (Throwable)ex);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.lc = dr.b(this);
        if (this.lc == null) {
            gs.W("Could not create ad overlay.");
            this.finish();
            return;
        }
        try {
            this.lc.onCreate(bundle);
        }
        catch (RemoteException ex) {
            gs.d("Could not forward onCreate to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.lc != null) {
                    this.lc.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onDestroy to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.lc != null) {
                    this.lc.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onPause to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.lc != null) {
                this.lc.onRestart();
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not forward onRestart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.lc != null) {
                this.lc.onResume();
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not forward onResume to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.lc != null) {
                    this.lc.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onSaveInstanceState to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.lc != null) {
                this.lc.onStart();
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not forward onStart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.lc != null) {
                    this.lc.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onStop to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        this.U();
    }
    
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        this.U();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
        this.U();
    }
}
