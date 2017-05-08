// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.MessageQueue$IdleHandler;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import android.app.Activity;
import android.content.Context;

public final class ap
{
    public bk a;
    public boolean b;
    public az c;
    public dr d;
    public Context e;
    public ax f;
    private volatile boolean g;
    
    public ap(final az c, final dr d, final Context e, final ax f) {
        this.g = false;
        this.b = false;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public static boolean a(final Context context) {
        try {
            return b(context);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.a("Could not find number of visible Activities. Please report this to support@crittercism.com", t);
            return false;
        }
    }
    
    private static boolean b(final Context context) {
        if (context instanceof Activity) {
            return true;
        }
        Class<?> forName;
        Method method;
        try {
            forName = Class.forName("android.app.ActivityThread");
            try {
                method = forName.getMethod("currentActivityThread", (Class<?>[])new Class[0]);
                if (method == null) {
                    throw new NoSuchMethodError("no method: " + "currentActivityThread");
                }
            }
            catch (NoSuchMethodException ex) {
                dw.a(ex);
                return false;
            }
        }
        catch (ClassNotFoundException ex2) {
            dw.a(ex2);
            return false;
        }
        method.setAccessible(true);
        if (!forName.isAssignableFrom(method.getReturnType())) {
            throw new ClassCastException("currentActivityThread" + " does not return a " + forName.getName());
        }
        Object invoke;
        try {
            invoke = method.invoke(null, new Object[0]);
            if (invoke == null) {
                dw.a(new NullPointerException("cannot retrieve current ActivityThread"));
                return false;
            }
        }
        catch (IllegalAccessException ex3) {
            dw.a(ex3);
            return false;
        }
        catch (InvocationTargetException ex4) {
            dw.a(ex4);
            return false;
        }
        Class<Integer> type;
        Field declaredField;
        try {
            type = Integer.TYPE;
            declaredField = forName.getDeclaredField("mNumVisibleActivities");
            if (declaredField == null) {
                throw new NoSuchFieldError("no field: " + "mNumVisibleActivities");
            }
        }
        catch (NoSuchFieldException ex5) {
            dw.a(ex5);
            return false;
        }
        declaredField.setAccessible(true);
        if (!type.isAssignableFrom(declaredField.getType())) {
            throw new ClassCastException("mNumVisibleActivities" + " is not a " + type.getName());
        }
        try {
            final Integer n = (Integer)declaredField.get(invoke);
            if (n != null && n > 0) {
                return true;
            }
        }
        catch (IllegalAccessException ex6) {
            dw.a(ex6);
            return false;
        }
        return false;
    }
    
    private boolean e() {
        if (this.g) {
            return true;
        }
        synchronized (this) {
            return this.g;
        }
    }
    
    public final Runnable a(final au au, final Context context, final az az, final ar ar, final ax ax) {
        this.c();
        return new ap$1(this, au, az, context, ar, ax);
    }
    
    public final void a() {
        this.b();
        if (this.d()) {
            this.f.a(this.a(this.f, this.e, this.c, this.f, this.f));
        }
    }
    
    public final void b() {
        if (!this.b) {
            this.b = true;
            be.b(this.f);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Looper.myQueue().addIdleHandler((MessageQueue$IdleHandler)new ax$c((byte)0));
            }
        }
    }
    
    public final void c() {
        if (this.g) {
            return;
        }
        synchronized (this) {
            this.g = true;
        }
    }
    
    public final boolean d() {
        if (this.e()) {
            dw.d("not sending a new app load - already sent");
            return false;
        }
        if (this.c.delaySendingAppLoad()) {
            dw.d("not sending a new app load - customer asked for delay");
            return false;
        }
        if (!this.b) {
            dw.d("not sending a new app load - have not seen one yet");
            return false;
        }
        return true;
    }
}
