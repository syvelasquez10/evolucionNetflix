// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Calendar;
import android.text.TextUtils;
import com.google.android.gms.internal.iu;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.common.images.WebImage;
import java.util.List;

public class MediaMetadata
{
    private static final String[] Fn;
    private static final a Fo;
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private final List<WebImage> EA;
    private final Bundle Fp;
    private int Fq;
    
    static {
        Fn = new String[] { null, "String", "int", "double", "ISO-8601 date String" };
        Fo = new a().a("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4).a("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4).a("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4).a("com.google.android.gms.cast.metadata.TITLE", "title", 1).a("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1).a("com.google.android.gms.cast.metadata.ARTIST", "artist", 1).a("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1).a("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1).a("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1).a("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2).a("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2).a("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2).a("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2).a("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1).a("com.google.android.gms.cast.metadata.STUDIO", "studio", 1).a("com.google.android.gms.cast.metadata.WIDTH", "width", 2).a("com.google.android.gms.cast.metadata.HEIGHT", "height", 2).a("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1).a("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3).a("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
    }
    
    public MediaMetadata() {
        this(0);
    }
    
    public MediaMetadata(final int fq) {
        this.EA = new ArrayList<WebImage>();
        this.Fp = new Bundle();
        this.Fq = fq;
    }
    
    private void a(final JSONObject jsonObject, final String... array) {
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (this.Fp.containsKey(s)) {
                    switch (MediaMetadata.Fo.aB(s)) {
                        case 1:
                        case 4: {
                            jsonObject.put(MediaMetadata.Fo.az(s), (Object)this.Fp.getString(s));
                            break;
                        }
                        case 2: {
                            jsonObject.put(MediaMetadata.Fo.az(s), this.Fp.getInt(s));
                            break;
                        }
                        case 3: {
                            jsonObject.put(MediaMetadata.Fo.az(s), this.Fp.getDouble(s));
                            break;
                        }
                    }
                }
            }
            for (final String s2 : this.Fp.keySet()) {
                if (!s2.startsWith("com.google.")) {
                    final Object value = this.Fp.get(s2);
                    if (value instanceof String) {
                        jsonObject.put(s2, value);
                    }
                    else if (value instanceof Integer) {
                        jsonObject.put(s2, value);
                    }
                    else {
                        if (!(value instanceof Double)) {
                            continue;
                        }
                        jsonObject.put(s2, value);
                    }
                }
            }
        }
        catch (JSONException ex) {}
    }
    
    private boolean a(final Bundle bundle, final Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            final Object value2 = bundle2.get(s);
            if (value instanceof Bundle && value2 instanceof Bundle && !this.a((Bundle)value, (Bundle)value2)) {
                return false;
            }
            if (value == null) {
                if (value2 != null || !bundle2.containsKey(s)) {
                    return false;
                }
                continue;
            }
            else {
                if (!value.equals(value2)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    private void b(final JSONObject jsonObject, String... array) {
        array = (String[])(Object)new HashSet((Collection<? extends E>)Arrays.asList(array));
        try {
            final Iterator keys = jsonObject.keys();
        Block_17_Outer:
            while (keys.hasNext()) {
                final String s = keys.next();
                if (!"metadataType".equals(s)) {
                    final String aa = MediaMetadata.Fo.aA(s);
                    Label_0249: {
                        if (aa == null) {
                            break Label_0249;
                        }
                        if (!((Set)(Object)array).contains(aa)) {
                            continue;
                        }
                        try {
                            final Object value = jsonObject.get(s);
                            if (value == null) {
                                continue Block_17_Outer;
                            }
                            switch (MediaMetadata.Fo.aB(aa)) {
                                case 1: {
                                    if (value instanceof String) {
                                        this.Fp.putString(aa, (String)value);
                                        continue Block_17_Outer;
                                    }
                                    continue Block_17_Outer;
                                }
                                case 4: {
                                    if (value instanceof String && iu.aL((String)value) != null) {
                                        this.Fp.putString(aa, (String)value);
                                        continue Block_17_Outer;
                                    }
                                    continue Block_17_Outer;
                                }
                                case 2: {
                                    if (value instanceof Integer) {
                                        this.Fp.putInt(aa, (int)value);
                                        continue Block_17_Outer;
                                    }
                                    continue Block_17_Outer;
                                }
                                case 3: {
                                    if (value instanceof Double) {
                                        this.Fp.putDouble(aa, (double)value);
                                        continue Block_17_Outer;
                                    }
                                    continue Block_17_Outer;
                                }
                                default: {
                                    continue Block_17_Outer;
                                }
                            }
                        Label_0282:
                            while (true) {
                                final Object value2;
                                Block_15: {
                                    Block_16: {
                                        break Block_16;
                                        value2 = jsonObject.get(s);
                                        break Block_15;
                                    }
                                    this.Fp.putInt(s, (int)value2);
                                    continue Block_17_Outer;
                                    this.Fp.putDouble(s, (double)value2);
                                    continue Block_17_Outer;
                                }
                                this.Fp.putString(s, (String)value2);
                                continue Block_17_Outer;
                                Label_0310:
                                continue;
                            }
                        }
                        // iftrue(Label_0310:, !value2 instanceof Integer)
                        // iftrue(Label_0282:, !value2 instanceof String)
                        // iftrue(Label_0018:, !value2 instanceof Double)
                        catch (JSONException ex) {}
                    }
                }
            }
        }
        catch (JSONException ex2) {}
    }
    
    private void f(final String s, final int n) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        final int ab = MediaMetadata.Fo.aB(s);
        if (ab != n && ab != 0) {
            throw new IllegalArgumentException("Value for " + s + " must be a " + MediaMetadata.Fn[n]);
        }
    }
    
    public void addImage(final WebImage webImage) {
        this.EA.add(webImage);
    }
    
    public JSONObject bL() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("metadataType", this.Fq);
                iu.a(jsonObject, this.EA);
                switch (this.Fq) {
                    default: {
                        this.a(jsonObject, new String[0]);
                        return jsonObject;
                    }
                    case 0: {
                        this.a(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return jsonObject;
                    }
                    case 1: {
                        this.a(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return jsonObject;
                    }
                    case 2: {
                        this.a(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
                        return jsonObject;
                    }
                    case 3: {
                        this.a(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return jsonObject;
                    }
                    case 4: {
                        this.a(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
                        return jsonObject;
                    }
                }
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void c(final JSONObject jsonObject) {
        this.clear();
        this.Fq = 0;
        while (true) {
            try {
                this.Fq = jsonObject.getInt("metadataType");
                iu.a(this.EA, jsonObject);
                switch (this.Fq) {
                    default: {
                        this.b(jsonObject, new String[0]);
                    }
                    case 0: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                    }
                    case 1: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                    }
                    case 2: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
                    }
                    case 3: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                    }
                    case 4: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
                    }
                }
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void clear() {
        this.Fp.clear();
        this.EA.clear();
    }
    
    public void clearImages() {
        this.EA.clear();
    }
    
    public boolean containsKey(final String s) {
        return this.Fp.containsKey(s);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MediaMetadata)) {
                return false;
            }
            final MediaMetadata mediaMetadata = (MediaMetadata)o;
            if (!this.a(this.Fp, mediaMetadata.Fp) || !this.EA.equals(mediaMetadata.EA)) {
                return false;
            }
        }
        return true;
    }
    
    public Calendar getDate(String string) {
        this.f(string, 4);
        string = this.Fp.getString(string);
        if (string != null) {
            return iu.aL(string);
        }
        return null;
    }
    
    public String getDateAsString(final String s) {
        this.f(s, 4);
        return this.Fp.getString(s);
    }
    
    public double getDouble(final String s) {
        this.f(s, 3);
        return this.Fp.getDouble(s);
    }
    
    public List<WebImage> getImages() {
        return this.EA;
    }
    
    public int getInt(final String s) {
        this.f(s, 2);
        return this.Fp.getInt(s);
    }
    
    public int getMediaType() {
        return this.Fq;
    }
    
    public String getString(final String s) {
        this.f(s, 1);
        return this.Fp.getString(s);
    }
    
    public boolean hasImages() {
        return this.EA != null && !this.EA.isEmpty();
    }
    
    @Override
    public int hashCode() {
        final Iterator<String> iterator = this.Fp.keySet().iterator();
        int n = 17;
        while (iterator.hasNext()) {
            n = this.Fp.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n * 31 + this.EA.hashCode();
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.Fp.keySet();
    }
    
    public void putDate(final String s, final Calendar calendar) {
        this.f(s, 4);
        this.Fp.putString(s, iu.a(calendar));
    }
    
    public void putDouble(final String s, final double n) {
        this.f(s, 3);
        this.Fp.putDouble(s, n);
    }
    
    public void putInt(final String s, final int n) {
        this.f(s, 2);
        this.Fp.putInt(s, n);
    }
    
    public void putString(final String s, final String s2) {
        this.f(s, 1);
        this.Fp.putString(s, s2);
    }
    
    private static class a
    {
        private final Map<String, String> Fr;
        private final Map<String, String> Fs;
        private final Map<String, Integer> Ft;
        
        public a() {
            this.Fr = new HashMap<String, String>();
            this.Fs = new HashMap<String, String>();
            this.Ft = new HashMap<String, Integer>();
        }
        
        public a a(final String s, final String s2, final int n) {
            this.Fr.put(s, s2);
            this.Fs.put(s2, s);
            this.Ft.put(s, n);
            return this;
        }
        
        public String aA(final String s) {
            return this.Fs.get(s);
        }
        
        public int aB(final String s) {
            final Integer n = this.Ft.get(s);
            if (n != null) {
                return n;
            }
            return 0;
        }
        
        public String az(final String s) {
            return this.Fr.get(s);
        }
    }
}
