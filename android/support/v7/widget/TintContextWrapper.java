// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.AssetManager;
import android.os.Build$VERSION;
import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.content.ContextWrapper;

public class TintContextWrapper extends ContextWrapper
{
    private static final Object CACHE_LOCK;
    private static ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources$Theme mTheme;
    
    static {
        CACHE_LOCK = new Object();
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
        return !(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources) && !(context.getResources() instanceof VectorEnabledTintResources) && (Build$VERSION.SDK_INT < 21 || VectorEnabledTintResources.shouldBeUsed());
    }
    
    public static Context wrap(final Context context) {
        if (shouldWrap(context)) {
        Label_0112_Outer:
            while (true) {
            Label_0139_Outer:
                while (true) {
                    int n = 0;
                Label_0174:
                    while (true) {
                    Label_0169:
                        while (true) {
                            Label_0162: {
                                synchronized (TintContextWrapper.CACHE_LOCK) {
                                    if (TintContextWrapper.sCache == null) {
                                        TintContextWrapper.sCache = new ArrayList<WeakReference<TintContextWrapper>>();
                                    }
                                    else {
                                        n = TintContextWrapper.sCache.size() - 1;
                                        if (n >= 0) {
                                            final WeakReference<TintContextWrapper> weakReference = TintContextWrapper.sCache.get(n);
                                            if (weakReference == null || weakReference.get() == null) {
                                                TintContextWrapper.sCache.remove(n);
                                            }
                                            break Label_0162;
                                        }
                                        else {
                                            n = TintContextWrapper.sCache.size() - 1;
                                            if (n >= 0) {
                                                final WeakReference<TintContextWrapper> weakReference2 = TintContextWrapper.sCache.get(n);
                                                if (weakReference2 == null) {
                                                    break Label_0169;
                                                }
                                                final Object o = weakReference2.get();
                                                if (o != null && ((TintContextWrapper)o).getBaseContext() == context) {
                                                    return (Context)o;
                                                }
                                                break Label_0174;
                                            }
                                        }
                                    }
                                    final TintContextWrapper tintContextWrapper = new TintContextWrapper(context);
                                    TintContextWrapper.sCache.add(new WeakReference<TintContextWrapper>(tintContextWrapper));
                                    return (Context)tintContextWrapper;
                                }
                                break;
                            }
                            --n;
                            continue Label_0112_Outer;
                        }
                        final Object o = null;
                        continue;
                    }
                    --n;
                    continue Label_0139_Outer;
                }
            }
        }
        return context;
    }
    
    public AssetManager getAssets() {
        return this.mResources.getAssets();
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
