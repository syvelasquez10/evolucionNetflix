// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common.network;

import java.util.Iterator;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OkHttpCallUtil
{
    public static void cancelTag(final OkHttpClient okHttpClient, final Object o) {
        for (final Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (o.equals(call.request().tag())) {
                call.cancel();
                return;
            }
        }
        for (final Call call2 : okHttpClient.dispatcher().runningCalls()) {
            if (o.equals(call2.request().tag())) {
                call2.cancel();
            }
        }
    }
}
