// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Util;
import android.os.Message;
import android.util.Pair;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import android.os.SystemClock;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.HandlerThread;
import android.os.Handler;
import java.util.List;
import android.os.Handler$Callback;

final class ExoPlayerImplInternal implements Handler$Callback
{
    private volatile long bufferedPositionUs;
    private int customMessagesProcessed;
    private int customMessagesSent;
    private volatile long durationUs;
    private long elapsedRealtimeUs;
    private final List<TrackRenderer> enabledRenderers;
    private final Handler eventHandler;
    private final Handler handler;
    private final HandlerThread internalPlaybackThread;
    private long lastSeekPositionMs;
    private final long minBufferUs;
    private final long minRebufferUs;
    private final AtomicInteger pendingSeekCount;
    private boolean playWhenReady;
    private volatile long positionUs;
    private boolean rebuffering;
    private boolean released;
    private MediaClock rendererMediaClock;
    private TrackRenderer rendererMediaClockSource;
    private TrackRenderer[] renderers;
    private final int[] selectedTrackIndices;
    private final StandaloneMediaClock standaloneMediaClock;
    private int state;
    private final MediaFormat[][] trackFormats;
    
    public ExoPlayerImplInternal(final Handler eventHandler, final boolean playWhenReady, final int[] array, final int n, final int n2) {
        this.customMessagesSent = 0;
        this.customMessagesProcessed = 0;
        this.eventHandler = eventHandler;
        this.playWhenReady = playWhenReady;
        this.minBufferUs = n * 1000L;
        this.minRebufferUs = n2 * 1000L;
        this.selectedTrackIndices = Arrays.copyOf(array, array.length);
        this.state = 1;
        this.durationUs = -1L;
        this.bufferedPositionUs = -1L;
        this.standaloneMediaClock = new StandaloneMediaClock();
        this.pendingSeekCount = new AtomicInteger();
        this.enabledRenderers = new ArrayList<TrackRenderer>(array.length);
        this.trackFormats = new MediaFormat[array.length][];
        (this.internalPlaybackThread = new PriorityHandlerThread("ExoPlayerImplInternal:Handler", -16)).start();
        this.handler = new Handler(this.internalPlaybackThread.getLooper(), (Handler$Callback)this);
    }
    
    private void doSomeWork() {
        TraceUtil.beginSection("doSomeWork");
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long durationUs;
        if (this.durationUs != -1L) {
            durationUs = this.durationUs;
        }
        else {
            durationUs = Long.MAX_VALUE;
        }
        this.updatePositionUs();
        int n = 1;
        int n2 = 1;
        int i = 0;
        long bufferedPositionUs = durationUs;
        while (i < this.enabledRenderers.size()) {
            final TrackRenderer trackRenderer = this.enabledRenderers.get(i);
            trackRenderer.doSomeWork(this.positionUs, this.elapsedRealtimeUs);
            if (n2 != 0 && trackRenderer.isEnded()) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            final boolean rendererReadyOrEnded = this.rendererReadyOrEnded(trackRenderer);
            if (!rendererReadyOrEnded) {
                trackRenderer.maybeThrowError();
            }
            if (n != 0 && rendererReadyOrEnded) {
                n = 1;
            }
            else {
                n = 0;
            }
            long min = 0L;
            Label_0138: {
                if (bufferedPositionUs == -1L) {
                    min = bufferedPositionUs;
                }
                else {
                    final long durationUs2 = trackRenderer.getDurationUs();
                    final long bufferedPositionUs2 = trackRenderer.getBufferedPositionUs();
                    if (bufferedPositionUs2 == -1L) {
                        min = -1L;
                    }
                    else {
                        min = bufferedPositionUs;
                        if (bufferedPositionUs2 != -3L) {
                            if (durationUs2 != -1L && durationUs2 != -2L) {
                                min = bufferedPositionUs;
                                if (bufferedPositionUs2 >= durationUs2) {
                                    break Label_0138;
                                }
                            }
                            min = Math.min(bufferedPositionUs, bufferedPositionUs2);
                        }
                    }
                }
            }
            ++i;
            bufferedPositionUs = min;
        }
        this.bufferedPositionUs = bufferedPositionUs;
        if (n2 != 0 && (this.durationUs == -1L || this.durationUs <= this.positionUs)) {
            this.setState(5);
            this.stopRenderers();
        }
        else if (this.state == 3 && n != 0) {
            this.setState(4);
            if (this.playWhenReady) {
                this.startRenderers();
            }
        }
        else if (this.state == 4 && n == 0) {
            this.rebuffering = this.playWhenReady;
            this.setState(3);
            this.stopRenderers();
        }
        this.handler.removeMessages(7);
        if ((this.playWhenReady && this.state == 4) || this.state == 3) {
            this.scheduleNextOperation(7, elapsedRealtime, 10L);
        }
        else if (!this.enabledRenderers.isEmpty()) {
            this.scheduleNextOperation(7, elapsedRealtime, 1000L);
        }
        TraceUtil.endSection();
    }
    
    private void enableRenderer(final TrackRenderer rendererMediaClockSource, final int n, final boolean b) {
        rendererMediaClockSource.enable(n, this.positionUs, b);
        this.enabledRenderers.add(rendererMediaClockSource);
        final MediaClock mediaClock = rendererMediaClockSource.getMediaClock();
        if (mediaClock != null) {
            Assertions.checkState(this.rendererMediaClock == null);
            this.rendererMediaClock = mediaClock;
            this.rendererMediaClockSource = rendererMediaClockSource;
        }
    }
    
    private void ensureDisabled(final TrackRenderer trackRenderer) {
        this.ensureStopped(trackRenderer);
        if (trackRenderer.getState() == 2) {
            trackRenderer.disable();
            if (trackRenderer == this.rendererMediaClockSource) {
                this.rendererMediaClock = null;
                this.rendererMediaClockSource = null;
            }
        }
    }
    
    private void ensureStopped(final TrackRenderer trackRenderer) {
        if (trackRenderer.getState() == 3) {
            trackRenderer.stop();
        }
    }
    
    private void incrementalPrepareInternal() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        int n = 1;
        int n2;
        for (int i = 0; i < this.renderers.length; ++i, n = n2) {
            final TrackRenderer trackRenderer = this.renderers[i];
            n2 = n;
            if (trackRenderer.getState() == 0) {
                n2 = n;
                if (trackRenderer.prepare(this.positionUs) == 0) {
                    trackRenderer.maybeThrowError();
                    n2 = 0;
                }
            }
        }
        if (n == 0) {
            this.scheduleNextOperation(2, elapsedRealtime, 10L);
            return;
        }
        long durationUs = 0L;
        int n3 = 1;
        int n4 = 1;
        int n5;
        int n6;
        long n7;
        for (int j = 0; j < this.renderers.length; ++j, n4 = n5, n3 = n6, durationUs = n7) {
            final TrackRenderer trackRenderer2 = this.renderers[j];
            final int trackCount = trackRenderer2.getTrackCount();
            final MediaFormat[] array = new MediaFormat[trackCount];
            for (int k = 0; k < trackCount; ++k) {
                array[k] = trackRenderer2.getFormat(k);
            }
            this.trackFormats[j] = array;
            n5 = n4;
            n6 = n3;
            n7 = durationUs;
            if (trackCount > 0) {
                long max;
                if (durationUs == -1L) {
                    max = durationUs;
                }
                else {
                    final long durationUs2 = trackRenderer2.getDurationUs();
                    if (durationUs2 == -1L) {
                        max = -1L;
                    }
                    else {
                        max = durationUs;
                        if (durationUs2 != -2L) {
                            max = Math.max(durationUs, durationUs2);
                        }
                    }
                }
                final int n8 = this.selectedTrackIndices[j];
                n5 = n4;
                n6 = n3;
                n7 = max;
                if (n8 >= 0) {
                    n5 = n4;
                    n6 = n3;
                    n7 = max;
                    if (n8 < array.length) {
                        this.enableRenderer(trackRenderer2, n8, false);
                        boolean b;
                        if (n3 != 0 && trackRenderer2.isEnded()) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        if (n4 != 0 && this.rendererReadyOrEnded(trackRenderer2)) {
                            n5 = 1;
                            n7 = max;
                            n6 = (b ? 1 : 0);
                        }
                        else {
                            n5 = 0;
                            n6 = (b ? 1 : 0);
                            n7 = max;
                        }
                    }
                }
            }
        }
        this.durationUs = durationUs;
        if (n3 != 0 && (durationUs == -1L || durationUs <= this.positionUs)) {
            this.state = 5;
        }
        else {
            int state;
            if (n4 != 0) {
                state = 4;
            }
            else {
                state = 3;
            }
            this.state = state;
        }
        this.eventHandler.obtainMessage(1, this.state, 0, (Object)this.trackFormats).sendToTarget();
        if (this.playWhenReady && this.state == 4) {
            this.startRenderers();
        }
        this.handler.sendEmptyMessage(7);
    }
    
    private void prepareInternal(final TrackRenderer[] renderers) {
        this.resetInternal();
        this.renderers = renderers;
        Arrays.fill(this.trackFormats, null);
        this.setState(2);
        this.incrementalPrepareInternal();
    }
    
    private void release(final TrackRenderer trackRenderer) {
        try {
            trackRenderer.release();
        }
        catch (ExoPlaybackException ex) {
            Log.e("ExoPlayerImplInternal", "Release failed.", (Throwable)ex);
        }
        catch (RuntimeException ex2) {
            Log.e("ExoPlayerImplInternal", "Release failed.", (Throwable)ex2);
        }
    }
    
    private void releaseInternal() {
        this.resetInternal();
        this.setState(1);
        synchronized (this) {
            this.released = true;
            this.notifyAll();
        }
    }
    
    private boolean rendererReadyOrEnded(final TrackRenderer trackRenderer) {
        final boolean b = false;
        if (!trackRenderer.isEnded()) {
            if (!trackRenderer.isReady()) {
                return false;
            }
            if (this.state != 4) {
                final long durationUs = trackRenderer.getDurationUs();
                final long bufferedPositionUs = trackRenderer.getBufferedPositionUs();
                long n;
                if (this.rebuffering) {
                    n = this.minRebufferUs;
                }
                else {
                    n = this.minBufferUs;
                }
                if (n > 0L && bufferedPositionUs != -1L && bufferedPositionUs != -3L && bufferedPositionUs < n + this.positionUs) {
                    boolean b2 = b;
                    if (durationUs == -1L) {
                        return b2;
                    }
                    b2 = b;
                    if (durationUs == -2L) {
                        return b2;
                    }
                    b2 = b;
                    if (bufferedPositionUs < durationUs) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return true;
    }
    
    private void resetInternal() {
        int i = 0;
        this.handler.removeMessages(7);
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.standaloneMediaClock.stop();
        if (this.renderers == null) {
            return;
        }
        while (i < this.renderers.length) {
            final TrackRenderer trackRenderer = this.renderers[i];
            this.stopAndDisable(trackRenderer);
            this.release(trackRenderer);
            ++i;
        }
        this.renderers = null;
        this.rendererMediaClock = null;
        this.rendererMediaClockSource = null;
        this.enabledRenderers.clear();
    }
    
    private void scheduleNextOperation(final int n, long n2, final long n3) {
        n2 = n2 + n3 - SystemClock.elapsedRealtime();
        if (n2 <= 0L) {
            this.handler.sendEmptyMessage(n);
            return;
        }
        this.handler.sendEmptyMessageDelayed(n, n2);
    }
    
    private void seekToInternal(final long n) {
        try {
            if (n == this.positionUs / 1000L) {
                return;
            }
            this.rebuffering = false;
            this.positionUs = n * 1000L;
            this.standaloneMediaClock.stop();
            this.standaloneMediaClock.setPositionUs(this.positionUs);
            if (this.state == 1 || this.state == 2) {
                return;
            }
            for (int i = 0; i < this.enabledRenderers.size(); ++i) {
                final TrackRenderer trackRenderer = this.enabledRenderers.get(i);
                this.ensureStopped(trackRenderer);
                trackRenderer.seekTo(this.positionUs);
            }
            this.setState(3);
            this.handler.sendEmptyMessage(7);
        }
        finally {
            this.pendingSeekCount.decrementAndGet();
        }
    }
    
    private <T> void sendMessageInternal(final int n, final Object o) {
        try {
            final Pair pair = (Pair)o;
            ((ExoPlayer$ExoPlayerComponent)pair.first).handleMessage(n, pair.second);
            if (this.state != 1 && this.state != 2) {
                this.handler.sendEmptyMessage(7);
            }
            synchronized (this) {
                ++this.customMessagesProcessed;
                this.notifyAll();
            }
        }
        finally {
            synchronized (this) {
                ++this.customMessagesProcessed;
                this.notifyAll();
            }
        }
    }
    
    private void setPlayWhenReadyInternal(final boolean playWhenReady) {
        while (true) {
            Label_0073: {
                try {
                    this.rebuffering = false;
                    if (!(this.playWhenReady = playWhenReady)) {
                        this.stopRenderers();
                        this.updatePositionUs();
                    }
                    else {
                        if (this.state != 4) {
                            break Label_0073;
                        }
                        this.startRenderers();
                        this.handler.sendEmptyMessage(7);
                    }
                    return;
                }
                finally {
                    this.eventHandler.obtainMessage(3).sendToTarget();
                }
            }
            if (this.state == 3) {
                this.handler.sendEmptyMessage(7);
            }
        }
    }
    
    private void setRendererSelectedTrackInternal(int n, final int n2) {
        boolean b = true;
        if (this.selectedTrackIndices[n] != n2) {
            this.selectedTrackIndices[n] = n2;
            if (this.state != 1 && this.state != 2) {
                final TrackRenderer trackRenderer = this.renderers[n];
                final int state = trackRenderer.getState();
                if (state != 0 && state != -1 && trackRenderer.getTrackCount() != 0) {
                    boolean b2;
                    if (state == 2 || state == 3) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (n2 >= 0 && n2 < this.trackFormats[n].length) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (b2) {
                        if (n == 0 && trackRenderer == this.rendererMediaClockSource) {
                            this.standaloneMediaClock.setPositionUs(this.rendererMediaClock.getPositionUs());
                        }
                        this.ensureDisabled(trackRenderer);
                        this.enabledRenderers.remove(trackRenderer);
                    }
                    if (n != 0) {
                        if (this.playWhenReady && this.state == 4) {
                            n = 1;
                        }
                        else {
                            n = 0;
                        }
                        if (b2 || n == 0) {
                            b = false;
                        }
                        this.enableRenderer(trackRenderer, n2, b);
                        if (n != 0) {
                            trackRenderer.start();
                        }
                        this.handler.sendEmptyMessage(7);
                    }
                }
            }
        }
    }
    
    private void setState(final int state) {
        if (this.state != state) {
            this.state = state;
            this.eventHandler.obtainMessage(2, state, 0).sendToTarget();
        }
    }
    
    private void startRenderers() {
        this.rebuffering = false;
        this.standaloneMediaClock.start();
        for (int i = 0; i < this.enabledRenderers.size(); ++i) {
            this.enabledRenderers.get(i).start();
        }
    }
    
    private void stopAndDisable(final TrackRenderer trackRenderer) {
        try {
            this.ensureDisabled(trackRenderer);
        }
        catch (ExoPlaybackException ex) {
            Log.e("ExoPlayerImplInternal", "Stop failed.", (Throwable)ex);
        }
        catch (RuntimeException ex2) {
            Log.e("ExoPlayerImplInternal", "Stop failed.", (Throwable)ex2);
        }
    }
    
    private void stopInternal() {
        this.resetInternal();
        this.setState(1);
    }
    
    private void stopRenderers() {
        this.standaloneMediaClock.stop();
        for (int i = 0; i < this.enabledRenderers.size(); ++i) {
            this.ensureStopped(this.enabledRenderers.get(i));
        }
    }
    
    private void updatePositionUs() {
        if (this.rendererMediaClock != null && this.enabledRenderers.contains(this.rendererMediaClockSource) && !this.rendererMediaClockSource.isEnded()) {
            this.positionUs = this.rendererMediaClock.getPositionUs();
            this.standaloneMediaClock.setPositionUs(this.positionUs);
        }
        else {
            this.positionUs = this.standaloneMediaClock.getPositionUs();
        }
        this.elapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000L;
    }
    
    public long getBufferedPosition() {
        if (this.bufferedPositionUs == -1L) {
            return -1L;
        }
        return this.bufferedPositionUs / 1000L;
    }
    
    public long getCurrentPosition() {
        if (this.pendingSeekCount.get() > 0) {
            return this.lastSeekPositionMs;
        }
        return this.positionUs / 1000L;
    }
    
    public long getDuration() {
        if (this.durationUs == -1L) {
            return -1L;
        }
        return this.durationUs / 1000L;
    }
    
    public boolean handleMessage(final Message message) {
        boolean playWhenReadyInternal = false;
        try {
            switch (message.what) {
                case 1: {
                    this.prepareInternal((TrackRenderer[])message.obj);
                    return true;
                }
                case 2: {
                    this.incrementalPrepareInternal();
                    return true;
                }
                case 3: {
                    if (message.arg1 != 0) {
                        playWhenReadyInternal = true;
                    }
                    this.setPlayWhenReadyInternal(playWhenReadyInternal);
                    return true;
                }
                case 7: {
                    this.doSomeWork();
                    return true;
                }
                case 6: {
                    this.seekToInternal(Util.getLong(message.arg1, message.arg2));
                    return true;
                }
                case 4: {
                    this.stopInternal();
                    return true;
                }
                case 5: {
                    this.releaseInternal();
                    return true;
                }
                case 9: {
                    this.sendMessageInternal(message.arg1, message.obj);
                    return true;
                }
                case 8: {
                    this.setRendererSelectedTrackInternal(message.arg1, message.arg2);
                    return true;
                }
            }
        }
        catch (ExoPlaybackException ex) {
            Log.e("ExoPlayerImplInternal", "Internal track renderer error.", (Throwable)ex);
            this.eventHandler.obtainMessage(4, (Object)ex).sendToTarget();
            this.stopInternal();
            return true;
        }
        catch (RuntimeException ex2) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", (Throwable)ex2);
            this.eventHandler.obtainMessage(4, (Object)new ExoPlaybackException(ex2, true)).sendToTarget();
            this.stopInternal();
            return true;
        }
        return false;
    }
    
    public void prepare(final TrackRenderer... array) {
        this.handler.obtainMessage(1, (Object)array).sendToTarget();
    }
    
    public void release() {
        while (true) {
            synchronized (this) {
                if (this.released) {
                    return;
                }
                this.handler.sendEmptyMessage(5);
                while (!this.released) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            this.internalPlaybackThread.quit();
        }
    }
    
    public void seekTo(final long lastSeekPositionMs) {
        this.lastSeekPositionMs = lastSeekPositionMs;
        this.pendingSeekCount.incrementAndGet();
        this.handler.obtainMessage(6, Util.getTopInt(lastSeekPositionMs), Util.getBottomInt(lastSeekPositionMs)).sendToTarget();
    }
    
    public void sendMessage(final ExoPlayer$ExoPlayerComponent exoPlayer$ExoPlayerComponent, final int n, final Object o) {
        ++this.customMessagesSent;
        this.handler.obtainMessage(9, n, 0, (Object)Pair.create((Object)exoPlayer$ExoPlayerComponent, o)).sendToTarget();
    }
    
    public void setPlayWhenReady(final boolean b) {
        final Handler handler = this.handler;
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        handler.obtainMessage(3, n, 0).sendToTarget();
    }
    
    public void setRendererSelectedTrack(final int n, final int n2) {
        this.handler.obtainMessage(8, n, n2).sendToTarget();
    }
    
    public void stop() {
        this.handler.sendEmptyMessage(4);
    }
}
