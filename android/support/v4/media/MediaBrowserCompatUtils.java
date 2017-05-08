// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Bundle;

public class MediaBrowserCompatUtils
{
    public static boolean areSameOptions(final Bundle bundle, final Bundle bundle2) {
        if (bundle != bundle2) {
            if (bundle == null) {
                if (bundle2.getInt("android.media.browse.extra.PAGE", -1) != -1 || bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1) {
                    return false;
                }
            }
            else if (bundle2 == null) {
                if (bundle.getInt("android.media.browse.extra.PAGE", -1) != -1 || bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1) {
                    return false;
                }
            }
            else if (bundle.getInt("android.media.browse.extra.PAGE", -1) != bundle2.getInt("android.media.browse.extra.PAGE", -1) || bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) != bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean hasDuplicatedItems(final Bundle bundle, final Bundle bundle2) {
        final int n = Integer.MAX_VALUE;
        int int1;
        if (bundle == null) {
            int1 = -1;
        }
        else {
            int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
        }
        int int2;
        if (bundle2 == null) {
            int2 = -1;
        }
        else {
            int2 = bundle2.getInt("android.media.browse.extra.PAGE", -1);
        }
        int int3;
        if (bundle == null) {
            int3 = -1;
        }
        else {
            int3 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        }
        int int4;
        if (bundle2 == null) {
            int4 = -1;
        }
        else {
            int4 = bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        }
        int n2;
        int n3;
        if (int1 == -1 || int3 == -1) {
            n2 = Integer.MAX_VALUE;
            n3 = 0;
        }
        else {
            final int n4;
            n2 = (n4 = int1 * int3) + int3 - 1;
            n3 = n4;
        }
        int n5;
        int n6;
        if (int2 == -1 || int4 == -1) {
            n5 = 0;
            n6 = n;
        }
        else {
            final int n7 = int4 * int2;
            n6 = n7 + int4 - 1;
            n5 = n7;
        }
        return (n3 <= n5 && n5 <= n2) || (n3 <= n6 && n6 <= n2);
    }
}
