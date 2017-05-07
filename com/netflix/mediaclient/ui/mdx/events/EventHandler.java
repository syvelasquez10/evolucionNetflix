// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;

public abstract class EventHandler implements MdxEventHandler
{
    protected static final String TAG = "mdxui";
    protected final String mAction;
    
    public EventHandler(final String mAction) {
        this.mAction = mAction;
        if (Log.isLoggable()) {
            Log.d("mdxui", "MDX event handler for " + mAction);
        }
    }
    
    @Override
    public String getAction() {
        return this.mAction;
    }
}
