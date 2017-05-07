// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Handler;
import com.facebook.RequestBatch;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookException;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.model.GraphObjectList;
import java.util.Collection;
import com.facebook.Response;
import android.content.Context;
import com.facebook.Request;
import android.support.v4.content.Loader;
import com.facebook.model.GraphObject;

class GraphObjectPagingLoader<T extends GraphObject> extends Loader<SimpleGraphObjectCursor<T>>
{
    private boolean appendResults;
    private Request currentRequest;
    private SimpleGraphObjectCursor<T> cursor;
    private final Class<T> graphObjectClass;
    private boolean loading;
    private Request nextRequest;
    private OnErrorListener onErrorListener;
    private Request originalRequest;
    private boolean skipRoundtripIfCached;
    
    public GraphObjectPagingLoader(final Context context, final Class<T> graphObjectClass) {
        super(context);
        this.appendResults = false;
        this.loading = false;
        this.graphObjectClass = graphObjectClass;
    }
    
    private void addResults(final Response response) {
        SimpleGraphObjectCursor<T> simpleGraphObjectCursor;
        if (this.cursor == null || !this.appendResults) {
            simpleGraphObjectCursor = new SimpleGraphObjectCursor<T>();
        }
        else {
            simpleGraphObjectCursor = new SimpleGraphObjectCursor<T>(this.cursor);
        }
        final PagedResults pagedResults = response.getGraphObjectAs(PagedResults.class);
        final boolean isFromCache = response.getIsFromCache();
        final GraphObjectList<T> castToList = pagedResults.getData().castToListOf(this.graphObjectClass);
        boolean b;
        if (castToList.size() > 0) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.nextRequest = response.getRequestForPagedResults(Response.PagingDirection.NEXT);
            simpleGraphObjectCursor.addGraphObjects(castToList, isFromCache);
            simpleGraphObjectCursor.setMoreObjectsAvailable(true);
        }
        if (!b) {
            simpleGraphObjectCursor.setMoreObjectsAvailable(false);
            simpleGraphObjectCursor.setFromCache(isFromCache);
            this.nextRequest = null;
        }
        if (!isFromCache) {
            this.skipRoundtripIfCached = false;
        }
        this.deliverResult(simpleGraphObjectCursor);
    }
    
    private CacheableRequestBatch putRequestIntoBatch(final Request request, final boolean b) {
        final boolean b2 = true;
        final CacheableRequestBatch cacheableRequestBatch = new CacheableRequestBatch(new Request[] { request });
        cacheableRequestBatch.setForceRoundTrip(!b && b2);
        return cacheableRequestBatch;
    }
    
    private void requestCompleted(final Response response) {
        if (response.getRequest() == this.currentRequest) {
            this.loading = false;
            this.currentRequest = null;
            final FacebookRequestError error = response.getError();
            FacebookException exception;
            if (error == null) {
                exception = null;
            }
            else {
                exception = error.getException();
            }
            FacebookException ex = exception;
            if (response.getGraphObject() == null && (ex = exception) == null) {
                ex = new FacebookException("GraphObjectPagingLoader received neither a result nor an error.");
            }
            if (ex == null) {
                this.addResults(response);
                return;
            }
            this.nextRequest = null;
            if (this.onErrorListener != null) {
                this.onErrorListener.onError(ex, this);
            }
        }
    }
    
    private void startLoading(final Request currentRequest, final boolean skipRoundtripIfCached, final long n) {
        this.skipRoundtripIfCached = skipRoundtripIfCached;
        this.appendResults = false;
        this.nextRequest = null;
        (this.currentRequest = currentRequest).setCallback((Request.Callback)new Request.Callback() {
            @Override
            public void onCompleted(final Response response) {
                GraphObjectPagingLoader.this.requestCompleted(response);
            }
        });
        this.loading = true;
        final Runnable runnable = new Runnable() {
            final /* synthetic */ RequestBatch val$batch = GraphObjectPagingLoader.this.putRequestIntoBatch(currentRequest, skipRoundtripIfCached);
            
            @Override
            public void run() {
                Request.executeBatchAsync(this.val$batch);
            }
        };
        if (n == 0L) {
            runnable.run();
            return;
        }
        new Handler().postDelayed((Runnable)runnable, n);
    }
    
    public void clearResults() {
        this.nextRequest = null;
        this.originalRequest = null;
        this.currentRequest = null;
        this.deliverResult(null);
    }
    
    @Override
    public void deliverResult(final SimpleGraphObjectCursor<T> cursor) {
        final SimpleGraphObjectCursor<T> cursor2 = this.cursor;
        this.cursor = cursor;
        if (this.isStarted()) {
            super.deliverResult(cursor);
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        }
    }
    
    public void followNextLink() {
        if (this.nextRequest != null) {
            this.appendResults = true;
            (this.currentRequest = this.nextRequest).setCallback((Request.Callback)new Request.Callback() {
                @Override
                public void onCompleted(final Response response) {
                    GraphObjectPagingLoader.this.requestCompleted(response);
                }
            });
            this.loading = true;
            Request.executeBatchAsync(this.putRequestIntoBatch(this.currentRequest, this.skipRoundtripIfCached));
        }
    }
    
    public SimpleGraphObjectCursor<T> getCursor() {
        return this.cursor;
    }
    
    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }
    
    public boolean isLoading() {
        return this.loading;
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (this.cursor != null) {
            this.deliverResult(this.cursor);
        }
    }
    
    public void refreshOriginalRequest(final long n) {
        if (this.originalRequest == null) {
            throw new FacebookException("refreshOriginalRequest may not be called until after startLoading has been called.");
        }
        this.startLoading(this.originalRequest, false, n);
    }
    
    public void setOnErrorListener(final OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
    
    public void startLoading(final Request originalRequest, final boolean b) {
        this.startLoading(this.originalRequest = originalRequest, b, 0L);
    }
    
    public interface OnErrorListener
    {
        void onError(final FacebookException p0, final GraphObjectPagingLoader<?> p1);
    }
    
    interface PagedResults extends GraphObject
    {
        GraphObjectList<GraphObject> getData();
    }
}
