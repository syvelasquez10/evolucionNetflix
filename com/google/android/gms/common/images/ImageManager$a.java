// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.app.ActivityManager;

final class ImageManager$a
{
    static int a(final ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }
}
