// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public final class $AutoValue_UmaCta$GsonTypeAdapter extends TypeAdapter<UmaCta>
{
    private final TypeAdapter<String> actionAdapter;
    private final TypeAdapter<String> actionTypeAdapter;
    private final TypeAdapter<Boolean> autoLoginAdapter;
    private final TypeAdapter<String> callbackAdapter;
    private final TypeAdapter<Boolean> selectedAdapter;
    private final TypeAdapter<String> textAdapter;
    
    public $AutoValue_UmaCta$GsonTypeAdapter(final Gson gson) {
        this.textAdapter = gson.getAdapter(String.class);
        this.actionAdapter = gson.getAdapter(String.class);
        this.actionTypeAdapter = gson.getAdapter(String.class);
        this.callbackAdapter = gson.getAdapter(String.class);
        this.selectedAdapter = gson.getAdapter(Boolean.class);
        this.autoLoginAdapter = gson.getAdapter(Boolean.class);
    }
    
    @Override
    public UmaCta read(final JsonReader jsonReader) {
        String s = null;
        jsonReader.beginObject();
        boolean booleanValue = false;
        boolean booleanValue2 = false;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
            }
            else {
                switch (nextName) {
                    default: {
                        jsonReader.skipValue();
                        continue;
                    }
                    case "text": {
                        s4 = this.textAdapter.read(jsonReader);
                        continue;
                    }
                    case "action": {
                        s3 = this.actionAdapter.read(jsonReader);
                        continue;
                    }
                    case "actionType": {
                        s2 = this.actionTypeAdapter.read(jsonReader);
                        continue;
                    }
                    case "callback": {
                        s = this.callbackAdapter.read(jsonReader);
                        continue;
                    }
                    case "selected": {
                        booleanValue2 = this.selectedAdapter.read(jsonReader);
                        continue;
                    }
                    case "autoLogin": {
                        booleanValue = this.autoLoginAdapter.read(jsonReader);
                        continue;
                    }
                }
            }
        }
        jsonReader.endObject();
        return new AutoValue_UmaCta(s4, s3, s2, s, booleanValue2, booleanValue);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final UmaCta umaCta) {
        jsonWriter.beginObject();
        if (umaCta.text() != null) {
            jsonWriter.name("text");
            this.textAdapter.write(jsonWriter, umaCta.text());
        }
        if (umaCta.action() != null) {
            jsonWriter.name("action");
            this.actionAdapter.write(jsonWriter, umaCta.action());
        }
        if (umaCta.actionType() != null) {
            jsonWriter.name("actionType");
            this.actionTypeAdapter.write(jsonWriter, umaCta.actionType());
        }
        if (umaCta.callback() != null) {
            jsonWriter.name("callback");
            this.callbackAdapter.write(jsonWriter, umaCta.callback());
        }
        jsonWriter.name("selected");
        this.selectedAdapter.write(jsonWriter, umaCta.selected());
        jsonWriter.name("autoLogin");
        this.autoLoginAdapter.write(jsonWriter, umaCta.autoLogin());
        jsonWriter.endObject();
    }
}
