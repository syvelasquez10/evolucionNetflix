// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

abstract class UserAgent$FetchTask<T> implements Runnable
{
    private final UserAgent$UserAgentCallback callback;
    
    public UserAgent$FetchTask(final UserAgent$UserAgentCallback callback) {
        this.callback = callback;
    }
    
    protected UserAgent$UserAgentCallback getCallback() {
        return this.callback;
    }
}
