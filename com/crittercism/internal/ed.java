// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.view.KeyEvent;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.ClientCertRequest;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.annotation.TargetApi;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class ed extends WebViewClient
{
    private WebViewClient a;
    private d b;
    private final String c;
    private e d;
    private c e;
    
    public final void doUpdateVisitedHistory(final WebView webView, final String s, final boolean b) {
        if (this.a != null) {
            this.a.doUpdateVisitedHistory(webView, s, b);
        }
    }
    
    public final void onFormResubmission(final WebView webView, final Message message, final Message message2) {
        if (this.a != null) {
            this.a.onFormResubmission(webView, message, message2);
        }
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        if (this.a != null) {
            this.a.onLoadResource(webView, s);
        }
    }
    
    @TargetApi(23)
    public final void onPageCommitVisible(final WebView webView, final String s) {
        if (this.a != null) {
            this.a.onPageCommitVisible(webView, s);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        while (true) {
            try {
                dw.d("******** onPageFinished: " + s);
                Label_0064: {
                    if (Build$VERSION.SDK_INT < 23) {
                        break Label_0064;
                    }
                    synchronized (this) {
                        if (this.e != null) {
                            this.e.d(System.currentTimeMillis());
                            this.d.a(this.e);
                            this.e = null;
                        }
                        // monitorexit(this)
                        if (webView.getSettings().getJavaScriptEnabled()) {
                            webView.loadUrl(this.c);
                        }
                        if (this.a != null) {
                            this.a.onPageFinished(webView, s);
                        }
                    }
                }
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
                continue;
            }
            break;
        }
    }
    
    public final void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        dw.d("******** onPageStarted: " + s);
        if (this.a != null) {
            this.a.onPageStarted(webView, s, bitmap);
        }
    }
    
    @TargetApi(21)
    public final void onReceivedClientCertRequest(final WebView webView, final ClientCertRequest clientCertRequest) {
        if (this.a != null) {
            this.a.onReceivedClientCertRequest(webView, clientCertRequest);
        }
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        if (this.a != null) {
            this.a.onReceivedError(webView, n, s, s2);
        }
    }
    
    @TargetApi(23)
    public final void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        while (true) {
            try {
                if (Build$VERSION.SDK_INT >= 23) {
                    dw.d("******** onReceivedError (Marshmallow, no http)");
                    if (webResourceRequest == null) {
                        dw.d("null request");
                    }
                    else {
                        final boolean forMainFrame = webResourceRequest.isForMainFrame();
                        final StringBuilder sb = new StringBuilder();
                        if (!forMainFrame) {
                            goto Label_0104;
                        }
                        dw.d(sb.append("").append("main frame").toString());
                        if (forMainFrame) {
                            if (webResourceError != null) {
                                goto Label_0111;
                            }
                            dw.d("null error (no error code)");
                        }
                    }
                }
                if (this.a != null) {
                    this.a.onReceivedError(webView, webResourceRequest, webResourceError);
                }
                return;
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
                continue;
            }
            try {
                Label_0131: {
                    if (this.e != null) {
                        this.e.a(webResourceError.getErrorCode());
                        break Label_0131;
                    }
                    break Label_0131;
                }
                continue;
            }
            finally {}
            break;
        }
    }
    
    public final void onReceivedHttpAuthRequest(final WebView webView, final HttpAuthHandler httpAuthHandler, final String s, final String s2) {
        if (this.a != null) {
            this.a.onReceivedHttpAuthRequest(webView, httpAuthHandler, s, s2);
        }
    }
    
    @TargetApi(23)
    public final void onReceivedHttpError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceResponse webResourceResponse) {
        while (true) {
            try {
                if (Build$VERSION.SDK_INT >= 23) {
                    dw.d("******** onReceivedHttpError (Marshmallow)");
                    final boolean forMainFrame = webResourceRequest.isForMainFrame();
                    final StringBuilder sb = new StringBuilder();
                    String s;
                    if (forMainFrame) {
                        s = "";
                    }
                    else {
                        s = "not ";
                    }
                    dw.d(sb.append(s).append("main frame").toString());
                    if (forMainFrame) {
                        if (webResourceResponse == null) {
                            dw.d("null response (no status code)");
                        }
                        else {
                            synchronized (this) {
                                if (this.e != null) {
                                    this.e.e = webResourceResponse.getStatusCode();
                                }
                            }
                        }
                    }
                }
                if (this.a != null) {
                    this.a.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                }
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
                continue;
            }
            break;
        }
    }
    
    @TargetApi(12)
    public final void onReceivedLoginRequest(final WebView webView, final String s, final String s2, final String s3) {
        if (this.a != null) {
            this.a.onReceivedLoginRequest(webView, s, s2, s3);
        }
    }
    
    @TargetApi(8)
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        if (this.a != null) {
            this.a.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }
    
    public final void onScaleChanged(final WebView webView, final float n, final float n2) {
        if (this.a != null) {
            this.a.onScaleChanged(webView, n, n2);
        }
    }
    
    public final void onTooManyRedirects(final WebView webView, final Message message, final Message message2) {
        if (this.a != null) {
            this.a.onTooManyRedirects(webView, message, message2);
        }
    }
    
    public final void onUnhandledKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        if (this.a != null) {
            this.a.onUnhandledKeyEvent(webView, keyEvent);
        }
    }
    
    @TargetApi(21)
    public final WebResourceResponse shouldInterceptRequest(final WebView p0, final WebResourceRequest p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.currentTimeMillis:()J
        //     3: lstore_3       
        //     4: getstatic       android/os/Build$VERSION.SDK_INT:I
        //     7: bipush          23
        //     9: if_icmplt       29
        //    12: aload_2        
        //    13: ifnull          29
        //    16: aload_2        
        //    17: invokeinterface android/webkit/WebResourceRequest.isForMainFrame:()Z
        //    22: istore          5
        //    24: iload           5
        //    26: ifne            73
        //    29: aload_0        
        //    30: getfield        com/crittercism/internal/ed.a:Landroid/webkit/WebViewClient;
        //    33: ifnull          231
        //    36: aload_0        
        //    37: getfield        com/crittercism/internal/ed.a:Landroid/webkit/WebViewClient;
        //    40: aload_1        
        //    41: aload_2        
        //    42: invokevirtual   android/webkit/WebViewClient.shouldInterceptRequest:(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
        //    45: astore_1       
        //    46: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    49: bipush          23
        //    51: if_icmplt       71
        //    54: aload_2        
        //    55: ifnull          71
        //    58: aload_2        
        //    59: invokeinterface android/webkit/WebResourceRequest.isForMainFrame:()Z
        //    64: istore          5
        //    66: iload           5
        //    68: ifne            183
        //    71: aload_1        
        //    72: areturn        
        //    73: ldc             "******** shouldInterceptRequest (Lollipop) part 1"
        //    75: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //    78: aload_0        
        //    79: monitorenter   
        //    80: aload_0        
        //    81: new             Lcom/crittercism/internal/c;
        //    84: dup            
        //    85: invokespecial   com/crittercism/internal/c.<init>:()V
        //    88: putfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //    91: aload_0        
        //    92: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //    95: aload_2        
        //    96: invokeinterface android/webkit/WebResourceRequest.getUrl:()Landroid/net/Uri;
        //   101: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //   104: invokevirtual   com/crittercism/internal/c.a:(Ljava/lang/String;)V
        //   107: aload_0        
        //   108: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   111: aload_2        
        //   112: invokeinterface android/webkit/WebResourceRequest.getMethod:()Ljava/lang/String;
        //   117: putfield        com/crittercism/internal/c.f:Ljava/lang/String;
        //   120: aload_0        
        //   121: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   124: lload_3        
        //   125: invokevirtual   com/crittercism/internal/c.c:(J)V
        //   128: aload_0        
        //   129: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   132: aload_0        
        //   133: getfield        com/crittercism/internal/ed.b:Lcom/crittercism/internal/d;
        //   136: invokevirtual   com/crittercism/internal/d.a:()Lcom/crittercism/internal/b;
        //   139: putfield        com/crittercism/internal/c.j:Lcom/crittercism/internal/b;
        //   142: invokestatic    com/crittercism/internal/ba.b:()Z
        //   145: ifeq            158
        //   148: aload_0        
        //   149: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   152: invokestatic    com/crittercism/internal/ba.a:()Landroid/location/Location;
        //   155: invokevirtual   com/crittercism/internal/c.a:(Landroid/location/Location;)V
        //   158: aload_0        
        //   159: monitorexit    
        //   160: goto            29
        //   163: astore          6
        //   165: aload_0        
        //   166: monitorexit    
        //   167: aload           6
        //   169: athrow         
        //   170: astore_1       
        //   171: aload_1        
        //   172: athrow         
        //   173: astore          6
        //   175: aload           6
        //   177: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   180: goto            29
        //   183: ldc             "******** shouldInterceptRequest (Lollipop) part 2"
        //   185: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //   188: aload_1        
        //   189: ifnull          71
        //   192: aload_0        
        //   193: monitorenter   
        //   194: aload_0        
        //   195: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   198: ifnull          212
        //   201: aload_0        
        //   202: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   205: aload_1        
        //   206: invokevirtual   android/webkit/WebResourceResponse.getStatusCode:()I
        //   209: putfield        com/crittercism/internal/c.e:I
        //   212: aload_0        
        //   213: monitorexit    
        //   214: aload_1        
        //   215: areturn        
        //   216: astore_2       
        //   217: aload_0        
        //   218: monitorexit    
        //   219: aload_2        
        //   220: athrow         
        //   221: astore_1       
        //   222: aload_1        
        //   223: athrow         
        //   224: astore_2       
        //   225: aload_2        
        //   226: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   229: aload_1        
        //   230: areturn        
        //   231: aconst_null    
        //   232: astore_1       
        //   233: goto            46
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  4      12     170    173    Ljava/lang/ThreadDeath;
        //  4      12     173    183    Ljava/lang/Throwable;
        //  16     24     170    173    Ljava/lang/ThreadDeath;
        //  16     24     173    183    Ljava/lang/Throwable;
        //  46     54     221    224    Ljava/lang/ThreadDeath;
        //  46     54     224    231    Ljava/lang/Throwable;
        //  58     66     221    224    Ljava/lang/ThreadDeath;
        //  58     66     224    231    Ljava/lang/Throwable;
        //  73     80     170    173    Ljava/lang/ThreadDeath;
        //  73     80     173    183    Ljava/lang/Throwable;
        //  80     158    163    170    Any
        //  158    160    163    170    Any
        //  165    167    163    170    Any
        //  167    170    170    173    Ljava/lang/ThreadDeath;
        //  167    170    173    183    Ljava/lang/Throwable;
        //  183    188    221    224    Ljava/lang/ThreadDeath;
        //  183    188    224    231    Ljava/lang/Throwable;
        //  192    194    221    224    Ljava/lang/ThreadDeath;
        //  192    194    224    231    Ljava/lang/Throwable;
        //  194    212    216    221    Any
        //  212    214    216    221    Any
        //  217    219    216    221    Any
        //  219    221    221    224    Ljava/lang/ThreadDeath;
        //  219    221    224    231    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
    
    @TargetApi(11)
    public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        if (this.a != null) {
            return this.a.shouldInterceptRequest(webView, s);
        }
        return null;
    }
    
    public final boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        return this.a != null && this.a.shouldOverrideKeyEvent(webView, keyEvent);
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        dw.d("******** shouldOverrideUrlLoading: " + s);
        return this.a != null && this.a.shouldOverrideUrlLoading(webView, s);
    }
}
