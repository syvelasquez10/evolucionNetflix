// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class WebViewAppLinkResolver$2$1 extends WebViewClient
{
    private boolean loaded;
    final /* synthetic */ WebViewAppLinkResolver$2 this$1;
    
    WebViewAppLinkResolver$2$1(final WebViewAppLinkResolver$2 this$1) {
        this.this$1 = this$1;
        this.loaded = false;
    }
    
    private void runJavaScript(final WebView webView) {
        if (!this.loaded) {
            this.loaded = true;
            webView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
        }
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        super.onLoadResource(webView, s);
        this.runJavaScript(webView);
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        this.runJavaScript(webView);
    }
}
