// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Set;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class MediaMetadataCompat implements Parcelable
{
    public static final Parcelable$Creator<MediaMetadataCompat> CREATOR;
    static final ArrayMap<String, Integer> METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int METADATA_TYPE_BITMAP = 2;
    static final int METADATA_TYPE_LONG = 0;
    static final int METADATA_TYPE_RATING = 3;
    static final int METADATA_TYPE_TEXT = 1;
    private static final String[] PREFERRED_BITMAP_ORDER;
    private static final String[] PREFERRED_DESCRIPTION_ORDER;
    private static final String[] PREFERRED_URI_ORDER;
    private static final String TAG = "MediaMetadata";
    final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;
    
    static {
        (METADATA_KEYS_TYPE = new ArrayMap<String, Integer>()).put("android.media.metadata.TITLE", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DATE", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ART", 2);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", 2);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", 3);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.RATING", 3);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", 2);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", 1);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        MediaMetadataCompat.METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", 1);
        PREFERRED_DESCRIPTION_ORDER = new String[] { "android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER" };
        PREFERRED_BITMAP_ORDER = new String[] { "android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART" };
        PREFERRED_URI_ORDER = new String[] { "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI" };
        CREATOR = (Parcelable$Creator)new MediaMetadataCompat$1();
    }
    
    MediaMetadataCompat(final Bundle bundle) {
        this.mBundle = new Bundle(bundle);
    }
    
    MediaMetadataCompat(final Parcel parcel) {
        this.mBundle = parcel.readBundle();
    }
    
    public static MediaMetadataCompat fromMediaMetadata(final Object mMetadataObj) {
        if (mMetadataObj == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        final Parcel obtain = Parcel.obtain();
        MediaMetadataCompatApi21.writeToParcel(mMetadataObj, obtain, 0);
        obtain.setDataPosition(0);
        final MediaMetadataCompat mediaMetadataCompat = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        mediaMetadataCompat.mMetadataObj = mMetadataObj;
        return mediaMetadataCompat;
    }
    
    public boolean containsKey(final String s) {
        return this.mBundle.containsKey(s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bitmap getBitmap(final String s) {
        try {
            return (Bitmap)this.mBundle.getParcelable(s);
        }
        catch (Exception ex) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", (Throwable)ex);
            return null;
        }
    }
    
    public Bundle getBundle() {
        return this.mBundle;
    }
    
    public MediaDescriptionCompat getDescription() {
        Uri parse = null;
        if (this.mDescription != null) {
            return this.mDescription;
        }
        final String string = this.getString("android.media.metadata.MEDIA_ID");
        final CharSequence[] array = new CharSequence[3];
        final CharSequence text = this.getText("android.media.metadata.DISPLAY_TITLE");
        if (!TextUtils.isEmpty(text)) {
            array[0] = text;
            array[1] = this.getText("android.media.metadata.DISPLAY_SUBTITLE");
            array[2] = this.getText("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        else {
            for (int n = 0, n2 = 0; n2 < array.length && n < MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER.length; ++n) {
                final CharSequence text2 = this.getText(MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER[n]);
                if (!TextUtils.isEmpty(text2)) {
                    final int n3 = n2 + 1;
                    array[n2] = text2;
                    n2 = n3;
                }
            }
        }
        while (true) {
            for (int i = 0; i < MediaMetadataCompat.PREFERRED_BITMAP_ORDER.length; ++i) {
                final Bitmap bitmap = this.getBitmap(MediaMetadataCompat.PREFERRED_BITMAP_ORDER[i]);
                if (bitmap != null) {
                    while (true) {
                        for (int j = 0; j < MediaMetadataCompat.PREFERRED_URI_ORDER.length; ++j) {
                            final String string2 = this.getString(MediaMetadataCompat.PREFERRED_URI_ORDER[j]);
                            if (!TextUtils.isEmpty((CharSequence)string2)) {
                                final Uri parse2 = Uri.parse(string2);
                                final String string3 = this.getString("android.media.metadata.MEDIA_URI");
                                if (!TextUtils.isEmpty((CharSequence)string3)) {
                                    parse = Uri.parse(string3);
                                }
                                final MediaDescriptionCompat$Builder mediaDescriptionCompat$Builder = new MediaDescriptionCompat$Builder();
                                mediaDescriptionCompat$Builder.setMediaId(string);
                                mediaDescriptionCompat$Builder.setTitle(array[0]);
                                mediaDescriptionCompat$Builder.setSubtitle(array[1]);
                                mediaDescriptionCompat$Builder.setDescription(array[2]);
                                mediaDescriptionCompat$Builder.setIconBitmap(bitmap);
                                mediaDescriptionCompat$Builder.setIconUri(parse2);
                                mediaDescriptionCompat$Builder.setMediaUri(parse);
                                if (this.mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
                                    final Bundle extras = new Bundle();
                                    extras.putLong("android.media.extra.BT_FOLDER_TYPE", this.getLong("android.media.metadata.BT_FOLDER_TYPE"));
                                    mediaDescriptionCompat$Builder.setExtras(extras);
                                }
                                return this.mDescription = mediaDescriptionCompat$Builder.build();
                            }
                        }
                        final Uri parse2 = null;
                        continue;
                    }
                }
            }
            final Bitmap bitmap = null;
            continue;
        }
    }
    
    public long getLong(final String s) {
        return this.mBundle.getLong(s, 0L);
    }
    
    public Object getMediaMetadata() {
        if (this.mMetadataObj != null || Build$VERSION.SDK_INT < 21) {
            return this.mMetadataObj;
        }
        final Parcel obtain = Parcel.obtain();
        this.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(obtain);
        obtain.recycle();
        return this.mMetadataObj;
    }
    
    public RatingCompat getRating(final String s) {
        try {
            if (Build$VERSION.SDK_INT >= 19) {
                return RatingCompat.fromRating(this.mBundle.getParcelable(s));
            }
            return (RatingCompat)this.mBundle.getParcelable(s);
        }
        catch (Exception ex) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", (Throwable)ex);
            return null;
        }
    }
    
    public String getString(final String s) {
        final CharSequence charSequence = this.mBundle.getCharSequence(s);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
    
    public CharSequence getText(final String s) {
        return this.mBundle.getCharSequence(s);
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.mBundle.keySet();
    }
    
    public int size() {
        return this.mBundle.size();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.mBundle);
    }
}
