// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.clipboard;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import com.facebook.react.bridge.ReactMethod;
import android.content.ClipData;
import com.facebook.react.bridge.Promise;
import android.content.ClipboardManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ClipboardModule extends ReactContextBaseJavaModule
{
    public ClipboardModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private ClipboardManager getClipboardService() {
        final ReactApplicationContext reactApplicationContext = this.getReactApplicationContext();
        this.getReactApplicationContext();
        return (ClipboardManager)reactApplicationContext.getSystemService("clipboard");
    }
    
    @Override
    public String getName() {
        return "Clipboard";
    }
    
    @ReactMethod
    public void getString(final Promise promise) {
        try {
            final ClipboardManager clipboardService = this.getClipboardService();
            final ClipData primaryClip = clipboardService.getPrimaryClip();
            if (primaryClip == null) {
                promise.resolve("");
                return;
            }
            if (primaryClip.getItemCount() >= 1) {
                promise.resolve("" + (Object)clipboardService.getPrimaryClip().getItemAt(0).getText());
                return;
            }
        }
        catch (Exception ex) {
            promise.reject(ex);
            return;
        }
        promise.resolve("");
    }
    
    @ReactMethod
    @SuppressLint({ "DeprecatedMethod" })
    public void setString(final String text) {
        this.getReactApplicationContext();
        if (Build$VERSION.SDK_INT >= 11) {
            this.getClipboardService().setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)text));
            return;
        }
        this.getClipboardService().setText((CharSequence)text);
    }
}
