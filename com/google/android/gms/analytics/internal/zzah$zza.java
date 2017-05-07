// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.net.URLConnection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import java.io.IOException;
import com.google.android.gms.common.internal.zzx;
import java.io.ByteArrayOutputStream;

class zzah$zza
{
    private int zzOt;
    private ByteArrayOutputStream zzOu;
    final /* synthetic */ zzah zzOv;
    
    public zzah$zza(final zzah zzOv) {
        this.zzOv = zzOv;
        this.zzOu = new ByteArrayOutputStream();
    }
    
    public byte[] getPayload() {
        return this.zzOu.toByteArray();
    }
    
    public boolean zzj(final zzab zzab) {
        zzx.zzv(zzab);
        if (this.zzOt + 1 > this.zzOv.zzif().zzjw()) {
            return false;
        }
        final String zza = this.zzOv.zza(zzab, false);
        if (zza == null) {
            this.zzOv.zzie().zza(zzab, "Error formatting hit");
            return true;
        }
        final byte[] bytes = zza.getBytes();
        final int length = bytes.length;
        if (length > this.zzOv.zzif().zzjo()) {
            this.zzOv.zzie().zza(zzab, "Hit size exceeds the maximum size limit");
            return true;
        }
        int n = length;
        if (this.zzOu.size() > 0) {
            n = length + 1;
        }
        if (n + this.zzOu.size() > this.zzOv.zzif().zzjq()) {
            return false;
        }
        try {
            if (this.zzOu.size() > 0) {
                this.zzOu.write(zzah.zzOs);
            }
            this.zzOu.write(bytes);
            ++this.zzOt;
            return true;
        }
        catch (IOException ex) {
            this.zzOv.zze("Failed to write payload when batching hits", ex);
            return true;
        }
    }
    
    public int zzkx() {
        return this.zzOt;
    }
}
