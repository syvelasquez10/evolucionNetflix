// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Collections;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Iterator;
import java.net.URLEncoder;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.Uri;
import android.text.TextUtils;
import java.net.HttpURLConnection;

class Request$1 implements Runnable
{
    final /* synthetic */ Request this$0;
    final /* synthetic */ String val$tag;
    final /* synthetic */ long val$threadId;
    
    Request$1(final Request this$0, final String val$tag, final long val$threadId) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
        this.val$threadId = val$threadId;
    }
    
    @Override
    public void run() {
        this.this$0.mEventLog.add(this.val$tag, this.val$threadId);
        this.this$0.mEventLog.finish(this.toString());
    }
}
