// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler;
import com.google.android.gms.common.api.Status;
import android.os.Looper;

class n implements ContainerHolder
{
    private final Looper AS;
    private Container WR;
    private Container WS;
    private b WT;
    private a WU;
    private boolean WV;
    private TagManager WW;
    private Status wJ;
    
    public n(final Status wj) {
        this.wJ = wj;
        this.AS = null;
    }
    
    public n(final TagManager ww, Looper mainLooper, final Container wr, final a wu) {
        this.WW = ww;
        if (mainLooper == null) {
            mainLooper = Looper.getMainLooper();
        }
        this.AS = mainLooper;
        this.WR = wr;
        this.WU = wu;
        this.wJ = Status.Bv;
        ww.a(this);
    }
    
    private void kf() {
        if (this.WT != null) {
            this.WT.bs(this.WS.kc());
        }
    }
    
    public void a(final Container container) {
        while (true) {
            Label_0031: {
                synchronized (this) {
                    if (!this.WV) {
                        if (container != null) {
                            break Label_0031;
                        }
                        bh.w("Unexpected null container.");
                    }
                    return;
                }
            }
            final Container ws;
            this.WS = ws;
            this.kf();
        }
    }
    
    public void bp(final String s) {
        synchronized (this) {
            if (!this.WV) {
                this.WR.bp(s);
            }
        }
    }
    
    void br(final String s) {
        if (this.WV) {
            bh.w("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return;
        }
        this.WU.br(s);
    }
    
    @Override
    public Container getContainer() {
        Container wr = null;
        synchronized (this) {
            if (this.WV) {
                bh.w("ContainerHolder is released.");
            }
            else {
                if (this.WS != null) {
                    this.WR = this.WS;
                    this.WS = null;
                }
                wr = this.WR;
            }
            return wr;
        }
    }
    
    String getContainerId() {
        if (this.WV) {
            bh.w("getContainerId called on a released ContainerHolder.");
            return "";
        }
        return this.WR.getContainerId();
    }
    
    @Override
    public Status getStatus() {
        return this.wJ;
    }
    
    String ke() {
        if (this.WV) {
            bh.w("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return "";
        }
        return this.WU.ke();
    }
    
    @Override
    public void refresh() {
        synchronized (this) {
            if (this.WV) {
                bh.w("Refreshing a released ContainerHolder.");
            }
            else {
                this.WU.kg();
            }
        }
    }
    
    @Override
    public void release() {
        synchronized (this) {
            if (this.WV) {
                bh.w("Releasing a released ContainerHolder.");
            }
            else {
                this.WV = true;
                this.WW.b(this);
                this.WR.release();
                this.WR = null;
                this.WS = null;
                this.WU = null;
                this.WT = null;
            }
        }
    }
    
    @Override
    public void setContainerAvailableListener(final ContainerAvailableListener containerAvailableListener) {
        while (true) {
            Label_0034: {
                synchronized (this) {
                    if (this.WV) {
                        bh.w("ContainerHolder is released.");
                    }
                    else {
                        if (containerAvailableListener != null) {
                            break Label_0034;
                        }
                        this.WT = null;
                    }
                    return;
                }
            }
            final ContainerAvailableListener containerAvailableListener2;
            this.WT = new b(containerAvailableListener2, this.AS);
            if (this.WS != null) {
                this.kf();
            }
        }
    }
    
    public interface a
    {
        void br(final String p0);
        
        String ke();
        
        void kg();
    }
    
    private class b extends Handler
    {
        private final ContainerAvailableListener WX;
        
        public b(final ContainerAvailableListener wx, final Looper looper) {
            super(looper);
            this.WX = wx;
        }
        
        public void bs(final String s) {
            this.sendMessage(this.obtainMessage(1, (Object)s));
        }
        
        protected void bt(final String s) {
            this.WX.onContainerAvailable(n.this, s);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    bh.w("Don't know how to handle this message.");
                }
                case 1: {
                    this.bt((String)message.obj);
                }
            }
        }
    }
}
