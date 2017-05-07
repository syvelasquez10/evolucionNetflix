// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.File;
import java.io.FileOutputStream;
import android.content.res.Resources$NotFoundException;
import java.io.FileNotFoundException;
import com.google.android.gms.common.api.Status;
import java.io.FileInputStream;
import java.io.IOException;
import com.google.android.gms.tagmanager.zzbg;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.content.Context;

public class zzqr
{
    public static final Integer zzaUg;
    public static final Integer zzaUh;
    private final Context mContext;
    private final ExecutorService zzaRv;
    
    static {
        zzaUg = 0;
        zzaUh = 1;
    }
    
    public zzqr(final Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }
    
    zzqr(final Context mContext, final ExecutorService zzaRv) {
        this.mContext = mContext;
        this.zzaRv = zzaRv;
    }
    
    private String zzfr(final String s) {
        return "resource_" + s;
    }
    
    private byte[] zzm(final InputStream ex) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzlr.zza((InputStream)ex, byteArrayOutputStream);
            try {
                ((InputStream)ex).close();
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex) {
                zzbg.zzaE("Error closing stream for reading resource from disk");
                return null;
            }
        }
        catch (IOException ex2) {
            zzbg.zzaE("Failed to read the resource from disk");
            try {
                ((InputStream)ex).close();
            }
            catch (IOException ex3) {
                zzbg.zzaE("Error closing stream for reading resource from disk");
                return null;
            }
        }
        finally {
            try {
                ((InputStream)ex).close();
            }
            catch (IOException ex4) {
                zzbg.zzaE("Error closing stream for reading resource from disk");
                return null;
            }
        }
    }
    
    public void zza(final String s, final Integer n, final zzql zzql, final zzqq zzqq) {
        this.zzaRv.execute(new zzqr$1(this, s, n, zzql, zzqq));
    }
    
    void zzb(final String s, final Integer n, final zzql zzql, final zzqq zzqq) {
        zzbg.v("DiskLoader: Starting to load resource from Disk.");
        try {
            final Object zzt = zzql.zzt(this.zzm(new FileInputStream(this.zzfq(s))));
            if (zzt != null) {
                zzbg.v("Saved resource loaded: " + this.zzfr(s));
                zzqq.zza(Status.zzaaD, zzt, zzqr.zzaUh, this.zzfp(s));
                return;
            }
            goto Label_0109;
        }
        catch (FileNotFoundException ex) {
            zzbg.e("Saved resource not found: " + this.zzfr(s));
        }
        catch (zzqp$zzg zzqp$zzg) {
            zzbg.e("Saved resource is corrupted: " + this.zzfr(s));
            goto Label_0109;
        }
        try {
            final InputStream openRawResource = this.mContext.getResources().openRawResource((int)n);
            if (openRawResource == null) {
                goto Label_0267;
            }
            final Object zzt2 = zzql.zzt(this.zzm(openRawResource));
            if (zzt2 != null) {
                zzbg.v("Default resource loaded: " + this.mContext.getResources().getResourceEntryName((int)n));
                zzqq.zza(Status.zzaaD, zzt2, zzqr.zzaUg, 0L);
                return;
            }
            goto Label_0267;
        }
        catch (Resources$NotFoundException ex2) {
            zzbg.e("Default resource not found. ID: " + n);
        }
        catch (zzqp$zzg zzqp$zzg2) {
            zzbg.e("Default resource resource is corrupted: " + n);
            goto Label_0267;
        }
    }
    
    public void zze(final String s, final byte[] array) {
        this.zzaRv.execute(new zzqr$2(this, s, array));
    }
    
    void zzf(final String ex, final byte[] array) {
        final File zzfq = this.zzfq((String)ex);
        FileOutputStream fileOutputStream;
        try {
            final FileOutputStream fileOutputStream2;
            fileOutputStream = (fileOutputStream2 = new FileOutputStream(zzfq));
            final byte[] array2 = array;
            fileOutputStream2.write(array2);
            final FileOutputStream fileOutputStream3 = fileOutputStream;
            fileOutputStream3.close();
            final StringBuilder sb = new StringBuilder();
            final String s = "Resource ";
            final StringBuilder sb2 = sb.append(s);
            final FileNotFoundException ex2 = ex;
            final StringBuilder sb3 = sb2.append((String)ex2);
            final String s2 = " saved on Disk.";
            final StringBuilder sb4 = sb3.append(s2);
            final String s3 = sb4.toString();
            zzbg.v(s3);
            return;
        }
        catch (FileNotFoundException ex) {
            zzbg.e("Error opening resource file for writing");
            return;
        }
        try {
            final FileOutputStream fileOutputStream2 = fileOutputStream;
            final byte[] array2 = array;
            fileOutputStream2.write(array2);
            try {
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                fileOutputStream3.close();
                final StringBuilder sb = new StringBuilder();
                final String s = "Resource ";
                final StringBuilder sb2 = sb.append(s);
                final FileNotFoundException ex2 = ex;
                final StringBuilder sb3 = sb2.append((String)ex2);
                final String s2 = " saved on Disk.";
                final StringBuilder sb4 = sb3.append(s2);
                final String s3 = sb4.toString();
                zzbg.v(s3);
            }
            catch (IOException ex) {
                zzbg.e("Error closing stream for writing resource to disk");
            }
        }
        catch (IOException ex3) {
            zzbg.e("Error writing resource to disk. Removing resource from disk");
            zzfq.delete();
            try {
                fileOutputStream.close();
                zzbg.v("Resource " + (String)ex + " saved on Disk.");
            }
            catch (IOException ex4) {
                zzbg.e("Error closing stream for writing resource to disk");
            }
        }
        finally {
            try {
                fileOutputStream.close();
                zzbg.v("Resource " + (String)ex + " saved on Disk.");
            }
            catch (IOException ex5) {
                zzbg.e("Error closing stream for writing resource to disk");
            }
        }
    }
    
    public long zzfp(final String s) {
        final File zzfq = this.zzfq(s);
        if (zzfq.exists()) {
            return zzfq.lastModified();
        }
        return 0L;
    }
    
    File zzfq(final String s) {
        return new File(this.mContext.getDir("google_tagmanager", 0), this.zzfr(s));
    }
}
