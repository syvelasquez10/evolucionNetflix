// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent$Listener;
import android.os.Message;
import android.util.Pair;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.c;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;

class y$a extends Handler
{
    private final Context mContext;
    
    private y$a(final Looper looper, final Context mContext) {
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
                        if (c instanceof DriveEvent$Listener) {
                            ((DriveEvent$Listener<ChangeEvent>)c).onEvent((ChangeEvent)driveEvent);
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
