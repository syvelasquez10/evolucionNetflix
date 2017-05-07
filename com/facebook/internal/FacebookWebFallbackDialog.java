// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.os.Bundle;
import android.webkit.WebView;
import android.os.Handler;
import android.os.Looper;
import com.facebook.widget.WebDialog$OnCompleteListener;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.widget.FacebookDialog$PendingCall;
import android.content.Context;
import com.facebook.widget.WebDialog;

public class FacebookWebFallbackDialog extends WebDialog
{
    private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
    private static final String TAG;
    private boolean waitingForDialogToClose;
    
    static {
        TAG = FacebookWebFallbackDialog.class.getName();
    }
    
    private FacebookWebFallbackDialog(final Context context, final String s, final String expectedRedirectUrl) {
        super(context, s);
        this.setExpectedRedirectUrl(expectedRedirectUrl);
    }
    
    public static boolean presentWebFallback(final Context context, final String s, final String s2, final FacebookDialog$PendingCall facebookDialog$PendingCall, final FacebookDialog$Callback facebookDialog$Callback) {
        if (Utility.isNullOrEmpty(s)) {
            return false;
        }
        final FacebookWebFallbackDialog facebookWebFallbackDialog = new FacebookWebFallbackDialog(context, s, String.format("fb%s://bridge/", s2));
        facebookWebFallbackDialog.setOnCompleteListener(new FacebookWebFallbackDialog$1(context, facebookDialog$PendingCall, facebookDialog$Callback));
        facebookWebFallbackDialog.show();
        return true;
    }
    
    @Override
    public void dismiss() {
        final WebView webView = this.getWebView();
        if (this.isListenerCalled() || webView == null || !webView.isShown()) {
            super.dismiss();
        }
        else if (!this.waitingForDialogToClose) {
            this.waitingForDialogToClose = true;
            webView.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed((Runnable)new FacebookWebFallbackDialog$2(this), 1500L);
        }
    }
    
    @Override
    protected Bundle parseResponseUri(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //     4: invokevirtual   android/net/Uri.getQuery:()Ljava/lang/String;
        //     7: invokestatic    com/facebook/internal/Utility.parseUrlQueryString:(Ljava/lang/String;)Landroid/os/Bundle;
        //    10: astore_3       
        //    11: aload_3        
        //    12: ldc             "bridge_args"
        //    14: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    17: astore_1       
        //    18: aload_3        
        //    19: ldc             "bridge_args"
        //    21: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //    24: aload_1        
        //    25: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    28: ifne            48
        //    31: aload_3        
        //    32: ldc             "com.facebook.platform.protocol.BRIDGE_ARGS"
        //    34: new             Lorg/json/JSONObject;
        //    37: dup            
        //    38: aload_1        
        //    39: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    42: invokestatic    com/facebook/internal/BundleJSONConverter.convertToBundle:(Lorg/json/JSONObject;)Landroid/os/Bundle;
        //    45: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //    48: aload_3        
        //    49: ldc             "method_results"
        //    51: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    54: astore_2       
        //    55: aload_3        
        //    56: ldc             "method_results"
        //    58: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //    61: aload_2        
        //    62: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    65: ifne            97
        //    68: aload_2        
        //    69: astore_1       
        //    70: aload_2        
        //    71: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    74: ifeq            80
        //    77: ldc             "{}"
        //    79: astore_1       
        //    80: aload_3        
        //    81: ldc             "com.facebook.platform.protocol.RESULT_ARGS"
        //    83: new             Lorg/json/JSONObject;
        //    86: dup            
        //    87: aload_1        
        //    88: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    91: invokestatic    com/facebook/internal/BundleJSONConverter.convertToBundle:(Lorg/json/JSONObject;)Landroid/os/Bundle;
        //    94: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //    97: aload_3        
        //    98: ldc             "version"
        //   100: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   103: aload_3        
        //   104: ldc             "com.facebook.platform.protocol.PROTOCOL_VERSION"
        //   106: invokestatic    com/facebook/internal/NativeProtocol.getLatestKnownVersion:()I
        //   109: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   112: aload_3        
        //   113: areturn        
        //   114: astore_1       
        //   115: getstatic       com/facebook/internal/FacebookWebFallbackDialog.TAG:Ljava/lang/String;
        //   118: ldc             "Unable to parse bridge_args JSON"
        //   120: aload_1        
        //   121: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   124: goto            48
        //   127: astore_1       
        //   128: getstatic       com/facebook/internal/FacebookWebFallbackDialog.TAG:Ljava/lang/String;
        //   131: ldc             "Unable to parse bridge_args JSON"
        //   133: aload_1        
        //   134: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   137: goto            97
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  31     48     114    127    Lorg/json/JSONException;
        //  80     97     127    140    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0080:
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
}
