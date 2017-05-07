// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;

class BackgroundTask$NamedAsyncTask extends AsyncTaskCompat<Runnable, Void, Void>
{
    @Override
    protected Void doInBackground(final Runnable... array) {
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final Runnable runnable = array[i];
                Thread.currentThread().setName("BackgroundTask-" + runnable.getClass().getSimpleName());
                runnable.run();
            }
        }
        catch (Exception ex) {
            Log.e("BackgroundTask", "Failed to execute!", ex);
        }
        return null;
    }
}
