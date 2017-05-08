// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import java.util.Iterator;
import com.facebook.systrace.Systrace;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.Arrays;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayList;
import java.util.Map;
import android.util.LongSparseArray;
import java.util.Comparator;
import com.facebook.react.bridge.LifecycleEventListener;

public class EventDispatcher implements LifecycleEventListener
{
    private static final Comparator<Event> EVENT_COMPARATOR;
    private final EventDispatcher$ScheduleDispatchFrameCallback mCurrentFrameCallback;
    private final EventDispatcher$DispatchEventsRunnable mDispatchEventsRunnable;
    private final LongSparseArray<Integer> mEventCookieToLastEventIdx;
    private final Map<String, Short> mEventNameToEventId;
    private final ArrayList<Event> mEventStaging;
    private final Object mEventsStagingLock;
    private Event[] mEventsToDispatch;
    private final Object mEventsToDispatchLock;
    private int mEventsToDispatchSize;
    private volatile boolean mHasDispatchScheduled;
    private volatile int mHasDispatchScheduledCount;
    private final ArrayList<EventDispatcherListener> mListeners;
    private short mNextEventTypeId;
    private volatile RCTEventEmitter mRCTEventEmitter;
    private final ReactApplicationContext mReactContext;
    
    static {
        EVENT_COMPARATOR = new EventDispatcher$1();
    }
    
    public EventDispatcher(final ReactApplicationContext mReactContext) {
        this.mEventsStagingLock = new Object();
        this.mEventsToDispatchLock = new Object();
        this.mEventCookieToLastEventIdx = (LongSparseArray<Integer>)new LongSparseArray();
        this.mEventNameToEventId = (Map<String, Short>)MapBuilder.newHashMap();
        this.mDispatchEventsRunnable = new EventDispatcher$DispatchEventsRunnable(this, null);
        this.mEventStaging = new ArrayList<Event>();
        this.mListeners = new ArrayList<EventDispatcherListener>();
        this.mEventsToDispatch = new Event[16];
        this.mEventsToDispatchSize = 0;
        this.mNextEventTypeId = 0;
        this.mHasDispatchScheduled = false;
        this.mHasDispatchScheduledCount = 0;
        (this.mReactContext = mReactContext).addLifecycleEventListener(this);
        this.mCurrentFrameCallback = new EventDispatcher$ScheduleDispatchFrameCallback(this, null);
    }
    
    private void addEventToEventsToDispatch(final Event event) {
        if (this.mEventsToDispatchSize == this.mEventsToDispatch.length) {
            this.mEventsToDispatch = Arrays.copyOf(this.mEventsToDispatch, this.mEventsToDispatch.length * 2);
        }
        this.mEventsToDispatch[this.mEventsToDispatchSize++] = event;
    }
    
    private void clearEventsToDispatch() {
        Arrays.fill(this.mEventsToDispatch, 0, this.mEventsToDispatchSize, null);
        this.mEventsToDispatchSize = 0;
    }
    
    private long getEventCookie(final int n, final String s, final short n2) {
        final Short n3 = this.mEventNameToEventId.get(s);
        short n4;
        if (n3 != null) {
            n4 = n3;
        }
        else {
            n4 = this.mNextEventTypeId;
            this.mNextEventTypeId = (short)(n4 + 1);
            this.mEventNameToEventId.put(s, n4);
        }
        return getEventCookie(n, n4, n2);
    }
    
    private static long getEventCookie(final int n, final short n2, final short n3) {
        return n | (n2 & 0xFFFFL) << 32 | (n3 & 0xFFFFL) << 48;
    }
    
    private void moveStagedEventsToDispatchQueue() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsStagingLock:Ljava/lang/Object;
        //     4: astore          7
        //     6: aload           7
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsToDispatchLock:Ljava/lang/Object;
        //    13: astore          8
        //    15: aload           8
        //    17: monitorenter   
        //    18: iconst_0       
        //    19: istore_1       
        //    20: iload_1        
        //    21: aload_0        
        //    22: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventStaging:Ljava/util/ArrayList;
        //    25: invokevirtual   java/util/ArrayList.size:()I
        //    28: if_icmpge       226
        //    31: aload_0        
        //    32: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventStaging:Ljava/util/ArrayList;
        //    35: iload_1        
        //    36: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    39: checkcast       Lcom/facebook/react/uimanager/events/Event;
        //    42: astore          4
        //    44: aload           4
        //    46: invokevirtual   com/facebook/react/uimanager/events/Event.canCoalesce:()Z
        //    49: ifne            61
        //    52: aload_0        
        //    53: aload           4
        //    55: invokespecial   com/facebook/react/uimanager/events/EventDispatcher.addEventToEventsToDispatch:(Lcom/facebook/react/uimanager/events/Event;)V
        //    58: goto            240
        //    61: aload_0        
        //    62: aload           4
        //    64: invokevirtual   com/facebook/react/uimanager/events/Event.getViewTag:()I
        //    67: aload           4
        //    69: invokevirtual   com/facebook/react/uimanager/events/Event.getEventName:()Ljava/lang/String;
        //    72: aload           4
        //    74: invokevirtual   com/facebook/react/uimanager/events/Event.getCoalescingKey:()S
        //    77: invokespecial   com/facebook/react/uimanager/events/EventDispatcher.getEventCookie:(ILjava/lang/String;S)J
        //    80: lstore_2       
        //    81: aload_0        
        //    82: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventCookieToLastEventIdx:Landroid/util/LongSparseArray;
        //    85: lload_2        
        //    86: invokevirtual   android/util/LongSparseArray.get:(J)Ljava/lang/Object;
        //    89: checkcast       Ljava/lang/Integer;
        //    92: astore          9
        //    94: aload           9
        //    96: ifnonnull       165
        //    99: aload_0        
        //   100: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventCookieToLastEventIdx:Landroid/util/LongSparseArray;
        //   103: lload_2        
        //   104: aload_0        
        //   105: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsToDispatchSize:I
        //   108: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   111: invokevirtual   android/util/LongSparseArray.put:(JLjava/lang/Object;)V
        //   114: aconst_null    
        //   115: astore          6
        //   117: aload           4
        //   119: astore          5
        //   121: aload           6
        //   123: astore          4
        //   125: aload           5
        //   127: ifnull          136
        //   130: aload_0        
        //   131: aload           5
        //   133: invokespecial   com/facebook/react/uimanager/events/EventDispatcher.addEventToEventsToDispatch:(Lcom/facebook/react/uimanager/events/Event;)V
        //   136: aload           4
        //   138: ifnull          240
        //   141: aload           4
        //   143: invokevirtual   com/facebook/react/uimanager/events/Event.dispose:()V
        //   146: goto            240
        //   149: astore          4
        //   151: aload           8
        //   153: monitorexit    
        //   154: aload           4
        //   156: athrow         
        //   157: astore          4
        //   159: aload           7
        //   161: monitorexit    
        //   162: aload           4
        //   164: athrow         
        //   165: aload_0        
        //   166: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsToDispatch:[Lcom/facebook/react/uimanager/events/Event;
        //   169: aload           9
        //   171: invokevirtual   java/lang/Integer.intValue:()I
        //   174: aaload         
        //   175: astore          6
        //   177: aload           4
        //   179: aload           6
        //   181: invokevirtual   com/facebook/react/uimanager/events/Event.coalesce:(Lcom/facebook/react/uimanager/events/Event;)Lcom/facebook/react/uimanager/events/Event;
        //   184: astore          5
        //   186: aload           5
        //   188: aload           6
        //   190: if_acmpeq       247
        //   193: aload_0        
        //   194: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventCookieToLastEventIdx:Landroid/util/LongSparseArray;
        //   197: lload_2        
        //   198: aload_0        
        //   199: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsToDispatchSize:I
        //   202: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   205: invokevirtual   android/util/LongSparseArray.put:(JLjava/lang/Object;)V
        //   208: aload_0        
        //   209: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventsToDispatch:[Lcom/facebook/react/uimanager/events/Event;
        //   212: aload           9
        //   214: invokevirtual   java/lang/Integer.intValue:()I
        //   217: aconst_null    
        //   218: aastore        
        //   219: aload           6
        //   221: astore          4
        //   223: goto            125
        //   226: aload           8
        //   228: monitorexit    
        //   229: aload_0        
        //   230: getfield        com/facebook/react/uimanager/events/EventDispatcher.mEventStaging:Ljava/util/ArrayList;
        //   233: invokevirtual   java/util/ArrayList.clear:()V
        //   236: aload           7
        //   238: monitorexit    
        //   239: return         
        //   240: iload_1        
        //   241: iconst_1       
        //   242: iadd           
        //   243: istore_1       
        //   244: goto            20
        //   247: aconst_null    
        //   248: astore          5
        //   250: goto            125
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      18     157    165    Any
        //  20     58     149    157    Any
        //  61     94     149    157    Any
        //  99     114    149    157    Any
        //  130    136    149    157    Any
        //  141    146    149    157    Any
        //  151    154    149    157    Any
        //  154    157    157    165    Any
        //  159    162    157    165    Any
        //  165    186    149    157    Any
        //  193    219    149    157    Any
        //  226    229    149    157    Any
        //  229    239    157    165    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: 2
        //     at com.strobel.core.ArrayUtilities$UnmodifiableArrayList.get(ArrayUtilities.java:2022)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCallCore(AstMethodBodyBuilder.java:1310)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCall(AstMethodBodyBuilder.java:1283)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1195)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:714)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:437)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:425)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
    
    private void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }
    
    public void addListener(final EventDispatcherListener eventDispatcherListener) {
        this.mListeners.add(eventDispatcherListener);
    }
    
    public void dispatchEvent(final Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        final Iterator<EventDispatcherListener> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            Systrace.startAsyncFlow(0L, event.getEventName(), event.getUniqueID());
            // monitorexit(this.mEventsStagingLock)
            if (this.mRCTEventEmitter != null) {
                this.mCurrentFrameCallback.maybePostFromNonUI();
            }
        }
    }
    
    public void onCatalystInstanceDestroyed() {
        this.stopFrameCallback();
    }
    
    @Override
    public void onHostPause() {
        this.stopFrameCallback();
    }
    
    @Override
    public void onHostResume() {
        UiThreadUtil.assertOnUiThread();
        if (this.mRCTEventEmitter == null) {
            this.mRCTEventEmitter = this.mReactContext.getJSModule(RCTEventEmitter.class);
        }
        this.mCurrentFrameCallback.maybePost();
    }
}
