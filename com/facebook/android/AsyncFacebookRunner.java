// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.os.Bundle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import android.content.Context;

@Deprecated
public class AsyncFacebookRunner
{
    Facebook fb;
    
    public AsyncFacebookRunner(final Facebook fb) {
        this.fb = fb;
    }
    
    @Deprecated
    public void logout(final Context context, final RequestListener requestListener) {
        this.logout(context, requestListener, null);
    }
    
    @Deprecated
    public void logout(final Context context, final RequestListener requestListener, final Object o) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final String logoutImpl = AsyncFacebookRunner.this.fb.logoutImpl(context);
                    if (logoutImpl.length() == 0 || logoutImpl.equals("false")) {
                        requestListener.onFacebookError(new FacebookError("auth.expireSession failed"), o);
                        return;
                    }
                    requestListener.onComplete(logoutImpl, o);
                }
                catch (FileNotFoundException ex) {
                    requestListener.onFileNotFoundException(ex, o);
                }
                catch (MalformedURLException ex2) {
                    requestListener.onMalformedURLException(ex2, o);
                }
                catch (IOException ex3) {
                    requestListener.onIOException(ex3, o);
                }
            }
        }.start();
    }
    
    @Deprecated
    public void request(final Bundle bundle, final RequestListener requestListener) {
        this.request(null, bundle, "GET", requestListener, null);
    }
    
    @Deprecated
    public void request(final Bundle bundle, final RequestListener requestListener, final Object o) {
        this.request(null, bundle, "GET", requestListener, o);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final RequestListener requestListener) {
        this.request(s, bundle, "GET", requestListener, null);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final RequestListener requestListener, final Object o) {
        this.request(s, bundle, "GET", requestListener, o);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final String s2, final RequestListener requestListener, final Object o) {
        new Thread() {
            @Override
            public void run() {
                try {
                    requestListener.onComplete(AsyncFacebookRunner.this.fb.requestImpl(s, bundle, s2), o);
                }
                catch (FileNotFoundException ex) {
                    requestListener.onFileNotFoundException(ex, o);
                }
                catch (MalformedURLException ex2) {
                    requestListener.onMalformedURLException(ex2, o);
                }
                catch (IOException ex3) {
                    requestListener.onIOException(ex3, o);
                }
            }
        }.start();
    }
    
    @Deprecated
    public void request(final String s, final RequestListener requestListener) {
        this.request(s, new Bundle(), "GET", requestListener, null);
    }
    
    @Deprecated
    public void request(final String s, final RequestListener requestListener, final Object o) {
        this.request(s, new Bundle(), "GET", requestListener, o);
    }
    
    @Deprecated
    public interface RequestListener
    {
        void onComplete(final String p0, final Object p1);
        
        void onFacebookError(final FacebookError p0, final Object p1);
        
        void onFileNotFoundException(final FileNotFoundException p0, final Object p1);
        
        void onIOException(final IOException p0, final Object p1);
        
        void onMalformedURLException(final MalformedURLException p0, final Object p1);
    }
}
