// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.upstream.NetworkLock;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import android.os.Handler;
import com.google.android.exoplayer.upstream.Allocator;

public final class DefaultLoadControl implements LoadControl
{
    private final Allocator allocator;
    private int bufferState;
    private final Handler eventHandler;
    private final DefaultLoadControl$EventListener eventListener;
    private boolean fillingBuffers;
    private final float highBufferLoad;
    private final long highWatermarkUs;
    private final HashMap<Object, DefaultLoadControl$LoaderState> loaderStates;
    private final List<Object> loaders;
    private final float lowBufferLoad;
    private final long lowWatermarkUs;
    private long maxLoadStartPositionUs;
    private boolean streamingPrioritySet;
    private int targetBufferSize;
    
    public DefaultLoadControl(final Allocator allocator) {
        this(allocator, null, null);
    }
    
    public DefaultLoadControl(final Allocator allocator, final Handler handler, final DefaultLoadControl$EventListener defaultLoadControl$EventListener) {
        this(allocator, handler, defaultLoadControl$EventListener, 15000, 30000, 0.2f, 0.8f);
    }
    
    public DefaultLoadControl(final Allocator allocator, final Handler eventHandler, final DefaultLoadControl$EventListener eventListener, final int n, final int n2, final float lowBufferLoad, final float highBufferLoad) {
        this.allocator = allocator;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.loaders = new ArrayList<Object>();
        this.loaderStates = new HashMap<Object, DefaultLoadControl$LoaderState>();
        this.lowWatermarkUs = n * 1000L;
        this.highWatermarkUs = n2 * 1000L;
        this.lowBufferLoad = lowBufferLoad;
        this.highBufferLoad = highBufferLoad;
    }
    
    private int getBufferState(final int n) {
        final float n2 = n / this.targetBufferSize;
        if (n2 > this.highBufferLoad) {
            return 0;
        }
        if (n2 < this.lowBufferLoad) {
            return 2;
        }
        return 1;
    }
    
    private int getLoaderBufferState(long n, final long n2) {
        if (n2 != -1L) {
            n = n2 - n;
            if (n <= this.highWatermarkUs) {
                if (n < this.lowWatermarkUs) {
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }
    
    private void notifyLoadingChanged(final boolean b) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new DefaultLoadControl$1(this, b));
        }
    }
    
    private void updateControlState() {
        final int n = 0;
        int n2 = this.bufferState;
        int i = 0;
        boolean b = false;
        boolean b2 = false;
        while (i < this.loaders.size()) {
            final DefaultLoadControl$LoaderState defaultLoadControl$LoaderState = this.loaderStates.get(this.loaders.get(i));
            final boolean b3 = b2 | defaultLoadControl$LoaderState.loading;
            boolean b4;
            if (defaultLoadControl$LoaderState.nextLoadPositionUs != -1L) {
                b4 = true;
            }
            else {
                b4 = false;
            }
            b |= b4;
            n2 = Math.max(n2, defaultLoadControl$LoaderState.bufferState);
            ++i;
            b2 = b3;
        }
        this.fillingBuffers = (!this.loaders.isEmpty() && (b2 || b) && (n2 == 2 || (n2 == 1 && this.fillingBuffers)));
        if (this.fillingBuffers && !this.streamingPrioritySet) {
            NetworkLock.instance.add(0);
            this.notifyLoadingChanged(this.streamingPrioritySet = true);
        }
        else if (!this.fillingBuffers && this.streamingPrioritySet && !b2) {
            NetworkLock.instance.remove(0);
            this.notifyLoadingChanged(this.streamingPrioritySet = false);
        }
        this.maxLoadStartPositionUs = -1L;
        if (this.fillingBuffers) {
            for (int j = n; j < this.loaders.size(); ++j) {
                final long nextLoadPositionUs = this.loaderStates.get(this.loaders.get(j)).nextLoadPositionUs;
                if (nextLoadPositionUs != -1L && (this.maxLoadStartPositionUs == -1L || nextLoadPositionUs < this.maxLoadStartPositionUs)) {
                    this.maxLoadStartPositionUs = nextLoadPositionUs;
                }
            }
        }
    }
    
    @Override
    public Allocator getAllocator() {
        return this.allocator;
    }
    
    @Override
    public void register(final Object o, final int n) {
        this.loaders.add(o);
        this.loaderStates.put(o, new DefaultLoadControl$LoaderState(n));
        this.targetBufferSize += n;
    }
    
    @Override
    public void trimAllocator() {
        this.allocator.trim(this.targetBufferSize);
    }
    
    @Override
    public void unregister(final Object o) {
        this.loaders.remove(o);
        this.targetBufferSize -= this.loaderStates.remove(o).bufferSizeContribution;
        this.updateControlState();
    }
    
    @Override
    public boolean update(final Object o, final long n, final long nextLoadPositionUs, final boolean loading) {
        final int loaderBufferState = this.getLoaderBufferState(n, nextLoadPositionUs);
        final DefaultLoadControl$LoaderState defaultLoadControl$LoaderState = this.loaderStates.get(o);
        boolean b;
        if (defaultLoadControl$LoaderState.bufferState != loaderBufferState || defaultLoadControl$LoaderState.nextLoadPositionUs != nextLoadPositionUs || defaultLoadControl$LoaderState.loading != loading) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            defaultLoadControl$LoaderState.bufferState = loaderBufferState;
            defaultLoadControl$LoaderState.nextLoadPositionUs = nextLoadPositionUs;
            defaultLoadControl$LoaderState.loading = loading;
        }
        final int totalBytesAllocated = this.allocator.getTotalBytesAllocated();
        final int bufferState = this.getBufferState(totalBytesAllocated);
        boolean b2;
        if (this.bufferState != bufferState) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2) {
            this.bufferState = bufferState;
        }
        if (b || b2) {
            this.updateControlState();
        }
        return totalBytesAllocated < this.targetBufferSize && nextLoadPositionUs != -1L && nextLoadPositionUs <= this.maxLoadStartPositionUs;
    }
}
