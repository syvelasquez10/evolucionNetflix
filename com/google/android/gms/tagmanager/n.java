// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.Status;

class n implements ContainerHolder
{
    private Status CM;
    private final Looper IB;
    private boolean NM;
    private Container anZ;
    private Container aoa;
    private b aob;
    private a aoc;
    private TagManager aod;
    
    public n(final Status cm) {
        this.CM = cm;
        this.IB = null;
    }
    
    public n(final TagManager aod, Looper mainLooper, final Container anZ, final a aoc) {
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
    public void setContainerAvailableListener(final ContainerAvailableListener containerAvailableListener) {
        while (true) {
            Label_0034: {
                synchronized (this) {
                    if (this.NM) {
                        bh.T("ContainerHolder is released.");
                    }
                    else {
                        if (containerAvailableListener != null) {
                            break Label_0034;
                        }
                        this.aob = null;
                    }
                    return;
                }
            }
            final ContainerAvailableListener containerAvailableListener2;
            this.aob = new b(containerAvailableListener2, this.IB);
            if (this.aoa != null) {
                this.nT();
            }
        }
    }
    
    public interface a
    {
        void co(final String p0);
        
        String nS();
        
        void nU();
    }
    
    private class b extends Handler
    {
        private final ContainerAvailableListener aoe;
        
        public b(final ContainerAvailableListener aoe, final Looper looper) {
            super(looper);
            this.aoe = aoe;
        }
        
        public void cp(final String s) {
            this.sendMessage(this.obtainMessage(1, (Object)s));
        }
        
        protected void cq(final String s) {
            this.aoe.onContainerAvailable(n.this, s);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    bh.T("Don't know how to handle this message.");
                }
                case 1: {
                    this.cq((String)message.obj);
                }
            }
        }
    }
}
