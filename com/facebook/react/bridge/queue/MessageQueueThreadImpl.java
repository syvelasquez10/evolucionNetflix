// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.common.logging.FLog;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.common.futures.SimpleSettableFuture;
import android.os.Process;
import com.facebook.react.bridge.UiThreadUtil;
import android.os.Looper;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class MessageQueueThreadImpl implements MessageQueueThread
{
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished;
    private final Looper mLooper;
    private final String mName;
    
    private MessageQueueThreadImpl(final String mName, final Looper mLooper, final QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this.mIsFinished = false;
        this.mName = mName;
        this.mLooper = mLooper;
        this.mHandler = new MessageQueueThreadHandler(mLooper, queueThreadExceptionHandler);
        this.mAssertionErrorMessage = "Expected to be called from the '" + this.getName() + "' thread!";
    }
    
    public static MessageQueueThreadImpl create(final MessageQueueThreadSpec messageQueueThreadSpec, final QueueThreadExceptionHandler queueThreadExceptionHandler) {
        switch (MessageQueueThreadImpl$4.$SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType[messageQueueThreadSpec.getThreadType().ordinal()]) {
            default: {
                throw new RuntimeException("Unknown thread type: " + messageQueueThreadSpec.getThreadType());
            }
            case 1: {
                return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
            }
            case 2: {
                return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
            }
        }
    }
    
    private static MessageQueueThreadImpl createForMainThread(final String s, final QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(s, Looper.getMainLooper(), queueThreadExceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
            MessageQueueThreadRegistry.register(messageQueueThreadImpl);
            return messageQueueThreadImpl;
        }
        UiThreadUtil.runOnUiThread(new MessageQueueThreadImpl$2(messageQueueThreadImpl));
        return messageQueueThreadImpl;
    }
    
    public static MessageQueueThreadImpl startNewBackgroundThread(final String s, final long n, final QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final SimpleSettableFuture<Looper> simpleSettableFuture = new SimpleSettableFuture<Looper>();
        final SimpleSettableFuture<MessageQueueThreadImpl> simpleSettableFuture2 = new SimpleSettableFuture<MessageQueueThreadImpl>();
        new Thread(null, new MessageQueueThreadImpl$3(simpleSettableFuture, simpleSettableFuture2), "mqt_" + s, n).start();
        final MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(s, simpleSettableFuture.getOrThrow(), queueThreadExceptionHandler);
        simpleSettableFuture2.set(messageQueueThreadImpl);
        return messageQueueThreadImpl;
    }
    
    @DoNotStrip
    @Override
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(this.isOnThread(), this.mAssertionErrorMessage);
    }
    
    @DoNotStrip
    @Override
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture<T> simpleSettableFuture = new SimpleSettableFuture<T>();
        this.runOnQueue(new MessageQueueThreadImpl$1(this, simpleSettableFuture, callable));
        return simpleSettableFuture;
    }
    
    public String getName() {
        return this.mName;
    }
    
    @DoNotStrip
    @Override
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }
    
    @DoNotStrip
    @Override
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() == Thread.currentThread()) {
            return;
        }
        try {
            this.mLooper.getThread().join();
        }
        catch (InterruptedException ex) {
            throw new RuntimeException("Got interrupted waiting to join thread " + this.mName);
        }
    }
    
    @DoNotStrip
    @Override
    public void runOnQueue(final Runnable runnable) {
        if (this.mIsFinished) {
            FLog.w("React", "Tried to enqueue runnable on already finished thread: '" + this.getName() + "... dropping Runnable.");
        }
        this.mHandler.post(runnable);
    }
}
