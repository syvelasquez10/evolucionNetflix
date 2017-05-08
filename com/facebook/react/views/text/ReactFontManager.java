// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.graphics.Typeface;
import android.content.res.AssetManager;
import java.util.HashMap;
import java.util.Map;

public class ReactFontManager
{
    private static final String[] EXTENSIONS;
    private static final String[] FILE_EXTENSIONS;
    private static ReactFontManager sReactFontManagerInstance;
    private Map<String, ReactFontManager$FontFamily> mFontCache;
    
    static {
        EXTENSIONS = new String[] { "", "_bold", "_italic", "_bold_italic" };
        FILE_EXTENSIONS = new String[] { ".ttf", ".otf" };
    }
    
    private ReactFontManager() {
        this.mFontCache = new HashMap<String, ReactFontManager$FontFamily>();
    }
    
    private static Typeface createTypeface(final String s, final int n, final AssetManager assetManager) {
        final String s2 = ReactFontManager.EXTENSIONS[n];
        final String[] file_EXTENSIONS = ReactFontManager.FILE_EXTENSIONS;
        final int length = file_EXTENSIONS.length;
        int i = 0;
        while (i < length) {
            final String string = "fonts/" + s + s2 + file_EXTENSIONS[i];
            try {
                return Typeface.createFromAsset(assetManager, string);
            }
            catch (RuntimeException ex) {
                ++i;
                continue;
            }
            break;
        }
        return Typeface.create(s, n);
    }
    
    public static ReactFontManager getInstance() {
        if (ReactFontManager.sReactFontManagerInstance == null) {
            ReactFontManager.sReactFontManagerInstance = new ReactFontManager();
        }
        return ReactFontManager.sReactFontManagerInstance;
    }
    
    public Typeface getTypeface(final String s, final int n, final AssetManager assetManager) {
        ReactFontManager$FontFamily reactFontManager$FontFamily;
        if ((reactFontManager$FontFamily = this.mFontCache.get(s)) == null) {
            reactFontManager$FontFamily = new ReactFontManager$FontFamily(null);
            this.mFontCache.put(s, reactFontManager$FontFamily);
        }
        Typeface typeface;
        if ((typeface = reactFontManager$FontFamily.getTypeface(n)) == null) {
            final Typeface typeface2 = createTypeface(s, n, assetManager);
            if ((typeface = typeface2) != null) {
                reactFontManager$FontFamily.setTypeface(n, typeface2);
                typeface = typeface2;
            }
        }
        return typeface;
    }
}
