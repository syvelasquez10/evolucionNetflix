// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import com.facebook.common.internal.Objects;
import android.view.MotionEvent;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.components.DraweeEventTracker$Event;
import java.util.concurrent.Executor;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.datasource.DataSource;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.gestures.GestureDetector$ClickListener;
import com.facebook.drawee.components.DeferredReleaser$Releasable;

public abstract class AbstractDraweeController<T, INFO> implements DeferredReleaser$Releasable, GestureDetector$ClickListener, DraweeController
{
    private static final Class<?> TAG;
    private Object mCallerContext;
    private ControllerListener<INFO> mControllerListener;
    private Drawable mControllerOverlay;
    private DataSource<T> mDataSource;
    private final DeferredReleaser mDeferredReleaser;
    private Drawable mDrawable;
    private final DraweeEventTracker mEventTracker;
    private T mFetchedImage;
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mRetainImageOnFailure;
    private RetryManager mRetryManager;
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;
    
    static {
        TAG = AbstractDraweeController.class;
    }
    
    public AbstractDraweeController(final DeferredReleaser mDeferredReleaser, final Executor mUiThreadImmediateExecutor, final String s, final Object o) {
        this.mEventTracker = new DraweeEventTracker();
        this.mDeferredReleaser = mDeferredReleaser;
        this.mUiThreadImmediateExecutor = mUiThreadImmediateExecutor;
        this.init(s, o, true);
    }
    
    private void init(final String mId, final Object mCallerContext, final boolean b) {
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_INIT_CONTROLLER);
        if (!b && this.mDeferredReleaser != null) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        this.releaseFetch();
        this.mRetainImageOnFailure = false;
        if (this.mRetryManager != null) {
            this.mRetryManager.init();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.init();
            this.mGestureDetector.setClickListener(this);
        }
        if (this.mControllerListener instanceof AbstractDraweeController$InternalForwardingListener) {
            ((AbstractDraweeController$InternalForwardingListener)this.mControllerListener).clearListeners();
        }
        else {
            this.mControllerListener = null;
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s -> %s: initialize", (Object)System.identityHashCode(this), this.mId, mId);
        }
        this.mId = mId;
        this.mCallerContext = mCallerContext;
    }
    
    private boolean isExpectedDataSource(final String s, final DataSource<T> dataSource) {
        return s.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }
    
    private void logMessageAndFailure(final String s, final Throwable t) {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: %s: failure: %s", (Object)System.identityHashCode(this), this.mId, s, t);
        }
    }
    
    private void logMessageAndImage(final String s, final T t) {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: %s: image: %s %x", System.identityHashCode(this), this.mId, s, this.getImageClass(t), this.getImageHash(t));
        }
    }
    
    private void onFailureInternal(final String s, final DataSource<T> dataSource, final Throwable t, final boolean b) {
        if (!this.isExpectedDataSource(s, dataSource)) {
            this.logMessageAndFailure("ignore_old_datasource @ onFailure", t);
            dataSource.close();
            return;
        }
        final DraweeEventTracker mEventTracker = this.mEventTracker;
        DraweeEventTracker$Event draweeEventTracker$Event;
        if (b) {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_DATASOURCE_FAILURE;
        }
        else {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_DATASOURCE_FAILURE_INT;
        }
        mEventTracker.recordEvent(draweeEventTracker$Event);
        if (b) {
            this.logMessageAndFailure("final_failed @ onFailure", t);
            this.mDataSource = null;
            this.mHasFetchFailed = true;
            if (this.mRetainImageOnFailure && this.mDrawable != null) {
                this.mSettableDraweeHierarchy.setImage(this.mDrawable, 1.0f, true);
            }
            else if (this.shouldRetryOnTap()) {
                this.mSettableDraweeHierarchy.setRetry(t);
            }
            else {
                this.mSettableDraweeHierarchy.setFailure(t);
            }
            this.getControllerListener().onFailure(this.mId, t);
            return;
        }
        this.logMessageAndFailure("intermediate_failed @ onFailure", t);
        this.getControllerListener().onIntermediateImageFailed(this.mId, t);
    }
    
    private void onNewResultInternal(final String p0, final DataSource<T> p1, final T p2, final float p3, final boolean p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.isExpectedDataSource:(Ljava/lang/String;Lcom/facebook/datasource/DataSource;)Z
        //     6: ifne            30
        //     9: aload_0        
        //    10: ldc_w           "ignore_old_datasource @ onNewResult"
        //    13: aload_3        
        //    14: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //    17: aload_0        
        //    18: aload_3        
        //    19: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseImage:(Ljava/lang/Object;)V
        //    22: aload_2        
        //    23: invokeinterface com/facebook/datasource/DataSource.close:()Z
        //    28: pop            
        //    29: return         
        //    30: aload_0        
        //    31: getfield        com/facebook/drawee/controller/AbstractDraweeController.mEventTracker:Lcom/facebook/drawee/components/DraweeEventTracker;
        //    34: astore          8
        //    36: iload           5
        //    38: ifeq            174
        //    41: getstatic       com/facebook/drawee/components/DraweeEventTracker$Event.ON_DATASOURCE_RESULT:Lcom/facebook/drawee/components/DraweeEventTracker$Event;
        //    44: astore          7
        //    46: aload           8
        //    48: aload           7
        //    50: invokevirtual   com/facebook/drawee/components/DraweeEventTracker.recordEvent:(Lcom/facebook/drawee/components/DraweeEventTracker$Event;)V
        //    53: aload_0        
        //    54: aload_3        
        //    55: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.createDrawable:(Ljava/lang/Object;)Landroid/graphics/drawable/Drawable;
        //    58: astore          7
        //    60: aload_0        
        //    61: getfield        com/facebook/drawee/controller/AbstractDraweeController.mFetchedImage:Ljava/lang/Object;
        //    64: astore_2       
        //    65: aload_0        
        //    66: getfield        com/facebook/drawee/controller/AbstractDraweeController.mDrawable:Landroid/graphics/drawable/Drawable;
        //    69: astore          8
        //    71: aload_0        
        //    72: aload_3        
        //    73: putfield        com/facebook/drawee/controller/AbstractDraweeController.mFetchedImage:Ljava/lang/Object;
        //    76: aload_0        
        //    77: aload           7
        //    79: putfield        com/facebook/drawee/controller/AbstractDraweeController.mDrawable:Landroid/graphics/drawable/Drawable;
        //    82: iload           5
        //    84: ifeq            208
        //    87: aload_0        
        //    88: ldc_w           "set_final_result @ onNewResult"
        //    91: aload_3        
        //    92: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //    95: aload_0        
        //    96: aconst_null    
        //    97: putfield        com/facebook/drawee/controller/AbstractDraweeController.mDataSource:Lcom/facebook/datasource/DataSource;
        //   100: aload_0        
        //   101: getfield        com/facebook/drawee/controller/AbstractDraweeController.mSettableDraweeHierarchy:Lcom/facebook/drawee/interfaces/SettableDraweeHierarchy;
        //   104: aload           7
        //   106: fconst_1       
        //   107: iload           6
        //   109: invokeinterface com/facebook/drawee/interfaces/SettableDraweeHierarchy.setImage:(Landroid/graphics/drawable/Drawable;FZ)V
        //   114: aload_0        
        //   115: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getControllerListener:()Lcom/facebook/drawee/controller/ControllerListener;
        //   118: aload_1        
        //   119: aload_0        
        //   120: aload_3        
        //   121: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getImageInfo:(Ljava/lang/Object;)Ljava/lang/Object;
        //   124: aload_0        
        //   125: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getAnimatable:()Landroid/graphics/drawable/Animatable;
        //   128: invokeinterface com/facebook/drawee/controller/ControllerListener.onFinalImageSet:(Ljava/lang/String;Ljava/lang/Object;Landroid/graphics/drawable/Animatable;)V
        //   133: aload           8
        //   135: ifnull          151
        //   138: aload           8
        //   140: aload           7
        //   142: if_acmpeq       151
        //   145: aload_0        
        //   146: aload           8
        //   148: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseDrawable:(Landroid/graphics/drawable/Drawable;)V
        //   151: aload_2        
        //   152: ifnull          29
        //   155: aload_2        
        //   156: aload_3        
        //   157: if_acmpeq       29
        //   160: aload_0        
        //   161: ldc_w           "release_previous_result @ onNewResult"
        //   164: aload_2        
        //   165: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //   168: aload_0        
        //   169: aload_2        
        //   170: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseImage:(Ljava/lang/Object;)V
        //   173: return         
        //   174: getstatic       com/facebook/drawee/components/DraweeEventTracker$Event.ON_DATASOURCE_RESULT_INT:Lcom/facebook/drawee/components/DraweeEventTracker$Event;
        //   177: astore          7
        //   179: goto            46
        //   182: astore          7
        //   184: aload_0        
        //   185: ldc_w           "drawable_failed @ onNewResult"
        //   188: aload_3        
        //   189: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //   192: aload_0        
        //   193: aload_3        
        //   194: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseImage:(Ljava/lang/Object;)V
        //   197: aload_0        
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload           7
        //   202: iload           5
        //   204: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.onFailureInternal:(Ljava/lang/String;Lcom/facebook/datasource/DataSource;Ljava/lang/Throwable;Z)V
        //   207: return         
        //   208: aload_0        
        //   209: ldc_w           "set_intermediate_result @ onNewResult"
        //   212: aload_3        
        //   213: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //   216: aload_0        
        //   217: getfield        com/facebook/drawee/controller/AbstractDraweeController.mSettableDraweeHierarchy:Lcom/facebook/drawee/interfaces/SettableDraweeHierarchy;
        //   220: aload           7
        //   222: fload           4
        //   224: iload           6
        //   226: invokeinterface com/facebook/drawee/interfaces/SettableDraweeHierarchy.setImage:(Landroid/graphics/drawable/Drawable;FZ)V
        //   231: aload_0        
        //   232: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getControllerListener:()Lcom/facebook/drawee/controller/ControllerListener;
        //   235: aload_1        
        //   236: aload_0        
        //   237: aload_3        
        //   238: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getImageInfo:(Ljava/lang/Object;)Ljava/lang/Object;
        //   241: invokeinterface com/facebook/drawee/controller/ControllerListener.onIntermediateImageSet:(Ljava/lang/String;Ljava/lang/Object;)V
        //   246: goto            133
        //   249: astore_1       
        //   250: aload           8
        //   252: ifnull          268
        //   255: aload           8
        //   257: aload           7
        //   259: if_acmpeq       268
        //   262: aload_0        
        //   263: aload           8
        //   265: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseDrawable:(Landroid/graphics/drawable/Drawable;)V
        //   268: aload_2        
        //   269: ifnull          290
        //   272: aload_2        
        //   273: aload_3        
        //   274: if_acmpeq       290
        //   277: aload_0        
        //   278: ldc_w           "release_previous_result @ onNewResult"
        //   281: aload_2        
        //   282: invokespecial   com/facebook/drawee/controller/AbstractDraweeController.logMessageAndImage:(Ljava/lang/String;Ljava/lang/Object;)V
        //   285: aload_0        
        //   286: aload_2        
        //   287: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.releaseImage:(Ljava/lang/Object;)V
        //   290: aload_1        
        //   291: athrow         
        //    Signature:
        //  (Ljava/lang/String;Lcom/facebook/datasource/DataSource<TT;>;TT;FZZ)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  53     60     182    208    Ljava/lang/Exception;
        //  87     133    249    292    Any
        //  208    246    249    292    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void onProgressUpdateInternal(final String s, final DataSource<T> dataSource, final float n, final boolean b) {
        if (!this.isExpectedDataSource(s, dataSource)) {
            this.logMessageAndFailure("ignore_old_datasource @ onProgress", null);
            dataSource.close();
        }
        else if (!b) {
            this.mSettableDraweeHierarchy.setProgress(n, false);
        }
    }
    
    private void releaseFetch() {
        final boolean mIsRequestSubmitted = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        if (this.mDataSource != null) {
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mDrawable != null) {
            this.releaseDrawable(this.mDrawable);
        }
        this.mDrawable = null;
        if (this.mFetchedImage != null) {
            this.logMessageAndImage("release", this.mFetchedImage);
            this.releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (mIsRequestSubmitted) {
            this.getControllerListener().onRelease(this.mId);
        }
    }
    
    private boolean shouldRetryOnTap() {
        return this.mHasFetchFailed && this.mRetryManager != null && this.mRetryManager.shouldRetryOnTap();
    }
    
    public void addControllerListener(final ControllerListener<? super INFO> mControllerListener) {
        Preconditions.checkNotNull(mControllerListener);
        if (this.mControllerListener instanceof AbstractDraweeController$InternalForwardingListener) {
            ((AbstractDraweeController$InternalForwardingListener)this.mControllerListener).addListener(mControllerListener);
            return;
        }
        if (this.mControllerListener != null) {
            this.mControllerListener = (ControllerListener<INFO>)AbstractDraweeController$InternalForwardingListener.createInternal((ControllerListener<? super Object>)this.mControllerListener, (ControllerListener<? super Object>)mControllerListener);
            return;
        }
        this.mControllerListener = (ControllerListener<INFO>)mControllerListener;
    }
    
    protected abstract Drawable createDrawable(final T p0);
    
    public Animatable getAnimatable() {
        if (this.mDrawable instanceof Animatable) {
            return (Animatable)this.mDrawable;
        }
        return null;
    }
    
    protected ControllerListener<INFO> getControllerListener() {
        if (this.mControllerListener == null) {
            return BaseControllerListener.getNoOpListener();
        }
        return this.mControllerListener;
    }
    
    protected abstract DataSource<T> getDataSource();
    
    protected GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }
    
    @Override
    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }
    
    protected String getImageClass(final T t) {
        if (t != null) {
            return t.getClass().getSimpleName();
        }
        return "<null>";
    }
    
    protected int getImageHash(final T t) {
        return System.identityHashCode(t);
    }
    
    protected abstract INFO getImageInfo(final T p0);
    
    protected RetryManager getRetryManager() {
        return this.mRetryManager;
    }
    
    protected void initialize(final String s, final Object o) {
        this.init(s, o, false);
    }
    
    @Override
    public void onAttach() {
        if (FLog.isLoggable(2)) {
            final Class<?> tag = AbstractDraweeController.TAG;
            final int identityHashCode = System.identityHashCode(this);
            final String mId = this.mId;
            String s;
            if (this.mIsRequestSubmitted) {
                s = "request already submitted";
            }
            else {
                s = "request needs submit";
            }
            FLog.v(tag, "controller %x %s: onAttach: %s", (Object)identityHashCode, mId, s);
        }
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            this.submitRequest();
        }
    }
    
    @Override
    public boolean onClick() {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: onClick", (Object)System.identityHashCode(this), this.mId);
        }
        if (this.shouldRetryOnTap()) {
            this.mRetryManager.notifyTapToRetry();
            this.mSettableDraweeHierarchy.reset();
            this.submitRequest();
            return true;
        }
        return false;
    }
    
    @Override
    public void onDetach() {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: onDetach", (Object)System.identityHashCode(this), this.mId);
        }
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: onTouchEvent %s", (Object)System.identityHashCode(this), this.mId, motionEvent);
        }
        if (this.mGestureDetector != null && (this.mGestureDetector.isCapturingGesture() || this.shouldHandleGesture())) {
            this.mGestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return false;
    }
    
    @Override
    public void release() {
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_RELEASE_CONTROLLER);
        if (this.mRetryManager != null) {
            this.mRetryManager.reset();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.reset();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
        }
        this.releaseFetch();
    }
    
    protected abstract void releaseDrawable(final Drawable p0);
    
    protected abstract void releaseImage(final T p0);
    
    protected void setGestureDetector(final GestureDetector mGestureDetector) {
        this.mGestureDetector = mGestureDetector;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setClickListener(this);
        }
    }
    
    @Override
    public void setHierarchy(final DraweeHierarchy draweeHierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.TAG, "controller %x %s: setHierarchy: %s", (Object)System.identityHashCode(this), this.mId, draweeHierarchy);
        }
        final DraweeEventTracker mEventTracker = this.mEventTracker;
        DraweeEventTracker$Event draweeEventTracker$Event;
        if (draweeHierarchy != null) {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_SET_HIERARCHY;
        }
        else {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_CLEAR_HIERARCHY;
        }
        mEventTracker.recordEvent(draweeEventTracker$Event);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            this.release();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (draweeHierarchy != null) {
            Preconditions.checkArgument(draweeHierarchy instanceof SettableDraweeHierarchy);
            (this.mSettableDraweeHierarchy = (SettableDraweeHierarchy)draweeHierarchy).setControllerOverlay(this.mControllerOverlay);
        }
    }
    
    protected void setRetainImageOnFailure(final boolean mRetainImageOnFailure) {
        this.mRetainImageOnFailure = mRetainImageOnFailure;
    }
    
    protected void setRetryManager(final RetryManager mRetryManager) {
        this.mRetryManager = mRetryManager;
    }
    
    protected boolean shouldHandleGesture() {
        return this.shouldRetryOnTap();
    }
    
    protected void submitRequest() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/drawee/controller/AbstractDraweeController.mEventTracker:Lcom/facebook/drawee/components/DraweeEventTracker;
        //     4: getstatic       com/facebook/drawee/components/DraweeEventTracker$Event.ON_DATASOURCE_SUBMIT:Lcom/facebook/drawee/components/DraweeEventTracker$Event;
        //     7: invokevirtual   com/facebook/drawee/components/DraweeEventTracker.recordEvent:(Lcom/facebook/drawee/components/DraweeEventTracker$Event;)V
        //    10: aload_0        
        //    11: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getControllerListener:()Lcom/facebook/drawee/controller/ControllerListener;
        //    14: aload_0        
        //    15: getfield        com/facebook/drawee/controller/AbstractDraweeController.mId:Ljava/lang/String;
        //    18: aload_0        
        //    19: getfield        com/facebook/drawee/controller/AbstractDraweeController.mCallerContext:Ljava/lang/Object;
        //    22: invokeinterface com/facebook/drawee/controller/ControllerListener.onSubmit:(Ljava/lang/String;Ljava/lang/Object;)V
        //    27: aload_0        
        //    28: getfield        com/facebook/drawee/controller/AbstractDraweeController.mSettableDraweeHierarchy:Lcom/facebook/drawee/interfaces/SettableDraweeHierarchy;
        //    31: fconst_0       
        //    32: iconst_1       
        //    33: invokeinterface com/facebook/drawee/interfaces/SettableDraweeHierarchy.setProgress:(FZ)V
        //    38: aload_0        
        //    39: iconst_1       
        //    40: putfield        com/facebook/drawee/controller/AbstractDraweeController.mIsRequestSubmitted:Z
        //    43: aload_0        
        //    44: iconst_0       
        //    45: putfield        com/facebook/drawee/controller/AbstractDraweeController.mHasFetchFailed:Z
        //    48: aload_0        
        //    49: aload_0        
        //    50: invokevirtual   com/facebook/drawee/controller/AbstractDraweeController.getDataSource:()Lcom/facebook/datasource/DataSource;
        //    53: putfield        com/facebook/drawee/controller/AbstractDraweeController.mDataSource:Lcom/facebook/datasource/DataSource;
        //    56: iconst_2       
        //    57: invokestatic    com/facebook/common/logging/FLog.isLoggable:(I)Z
        //    60: ifeq            93
        //    63: getstatic       com/facebook/drawee/controller/AbstractDraweeController.TAG:Ljava/lang/Class;
        //    66: ldc_w           "controller %x %s: submitRequest: dataSource: %x"
        //    69: aload_0        
        //    70: invokestatic    java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        //    73: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    76: aload_0        
        //    77: getfield        com/facebook/drawee/controller/AbstractDraweeController.mId:Ljava/lang/String;
        //    80: aload_0        
        //    81: getfield        com/facebook/drawee/controller/AbstractDraweeController.mDataSource:Lcom/facebook/datasource/DataSource;
        //    84: invokestatic    java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        //    87: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    90: invokestatic    com/facebook/common/logging/FLog.v:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //    93: new             new            !!! ERROR
        //    96: dup            
        //    97: aload_0        
        //    98: aload_0        
        //    99: getfield        com/facebook/drawee/controller/AbstractDraweeController.mId:Ljava/lang/String;
        //   102: aload_0        
        //   103: getfield        com/facebook/drawee/controller/AbstractDraweeController.mDataSource:Lcom/facebook/datasource/DataSource;
        //   106: invokeinterface com/facebook/datasource/DataSource.hasResult:()Z
        //   111: invokespecial   invokespecial  !!! ERROR
        //   114: astore_1       
        //   115: aload_0        
        //   116: getfield        com/facebook/drawee/controller/AbstractDraweeController.mDataSource:Lcom/facebook/datasource/DataSource;
        //   119: aload_1        
        //   120: aload_0        
        //   121: getfield        com/facebook/drawee/controller/AbstractDraweeController.mUiThreadImmediateExecutor:Ljava/util/concurrent/Executor;
        //   124: invokeinterface com/facebook/datasource/DataSource.subscribe:(Lcom/facebook/datasource/DataSubscriber;Ljava/util/concurrent/Executor;)V
        //   129: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", this.getImageHash(this.mFetchedImage)).add("events", this.mEventTracker.toString()).toString();
    }
}
