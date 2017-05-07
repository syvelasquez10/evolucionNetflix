// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.view.View$OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.os.Handler;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.concurrent.ExecutorService;
import java.util.Set;
import java.util.Map;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import java.lang.reflect.Method;
import com.netflix.model.branches.FalkorValidator;
import android.support.v4.util.Pair;
import java.util.List;
import com.netflix.mediaclient.Log;
import java.util.concurrent.Callable;

abstract class FalkorValidationActivity$TestRunnerTask<T> implements Callable<FalkorValidationActivity$Result>
{
    private final String name;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    protected FalkorValidationActivity$TestRunnerTask(final FalkorValidationActivity this$0) {
        this.this$0 = this$0;
        this.name = this.getClass().getSimpleName();
    }
    
    protected FalkorValidationActivity$TestRunnerTask(final FalkorValidationActivity this$0, final String name) {
        this.this$0 = this$0;
        this.name = name;
        Log.v("FalkorValidationActivity", "Created task: " + name);
    }
    
    private FalkorValidationActivity$Result validate(final Object o, final Object o2) {
        FalkorValidationActivity$Result falkorValidationActivity$Result;
        if (o instanceof List) {
            falkorValidationActivity$Result = this.validateList((List<?>)o, (List<?>)o2);
        }
        else if (o instanceof Pair) {
            final Pair pair = (Pair)o;
            final Pair pair2 = (Pair)o2;
            if (!(falkorValidationActivity$Result = this.validate(pair.first, pair2.first)).isError()) {
                return this.validate(pair.second, pair2.second);
            }
        }
        else {
            if (o == null || o2 == null) {
                return FalkorValidationActivity$Result.NULL_OBJECT;
            }
            Log.d("FalkorValidationActivity", "Validating Volley class: " + o.getClass() + ", vs. Falkor class: " + o2.getClass());
            final Class[] array = FalkorValidationActivity.INTERFACE_MAP.get(o.getClass());
            if (array == null) {
                return FalkorValidationActivity$Result.INTERFACE_NOT_FOUND_IN_MAP.append(o.getClass().getCanonicalName());
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final Class clazz = array[i];
                if (!clazz.isInstance(o)) {
                    return FalkorValidationActivity$Result.INTERFACE_NOT_IMPLEMENTED.append(o.getClass().getName() + " does not implement " + clazz.getCanonicalName());
                }
                if (!clazz.isInstance(o2)) {
                    return FalkorValidationActivity$Result.INTERFACE_NOT_IMPLEMENTED.append(o2.getClass().getName() + " does not implement " + clazz.getCanonicalName());
                }
            }
            for (int length2 = array.length, j = 0; j < length2; ++j) {
                final Class clazz2 = array[j];
                Log.d("FalkorValidationActivity", "Getting methods for interface: " + clazz2);
                final Method[] methods = clazz2.getMethods();
                for (int length3 = methods.length, k = 0; k < length3; ++k) {
                    final Method method = methods[k];
                    if (method.getGenericParameterTypes().length > 0) {
                        Log.d("FalkorValidationActivity", "Skipping method because it requires input params: " + method.getName());
                    }
                    else {
                        final String ignoreKey = FalkorValidationActivity.createIgnoreKey(clazz2, method.getName());
                        if (!FalkorValidationActivity.METHOD_IGNORE_SET.contains(ignoreKey)) {
                            try {
                                final Object invoke = method.invoke(o, new Object[0]);
                                final Object invoke2 = method.invoke(o2, new Object[0]);
                                final StringBuilder append = new StringBuilder().append("Testing class: ");
                                String simpleName;
                                if (o == null) {
                                    simpleName = "n/a";
                                }
                                else {
                                    simpleName = o.getClass().getSimpleName();
                                }
                                final String string = append.append(simpleName).append(", method: ").append(method.getName()).append(", return type: ").append(method.getReturnType()).append(", Volley value: ").append(invoke).append(", Falkor value: ").append(invoke2).toString();
                                Log.d("FalkorValidationActivity", string);
                                if (invoke == null) {
                                    if (invoke2 != null) {
                                        return FalkorValidationActivity$Result.VALUE_MISMATCH.append(string);
                                    }
                                    continue;
                                }
                                else if (invoke instanceof List) {
                                    Log.d("FalkorValidationActivity", "Method returned a list, validating list...");
                                    final FalkorValidationActivity$Result validateList = this.validateList((List<?>)invoke, (List<?>)invoke2);
                                    if (validateList.isError()) {
                                        return validateList;
                                    }
                                    continue;
                                }
                                else if (invoke instanceof FalkorValidator) {
                                    final FalkorValidationActivity$Result validate = this.validate(invoke, invoke2);
                                    if (validate.isError()) {
                                        return validate;
                                    }
                                    continue;
                                }
                                else if ("getBookmarkPosition".equals(method.getName())) {
                                    final int intValue = (int)invoke;
                                    final int intValue2 = (int)invoke2;
                                    Log.d("FalkorValidationActivity", "GKB: Special check for bookmark positions - can differ by 1 ms");
                                    if (Math.abs(intValue - intValue2) > 1) {
                                        return FalkorValidationActivity$Result.VALUE_MISMATCH.append(string);
                                    }
                                    continue;
                                }
                                else {
                                    if (!invoke.equals(invoke2)) {
                                        return FalkorValidationActivity$Result.VALUE_MISMATCH.append(string);
                                    }
                                    continue;
                                }
                            }
                            catch (Exception ex) {
                                Log.d("FalkorValidationActivity", "Exception testing method: " + method.getName() + ", return type: " + method.getReturnType());
                                Log.handleException("FalkorValidationActivity", ex);
                                return FalkorValidationActivity$Result.INVOCATION_EXCEPTION.append(ex.getMessage());
                            }
                            break;
                        }
                        Log.d("FalkorValidationActivity", "Skipping method due to override: " + ignoreKey);
                    }
                }
            }
            return FalkorValidationActivity$Result.OK;
        }
        return falkorValidationActivity$Result;
    }
    
    private FalkorValidationActivity$Result validateList(final List<?> list, final List<?> list2) {
        if (list == null || list2 == null) {
            return FalkorValidationActivity$Result.NULL_LIST;
        }
        final String string = "Volley list size: " + list.size() + ", Falkor list size: " + list2.size();
        if (list.size() != list2.size()) {
            return FalkorValidationActivity$Result.LIST_SIZE_MISMATCH.append(string);
        }
        Log.d("FalkorValidationActivity", string);
        for (int i = 0; i < list.size(); ++i) {
            final FalkorValidationActivity$Result validate = this.validate(list.get(i), list2.get(i));
            if (validate.isError()) {
                return validate;
            }
        }
        return FalkorValidationActivity$Result.OK;
    }
    
    @Override
    public FalkorValidationActivity$Result call() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;
        //     3: dup            
        //     4: aload_0        
        //     5: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback.<init>:(Ljava/lang/Object;)V
        //     8: astore_1       
        //     9: aload_0        
        //    10: monitorenter   
        //    11: aload_0        
        //    12: aload_0        
        //    13: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //    16: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$500:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/service/falkor/FalkorAccess;
        //    19: aload_0        
        //    20: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //    23: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
        //    26: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getClientId:()I
        //    29: aload_0        
        //    30: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //    33: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
        //    36: aload_1        
        //    37: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getRequestId:(Lcom/netflix/mediaclient/servicemgr/ManagerCallback;)I
        //    40: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.makeFalkorRequest:(Lcom/netflix/mediaclient/service/falkor/FalkorAccess;II)V
        //    43: ldc             "FalkorValidationActivity"
        //    45: ldc_w           "Waiting for Falkor loading to complete..."
        //    48: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    51: pop            
        //    52: aload_0        
        //    53: invokevirtual   java/lang/Object.wait:()V
        //    56: ldc             "FalkorValidationActivity"
        //    58: ldc_w           "Falkor loading complete"
        //    61: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    64: pop            
        //    65: aload_0        
        //    66: monitorexit    
        //    67: new             Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;
        //    70: dup            
        //    71: aload_0        
        //    72: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback.<init>:(Ljava/lang/Object;)V
        //    75: astore_2       
        //    76: aload_0        
        //    77: monitorenter   
        //    78: aload_0        
        //    79: aload_0        
        //    80: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //    83: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$600:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/service/BrowseAccess;
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //    90: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
        //    93: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getClientId:()I
        //    96: aload_0        
        //    97: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
        //   100: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
        //   103: aload_2        
        //   104: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getRequestId:(Lcom/netflix/mediaclient/servicemgr/ManagerCallback;)I
        //   107: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.makeBrowseRequest:(Lcom/netflix/mediaclient/service/BrowseAccess;II)V
        //   110: ldc             "FalkorValidationActivity"
        //   112: ldc_w           "Waiting for Volley loading to complete..."
        //   115: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   118: pop            
        //   119: aload_0        
        //   120: invokevirtual   java/lang/Object.wait:()V
        //   123: ldc             "FalkorValidationActivity"
        //   125: ldc_w           "Volley loading complete"
        //   128: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   131: pop            
        //   132: aload_0        
        //   133: monitorexit    
        //   134: aload_0        
        //   135: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.shouldValidate:()Z
        //   138: ifeq            180
        //   141: aload_0        
        //   142: aload_0        
        //   143: aload_2        
        //   144: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.getOutput:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)Ljava/lang/Object;
        //   147: aload_0        
        //   148: aload_1        
        //   149: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.getOutput:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)Ljava/lang/Object;
        //   152: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.validate:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$Result;
        //   155: astore_2       
        //   156: aload_2        
        //   157: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$Result.isSucces:()Z
        //   160: ifeq            168
        //   163: aload_0        
        //   164: aload_1        
        //   165: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.storeResults:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)V
        //   168: aload_2        
        //   169: areturn        
        //   170: astore_1       
        //   171: aload_0        
        //   172: monitorexit    
        //   173: aload_1        
        //   174: athrow         
        //   175: astore_1       
        //   176: aload_0        
        //   177: monitorexit    
        //   178: aload_1        
        //   179: athrow         
        //   180: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity$Result.access$300:()Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$Result;
        //   183: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     67     170    175    Any
        //  78     134    175    180    Any
        //  171    173    170    175    Any
        //  176    178    175    180    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
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
    
    public String getName() {
        return this.name;
    }
    
    protected abstract T getOutput(final FalkorValidationActivity$ObjectNotifierCallback p0);
    
    protected abstract void makeBrowseRequest(final BrowseAccess p0, final int p1, final int p2);
    
    protected abstract void makeFalkorRequest(final FalkorAccess p0, final int p1, final int p2);
    
    protected boolean shouldValidate() {
        return true;
    }
    
    protected abstract void storeResults(final FalkorValidationActivity$ObjectNotifierCallback p0);
}
