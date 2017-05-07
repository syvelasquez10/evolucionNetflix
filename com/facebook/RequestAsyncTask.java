// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Handler;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.net.HttpURLConnection;
import java.lang.reflect.Method;
import java.util.List;
import android.os.AsyncTask;

public class RequestAsyncTask extends AsyncTask<Void, Void, List<Response>>
{
    private static final String TAG;
    private static Method executeOnExecutorMethod;
    private final HttpURLConnection connection;
    private Exception exception;
    private final RequestBatch requests;
    
    static {
        TAG = RequestAsyncTask.class.getCanonicalName();
        final Method[] methods = AsyncTask.class.getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method executeOnExecutorMethod = methods[i];
            if ("executeOnExecutor".equals(executeOnExecutorMethod.getName())) {
                final Class<?>[] parameterTypes = executeOnExecutorMethod.getParameterTypes();
                if (parameterTypes.length == 2 && parameterTypes[0] == Executor.class && parameterTypes[1].isArray()) {
                    RequestAsyncTask.executeOnExecutorMethod = executeOnExecutorMethod;
                    break;
                }
            }
        }
    }
    
    public RequestAsyncTask(final RequestBatch requestBatch) {
        this(null, requestBatch);
    }
    
    public RequestAsyncTask(final HttpURLConnection connection, final RequestBatch requests) {
        this.requests = requests;
        this.connection = connection;
    }
    
    protected List<Response> doInBackground(final Void... array) {
        try {
            if (this.connection == null) {
                return this.requests.executeAndWait();
            }
            return Request.executeConnectionAndWait(this.connection, this.requests);
        }
        catch (Exception exception) {
            this.exception = exception;
            return null;
        }
    }
    
    RequestAsyncTask executeOnSettingsExecutor() {
        Label_0030: {
            if (RequestAsyncTask.executeOnExecutorMethod == null) {
                break Label_0030;
            }
            try {
                RequestAsyncTask.executeOnExecutorMethod.invoke(this, Settings.getExecutor(), null);
                return this;
                this.execute((Object[])new Void[0]);
                return this;
            }
            catch (IllegalAccessException ex) {
                return this;
            }
            catch (InvocationTargetException ex2) {
                return this;
            }
        }
    }
    
    protected void onPostExecute(final List<Response> list) {
        super.onPostExecute((Object)list);
        if (this.exception != null) {
            Log.d(RequestAsyncTask.TAG, String.format("onPostExecute: exception encountered during request: %s", this.exception.getMessage()));
        }
    }
    
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.requests.getCallbackHandler() == null) {
            this.requests.setCallbackHandler(new Handler());
        }
    }
    
    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
    }
}
