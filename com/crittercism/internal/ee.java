// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.os.Build$VERSION;

public final class ee
{
    public ee() {
        if (Build$VERSION.SDK_INT < 14 || Build$VERSION.SDK_INT > 23) {
            throw new cj("API Level " + Build$VERSION.SDK_INT + " does not supportWebView monitoring. Skipping instrumentation.");
        }
    }
    
    public static WebViewClient a(final WebView webView) {
        try {
            final Object value = j.a(WebView.class, Class.forName("android.webkit.CallbackProxy"), true).get(webView);
            return (WebViewClient)value.getClass().getMethod("getWebViewClient", (Class<?>[])new Class[0]).invoke(value, new Object[0]);
        }
        catch (ClassNotFoundException ex) {
            throw new cj(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new cj(ex2);
        }
        catch (NoSuchMethodException ex3) {
            throw new cj(ex3);
        }
        catch (IllegalAccessException ex4) {
            throw new cj(ex4);
        }
        catch (SecurityException ex5) {
            throw new cj(ex5);
        }
    }
    
    public static WebViewClient b(final WebView webView) {
        try {
            final Object invoke = WebView.class.getMethod("getWebViewProvider", (Class<?>[])new Class[0]).invoke(webView, new Object[0]);
            return (WebViewClient)invoke.getClass().getMethod("getWebViewClient", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
        }
        catch (InvocationTargetException ex) {
            throw new cj(ex);
        }
        catch (NoSuchMethodException ex2) {
            throw new cj(ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new cj(ex3);
        }
        catch (SecurityException ex4) {
            throw new cj(ex4);
        }
    }
    
    public static WebViewClient c(final WebView webView) {
        try {
            final Object invoke = WebView.class.getMethod("getWebViewProvider", (Class<?>[])new Class[0]).invoke(webView, new Object[0]);
            final Field declaredField = invoke.getClass().getDeclaredField("mContentsClientAdapter");
            declaredField.setAccessible(true);
            final Object value = declaredField.get(invoke);
            final Field declaredField2 = value.getClass().getDeclaredField("mWebViewClient");
            declaredField2.setAccessible(true);
            return (WebViewClient)declaredField2.get(value);
        }
        catch (InvocationTargetException ex) {
            throw new cj(ex);
        }
        catch (NoSuchMethodException ex2) {
            throw new cj(ex2);
        }
        catch (NoSuchFieldException ex3) {
            throw new cj(ex3);
        }
        catch (IllegalAccessException ex4) {
            throw new cj(ex4);
        }
        catch (SecurityException ex5) {
            throw new cj(ex5);
        }
    }
}
