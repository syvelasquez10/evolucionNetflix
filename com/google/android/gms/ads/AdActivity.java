// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.internal.cj;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.dw;
import com.google.android.gms.internal.ck;
import android.app.Activity;

public final class AdActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private ck ko;
    
    private void N() {
        if (this.ko == null) {
            return;
        }
        try {
            this.ko.N();
        }
        catch (RemoteException ex) {
            dw.c("Could not forward setContentViewSet to ad overlay:", (Throwable)ex);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.ko = cj.a(this);
        if (this.ko == null) {
            dw.z("Could not create ad overlay.");
            this.finish();
            return;
        }
        try {
            this.ko.onCreate(bundle);
        }
        catch (RemoteException ex) {
            dw.c("Could not forward onCreate to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.ko != null) {
                    this.ko.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                dw.c("Could not forward onDestroy to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.ko != null) {
                    this.ko.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                dw.c("Could not forward onPause to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.ko != null) {
                this.ko.onRestart();
            }
        }
        catch (RemoteException ex) {
            dw.c("Could not forward onRestart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.ko != null) {
                this.ko.onResume();
            }
        }
        catch (RemoteException ex) {
            dw.c("Could not forward onResume to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.ko != null) {
                    this.ko.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                dw.c("Could not forward onSaveInstanceState to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.ko != null) {
                this.ko.onStart();
            }
        }
        catch (RemoteException ex) {
            dw.c("Could not forward onStart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.ko != null) {
                    this.ko.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                dw.c("Could not forward onStop to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        this.N();
    }
    
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        this.N();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
        this.N();
    }
}
