// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import java.util.Iterator;
import org.json.JSONArray;
import java.util.ArrayList;
import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.HashMap;
import com.netflix.mediaclient.javabridge.Bridge;
import java.util.List;
import java.util.Map;
import com.netflix.mediaclient.javabridge.ui.Storage;

public final class NativeStorage extends NativeNrdObject implements Storage
{
    private Map<String, List<KeyValuePair>> itemMapsByAccount;
    
    public NativeStorage(final Bridge bridge) {
        super(bridge);
        this.itemMapsByAccount = new HashMap<String, List<KeyValuePair>>();
    }
    
    private int handlePropertyUpdate(JSONObject jsonObject) throws JSONException {
        jsonObject = this.getJSONObject(jsonObject, "properties", null);
        if (jsonObject == null) {
            Log.w("nf_object", "handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        this.load(JsonUtils.getString(jsonObject, "data", null));
        return 1;
    }
    
    private void load(final String s) throws JSONException {
        this.itemMapsByAccount.clear();
        if (s != null && !s.equals("")) {
            final JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("accounts")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("accounts");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        final String string = JsonUtils.getString(jsonObject2, "dak", null);
                        final ArrayList<KeyValuePair> list = new ArrayList<KeyValuePair>();
                        this.itemMapsByAccount.put(string, list);
                        final JSONArray jsonArray2 = JsonUtils.getJSONArray(jsonObject2, "items");
                        int n = 0;
                        while (i < jsonArray2.length()) {
                            list.add(new KeyValuePair(jsonArray2.getJSONObject(n)));
                            ++n;
                        }
                    }
                }
            }
        }
    }
    
    private void save() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("accounts", (Object)jsonArray);
            for (final String s : this.itemMapsByAccount.keySet()) {
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("dak", (Object)s);
                jsonArray.put((Object)jsonObject2);
                final List<KeyValuePair> list = this.itemMapsByAccount.get(s);
                if (list != null) {
                    final JSONArray jsonArray2 = new JSONArray();
                    jsonObject2.put("items", (Object)jsonArray2);
                    final Iterator<KeyValuePair> iterator2 = list.iterator();
                    while (iterator2.hasNext()) {
                        jsonArray2.put((Object)iterator2.next().toJson());
                    }
                }
            }
        }
        catch (JSONException ex) {
            Log.e("nf_object", "Failed to save data", (Throwable)ex);
            return;
        }
        this.bridge.getNrdProxy().setProperty("storage", "data", jsonObject.toString());
    }
    
    @Override
    public void clear(final String s) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                try {
                    if (this.itemMapsByAccount.remove(s) == null) {
                        Log.w("nf_object", "Items not found! Nothing to clear!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                this.save();
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void clearAll() {
        synchronized (this) {
            this.itemMapsByAccount.clear();
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("accounts", (Object)"");
                this.bridge.getNrdProxy().setProperty("storage", "data", jsonObject.toString());
            }
            catch (JSONException ex) {
                Log.e("nf_object", "Failed to save data", (Throwable)ex);
            }
        }
    }
    
    @Override
    public String getItem(final String s, final String s2) {
        final KeyValuePair keyValuePair = null;
        // monitorenter(this)
        Object value = keyValuePair;
        Label_0019: {
            if (s2 != null) {
                if (s == null) {
                    value = keyValuePair;
                }
                else {
                    try {
                        final List<KeyValuePair> list = this.itemMapsByAccount.get(s);
                        value = keyValuePair;
                        if (list != null) {
                            final Iterator<KeyValuePair> iterator = list.iterator();
                            do {
                                value = keyValuePair;
                                if (!iterator.hasNext()) {
                                    break Label_0019;
                                }
                                value = iterator.next();
                            } while (!s2.equals(((KeyValuePair)value).key));
                            value = ((KeyValuePair)value).value;
                        }
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
            }
        }
        // monitorexit(this)
        return (String)value;
    }
    
    @Override
    public String getName() {
        return "storage";
    }
    
    @Override
    public String getPath() {
        return "nrdp.storage";
    }
    
    @Override
    public String key(final String s, final int n) {
        final String s2 = null;
        synchronized (this) {
            final List<KeyValuePair> list = this.itemMapsByAccount.get(s);
            String key;
            if (list != null) {
                final KeyValuePair keyValuePair = list.get(n);
                key = s2;
                if (keyValuePair != null) {
                    key = keyValuePair.key;
                }
            }
            else {
                key = s2;
                if (Log.isLoggable("nf_object", 3)) {
                    Log.d("nf_object", "Map not found for account key " + s);
                    key = s2;
                }
            }
            return key;
        }
    }
    
    @Override
    public int length(final String s) {
        return 0;
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (Log.isLoggable("nf_object", 3)) {
                Log.d("nf_object", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && Log.isLoggable("nf_object", 3)) {
                    Log.d("nf_object", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
        }
        catch (Exception ex) {
            Log.e("nf_object", "Failed with JSON", ex);
        }
        return 1;
    }
    
    @Override
    public void removeItem(final String s, final String s2) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                List<KeyValuePair> list;
                try {
                    list = this.itemMapsByAccount.get(s);
                    if (list == null) {
                        Log.w("nf_object", "Items not found! We can not remove item!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                final KeyValuePair keyValuePair = null;
                final Iterator<KeyValuePair> iterator = list.iterator();
                KeyValuePair keyValuePair2;
                do {
                    keyValuePair2 = keyValuePair;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    keyValuePair2 = iterator.next();
                } while (!s2.equals(keyValuePair2.key));
                if (keyValuePair2 != null) {
                    list.remove(keyValuePair2);
                }
                else if (Log.isLoggable("nf_object", 3)) {
                    final String s3;
                    Log.d("nf_object", "Item was not found for key " + s2 + " and account " + s3);
                }
                this.save();
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void setItem(final String s, final String s2, final String s3) {
        // monitorenter(this)
        if (s != null) {
            try {
                List<KeyValuePair> list;
                if ((list = this.itemMapsByAccount.get(s)) == null) {
                    list = new ArrayList<KeyValuePair>();
                    this.itemMapsByAccount.put(s, list);
                }
                list.add(new KeyValuePair(s2, s3));
                this.save();
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    @Override
    public int size() {
        return 0;
    }
    
    private class KeyValuePair
    {
        public String key;
        public String value;
        
        public KeyValuePair(final String key, final String value) {
            this.key = key;
            this.value = value;
        }
        
        public KeyValuePair(final JSONObject jsonObject) throws JSONException {
            this.key = JsonUtils.getString(jsonObject, "key", null);
            this.value = JsonUtils.getString(jsonObject, "value", null);
        }
        
        public JSONObject toJson() throws JSONException {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", (Object)this.key);
            jsonObject.put("value", (Object)this.value);
            return jsonObject;
        }
    }
}
