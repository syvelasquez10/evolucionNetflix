// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Message;
import android.util.Pair;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.fq;
import android.os.Looper;
import com.google.android.gms.drive.events.DriveEvent;

public class s<C extends DriveEvent> extends w.a
{
    private final int ES;
    private final a<C> FA;
    private final DriveEvent.Listener<C> Fz;
    
    public s(final Looper looper, final int es, final DriveEvent.Listener<C> fz) {
        this.ES = es;
        this.Fz = fz;
        this.FA = new a<C>(looper);
    }
    
    public void a(final OnEventResponse onEventResponse) throws RemoteException {
        fq.x(this.ES == onEventResponse.getEventType());
        switch (onEventResponse.getEventType()) {
            default: {
                Log.w("EventCallback", "Unexpected event type:" + onEventResponse.getEventType());
            }
            case 1: {
                this.FA.a(this.Fz, (C)onEventResponse.fL());
            }
            case 2: {
                this.FA.a(this.Fz, (C)onEventResponse.fM());
            }
        }
    }
    
    private static class a<E extends DriveEvent> extends Handler
    {
        private a(final Looper looper) {
            super(looper);
        }
        
        public void a(final DriveEvent.Listener<E> listener, final E e) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)listener, (Object)e)));
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("EventCallback", "Don't know how to handle this event");
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    ((DriveEvent.Listener)pair.first).onEvent((DriveEvent)pair.second);
                }
            }
        }
    }
}
