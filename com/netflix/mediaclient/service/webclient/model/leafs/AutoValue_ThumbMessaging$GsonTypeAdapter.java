// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public final class AutoValue_ThumbMessaging$GsonTypeAdapter extends TypeAdapter<ThumbMessaging>
{
    private final TypeAdapter<Boolean> shouldShowFirstThumbsRatingMessageAdapter;
    private final TypeAdapter<Boolean> shouldShowOneTimeProfileThumbsMessageAdapter;
    
    public AutoValue_ThumbMessaging$GsonTypeAdapter(final Gson gson) {
        this.shouldShowOneTimeProfileThumbsMessageAdapter = gson.getAdapter(Boolean.class);
        this.shouldShowFirstThumbsRatingMessageAdapter = gson.getAdapter(Boolean.class);
    }
    
    @Override
    public ThumbMessaging read(final JsonReader jsonReader) {
        jsonReader.beginObject();
        boolean b = false;
        boolean b2 = false;
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
            }
            else {
                int n = -1;
                switch (nextName.hashCode()) {
                    case 623722052: {
                        if (nextName.equals("shouldShowOneTimeProfileThumbsMessage")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 1220374509: {
                        if (nextName.equals("shouldShowFirstThumbsRatingMessage")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                }
                boolean b4 = false;
                boolean b5 = false;
                switch (n) {
                    default: {
                        jsonReader.skipValue();
                        final boolean b3 = b;
                        b4 = b2;
                        b5 = b3;
                        break;
                    }
                    case 0: {
                        final boolean booleanValue = this.shouldShowOneTimeProfileThumbsMessageAdapter.read(jsonReader);
                        b5 = b;
                        b4 = booleanValue;
                        break;
                    }
                    case 1: {
                        final boolean booleanValue2 = this.shouldShowFirstThumbsRatingMessageAdapter.read(jsonReader);
                        b4 = b2;
                        b5 = booleanValue2;
                        break;
                    }
                }
                final boolean b6 = b4;
                b = b5;
                b2 = b6;
            }
        }
        jsonReader.endObject();
        return new AutoValue_ThumbMessaging(b2, b);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final ThumbMessaging thumbMessaging) {
        jsonWriter.beginObject();
        jsonWriter.name("shouldShowOneTimeProfileThumbsMessage");
        this.shouldShowOneTimeProfileThumbsMessageAdapter.write(jsonWriter, thumbMessaging.shouldShowOneTimeProfileThumbsMessage());
        jsonWriter.name("shouldShowFirstThumbsRatingMessage");
        this.shouldShowFirstThumbsRatingMessageAdapter.write(jsonWriter, thumbMessaging.shouldShowFirstThumbsRatingMessage());
        jsonWriter.endObject();
    }
}
