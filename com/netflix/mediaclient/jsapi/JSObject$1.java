// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.jsapi;

class JSObject$1 implements Runnable
{
    final /* synthetic */ JSObject this$0;
    final /* synthetic */ String val$javascriptToInject;
    
    JSObject$1(final JSObject this$0, final String val$javascriptToInject) {
        this.this$0 = this$0;
        this.val$javascriptToInject = val$javascriptToInject;
    }
    
    @Override
    public void run() {
        this.this$0.webview.loadUrl(this.val$javascriptToInject);
    }
}
