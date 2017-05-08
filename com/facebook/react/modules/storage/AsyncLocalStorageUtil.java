// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.util.Iterator;
import org.json.JSONObject;
import com.facebook.react.bridge.ReadableArray;
import android.text.TextUtils;
import java.util.Arrays;

public class AsyncLocalStorageUtil
{
    static String buildKeySelection(final int n) {
        final String[] array = new String[n];
        Arrays.fill(array, "?");
        return "key IN (" + TextUtils.join((CharSequence)", ", (Object[])array) + ")";
    }
    
    static String[] buildKeySelectionArgs(final ReadableArray readableArray, final int n, final int n2) {
        final String[] array = new String[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = readableArray.getString(n + i);
        }
        return array;
    }
    
    private static void deepMergeInto(final JSONObject jsonObject, final JSONObject jsonObject2) {
        final Iterator keys = jsonObject2.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final JSONObject optJSONObject = jsonObject2.optJSONObject(s);
            final JSONObject optJSONObject2 = jsonObject.optJSONObject(s);
            if (optJSONObject != null && optJSONObject2 != null) {
                deepMergeInto(optJSONObject2, optJSONObject);
                jsonObject.put(s, (Object)optJSONObject2);
            }
            else {
                jsonObject.put(s, jsonObject2.get(s));
            }
        }
    }
    
    public static String getItemImpl(SQLiteDatabase query, String string) {
        query = (SQLiteDatabase)query.query("catalystLocalStorage", new String[] { "value" }, "key=?", new String[] { string }, (String)null, (String)null, (String)null);
        try {
            if (!((Cursor)query).moveToFirst()) {
                return null;
            }
            string = ((Cursor)query).getString(0);
            return string;
        }
        finally {
            ((Cursor)query).close();
        }
    }
    
    static boolean mergeImpl(final SQLiteDatabase sqLiteDatabase, final String s, String string) {
        final String itemImpl = getItemImpl(sqLiteDatabase, s);
        if (itemImpl != null) {
            final JSONObject jsonObject = new JSONObject(itemImpl);
            deepMergeInto(jsonObject, new JSONObject(string));
            string = jsonObject.toString();
        }
        return setItemImpl(sqLiteDatabase, s, string);
    }
    
    static boolean setItemImpl(final SQLiteDatabase sqLiteDatabase, final String s, final String s2) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("key", s);
        contentValues.put("value", s2);
        return -1L != sqLiteDatabase.insertWithOnConflict("catalystLocalStorage", (String)null, contentValues, 5);
    }
}
