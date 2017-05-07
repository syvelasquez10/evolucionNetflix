// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.internal.zzfj;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfk;
import android.app.Activity;

public class AdActivity extends Activity
{
    private zzfk zznK;
    
    private void zzaE() {
        if (this.zznK == null) {
            return;
        }
        try {
            this.zznK.zzaE();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward setContentViewSet to ad overlay:", (Throwable)ex);
        }
    }
    
    public void onBackPressed() {
        boolean zzeF = true;
        while (true) {
            try {
                if (this.zznK != null) {
                    zzeF = this.zznK.zzeF();
                }
                if (zzeF) {
                    super.onBackPressed();
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onBackPressed to ad overlay:", (Throwable)ex);
                zzeF = zzeF;
                continue;
            }
            break;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zznK = zzfj.zzb(this);
        if (this.zznK == null) {
            zzb.zzaH("Could not create ad overlay.");
            this.finish();
            return;
        }
        try {
            this.zznK.onCreate(bundle);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onCreate to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zznK != null) {
                    this.zznK.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onDestroy to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.zznK != null) {
                    this.zznK.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onPause to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zznK != null) {
                this.zznK.onRestart();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onRestart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.zznK != null) {
                this.zznK.onResume();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onResume to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.zznK != null) {
                    this.zznK.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onSaveInstanceState to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.zznK != null) {
                this.zznK.onStart();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onStart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.zznK != null) {
                    this.zznK.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onStop to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        this.zzaE();
    }
    
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        this.zzaE();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
        this.zzaE();
    }
}
