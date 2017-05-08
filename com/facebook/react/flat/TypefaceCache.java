// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.facebook.infer.annotation.Assertions;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.HashMap;

final class TypefaceCache
{
    private static final String[] EXTENSIONS;
    private static final String[] FILE_EXTENSIONS;
    private static final HashMap<String, Typeface[]> FONTFAMILY_CACHE;
    private static final HashMap<Typeface, Typeface[]> TYPEFACE_CACHE;
    private static AssetManager sAssetManager;
    
    static {
        FONTFAMILY_CACHE = new HashMap<String, Typeface[]>();
        TYPEFACE_CACHE = new HashMap<Typeface, Typeface[]>();
        EXTENSIONS = new String[] { "", "_bold", "_italic", "_bold_italic" };
        FILE_EXTENSIONS = new String[] { ".ttf", ".otf" };
        TypefaceCache.sAssetManager = null;
    }
    
    private static Typeface createTypeface(final String s, final int n) {
        final StringBuilder append = new StringBuilder(32).append("fonts/").append(s).append(TypefaceCache.EXTENSIONS[n]);
        final int length = append.length();
        final String[] file_EXTENSIONS = TypefaceCache.FILE_EXTENSIONS;
        final int length2 = file_EXTENSIONS.length;
        int i = 0;
        while (i < length2) {
            final String string = append.append(file_EXTENSIONS[i]).toString();
            try {
                return Typeface.createFromAsset(TypefaceCache.sAssetManager, string);
            }
            catch (RuntimeException ex) {
                append.setLength(length);
                ++i;
                continue;
            }
            break;
        }
        return Assertions.assumeNotNull(Typeface.create(s, n));
    }
    
    public static Typeface getTypeface(Typeface create, final int n) {
        if (create == null) {
            return Typeface.defaultFromStyle(n);
        }
        final Typeface[] array = TypefaceCache.TYPEFACE_CACHE.get(create);
        Typeface[] array2;
        if (array == null) {
            array2 = new Typeface[4];
            array2[create.getStyle()] = create;
        }
        else {
            array2 = array;
            if (array[n] != null) {
                return array[n];
            }
        }
        create = Typeface.create(create, n);
        array2[n] = create;
        TypefaceCache.TYPEFACE_CACHE.put(create, array2);
        return create;
    }
    
    public static Typeface getTypeface(final String s, final int n) {
        final Typeface[] array = TypefaceCache.FONTFAMILY_CACHE.get(s);
        Typeface[] array2;
        if (array == null) {
            array2 = new Typeface[4];
            TypefaceCache.FONTFAMILY_CACHE.put(s, array2);
        }
        else {
            array2 = array;
            if (array[n] != null) {
                return array[n];
            }
        }
        final Typeface typeface = createTypeface(s, n);
        array2[n] = typeface;
        TypefaceCache.TYPEFACE_CACHE.put(typeface, array2);
        return typeface;
    }
}
