// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import com.google.android.gms.ads.internal.zzp;
import android.text.TextUtils;
import java.net.URL;
import java.net.HttpURLConnection;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Context;

@zzgr
public final class zzij extends zzhz
{
    private final Context mContext;
    private final String zzF;
    private String zzIa;
    private final String zzqV;
    
    public zzij(final Context mContext, final String zzqV, final String zzF) {
        this.zzIa = null;
        this.mContext = mContext;
        this.zzqV = zzqV;
        this.zzF = zzF;
    }
    
    public zzij(final Context mContext, final String zzqV, final String zzF, final String zzIa) {
        this.zzIa = null;
        this.mContext = mContext;
        this.zzqV = zzqV;
        this.zzF = zzF;
        this.zzIa = zzIa;
    }
    
    @Override
    public void zzbn() {
        try {
            zzb.v("Pinging URL: " + this.zzF);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.zzF).openConnection();
            try {
                if (TextUtils.isEmpty((CharSequence)this.zzIa)) {
                    zzp.zzbv().zza(this.mContext, this.zzqV, true, httpURLConnection);
                }
                else {
                    zzp.zzbv().zza(this.mContext, this.zzqV, true, httpURLConnection, this.zzIa);
                }
                final int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    zzb.zzaH("Received non-success response code " + responseCode + " from pinging URL: " + this.zzF);
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            zzb.zzaH("Error while parsing ping URL: " + this.zzF + ". " + ex.getMessage());
        }
        catch (IOException ex2) {
            zzb.zzaH("Error while pinging URL: " + this.zzF + ". " + ex2.getMessage());
        }
        catch (RuntimeException ex3) {
            zzb.zzaH("Error while pinging URL: " + this.zzF + ". " + ex3.getMessage());
        }
    }
}
