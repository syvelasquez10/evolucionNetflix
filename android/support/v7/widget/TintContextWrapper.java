// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Build$VERSION;
import android.support.v7.app.AppCompatDelegate;
import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.content.ContextWrapper;

public class TintContextWrapper extends ContextWrapper
{
    private static final ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources$Theme mTheme;
    
    static {
        sCache = new ArrayList<WeakReference<TintContextWrapper>>();
    }
    
    private TintContextWrapper(final Context context) {
        super(context);
        if (VectorEnabledTintResources.shouldBeUsed()) {
            this.mResources = new VectorEnabledTintResources((Context)this, context.getResources());
            (this.mTheme = this.mResources.newTheme()).setTo(context.getTheme());
            return;
        }
        this.mResources = new TintResources((Context)this, context.getResources());
        this.mTheme = null;
    }
    
    private static boolean shouldWrap(final Context context) {
        return !(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources) && !(context.getResources() instanceof VectorEnabledTintResources) && (!AppCompatDelegate.isCompatVectorFromResourcesEnabled() || Build$VERSION.SDK_INT <= 20);
    }
    
    public static Context wrap(final Context context) {
        Object o = context;
        if (shouldWrap(context)) {
            for (int size = TintContextWrapper.sCache.size(), i = 0; i < size; ++i) {
                final WeakReference<TintContextWrapper> weakReference = TintContextWrapper.sCache.get(i);
                if (weakReference != null) {
                    o = weakReference.get();
                }
                else {
                    o = null;
                }
                if (o != null && ((TintContextWrapper)o).getBaseContext() == context) {
                    return (Context)o;
                }
            }
            final TintContextWrapper tintContextWrapper = new TintContextWrapper(context);
            TintContextWrapper.sCache.add(new WeakReference<TintContextWrapper>(tintContextWrapper));
            return (Context)tintContextWrapper;
        }
        return (Context)o;
    }
    
    public Resources getResources() {
        return this.mResources;
    }
    
    public Resources$Theme getTheme() {
        if (this.mTheme == null) {
            return super.getTheme();
        }
        return this.mTheme;
    }
    
    public void setTheme(final int theme) {
        if (this.mTheme == null) {
            super.setTheme(theme);
            return;
        }
        this.mTheme.applyStyle(theme, true);
    }
}
