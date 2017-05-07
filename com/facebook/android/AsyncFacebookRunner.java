// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.os.Bundle;
import android.content.Context;

@Deprecated
public class AsyncFacebookRunner
{
    Facebook fb;
    
    public AsyncFacebookRunner(final Facebook fb) {
        this.fb = fb;
    }
    
    @Deprecated
    public void logout(final Context context, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener) {
        this.logout(context, asyncFacebookRunner$RequestListener, null);
    }
    
    @Deprecated
    public void logout(final Context context, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener, final Object o) {
        new AsyncFacebookRunner$1(this, context, asyncFacebookRunner$RequestListener, o).start();
    }
    
    @Deprecated
    public void request(final Bundle bundle, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener) {
        this.request(null, bundle, "GET", asyncFacebookRunner$RequestListener, null);
    }
    
    @Deprecated
    public void request(final Bundle bundle, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener, final Object o) {
        this.request(null, bundle, "GET", asyncFacebookRunner$RequestListener, o);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener) {
        this.request(s, bundle, "GET", asyncFacebookRunner$RequestListener, null);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener, final Object o) {
        this.request(s, bundle, "GET", asyncFacebookRunner$RequestListener, o);
    }
    
    @Deprecated
    public void request(final String s, final Bundle bundle, final String s2, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener, final Object o) {
        new AsyncFacebookRunner$2(this, s, bundle, s2, asyncFacebookRunner$RequestListener, o).start();
    }
    
    @Deprecated
    public void request(final String s, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener) {
        this.request(s, new Bundle(), "GET", asyncFacebookRunner$RequestListener, null);
    }
    
    @Deprecated
    public void request(final String s, final AsyncFacebookRunner$RequestListener asyncFacebookRunner$RequestListener, final Object o) {
        this.request(s, new Bundle(), "GET", asyncFacebookRunner$RequestListener, o);
    }
}
