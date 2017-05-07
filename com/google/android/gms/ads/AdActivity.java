// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.internal.ct;
import com.google.android.gms.internal.br;
import android.os.Bundle;
import com.google.android.gms.internal.bs;
import android.app.Activity;

public final class AdActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private bs dV;
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.dV = br.a(this);
        if (this.dV == null) {
            ct.v("Could not create ad overlay.");
            this.finish();
            return;
        }
        try {
            this.dV.onCreate(bundle);
        }
        catch (RemoteException ex) {
            ct.b("Could not forward onCreate to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.dV != null) {
                    this.dV.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                ct.b("Could not forward onDestroy to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.dV != null) {
                    this.dV.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                ct.b("Could not forward onPause to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.dV != null) {
                this.dV.onRestart();
            }
        }
        catch (RemoteException ex) {
            ct.b("Could not forward onRestart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.dV != null) {
                this.dV.onResume();
            }
        }
        catch (RemoteException ex) {
            ct.b("Could not forward onResume to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.dV != null) {
                    this.dV.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                ct.b("Could not forward onSaveInstanceState to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.dV != null) {
                this.dV.onStart();
            }
        }
        catch (RemoteException ex) {
            ct.b("Could not forward onStart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.dV != null) {
                    this.dV.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                ct.b("Could not forward onStop to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
}
