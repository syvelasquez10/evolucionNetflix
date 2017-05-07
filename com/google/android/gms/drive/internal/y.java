// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.ChangeEvent;
import android.os.Message;
import android.util.Pair;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.content.Context;
import android.os.Looper;
import java.util.List;
import com.google.android.gms.drive.events.c;

public class y extends ad.a
{
    private final int NS;
    private final c OW;
    private final a OX;
    private final List<Integer> OY;
    
    public y(final Looper looper, final Context context, final int ns, final c ow) {
        this.OY = new ArrayList<Integer>();
        this.NS = ns;
        this.OW = ow;
        this.OX = new a(looper, context);
    }
    
    public void bq(final int n) {
        this.OY.add(n);
    }
    
    public boolean br(final int n) {
        return this.OY.contains(n);
    }
    
    public void c(final OnEventResponse onEventResponse) throws RemoteException {
        final DriveEvent ih = onEventResponse.ih();
        n.I(this.NS == ih.getType());
        n.I(this.OY.contains(ih.getType()));
        this.OX.a(this.OW, ih);
    }
    
    private static class a extends Handler
    {
        private final Context mContext;
        
        private a(final Looper looper, final Context mContext) {
            super(looper);
            this.mContext = mContext;
        }
        
        public void a(final c c, final DriveEvent driveEvent) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)c, (Object)driveEvent)));
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    v.e(this.mContext, "EventCallback", "Don't know how to handle this event");
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    final c c = (c)pair.first;
                    final DriveEvent driveEvent = (DriveEvent)pair.second;
                    switch (driveEvent.getType()) {
                        default: {
                            v.p("EventCallback", "Unexpected event: " + driveEvent);
                            return;
                        }
                        case 1: {
                            if (c instanceof DriveEvent.Listener) {
                                ((DriveEvent.Listener<ChangeEvent>)c).onEvent((ChangeEvent)driveEvent);
                                return;
                            }
                            ((ChangeListener)c).onChange((ChangeEvent)driveEvent);
                            return;
                        }
                        case 2: {
                            ((CompletionListener)c).onCompletion((CompletionEvent)driveEvent);
                            return;
                        }
                    }
                    break;
                }
            }
        }
    }
}
