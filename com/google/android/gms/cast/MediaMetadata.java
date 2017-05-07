// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Calendar;
import com.google.android.gms.internal.dp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import com.google.android.gms.common.images.WebImage;
import java.util.List;
import android.os.Bundle;

public class MediaMetadata
{
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
    private static final String[] kO;
    private static final a kP;
    private final Bundle kQ;
    private int kR;
    private final List<WebImage> ki;
    
    static {
        kO = new String[] { null, "String", "int", "double", "ISO-8601 date String" };
        kP = new a().a("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4).a("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4).a("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4).a("com.google.android.gms.cast.metadata.TITLE", "title", 1).a("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1).a("com.google.android.gms.cast.metadata.ARTIST", "artist", 1).a("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1).a("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1).a("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1).a("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2).a("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2).a("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2).a("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2).a("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1).a("com.google.android.gms.cast.metadata.STUDIO", "studio", 1).a("com.google.android.gms.cast.metadata.WIDTH", "width", 2).a("com.google.android.gms.cast.metadata.HEIGHT", "height", 2).a("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1).a("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3).a("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
    }
    
    public MediaMetadata() {
        this(0);
    }
    
    public MediaMetadata(final int kr) {
        this.ki = new ArrayList<WebImage>();
        this.kQ = new Bundle();
        this.kR = kr;
    }
    
    private void a(final String s, final int n) throws IllegalArgumentException {
        final int a = MediaMetadata.kP.A(s);
        if (a != n && a != 0) {
            throw new IllegalArgumentException("Value for " + s + " must be a " + MediaMetadata.kO[n]);
        }
    }
    
    private void a(final JSONObject jsonObject, final String... array) {
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (this.kQ.containsKey(s)) {
                    switch (MediaMetadata.kP.A(s)) {
                        case 1:
                        case 4: {
                            jsonObject.put(MediaMetadata.kP.y(s), (Object)this.kQ.getString(s));
                            break;
                        }
                        case 2: {
                            jsonObject.put(MediaMetadata.kP.y(s), this.kQ.getInt(s));
                            break;
                        }
                        case 3: {
                            jsonObject.put(MediaMetadata.kP.y(s), this.kQ.getDouble(s));
                            break;
                        }
                    }
                }
            }
            for (final String s2 : this.kQ.keySet()) {
                if (!s2.startsWith("com.google.")) {
                    final Object value = this.kQ.get(s2);
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
        Block_15_Outer:
            while (keys.hasNext()) {
                final String s = keys.next();
                final String z = MediaMetadata.kP.z(s);
                Label_0237: {
                    if (z == null) {
                        break Label_0237;
                    }
                    if (!((Set)(Object)array).contains(z)) {
                        continue;
                    }
                    try {
                        final Object value = jsonObject.get(s);
                        if (value == null) {
                            continue;
                        }
                        switch (MediaMetadata.kP.A(z)) {
                            case 1: {
                                if (value instanceof String) {
                                    this.kQ.putString(z, (String)value);
                                    continue;
                                }
                                continue;
                            }
                            case 4: {
                                if (value instanceof String && dp.G((String)value) != null) {
                                    this.kQ.putString(z, (String)value);
                                    continue;
                                }
                                continue;
                            }
                            case 2: {
                                if (value instanceof Integer) {
                                    this.kQ.putInt(z, (int)value);
                                    continue;
                                }
                                continue;
                            }
                            case 3: {
                                if (value instanceof Double) {
                                    this.kQ.putDouble(z, (double)value);
                                    continue;
                                }
                                continue;
                            }
                            default: {
                                continue;
                            }
                        }
                        final Object value2 = jsonObject.get(s);
                        // iftrue(Label_0270:, !value2 instanceof String)
                        // iftrue(Label_0018:, !value2 instanceof Double)
                        // iftrue(Label_0298:, !value2 instanceof Integer)
                        Block_16: {
                            Block_14: {
                                break Block_14;
                                while (true) {
                                    this.kQ.putInt(z, (int)value2);
                                    continue Block_15_Outer;
                                    Label_0298: {
                                        break Block_16;
                                    }
                                    Label_0270:
                                    continue;
                                }
                            }
                            this.kQ.putString(z, (String)value2);
                            continue;
                        }
                        this.kQ.putDouble(z, (double)value2);
                    }
                    catch (JSONException ex) {}
                }
            }
        }
        catch (JSONException ex2) {}
    }
    
    public JSONObject aP() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("metadataType", this.kR);
                dp.a(jsonObject, this.ki);
                switch (this.kR) {
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
                        break;
                    }
                }
                this.a(jsonObject, new String[0]);
                return jsonObject;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void addImage(final WebImage webImage) {
        this.ki.add(webImage);
    }
    
    public void b(final JSONObject jsonObject) {
        this.clear();
        this.kR = 0;
        while (true) {
            try {
                this.kR = jsonObject.getInt("metadataType");
                dp.a(this.ki, jsonObject);
                switch (this.kR) {
                    case 0: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return;
                    }
                    case 1: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return;
                    }
                    case 2: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
                        return;
                    }
                    case 3: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
                        return;
                    }
                    case 4: {
                        this.b(jsonObject, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
                        break;
                    }
                }
                this.b(jsonObject, new String[0]);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void clear() {
        this.kQ.clear();
        this.ki.clear();
    }
    
    public void clearImages() {
        this.ki.clear();
    }
    
    public boolean containsKey(final String s) {
        return this.kQ.containsKey(s);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MediaMetadata)) {
                return false;
            }
            final MediaMetadata mediaMetadata = (MediaMetadata)o;
            if (!this.a(this.kQ, mediaMetadata.kQ) || !this.ki.equals(mediaMetadata.ki)) {
                return false;
            }
        }
        return true;
    }
    
    public Calendar getDate(String string) {
        this.a(string, 4);
        string = this.kQ.getString(string);
        if (string != null) {
            return dp.G(string);
        }
        return null;
    }
    
    public String getDateAsString(final String s) {
        this.a(s, 4);
        return this.kQ.getString(s);
    }
    
    public double getDouble(final String s) {
        this.a(s, 3);
        return this.kQ.getDouble(s);
    }
    
    public List<WebImage> getImages() {
        return this.ki;
    }
    
    public int getInt(final String s) {
        this.a(s, 2);
        return this.kQ.getInt(s);
    }
    
    public int getMediaType() {
        return this.kR;
    }
    
    public String getString(final String s) {
        this.a(s, 1);
        return this.kQ.getString(s);
    }
    
    public boolean hasImages() {
        return this.ki != null && !this.ki.isEmpty();
    }
    
    @Override
    public int hashCode() {
        final Iterator<String> iterator = this.kQ.keySet().iterator();
        int n = 17;
        while (iterator.hasNext()) {
            n = this.kQ.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n * 31 + this.ki.hashCode();
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.kQ.keySet();
    }
    
    public void putDate(final String s, final Calendar calendar) {
        this.a(s, 4);
        this.kQ.putString(s, dp.a(calendar));
    }
    
    public void putDouble(final String s, final double n) {
        this.a(s, 3);
        this.kQ.putDouble(s, n);
    }
    
    public void putInt(final String s, final int n) {
        this.a(s, 2);
        this.kQ.putInt(s, n);
    }
    
    public void putString(final String s, final String s2) {
        this.a(s, 1);
        this.kQ.putString(s, s2);
    }
    
    private static class a
    {
        private final Map<String, String> kS;
        private final Map<String, String> kT;
        private final Map<String, Integer> kU;
        
        public a() {
            this.kS = new HashMap<String, String>();
            this.kT = new HashMap<String, String>();
            this.kU = new HashMap<String, Integer>();
        }
        
        public int A(final String s) {
            final Integer n = this.kU.get(s);
            if (n != null) {
                return n;
            }
            return 0;
        }
        
        public a a(final String s, final String s2, final int n) {
            this.kS.put(s, s2);
            this.kT.put(s2, s);
            this.kU.put(s, n);
            return this;
        }
        
        public String y(final String s) {
            return this.kS.get(s);
        }
        
        public String z(final String s) {
            return this.kT.get(s);
        }
    }
}
