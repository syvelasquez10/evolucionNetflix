// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.Resources$NotFoundException;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.content.res.Resources;

public class ResourcesCompat
{
    public Drawable getDrawable(final Resources resources, final int n, final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        if (Build$VERSION.SDK_INT >= 21) {
            return ResourcesCompatApi21.getDrawable(resources, n, resources$Theme);
        }
        return resources.getDrawable(n);
    }
}
