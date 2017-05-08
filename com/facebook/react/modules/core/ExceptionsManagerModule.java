// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMethod;
import java.util.regex.Matcher;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.devsupport.DevSupportManager;
import java.util.regex.Pattern;
import com.facebook.react.bridge.BaseJavaModule;

public class ExceptionsManagerModule extends BaseJavaModule
{
    private static final Pattern mJsModuleIdPattern;
    private final DevSupportManager mDevSupportManager;
    
    static {
        mJsModuleIdPattern = Pattern.compile("(?:^|[/\\\\])(\\d+\\.js)$");
    }
    
    public ExceptionsManagerModule(final DevSupportManager mDevSupportManager) {
        this.mDevSupportManager = mDevSupportManager;
    }
    
    private void showOrThrowError(final String s, final ReadableArray readableArray, final int n) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(s, readableArray, n);
            return;
        }
        throw new JavascriptException(this.stackTraceToString(s, readableArray));
    }
    
    private static String stackFrameToModuleId(final ReadableMap readableMap) {
        if (readableMap.hasKey("file") && !readableMap.isNull("file") && readableMap.getType("file") == ReadableType.String) {
            final Matcher matcher = ExceptionsManagerModule.mJsModuleIdPattern.matcher(readableMap.getString("file"));
            if (matcher.find()) {
                return matcher.group(1) + ":";
            }
        }
        return "";
    }
    
    private String stackTraceToString(final String s, final ReadableArray readableArray) {
        final StringBuilder append = new StringBuilder(s).append(", stack:\n");
        for (int i = 0; i < readableArray.size(); ++i) {
            final ReadableMap map = readableArray.getMap(i);
            append.append(map.getString("methodName")).append("@").append(stackFrameToModuleId(map)).append(map.getInt("lineNumber"));
            if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                append.append(":").append(map.getInt("column"));
            }
            append.append("\n");
        }
        return append.toString();
    }
    
    @ReactMethod
    public void dismissRedbox() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.hideRedboxDialog();
        }
    }
    
    @Override
    public String getName() {
        return "RKExceptionsManager";
    }
    
    @ReactMethod
    public void reportFatalException(final String s, final ReadableArray readableArray, final int n) {
        this.showOrThrowError(s, readableArray, n);
    }
    
    @ReactMethod
    public void reportSoftException(final String s, final ReadableArray readableArray, final int n) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(s, readableArray, n);
            return;
        }
        FLog.e("React", this.stackTraceToString(s, readableArray));
    }
    
    @ReactMethod
    public void updateExceptionMessage(final String s, final ReadableArray readableArray, final int n) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.updateJSError(s, readableArray, n);
        }
    }
}
