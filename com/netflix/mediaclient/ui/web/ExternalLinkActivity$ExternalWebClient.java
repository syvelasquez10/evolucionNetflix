// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.web;

import com.netflix.mediaclient.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class ExternalLinkActivity$ExternalWebClient extends WebChromeClient
{
    final /* synthetic */ ExternalLinkActivity this$0;
    
    private ExternalLinkActivity$ExternalWebClient(final ExternalLinkActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        if (Log.isLoggable()) {
            Log.d("ExternalLinkActivity_js", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        }
        return true;
    }
}
