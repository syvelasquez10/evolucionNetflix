// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Callable;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URLConnection;
import java.util.Map;
import android.content.Context;
import android.net.Uri;
import org.json.JSONArray;

class WebViewAppLinkResolver$1 implements Continuation<JSONArray, AppLink>
{
    final /* synthetic */ WebViewAppLinkResolver this$0;
    final /* synthetic */ Uri val$url;
    
    WebViewAppLinkResolver$1(final WebViewAppLinkResolver this$0, final Uri val$url) {
        this.this$0 = this$0;
        this.val$url = val$url;
    }
    
    @Override
    public AppLink then(final Task<JSONArray> task) {
        return makeAppLinkFromAlData(parseAlData(task.getResult()), this.val$url);
    }
}
