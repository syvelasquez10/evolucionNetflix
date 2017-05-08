// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.Response$ErrorListener;

public interface ImageLoader$ImageListener extends Response$ErrorListener
{
    void onResponse(final ImageLoader$ImageContainer p0, final ImageLoader$Type p1);
}
