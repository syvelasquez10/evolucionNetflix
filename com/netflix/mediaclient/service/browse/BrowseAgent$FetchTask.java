// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

abstract class BrowseAgent$FetchTask<T> implements Runnable
{
    private final BrowseAgentCallback callback;
    private final String category;
    private final int from;
    private final int to;
    
    public BrowseAgent$FetchTask(final String category, final int from, final int to, final BrowseAgentCallback callback) {
        this.category = category;
        this.from = from;
        this.to = to;
        this.callback = callback;
    }
    
    protected BrowseAgentCallback getCallback() {
        return this.callback;
    }
    
    protected String getCategory() {
        return this.category;
    }
    
    protected int getFromIndex() {
        return this.from;
    }
    
    protected int getToIndex() {
        return this.to;
    }
}
