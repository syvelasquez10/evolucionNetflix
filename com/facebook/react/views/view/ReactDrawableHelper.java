// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import android.graphics.drawable.RippleDrawable;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Build$VERSION;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.SoftAssertions;
import android.graphics.drawable.Drawable;
import com.facebook.react.bridge.ReadableMap;
import android.content.Context;
import android.util.TypedValue;

public class ReactDrawableHelper
{
    private static final TypedValue sResolveOutValue;
    
    static {
        sResolveOutValue = new TypedValue();
    }
    
    public static Drawable createDrawableFromJSDescription(final Context context, final ReadableMap readableMap) {
        final String string = readableMap.getString("type");
        if ("ThemeAttrAndroid".equals(string)) {
            final String string2 = readableMap.getString("attribute");
            SoftAssertions.assertNotNull(string2);
            final int identifier = context.getResources().getIdentifier(string2, "attr", "android");
            if (identifier == 0) {
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be found in the resource list");
            }
            if (!context.getTheme().resolveAttribute(identifier, ReactDrawableHelper.sResolveOutValue, true)) {
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be resolved into a drawable");
            }
            if (Build$VERSION.SDK_INT >= 21) {
                return context.getResources().getDrawable(ReactDrawableHelper.sResolveOutValue.resourceId, context.getTheme());
            }
            return context.getResources().getDrawable(ReactDrawableHelper.sResolveOutValue.resourceId);
        }
        else {
            if (!"RippleAndroid".equals(string)) {
                throw new JSApplicationIllegalArgumentException("Invalid type for android drawable: " + string);
            }
            if (Build$VERSION.SDK_INT < 21) {
                throw new JSApplicationIllegalArgumentException("Ripple drawable is not available on android API <21");
            }
            int n;
            if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
                n = readableMap.getInt("color");
            }
            else {
                if (!context.getTheme().resolveAttribute(16843820, ReactDrawableHelper.sResolveOutValue, true)) {
                    throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
                }
                n = context.getResources().getColor(ReactDrawableHelper.sResolveOutValue.resourceId);
            }
            Object o;
            if (!readableMap.hasKey("borderless") || readableMap.isNull("borderless") || !readableMap.getBoolean("borderless")) {
                o = new ColorDrawable(-1);
            }
            else {
                o = null;
            }
            return (Drawable)new RippleDrawable(new ColorStateList(new int[][] { new int[0] }, new int[] { n }), (Drawable)null, (Drawable)o);
        }
    }
}
