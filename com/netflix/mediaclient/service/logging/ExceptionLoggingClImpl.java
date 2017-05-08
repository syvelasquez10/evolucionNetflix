// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.ExceptionClEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ExceptionLoggingCl;

final class ExceptionLoggingClImpl implements ExceptionLoggingCl
{
    private static final String TAG = "ExceptionLoggingClImpl";
    private DataContext mDataContext;
    private final EventHandler mEventHandler;
    
    ExceptionLoggingClImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleLogException(Intent instance) {
        final String stringExtra = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)Error.createInstance(stringExtra);
                this.reportExceptionToCL((Error)instance);
            }
            catch (JSONException ex) {
                Log.e("ExceptionLoggingClImpl", "Failed JSON", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    private void populateEvent(final Event event, final DataContext dataContext) {
        if (event != null) {
            event.setDataContext(dataContext);
        }
    }
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_EXCEPTION_CL".equals(action)) {
            this.handleLogException(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("ExceptionLoggingClImpl", "We do not support action %s", action);
        }
        return false;
    }
    
    @Override
    public void reportExceptionToCL(final Error error) {
        final ExceptionClEvent exceptionClEvent = new ExceptionClEvent(error);
        this.populateEvent(exceptionClEvent, this.mDataContext);
        this.mEventHandler.post(exceptionClEvent);
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
}
