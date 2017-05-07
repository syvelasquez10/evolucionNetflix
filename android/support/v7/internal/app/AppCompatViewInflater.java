// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatEditText;
import android.content.res.TypedArray;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.Log;
import android.support.v7.appcompat.R$styleable;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

public class AppCompatViewInflater
{
    private static final Map<String, Constructor<? extends View>> sConstructorMap;
    static final Class<?>[] sConstructorSignature;
    private final Object[] mConstructorArgs;
    
    static {
        sConstructorSignature = new Class[] { Context.class, AttributeSet.class };
        sConstructorMap = new ArrayMap<String, Constructor<? extends View>>();
    }
    
    public AppCompatViewInflater() {
        this.mConstructorArgs = new Object[2];
    }
    
    private View createView(final Context context, final String s, final String s2) {
        Label_0083: {
            Constructor<? extends View> constructor;
            if ((constructor = AppCompatViewInflater.sConstructorMap.get(s)) != null) {
                break Label_0083;
            }
            try {
                final ClassLoader classLoader = context.getClassLoader();
                String string;
                if (s2 != null) {
                    string = s2 + s;
                }
                else {
                    string = s;
                }
                constructor = classLoader.loadClass(string).asSubclass(View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
                AppCompatViewInflater.sConstructorMap.put(s, constructor);
                constructor.setAccessible(true);
                return (View)constructor.newInstance(this.mConstructorArgs);
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
    
    private View createViewFromTag(final Context context, final String s, final AttributeSet set) {
        String attributeValue = s;
        if (s.equals("view")) {
            attributeValue = set.getAttributeValue((String)null, "class");
        }
        try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = set;
            if (-1 == attributeValue.indexOf(46)) {
                return this.createView(context, attributeValue, "android.widget.");
            }
            return this.createView(context, attributeValue, null);
        }
        catch (Exception ex) {
            return null;
        }
        finally {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
        }
    }
    
    private static Context themifyContext(final Context context, final AttributeSet set, final boolean b, final boolean b2) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.View, 0, 0);
        int resourceId;
        if (b) {
            resourceId = obtainStyledAttributes.getResourceId(R$styleable.View_android_theme, 0);
        }
        else {
            resourceId = 0;
        }
        int n = resourceId;
        if (b2 && (n = resourceId) == 0) {
            final int resourceId2 = obtainStyledAttributes.getResourceId(R$styleable.View_theme, 0);
            if ((n = resourceId2) != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
                n = resourceId2;
            }
        }
        obtainStyledAttributes.recycle();
        Object o = context;
        if (n != 0) {
            if (context instanceof ContextThemeWrapper) {
                o = context;
                if (((ContextThemeWrapper)context).getThemeResId() == n) {
                    return (Context)o;
                }
            }
            o = new ContextThemeWrapper(context, n);
        }
        return (Context)o;
    }
    
    public final View createView(final View view, final String s, final Context context, final AttributeSet set, final boolean b, final boolean b2, final boolean b3) {
        Context context2;
        if (b && view != null) {
            context2 = view.getContext();
        }
        else {
            context2 = context;
        }
        Context themifyContext = null;
        Label_0039: {
            if (!b2) {
                themifyContext = context2;
                if (!b3) {
                    break Label_0039;
                }
            }
            themifyContext = themifyContext(context2, set, b2, b3);
        }
        switch (s) {
            default: {
                if (context != themifyContext) {
                    return this.createViewFromTag(themifyContext, s, set);
                }
                return null;
            }
            case "EditText": {
                return (View)new AppCompatEditText(themifyContext, set);
            }
            case "Spinner": {
                return (View)new AppCompatSpinner(themifyContext, set);
            }
            case "CheckBox": {
                return (View)new AppCompatCheckBox(themifyContext, set);
            }
            case "RadioButton": {
                return (View)new AppCompatRadioButton(themifyContext, set);
            }
            case "CheckedTextView": {
                return (View)new AppCompatCheckedTextView(themifyContext, set);
            }
            case "AutoCompleteTextView": {
                return (View)new AppCompatAutoCompleteTextView(themifyContext, set);
            }
            case "MultiAutoCompleteTextView": {
                return (View)new AppCompatMultiAutoCompleteTextView(themifyContext, set);
            }
            case "RatingBar": {
                return (View)new AppCompatRatingBar(themifyContext, set);
            }
            case "Button": {
                return (View)new AppCompatButton(themifyContext, set);
            }
            case "TextView": {
                return (View)new AppCompatTextView(themifyContext, set);
            }
        }
    }
}
