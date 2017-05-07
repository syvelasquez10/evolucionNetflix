// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.webkit.JavascriptInterface;
import org.json.JSONException;
import org.json.JSONArray;

class WebViewAppLinkResolver$2$2
{
    final /* synthetic */ WebViewAppLinkResolver$2 this$1;
    final /* synthetic */ Task$TaskCompletionSource val$tcs;
    
    WebViewAppLinkResolver$2$2(final WebViewAppLinkResolver$2 this$1, final Task$TaskCompletionSource val$tcs) {
        this.this$1 = this$1;
        this.val$tcs = val$tcs;
    }
    
    @JavascriptInterface
    public void setValue(final String s) {
        try {
            this.val$tcs.trySetResult(new JSONArray(s));
        }
        catch (JSONException ex) {
            this.val$tcs.trySetError((Exception)ex);
        }
    }
}
