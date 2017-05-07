// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.internal.fq;
import android.content.Intent;
import android.util.Log;
import android.app.IntentService;

public abstract class DriveEventService extends IntentService
{
    private final String mName;
    
    protected DriveEventService(final String mName) {
        super(mName);
        this.mName = mName;
    }
    
    public void a(final ConflictEvent conflictEvent) {
        Log.w("DriveEventService", "Unhandled ConflictEvent: " + conflictEvent);
    }
    
    public void onChangeEvent(final ChangeEvent changeEvent) {
        Log.w("DriveEventService", "Unhandled ChangeEvent: " + changeEvent);
    }
    
    protected final void onHandleIntent(final Intent intent) {
        while (true) {
            intent.setExtrasClassLoader(this.getClassLoader());
            final DriveEvent driveEvent = (DriveEvent)intent.getParcelableExtra("event");
            while (true) {
                Label_0211: {
                    try {
                        switch (driveEvent.getType()) {
                            case 1: {
                                fq.a(driveEvent instanceof ChangeEvent, (Object)("Unexpected event type: " + driveEvent));
                                this.onChangeEvent((ChangeEvent)driveEvent);
                                return;
                            }
                            case 2: {
                                goto Label_0146;
                                goto Label_0146;
                            }
                            default: {
                                break Label_0211;
                            }
                        }
                        Log.w(this.mName, "Unrecognized event: " + intent);
                        return;
                    }
                    catch (ClassCastException ex) {
                        Log.wtf(this.mName, "Service does not implement listener for type:" + driveEvent.getType(), (Throwable)ex);
                        return;
                    }
                    catch (Exception ex2) {
                        Log.w(this.mName, "Error handling event: " + intent, (Throwable)ex2);
                        return;
                    }
                }
                continue;
            }
        }
    }
}
