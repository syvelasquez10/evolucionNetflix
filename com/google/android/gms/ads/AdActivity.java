// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.internal.zzfd;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfe;
import android.app.Activity;

public class AdActivity extends Activity
{
    private zzfe zznJ;
    
    private void zzaE() {
        if (this.zznJ == null) {
            return;
        }
        try {
            this.zznJ.zzaE();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward setContentViewSet to ad overlay:", (Throwable)ex);
        }
    }
    
    public void onBackPressed() {
        boolean zzez = true;
        while (true) {
            try {
                if (this.zznJ != null) {
                    zzez = this.zznJ.zzez();
                }
                if (zzez) {
                    super.onBackPressed();
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onBackPressed to ad overlay:", (Throwable)ex);
                zzez = zzez;
                continue;
            }
            break;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zznJ = zzfd.zzb(this);
        if (this.zznJ == null) {
            zzb.zzaE("Could not create ad overlay.");
            this.finish();
            return;
        }
        try {
            this.zznJ.onCreate(bundle);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onCreate to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zznJ != null) {
                    this.zznJ.onDestroy();
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
                if (this.zznJ != null) {
                    this.zznJ.onPause();
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
            if (this.zznJ != null) {
                this.zznJ.onRestart();
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
            if (this.zznJ != null) {
                this.zznJ.onResume();
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
                if (this.zznJ != null) {
                    this.zznJ.onSaveInstanceState(bundle);
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
            if (this.zznJ != null) {
                this.zznJ.onStart();
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
                if (this.zznJ != null) {
                    this.zznJ.onStop();
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
