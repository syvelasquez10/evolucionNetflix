// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.webkit.JsPromptResult;

public class PromptResultForJavaScript implements PromptResult
{
    private JsPromptResult result;
    
    public PromptResultForJavaScript(final JsPromptResult result) {
        if (result == null) {
            throw new IllegalArgumentException("JS result cannot be null");
        }
        this.result = result;
    }
    
    @Override
    public void cancel() {
        this.result.cancel();
    }
    
    @Override
    public void confirm(final String s) {
        this.result.confirm(s);
    }
}
