// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.systrace.Systrace$EventScope;
import com.facebook.systrace.Systrace;
import java.util.HashMap;
import android.util.Pair;
import java.util.Map;
import com.facebook.imagepipeline.listener.RequestListener;

public class SystraceRequestListener implements RequestListener
{
    int mCurrentID;
    Map<String, Pair<Integer, String>> mProducerID;
    Map<String, Pair<Integer, String>> mRequestsID;
    
    public SystraceRequestListener() {
        this.mCurrentID = 0;
        this.mProducerID = new HashMap<String, Pair<Integer, String>>();
        this.mRequestsID = new HashMap<String, Pair<Integer, String>>();
    }
    
    @Override
    public void onProducerEvent(final String s, final String s2, final String s3) {
        if (!Systrace.isTracing(0L)) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("FRESCO_PRODUCER_EVENT_");
        sb.append(s.replace(':', '_'));
        sb.append("_");
        sb.append(s2.replace(':', '_'));
        sb.append("_");
        sb.append(s3.replace(':', '_'));
        Systrace.traceInstant(0L, sb.toString(), Systrace$EventScope.THREAD);
    }
    
    @Override
    public void onProducerFinishWithCancellation(final String s, final String s2, final Map<String, String> map) {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mProducerID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mProducerID.remove(s);
        }
    }
    
    @Override
    public void onProducerFinishWithFailure(final String s, final String s2, final Throwable t, final Map<String, String> map) {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mProducerID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mProducerID.remove(s);
        }
    }
    
    @Override
    public void onProducerFinishWithSuccess(final String s, final String s2, final Map<String, String> map) {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mProducerID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mProducerID.remove(s);
        }
    }
    
    @Override
    public void onProducerStart(final String s, final String s2) {
        if (!Systrace.isTracing(0L)) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("FRESCO_PRODUCER_");
        sb.append(s2.replace(':', '_'));
        final Pair create = Pair.create((Object)this.mCurrentID, (Object)sb.toString());
        Systrace.beginAsyncSection(0L, (String)create.second, this.mCurrentID);
        this.mProducerID.put(s, (Pair<Integer, String>)create);
        ++this.mCurrentID;
    }
    
    @Override
    public void onRequestCancellation(final String s) {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mRequestsID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mRequestsID.remove(s);
        }
    }
    
    @Override
    public void onRequestFailure(final ImageRequest imageRequest, final String s, final Throwable t, final boolean b) {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mRequestsID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mRequestsID.remove(s);
        }
    }
    
    @Override
    public void onRequestStart(final ImageRequest imageRequest, final Object o, final String s, final boolean b) {
        if (!Systrace.isTracing(0L)) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("FRESCO_REQUEST_");
        sb.append(imageRequest.getSourceUri().toString().replace(':', '_'));
        final Pair create = Pair.create((Object)this.mCurrentID, (Object)sb.toString());
        Systrace.beginAsyncSection(0L, (String)create.second, this.mCurrentID);
        this.mRequestsID.put(s, (Pair<Integer, String>)create);
        ++this.mCurrentID;
    }
    
    @Override
    public void onRequestSuccess(final ImageRequest imageRequest, final String s, final boolean b) {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(s)) {
            final Pair<Integer, String> pair = this.mRequestsID.get(s);
            Systrace.endAsyncSection(0L, (String)pair.second, (int)pair.first);
            this.mRequestsID.remove(s);
        }
    }
    
    @Override
    public boolean requiresExtraMap(final String s) {
        return false;
    }
}
