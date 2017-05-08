// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.view.KeyEvent;
import android.view.InputEvent;
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
import android.util.Base64;
import android.webkit.WebViewClient;

public final class ed extends WebViewClient
{
    private WebViewClient a;
    private d b;
    private final String c;
    private e d;
    private c e;
    
    public ed(final WebViewClient a, final e d, final d b, final String s) {
        this.a = a;
        this.d = d;
        this.b = b;
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:(function() {");
        sb.append("  if (typeof(Crittercism) !== \"undefined\") {");
        sb.append("    Crittercism.init({");
        sb.append("      \"platform\": \"android\"});");
        sb.append("  } else {");
        sb.append("    (");
        sb.append("      function() {");
        sb.append("        var parent = document.getElementsByTagName('head').item(0);");
        sb.append("        var script = document.createElement('script');");
        sb.append("        script.type = 'text/javascript';");
        sb.append("        script.innerHTML = window.atob('");
        sb.append(Base64.encodeToString(s.getBytes(), 2));
        sb.append("                                     ');");
        sb.append("        parent.appendChild(script)");
        sb.append("      }");
        sb.append("    )();");
        sb.append("    if (typeof(BasicCrittercism) !== \"undefined\") {");
        sb.append("      BasicCrittercism.instrumentOnError({");
        sb.append("        errorCallback: function(errorMsg, stackStr) {");
        sb.append("          _crttr.logError(errorMsg, stackStr);");
        sb.append("          }, ");
        sb.append("        platform: \"android\"");
        sb.append("      });");
        sb.append("      BasicCrittercism.initApm();");
        sb.append("    } ");
        sb.append("  }");
        sb.append("})()");
        this.c = sb.toString();
    }
    
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
    
    @TargetApi(21)
    public final void onUnhandledInputEvent(final WebView webView, final InputEvent inputEvent) {
        if (this.a != null) {
            this.a.onUnhandledInputEvent(webView, inputEvent);
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
        //    33: ifnull          233
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
        //    68: ifne            184
        //    71: aload_1        
        //    72: areturn        
        //    73: ldc_w           "******** shouldInterceptRequest (Lollipop) part 1"
        //    76: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //    79: aload_0        
        //    80: monitorenter   
        //    81: aload_0        
        //    82: new             Lcom/crittercism/internal/c;
        //    85: dup            
        //    86: invokespecial   com/crittercism/internal/c.<init>:()V
        //    89: putfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //    92: aload_0        
        //    93: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //    96: aload_2        
        //    97: invokeinterface android/webkit/WebResourceRequest.getUrl:()Landroid/net/Uri;
        //   102: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //   105: invokevirtual   com/crittercism/internal/c.a:(Ljava/lang/String;)V
        //   108: aload_0        
        //   109: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   112: aload_2        
        //   113: invokeinterface android/webkit/WebResourceRequest.getMethod:()Ljava/lang/String;
        //   118: putfield        com/crittercism/internal/c.f:Ljava/lang/String;
        //   121: aload_0        
        //   122: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   125: lload_3        
        //   126: invokevirtual   com/crittercism/internal/c.c:(J)V
        //   129: aload_0        
        //   130: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   133: aload_0        
        //   134: getfield        com/crittercism/internal/ed.b:Lcom/crittercism/internal/d;
        //   137: invokevirtual   com/crittercism/internal/d.a:()Lcom/crittercism/internal/b;
        //   140: putfield        com/crittercism/internal/c.j:Lcom/crittercism/internal/b;
        //   143: invokestatic    com/crittercism/internal/ba.b:()Z
        //   146: ifeq            159
        //   149: aload_0        
        //   150: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   153: invokestatic    com/crittercism/internal/ba.a:()Landroid/location/Location;
        //   156: invokevirtual   com/crittercism/internal/c.a:(Landroid/location/Location;)V
        //   159: aload_0        
        //   160: monitorexit    
        //   161: goto            29
        //   164: astore          6
        //   166: aload_0        
        //   167: monitorexit    
        //   168: aload           6
        //   170: athrow         
        //   171: astore_1       
        //   172: aload_1        
        //   173: athrow         
        //   174: astore          6
        //   176: aload           6
        //   178: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   181: goto            29
        //   184: ldc_w           "******** shouldInterceptRequest (Lollipop) part 2"
        //   187: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //   190: aload_1        
        //   191: ifnull          71
        //   194: aload_0        
        //   195: monitorenter   
        //   196: aload_0        
        //   197: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   200: ifnull          214
        //   203: aload_0        
        //   204: getfield        com/crittercism/internal/ed.e:Lcom/crittercism/internal/c;
        //   207: aload_1        
        //   208: invokevirtual   android/webkit/WebResourceResponse.getStatusCode:()I
        //   211: putfield        com/crittercism/internal/c.e:I
        //   214: aload_0        
        //   215: monitorexit    
        //   216: aload_1        
        //   217: areturn        
        //   218: astore_2       
        //   219: aload_0        
        //   220: monitorexit    
        //   221: aload_2        
        //   222: athrow         
        //   223: astore_1       
        //   224: aload_1        
        //   225: athrow         
        //   226: astore_2       
        //   227: aload_2        
        //   228: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   231: aload_1        
        //   232: areturn        
        //   233: aconst_null    
        //   234: astore_1       
        //   235: goto            46
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  4      12     171    174    Ljava/lang/ThreadDeath;
        //  4      12     174    184    Ljava/lang/Throwable;
        //  16     24     171    174    Ljava/lang/ThreadDeath;
        //  16     24     174    184    Ljava/lang/Throwable;
        //  46     54     223    226    Ljava/lang/ThreadDeath;
        //  46     54     226    233    Ljava/lang/Throwable;
        //  58     66     223    226    Ljava/lang/ThreadDeath;
        //  58     66     226    233    Ljava/lang/Throwable;
        //  73     81     171    174    Ljava/lang/ThreadDeath;
        //  73     81     174    184    Ljava/lang/Throwable;
        //  81     159    164    171    Any
        //  159    161    164    171    Any
        //  166    168    164    171    Any
        //  168    171    171    174    Ljava/lang/ThreadDeath;
        //  168    171    174    184    Ljava/lang/Throwable;
        //  184    190    223    226    Ljava/lang/ThreadDeath;
        //  184    190    226    233    Ljava/lang/Throwable;
        //  194    196    223    226    Ljava/lang/ThreadDeath;
        //  194    196    226    233    Ljava/lang/Throwable;
        //  196    214    218    223    Any
        //  214    216    218    223    Any
        //  219    221    218    223    Any
        //  221    223    223    226    Ljava/lang/ThreadDeath;
        //  221    223    226    233    Ljava/lang/Throwable;
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
