// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.view.Display;
import android.content.ContentResolver;
import java.util.List;
import java.util.Collections;
import android.util.Log;
import java.util.Iterator;
import java.util.Locale;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;
import android.support.v4.media.VolumeProviderCompat;

class MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 extends VolumeProviderCompat
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1;
    
    MediaRouter$GlobalMediaRouter$MediaSessionRecord$1(final MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1, final int n, final int n2, final int n3) {
        this.this$1 = this$1;
        super(n, n2, n3);
    }
    
    @Override
    public void onAdjustVolume(final int n) {
        if (this.this$1.this$0.mSelectedRoute != null) {
            this.this$1.this$0.mSelectedRoute.requestUpdateVolume(n);
        }
    }
    
    @Override
    public void onSetVolumeTo(final int n) {
        if (this.this$1.this$0.mSelectedRoute != null) {
            this.this$1.this$0.mSelectedRoute.requestSetVolume(n);
        }
    }
}
