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

@zzgk
public final class zzia extends zzhq
{
    private final Context mContext;
    private final String zzF;
    private String zzHj;
    private final String zzqK;
    
    public zzia(final Context mContext, final String zzqK, final String zzF) {
        this.zzHj = null;
        this.mContext = mContext;
        this.zzqK = zzqK;
        this.zzF = zzF;
    }
    
    public zzia(final Context mContext, final String zzqK, final String zzF, final String zzHj) {
        this.zzHj = null;
        this.mContext = mContext;
        this.zzqK = zzqK;
        this.zzF = zzF;
        this.zzHj = zzHj;
    }
    
    @Override
    public void zzdG() {
        try {
            zzb.v("Pinging URL: " + this.zzF);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.zzF).openConnection();
            try {
                if (TextUtils.isEmpty((CharSequence)this.zzHj)) {
                    zzp.zzbx().zza(this.mContext, this.zzqK, true, httpURLConnection);
                }
                else {
                    zzp.zzbx().zza(this.mContext, this.zzqK, true, httpURLConnection, this.zzHj);
                }
                final int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    zzb.zzaE("Received non-success response code " + responseCode + " from pinging URL: " + this.zzF);
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            zzb.zzaE("Error while parsing ping URL: " + this.zzF + ". " + ex.getMessage());
        }
        catch (IOException ex2) {
            zzb.zzaE("Error while pinging URL: " + this.zzF + ". " + ex2.getMessage());
        }
        catch (RuntimeException ex3) {
            zzb.zzaE("Error while pinging URL: " + this.zzF + ". " + ex3.getMessage());
        }
    }
}
