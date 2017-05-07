// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import java.util.Collections;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.content.ContentValues;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import android.content.Context;

class zzby$zza implements zzcx$zza
{
    final /* synthetic */ zzby zzaQS;
    
    zzby$zza(final zzby zzaQS) {
        this.zzaQS = zzaQS;
    }
    
    @Override
    public void zza(final zzaq zzaq) {
        this.zzaQS.zzq(zzaq.zzAe());
    }
    
    @Override
    public void zzb(final zzaq zzaq) {
        this.zzaQS.zzq(zzaq.zzAe());
        zzbg.v("Permanent failure dispatching hitId: " + zzaq.zzAe());
    }
    
    @Override
    public void zzc(final zzaq zzaq) {
        final long zzAf = zzaq.zzAf();
        if (zzAf == 0L) {
            this.zzaQS.zzc(zzaq.zzAe(), this.zzaQS.zzpO.currentTimeMillis());
        }
        else if (zzAf + 14400000L < this.zzaQS.zzpO.currentTimeMillis()) {
            this.zzaQS.zzq(zzaq.zzAe());
            zzbg.v("Giving up on failed hitId: " + zzaq.zzAe());
        }
    }
}
