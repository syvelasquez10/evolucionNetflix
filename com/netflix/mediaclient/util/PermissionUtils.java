// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.support.v4.content.ContextCompat;
import android.content.Context;

public final class PermissionUtils
{
    public static final int REQUEST_PERMISSION_TO_DUMP_CACHE = 145;
    public static final int REQUEST_PERMISSION_TO_PERF_DUMP = 232;
    
    public static boolean shouldRequestPermission(final Context context, final String s) {
        return ContextCompat.checkSelfPermission(context, s) != 0;
    }
    
    public static boolean shouldRequestPermissions(final Context context, final String[] array) {
        final boolean b = false;
        final int length = array.length;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            if (shouldRequestPermission(context, array[n])) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public static boolean verifyPermissions(final int[] array) {
        if (array.length >= 1) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (array[i] != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean verifyPermissions(final int[] array, final int n) {
        return array.length == n && verifyPermissions(array);
    }
}
