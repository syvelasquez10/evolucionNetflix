// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public class DialogShow extends MdxMessage
{
    private static String PROPERTY_data;
    private static String PROPERTY_message;
    private static String PROPERTY_name;
    private static String PROPERTY_options;
    private static String PROPERTY_title;
    private static String PROPERTY_uid;
    private DialogItem[] mChoices;
    private String mMessage;
    private String mTitle;
    private String mUid;
    
    static {
        DialogShow.PROPERTY_uid = "uid";
        DialogShow.PROPERTY_title = "title";
        DialogShow.PROPERTY_message = "message";
        DialogShow.PROPERTY_options = "options";
        DialogShow.PROPERTY_name = "name";
        DialogShow.PROPERTY_data = "data";
    }
    
    public DialogShow(final JSONObject mJson) throws JSONException {
        super("DIALOG_SHOW");
        this.mJson = mJson;
        this.mUid = mJson.getString(DialogShow.PROPERTY_uid);
        this.mTitle = mJson.getString(DialogShow.PROPERTY_title);
        this.mMessage = mJson.getString(DialogShow.PROPERTY_message);
        final JSONArray jsonArray = mJson.getJSONArray(DialogShow.PROPERTY_options);
        this.mChoices = new DialogItem[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); ++i) {
            this.mChoices[i] = DialogItem.fromJson(jsonArray.getJSONObject(i));
        }
    }
    
    public DialogItem[] getChoices() {
        return this.mChoices;
    }
    
    public String getMessage() {
        return this.mMessage;
    }
    
    public String getTitle() {
        return this.mTitle;
    }
    
    public String getUid() {
        return this.mUid;
    }
    
    public static class DialogItem
    {
        public String data;
        public String name;
        
        public DialogItem(final String name, final String data) {
            this.name = name;
            this.data = data;
        }
        
        public static DialogItem fromJson(final JSONObject jsonObject) throws JSONException {
            return new DialogItem(jsonObject.getString(DialogShow.PROPERTY_name), jsonObject.getString(DialogShow.PROPERTY_data));
        }
        
        public JSONObject toJSon() throws JSONException {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put(DialogShow.PROPERTY_name, (Object)this.name);
            jsonObject.put(DialogShow.PROPERTY_data, (Object)this.data);
            return jsonObject;
        }
    }
}
