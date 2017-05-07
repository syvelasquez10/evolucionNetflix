// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.Resources$NotFoundException;
import android.util.Log;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;

public class ej
{
    public static String a(String attributeValue, final String s, final Context context, AttributeSet string, final boolean b, final boolean b2, final String s2) {
    Label_0104:
        while (true) {
            while (true) {
                Label_0006: {
                    if (string == null) {
                        attributeValue = null;
                        break Label_0006;
                    }
                    Label_0145: {
                        break Label_0145;
                        while (true) {
                            final String substring = attributeValue.substring("@string/".length());
                            final String packageName = context.getPackageName();
                            string = (AttributeSet)new TypedValue();
                        Label_0195:
                            while (true) {
                                try {
                                    context.getResources().getValue(packageName + ":string/" + substring, (TypedValue)string, true);
                                    if (((TypedValue)string).string != null) {
                                        string = (AttributeSet)((TypedValue)string).string.toString();
                                        if (b2 && string == null) {
                                            Log.w(s2, "Required XML attribute \"" + s + "\" missing");
                                        }
                                        return (String)string;
                                    }
                                    break Label_0195;
                                    attributeValue = string.getAttributeValue(attributeValue, s);
                                    break;
                                }
                                catch (Resources$NotFoundException ex) {
                                    Log.w(s2, "Could not find resource for " + s + ": " + attributeValue);
                                    continue;
                                }
                                break;
                            }
                            Log.w(s2, "Resource " + s + " was not a string: " + string);
                            string = (AttributeSet)attributeValue;
                            continue Label_0104;
                        }
                    }
                }
                string = (AttributeSet)attributeValue;
                if (attributeValue == null) {
                    continue Label_0104;
                }
                string = (AttributeSet)attributeValue;
                if (!attributeValue.startsWith("@string/")) {
                    continue Label_0104;
                }
                string = (AttributeSet)attributeValue;
                if (b) {
                    continue;
                }
                break;
            }
            continue Label_0104;
        }
    }
}
