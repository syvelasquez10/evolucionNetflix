// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONArray;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import org.json.JSONObject;

public class DialogShow$DialogItem
{
    public String data;
    public String name;
    
    public DialogShow$DialogItem(final String name, final String data) {
        this.name = name;
        this.data = data;
    }
    
    public static DialogShow$DialogItem fromJson(final JSONObject jsonObject) {
        return new DialogShow$DialogItem(jsonObject.getString(DialogShow.PROPERTY_name), jsonObject.getString(DialogShow.PROPERTY_data));
    }
    
    public JSONObject toJSon() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put(DialogShow.PROPERTY_name, (Object)this.name);
        jsonObject.put(DialogShow.PROPERTY_data, (Object)this.data);
        return jsonObject;
    }
}
