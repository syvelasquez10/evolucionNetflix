// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class SignupActivity$signUpWebChromeClient extends WebChromeClient
{
    final /* synthetic */ SignupActivity this$0;
    
    private SignupActivity$signUpWebChromeClient(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        Log.i("JavaScript", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        return true;
    }
}
