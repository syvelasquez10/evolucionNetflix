// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.List;

public class BaseProducerContext implements ProducerContext
{
    private final List<ProducerContextCallbacks> mCallbacks;
    private final Object mCallerContext;
    private final String mId;
    private final ImageRequest mImageRequest;
    private boolean mIsCancelled;
    private boolean mIsIntermediateResultExpected;
    private boolean mIsPrefetch;
    private final ImageRequest$RequestLevel mLowestPermittedRequestLevel;
    private Priority mPriority;
    private final ProducerListener mProducerListener;
    
    public BaseProducerContext(final ImageRequest mImageRequest, final String mId, final ProducerListener mProducerListener, final Object mCallerContext, final ImageRequest$RequestLevel mLowestPermittedRequestLevel, final boolean mIsPrefetch, final boolean mIsIntermediateResultExpected, final Priority mPriority) {
        this.mImageRequest = mImageRequest;
        this.mId = mId;
        this.mProducerListener = mProducerListener;
        this.mCallerContext = mCallerContext;
        this.mLowestPermittedRequestLevel = mLowestPermittedRequestLevel;
        this.mIsPrefetch = mIsPrefetch;
        this.mPriority = mPriority;
        this.mIsIntermediateResultExpected = mIsIntermediateResultExpected;
        this.mIsCancelled = false;
        this.mCallbacks = new ArrayList<ProducerContextCallbacks>();
    }
    
    public static void callOnCancellationRequested(final List<ProducerContextCallbacks> list) {
        if (list != null) {
            final Iterator<ProducerContextCallbacks> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().onCancellationRequested();
            }
        }
    }
    
    public static void callOnIsIntermediateResultExpectedChanged(final List<ProducerContextCallbacks> list) {
        if (list != null) {
            final Iterator<ProducerContextCallbacks> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().onIsIntermediateResultExpectedChanged();
            }
        }
    }
    
    public static void callOnIsPrefetchChanged(final List<ProducerContextCallbacks> list) {
        if (list != null) {
            final Iterator<ProducerContextCallbacks> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().onIsPrefetchChanged();
            }
        }
    }
    
    public static void callOnPriorityChanged(final List<ProducerContextCallbacks> list) {
        if (list != null) {
            final Iterator<ProducerContextCallbacks> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().onPriorityChanged();
            }
        }
    }
    
    @Override
    public void addCallbacks(final ProducerContextCallbacks producerContextCallbacks) {
        boolean b = false;
        synchronized (this) {
            this.mCallbacks.add(producerContextCallbacks);
            if (this.mIsCancelled) {
                b = true;
            }
            // monitorexit(this)
            if (b) {
                producerContextCallbacks.onCancellationRequested();
            }
        }
    }
    
    public void cancel() {
        callOnCancellationRequested(this.cancelNoCallbacks());
    }
    
    public List<ProducerContextCallbacks> cancelNoCallbacks() {
        synchronized (this) {
            List<ProducerContextCallbacks> list;
            if (this.mIsCancelled) {
                list = null;
            }
            else {
                this.mIsCancelled = true;
                list = new ArrayList<ProducerContextCallbacks>(this.mCallbacks);
            }
            return list;
        }
    }
    
    @Override
    public Object getCallerContext() {
        return this.mCallerContext;
    }
    
    @Override
    public String getId() {
        return this.mId;
    }
    
    @Override
    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }
    
    @Override
    public ProducerListener getListener() {
        return this.mProducerListener;
    }
    
    @Override
    public ImageRequest$RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }
    
    @Override
    public Priority getPriority() {
        synchronized (this) {
            return this.mPriority;
        }
    }
    
    @Override
    public boolean isIntermediateResultExpected() {
        synchronized (this) {
            return this.mIsIntermediateResultExpected;
        }
    }
    
    @Override
    public boolean isPrefetch() {
        synchronized (this) {
            return this.mIsPrefetch;
        }
    }
    
    public List<ProducerContextCallbacks> setIsIntermediateResultExpectedNoCallbacks(final boolean mIsIntermediateResultExpected) {
        synchronized (this) {
            List<ProducerContextCallbacks> list;
            if (mIsIntermediateResultExpected == this.mIsIntermediateResultExpected) {
                list = null;
            }
            else {
                this.mIsIntermediateResultExpected = mIsIntermediateResultExpected;
                list = new ArrayList<ProducerContextCallbacks>(this.mCallbacks);
            }
            return list;
        }
    }
    
    public List<ProducerContextCallbacks> setIsPrefetchNoCallbacks(final boolean mIsPrefetch) {
        synchronized (this) {
            List<ProducerContextCallbacks> list;
            if (mIsPrefetch == this.mIsPrefetch) {
                list = null;
            }
            else {
                this.mIsPrefetch = mIsPrefetch;
                list = new ArrayList<ProducerContextCallbacks>(this.mCallbacks);
            }
            return list;
        }
    }
    
    public List<ProducerContextCallbacks> setPriorityNoCallbacks(final Priority mPriority) {
        synchronized (this) {
            List<ProducerContextCallbacks> list;
            if (mPriority == this.mPriority) {
                list = null;
            }
            else {
                this.mPriority = mPriority;
                list = new ArrayList<ProducerContextCallbacks>(this.mCallbacks);
            }
            return list;
        }
    }
}
