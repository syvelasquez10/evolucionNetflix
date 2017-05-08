// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Build;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

final class ErrorLoggingManager$1 implements UncaughtExceptionHandler
{
    final /* synthetic */ UncaughtExceptionHandler val$critDefaultHandler;
    final /* synthetic */ Context val$globalContext;
    
    ErrorLoggingManager$1(final Context val$globalContext, final UncaughtExceptionHandler val$critDefaultHandler) {
        this.val$globalContext = val$globalContext;
        this.val$critDefaultHandler = val$critDefaultHandler;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        while (true) {
            try {
                Throwable t2;
                if (PersistentConfig.inMementoTest(this.val$globalContext)) {
                    t2 = wrapThrowableWithPrefix(String.format("Memento_%d ", PersistentConfig.getMemento(this.val$globalContext).getCellId()), t);
                }
                else if (PersistentConfig.inMemento2Test(this.val$globalContext)) {
                    t2 = wrapThrowableWithPrefix(String.format("Memento2_%d ", PersistentConfig.getMemento2(this.val$globalContext).getCellId()), t);
                }
                else if (PrefetchLolomoABTestUtils.isInTest(this.val$globalContext)) {
                    final String format = String.format("AimLow7480_%d ", PersistentConfig.getPrefetchLolomoConfig(this.val$globalContext).getCellId());
                    if (Log.isLoggable()) {
                        Log.d("nf_log_crit", "uncaughtException: message = " + format);
                    }
                    t2 = wrapThrowableWithPrefix(format, t);
                }
                else {
                    final int n = PersistentConfig.getCoppola1ABTestCell(this.val$globalContext).ordinal() + 1;
                    if (n > 1) {
                        t2 = wrapThrowableWithPrefix(String.format("Coppola_%d ", n), t);
                    }
                    else {
                        final int n2 = PersistentConfig.getCoppola2ABTestCell(this.val$globalContext).ordinal() + 1;
                        t2 = t;
                        if (n2 > 1) {
                            t2 = wrapThrowableWithPrefix(String.format("Coppola2_%d ", n2), t);
                        }
                    }
                }
                this.val$critDefaultHandler.uncaughtException(thread, t2);
            }
            catch (Throwable t3) {
                final String string = "SPY-9027 - got throwable while wrapping stack trace: " + t3;
                ErrorLoggingManager.logHandledException(string);
                Log.e("nf_log_crit", string);
                final Throwable t2 = t;
                continue;
            }
            break;
        }
    }
}
