// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.util.Log;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(11)
class AppCompatDrawableManager$AvdcInflateDelegate implements AppCompatDrawableManager$InflateDelegate
{
    @SuppressLint({ "NewApi" })
    @Override
    public Drawable createFromXmlInner(final Context context, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        try {
            return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, set, resources$Theme);
        }
        catch (Exception ex) {
            Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", (Throwable)ex);
            return null;
        }
    }
}
