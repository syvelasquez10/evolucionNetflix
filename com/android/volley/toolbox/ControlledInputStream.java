// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import java.io.InputStream;

public class ControlledInputStream extends InputStream
{
    private static long BYTES_READ_SINCE_MEASUREMENT;
    private static final Object LOCK;
    public static long NETWORK_SPEED_BYTES_PER_SECOND;
    public static long TIME_SPEED_MEASURED;
    private static long mBytesLimitPerTimeUnit;
    private static long mBytesReadInTimeUnit;
    private static long mTargetNetworkSpeedBytesPerSecond;
    private static long mTimeUnitStartTime;
    private final InputStream mInputStream;
    
    static {
        ControlledInputStream.mTargetNetworkSpeedBytesPerSecond = -1L;
        ControlledInputStream.mBytesLimitPerTimeUnit = ControlledInputStream.mTargetNetworkSpeedBytesPerSecond * 25L / TimeUnit.SECONDS.toMillis(1L);
        ControlledInputStream.mBytesReadInTimeUnit = 0L;
        ControlledInputStream.mTimeUnitStartTime = 0L;
        LOCK = new Object();
        ControlledInputStream.TIME_SPEED_MEASURED = 0L;
        ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT = 0L;
        ControlledInputStream.NETWORK_SPEED_BYTES_PER_SECOND = 0L;
    }
    
    public static long getTargetNetworkSpeedBytesPerSecond() {
        return ControlledInputStream.mTargetNetworkSpeedBytesPerSecond;
    }
    
    public static void setNetworkSpeedInBitsPerSecond(final long n) {
        Log.i("controlledInputStream", "..............setNetworkSpeedInBitsPerSecond speed in bitsPerSecond=" + n);
        if (n != -1L) {
            ControlledInputStream.mTargetNetworkSpeedBytesPerSecond = n / 8L;
            ControlledInputStream.mBytesLimitPerTimeUnit = ControlledInputStream.mTargetNetworkSpeedBytesPerSecond * 25L / TimeUnit.SECONDS.toMillis(1L);
            return;
        }
        Log.i("controlledInputStream", "............setNetworkSpeedInBitsPerSecond NO LIMIT");
        ControlledInputStream.mTargetNetworkSpeedBytesPerSecond = -1L;
    }
    
    @Override
    public int available() {
        return this.mInputStream.available();
    }
    
    @Override
    public void close() {
        this.mInputStream.close();
    }
    
    @Override
    public void mark(final int n) {
        synchronized (this) {
            this.mInputStream.mark(n);
        }
    }
    
    @Override
    public boolean markSupported() {
        return this.mInputStream.markSupported();
    }
    
    @Override
    public int read() {
        return this.mInputStream.read();
    }
    
    @Override
    public int read(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/android/volley/toolbox/ControlledInputStream.mInputStream:Ljava/io/InputStream;
        //     4: aload_1        
        //     5: invokevirtual   java/io/InputStream.read:([B)I
        //     8: istore_2       
        //     9: iload_2        
        //    10: ifge            15
        //    13: iload_2        
        //    14: ireturn        
        //    15: getstatic       com/android/volley/toolbox/ControlledInputStream.mTargetNetworkSpeedBytesPerSecond:J
        //    18: ldc2_w          -1
        //    21: lcmp           
        //    22: ifeq            13
        //    25: getstatic       com/android/volley/toolbox/ControlledInputStream.LOCK:Ljava/lang/Object;
        //    28: astore_1       
        //    29: aload_1        
        //    30: monitorenter   
        //    31: invokestatic    java/lang/System.currentTimeMillis:()J
        //    34: lstore_3       
        //    35: getstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //    38: iload_2        
        //    39: i2l            
        //    40: ladd           
        //    41: putstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //    44: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //    47: iload_2        
        //    48: i2l            
        //    49: ladd           
        //    50: putstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //    53: getstatic       com/android/volley/toolbox/ControlledInputStream.mTimeUnitStartTime:J
        //    56: lconst_0       
        //    57: lcmp           
        //    58: ifne            65
        //    61: lload_3        
        //    62: putstatic       com/android/volley/toolbox/ControlledInputStream.mTimeUnitStartTime:J
        //    65: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //    68: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesLimitPerTimeUnit:J
        //    71: lcmp           
        //    72: ifle            260
        //    75: ldc             "controlledInputStream"
        //    77: ldc             "read: mBytesReadInTimeUnit=%d mBytesLimitPerTimeUnit=%d"
        //    79: iconst_2       
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: iconst_0       
        //    85: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //    88: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    91: aastore        
        //    92: dup            
        //    93: iconst_1       
        //    94: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesLimitPerTimeUnit:J
        //    97: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   100: aastore        
        //   101: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   104: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   107: pop            
        //   108: invokestatic    java/lang/System.currentTimeMillis:()J
        //   111: lstore_3       
        //   112: lload_3        
        //   113: getstatic       com/android/volley/toolbox/ControlledInputStream.mTimeUnitStartTime:J
        //   116: lsub           
        //   117: lstore          7
        //   119: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //   122: ldc2_w          25
        //   125: lmul           
        //   126: getstatic       com/android/volley/toolbox/ControlledInputStream.mBytesLimitPerTimeUnit:J
        //   129: ldiv           
        //   130: lstore          9
        //   132: lload           9
        //   134: lload           7
        //   136: lsub           
        //   137: lstore          5
        //   139: lload_3        
        //   140: getstatic       com/android/volley/toolbox/ControlledInputStream.TIME_SPEED_MEASURED:J
        //   143: lsub           
        //   144: lstore          11
        //   146: lload           5
        //   148: lstore_3       
        //   149: lload           11
        //   151: lconst_0       
        //   152: lcmp           
        //   153: ifle            189
        //   156: lload           5
        //   158: lstore_3       
        //   159: getstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //   162: lconst_0       
        //   163: lcmp           
        //   164: ifle            189
        //   167: lload           5
        //   169: lstore_3       
        //   170: getstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //   173: ldc2_w          1000
        //   176: lmul           
        //   177: lload           11
        //   179: ldiv           
        //   180: getstatic       com/android/volley/toolbox/ControlledInputStream.mTargetNetworkSpeedBytesPerSecond:J
        //   183: lcmp           
        //   184: ifgt            189
        //   187: lconst_0       
        //   188: lstore_3       
        //   189: ldc             "controlledInputStream"
        //   191: ldc             "read: timeAlreadyTaken=%d timeShouldHaveTaken=%d"
        //   193: iconst_2       
        //   194: anewarray       Ljava/lang/Object;
        //   197: dup            
        //   198: iconst_0       
        //   199: lload           7
        //   201: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   204: aastore        
        //   205: dup            
        //   206: iconst_1       
        //   207: lload           9
        //   209: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   212: aastore        
        //   213: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   216: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   219: pop            
        //   220: ldc             "controlledInputStream"
        //   222: ldc             "read: ...sleeping... for=%dms"
        //   224: iconst_1       
        //   225: anewarray       Ljava/lang/Object;
        //   228: dup            
        //   229: iconst_0       
        //   230: lload_3        
        //   231: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   234: aastore        
        //   235: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   238: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   241: pop            
        //   242: lload_3        
        //   243: lconst_0       
        //   244: lcmp           
        //   245: ifle            252
        //   248: lload_3        
        //   249: invokestatic    java/lang/Thread.sleep:(J)V
        //   252: lconst_0       
        //   253: putstatic       com/android/volley/toolbox/ControlledInputStream.mBytesReadInTimeUnit:J
        //   256: lconst_0       
        //   257: putstatic       com/android/volley/toolbox/ControlledInputStream.mTimeUnitStartTime:J
        //   260: invokestatic    java/lang/System.currentTimeMillis:()J
        //   263: lstore_3       
        //   264: lload_3        
        //   265: getstatic       com/android/volley/toolbox/ControlledInputStream.TIME_SPEED_MEASURED:J
        //   268: lsub           
        //   269: lstore          5
        //   271: lload           5
        //   273: ldc2_w          1000
        //   276: lcmp           
        //   277: iflt            339
        //   280: getstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //   283: ldc2_w          1000
        //   286: lmul           
        //   287: lload           5
        //   289: ldiv           
        //   290: putstatic       com/android/volley/toolbox/ControlledInputStream.NETWORK_SPEED_BYTES_PER_SECOND:J
        //   293: lload_3        
        //   294: putstatic       com/android/volley/toolbox/ControlledInputStream.TIME_SPEED_MEASURED:J
        //   297: ldc             "controlledInputStream"
        //   299: new             Ljava/lang/StringBuilder;
        //   302: dup            
        //   303: invokespecial   java/lang/StringBuilder.<init>:()V
        //   306: ldc             "BytesReadInOneSecond="
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: getstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //   314: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   317: ldc             " target="
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: getstatic       com/android/volley/toolbox/ControlledInputStream.mTargetNetworkSpeedBytesPerSecond:J
        //   325: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   328: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   331: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   334: pop            
        //   335: lconst_0       
        //   336: putstatic       com/android/volley/toolbox/ControlledInputStream.BYTES_READ_SINCE_MEASUREMENT:J
        //   339: aload_1        
        //   340: monitorexit    
        //   341: iload_2        
        //   342: ireturn        
        //   343: astore          13
        //   345: aload_1        
        //   346: monitorexit    
        //   347: aload           13
        //   349: athrow         
        //   350: astore          13
        //   352: aload           13
        //   354: invokevirtual   java/lang/InterruptedException.printStackTrace:()V
        //   357: goto            252
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  31     65     343    350    Any
        //  65     132    343    350    Any
        //  139    146    343    350    Any
        //  159    167    343    350    Any
        //  170    187    343    350    Any
        //  189    220    343    350    Any
        //  220    242    350    360    Ljava/lang/InterruptedException;
        //  220    242    343    350    Any
        //  248    252    350    360    Ljava/lang/InterruptedException;
        //  248    252    343    350    Any
        //  252    260    343    350    Any
        //  260    271    343    350    Any
        //  280    339    343    350    Any
        //  339    341    343    350    Any
        //  345    347    343    350    Any
        //  352    357    343    350    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0252:
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
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        return this.mInputStream.read(array, n, n2);
    }
    
    @Override
    public void reset() {
        synchronized (this) {
            this.mInputStream.reset();
        }
    }
    
    @Override
    public long skip(final long n) {
        return this.mInputStream.skip(n);
    }
    
    @Override
    public String toString() {
        return this.mInputStream.toString();
    }
}
