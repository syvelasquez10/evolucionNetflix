// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.util.Arrays;
import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Pair;

public class RemoteDialog
{
    private final String TAG;
    private String mMessage;
    private String mTitle;
    private Pair<String, String>[] options;
    
    public RemoteDialog(final String s) throws JSONException {
        this(new JSONObject(s));
    }
    
    public RemoteDialog(final JSONObject jsonObject) throws JSONException {
        this.TAG = "mdxui";
        this.mTitle = JsonUtils.getString(jsonObject, "title", null);
        this.mMessage = JsonUtils.getString(jsonObject, "message", null);
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "options");
        if (jsonArray == null) {
            Log.e("mdxui", "Invalid data, no options found!");
            this.options = (Pair<String, String>[])new Pair[0];
        }
        else {
            this.options = (Pair<String, String>[])new Pair[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                this.options[i] = (Pair<String, String>)Pair.create((Object)JsonUtils.getString(jsonObject2, "name", null), (Object)JsonUtils.getString(jsonObject2, "data", null));
            }
        }
    }
    
    public String getMessage() {
        return this.mMessage;
    }
    
    public Pair<String, String>[] getOptions() {
        return this.options;
    }
    
    public String getTitle() {
        return this.mTitle;
    }
    
    public boolean isValid() {
        return this.options != null && this.options.length > 0;
    }
    
    public void setTitle(final String mTitle) {
        this.mTitle = mTitle;
    }
    
    @Override
    public String toString() {
        return "RemoteDialog [ mTitle=" + this.mTitle + ", mMessage=" + this.mMessage + ", options=" + Arrays.toString(this.options) + "]";
    }
}
