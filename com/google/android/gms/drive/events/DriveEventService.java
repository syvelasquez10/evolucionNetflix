// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.internal.ad;
import android.os.Message;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import android.os.Looper;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.drive.internal.OnEventResponse;
import java.util.concurrent.CountDownLatch;
import android.app.Service;

public abstract class DriveEventService extends Service implements ChangeListener, CompletionListener
{
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    private CountDownLatch NN;
    a NO;
    int NP;
    private final String mName;
    
    protected DriveEventService() {
        this("DriveEventService");
    }
    
    protected DriveEventService(final String mName) {
        this.NP = -1;
        this.mName = mName;
    }
    
    private void a(OnEventResponse ih) {
    Label_0121:
        while (true) {
            ih = (OnEventResponse)ih.ih();
            v.n("DriveEventService", "handleEventMessage: " + ih);
            while (true) {
                Label_0130: {
                    try {
                        switch (((DriveEvent)ih).getType()) {
                            case 1: {
                                this.onChange((ChangeEvent)ih);
                                return;
                            }
                            case 2: {
                                break Label_0121;
                            }
                            default: {
                                break Label_0130;
                            }
                        }
                        v.p(this.mName, "Unhandled event: " + ih);
                        return;
                    }
                    catch (Exception ex) {
                        v.a(this.mName, ex, "Error handling event: " + ih);
                        return;
                    }
                    break;
                }
                continue;
            }
        }
        this.onCompletion((CompletionEvent)ih);
    }
    
    private boolean bc(int n) {
        final boolean b = false;
        final String[] packagesForUid = this.getPackageManager().getPackagesForUid(n);
        boolean b2 = b;
        if (packagesForUid != null) {
            final int length = packagesForUid.length;
            n = 0;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if ("com.google.android.gms".equals(packagesForUid[n])) {
                    b2 = true;
                    break;
                }
                ++n;
            }
        }
        return b2;
    }
    
    private void hV() throws SecurityException {
        final int callingUid = this.getCallingUid();
        if (callingUid == this.NP) {
            return;
        }
        if (GooglePlayServicesUtil.b(this.getPackageManager(), "com.google.android.gms") && this.bc(callingUid)) {
            this.NP = callingUid;
            return;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
    
    protected int getCallingUid() {
        return Binder.getCallingUid();
    }
    
    public final IBinder onBind(final Intent intent) {
        while (true) {
            synchronized (this) {
                if ("com.google.android.gms.drive.events.HANDLE_EVENT".equals(intent.getAction())) {
                    Label_0065: {
                        if (this.NO != null) {
                            break Label_0065;
                        }
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        this.NN = new CountDownLatch(1);
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Looper.prepare();
                                    DriveEventService.this.NO = new a();
                                    countDownLatch.countDown();
                                    v.n("DriveEventService", "Bound and starting loop");
                                    Looper.loop();
                                    v.n("DriveEventService", "Finished loop");
                                }
                                finally {
                                    DriveEventService.this.NN.countDown();
                                }
                            }
                        }.start();
                        try {
                            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
                            return ((ad.a)new b()).asBinder();
                        }
                        catch (InterruptedException ex) {
                            throw new RuntimeException("Unable to start event handler", ex);
                        }
                    }
                }
            }
            return null;
        }
    }
    
    public void onChange(final ChangeEvent changeEvent) {
        v.p(this.mName, "Unhandled change event: " + changeEvent);
    }
    
    public void onCompletion(final CompletionEvent completionEvent) {
        v.p(this.mName, "Unhandled completion event: " + completionEvent);
    }
    
    public void onDestroy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: ldc             "DriveEventService"
        //     4: ldc             "onDestroy"
        //     6: invokestatic    com/google/android/gms/drive/internal/v.n:(Ljava/lang/String;Ljava/lang/String;)V
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/drive/events/DriveEventService.NO:Lcom/google/android/gms/drive/events/DriveEventService$a;
        //    13: ifnull          57
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/drive/events/DriveEventService.NO:Lcom/google/android/gms/drive/events/DriveEventService$a;
        //    20: invokestatic    com/google/android/gms/drive/events/DriveEventService$a.a:(Lcom/google/android/gms/drive/events/DriveEventService$a;)Landroid/os/Message;
        //    23: astore_1       
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/drive/events/DriveEventService.NO:Lcom/google/android/gms/drive/events/DriveEventService$a;
        //    28: aload_1        
        //    29: invokevirtual   com/google/android/gms/drive/events/DriveEventService$a.sendMessage:(Landroid/os/Message;)Z
        //    32: pop            
        //    33: aload_0        
        //    34: aconst_null    
        //    35: putfield        com/google/android/gms/drive/events/DriveEventService.NO:Lcom/google/android/gms/drive/events/DriveEventService$a;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/drive/events/DriveEventService.NN:Ljava/util/concurrent/CountDownLatch;
        //    42: ldc2_w          5000
        //    45: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    48: invokevirtual   java/util/concurrent/CountDownLatch.await:(JLjava/util/concurrent/TimeUnit;)Z
        //    51: pop            
        //    52: aload_0        
        //    53: aconst_null    
        //    54: putfield        com/google/android/gms/drive/events/DriveEventService.NN:Ljava/util/concurrent/CountDownLatch;
        //    57: aload_0        
        //    58: invokespecial   android/app/Service.onDestroy:()V
        //    61: aload_0        
        //    62: monitorexit    
        //    63: return         
        //    64: astore_1       
        //    65: aload_0        
        //    66: monitorexit    
        //    67: aload_1        
        //    68: athrow         
        //    69: astore_1       
        //    70: goto            52
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      38     64     69     Any
        //  38     52     69     73     Ljava/lang/InterruptedException;
        //  38     52     64     69     Any
        //  52     57     64     69     Any
        //  57     61     64     69     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
    
    public boolean onUnbind(final Intent intent) {
        return true;
    }
    
    final class a extends Handler
    {
        private Message b(final OnEventResponse onEventResponse) {
            return this.obtainMessage(1, (Object)onEventResponse);
        }
        
        private Message hW() {
            return this.obtainMessage(2);
        }
        
        public void handleMessage(final Message message) {
            v.n("DriveEventService", "handleMessage message type:" + message.what);
            switch (message.what) {
                default: {
                    v.p("DriveEventService", "Unexpected message type:" + message.what);
                }
                case 1: {
                    DriveEventService.this.a((OnEventResponse)message.obj);
                }
                case 2: {
                    this.getLooper().quit();
                }
            }
        }
    }
    
    final class b extends ad.a
    {
        public void c(final OnEventResponse onEventResponse) throws RemoteException {
            synchronized (DriveEventService.this) {
                v.n("DriveEventService", "onEvent: " + onEventResponse);
                n.i(DriveEventService.this.NO);
                DriveEventService.this.hV();
                DriveEventService.this.NO.sendMessage(DriveEventService.this.NO.b(onEventResponse));
            }
        }
    }
}
