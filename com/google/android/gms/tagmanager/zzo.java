// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;

class zzo implements ContainerHolder
{
    private Status zzQA;
    private final Looper zzYV;
    private Container zzaPa;
    private Container zzaPb;
    private zzo$zzb zzaPc;
    private zzo$zza zzaPd;
    private TagManager zzaPe;
    private boolean zzahz;
    
    public zzo(final Status zzQA) {
        this.zzQA = zzQA;
        this.zzYV = null;
    }
    
    public zzo(final TagManager zzaPe, Looper mainLooper, final Container zzaPa, final zzo$zza zzaPd) {
        this.zzaPe = zzaPe;
        if (mainLooper == null) {
            mainLooper = Looper.getMainLooper();
        }
        this.zzYV = mainLooper;
        this.zzaPa = zzaPa;
        this.zzaPd = zzaPd;
        this.zzQA = Status.zzaaD;
        zzaPe.zza(this);
    }
    
    @Override
    public Container getContainer() {
        Container zzaPa = null;
        synchronized (this) {
            if (this.zzahz) {
                zzbg.e("ContainerHolder is released.");
            }
            else {
                if (this.zzaPb != null) {
                    this.zzaPa = this.zzaPb;
                    this.zzaPb = null;
                }
                zzaPa = this.zzaPa;
            }
            return zzaPa;
        }
    }
    
    @Override
    public Status getStatus() {
        return this.zzQA;
    }
    
    @Override
    public void release() {
        synchronized (this) {
            if (this.zzahz) {
                zzbg.e("Releasing a released ContainerHolder.");
            }
            else {
                this.zzahz = true;
                this.zzaPe.zzb(this);
                this.zzaPa.release();
                this.zzaPa = null;
                this.zzaPb = null;
                this.zzaPd = null;
                this.zzaPc = null;
            }
        }
    }
    
    public void zzew(final String s) {
        synchronized (this) {
            if (!this.zzahz) {
                this.zzaPa.zzew(s);
            }
        }
    }
}
