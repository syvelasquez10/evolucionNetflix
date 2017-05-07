// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;

class n implements ContainerHolder
{
    private Status CM;
    private final Looper IB;
    private boolean NM;
    private Container anZ;
    private Container aoa;
    private n$b aob;
    private n$a aoc;
    private TagManager aod;
    
    public n(final Status cm) {
        this.CM = cm;
        this.IB = null;
    }
    
    public n(final TagManager aod, Looper mainLooper, final Container anZ, final n$a aoc) {
        this.aod = aod;
        if (mainLooper == null) {
            mainLooper = Looper.getMainLooper();
        }
        this.IB = mainLooper;
        this.anZ = anZ;
        this.aoc = aoc;
        this.CM = Status.Jo;
        aod.a(this);
    }
    
    private void nT() {
        if (this.aob != null) {
            this.aob.cp(this.aoa.nQ());
        }
    }
    
    public void a(final Container container) {
        while (true) {
            Label_0031: {
                synchronized (this) {
                    if (!this.NM) {
                        if (container != null) {
                            break Label_0031;
                        }
                        bh.T("Unexpected null container.");
                    }
                    return;
                }
            }
            final Container aoa;
            this.aoa = aoa;
            this.nT();
        }
    }
    
    public void cm(final String s) {
        synchronized (this) {
            if (!this.NM) {
                this.anZ.cm(s);
            }
        }
    }
    
    void co(final String s) {
        if (this.NM) {
            bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return;
        }
        this.aoc.co(s);
    }
    
    @Override
    public Container getContainer() {
        Container anZ = null;
        synchronized (this) {
            if (this.NM) {
                bh.T("ContainerHolder is released.");
            }
            else {
                if (this.aoa != null) {
                    this.anZ = this.aoa;
                    this.aoa = null;
                }
                anZ = this.anZ;
            }
            return anZ;
        }
    }
    
    String getContainerId() {
        if (this.NM) {
            bh.T("getContainerId called on a released ContainerHolder.");
            return "";
        }
        return this.anZ.getContainerId();
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    String nS() {
        if (this.NM) {
            bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return "";
        }
        return this.aoc.nS();
    }
    
    @Override
    public void refresh() {
        synchronized (this) {
            if (this.NM) {
                bh.T("Refreshing a released ContainerHolder.");
            }
            else {
                this.aoc.nU();
            }
        }
    }
    
    @Override
    public void release() {
        synchronized (this) {
            if (this.NM) {
                bh.T("Releasing a released ContainerHolder.");
            }
            else {
                this.NM = true;
                this.aod.b(this);
                this.anZ.release();
                this.anZ = null;
                this.aoa = null;
                this.aoc = null;
                this.aob = null;
            }
        }
    }
    
    @Override
    public void setContainerAvailableListener(final ContainerHolder$ContainerAvailableListener containerHolder$ContainerAvailableListener) {
        while (true) {
            Label_0034: {
                synchronized (this) {
                    if (this.NM) {
                        bh.T("ContainerHolder is released.");
                    }
                    else {
                        if (containerHolder$ContainerAvailableListener != null) {
                            break Label_0034;
                        }
                        this.aob = null;
                    }
                    return;
                }
            }
            final ContainerHolder$ContainerAvailableListener containerHolder$ContainerAvailableListener2;
            this.aob = new n$b(this, containerHolder$ContainerAvailableListener2, this.IB);
            if (this.aoa != null) {
                this.nT();
            }
        }
    }
}
