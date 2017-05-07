// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseUIEvent extends BaseNccpEvent
{
    public static final String DATA = "data";
    public static final String NAME = "name";
    public static final String NAME_IMC = "IMediaControl";
    public static final String NRDP = "nrdp";
    public static final String OBJECT = "object";
    protected static final String TAG = "nf_ui_event";
    public static final String TYPE = "type";
    public static final String TYPE_EVENT = "Event";
    
    public BaseUIEvent(final String s) {
        super(s);
    }
    
    public String getJSON() {
        while (true) {
            try {
                while (true) {
                    final JSONObject jsonObject = new JSONObject();
                    while (true) {
                        try {
                            jsonObject.put("name", (Object)this.getName());
                            jsonObject.put("type", (Object)"Event");
                            jsonObject.put("object", (Object)this.getObject());
                            jsonObject.put("nrdp", 0);
                            if (this.getData() != null) {
                                jsonObject.put("data", (Object)this.getData());
                            }
                            else {
                                jsonObject.put("data", (Object)new JSONObject());
                            }
                            return jsonObject.toString();
                        }
                        catch (JSONException ex2) {}
                        final JSONException ex;
                        Log.e("nf_ui_event", "Failed to create", (Throwable)ex);
                        continue;
                    }
                }
            }
            catch (JSONException ex) {
                final JSONObject jsonObject = null;
                continue;
            }
            break;
        }
    }
    
    @Override
    public String getName() {
        return this.getType();
    }
    
    @Override
    public int getNrdSource() {
        return 0;
    }
}
