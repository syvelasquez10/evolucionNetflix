// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class FriendProfile implements JsonMerger, JsonPopulator
{
    private static final String TAG = "FriendProfile";
    private String firstName;
    private String id;
    private String image145x145;
    private String imageUrl;
    private String lastName;
    
    public FriendProfile() {
    }
    
    public FriendProfile(final String id, final String firstName, final String lastName, final String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }
    
    public FriendProfile(final String s, final String s2, final String s3, final String s4, final String image145x145) {
        this(s, s2, s3, s4);
        this.image145x145 = image145x145;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final FriendProfile friendProfile = (FriendProfile)o;
            if (this.firstName == null) {
                if (friendProfile.firstName != null) {
                    return false;
                }
            }
            else if (!this.firstName.equals(friendProfile.firstName)) {
                return false;
            }
            if (this.id == null) {
                if (friendProfile.id != null) {
                    return false;
                }
            }
            else if (!this.id.equals(friendProfile.id)) {
                return false;
            }
            if (this.imageUrl == null) {
                if (friendProfile.imageUrl != null) {
                    return false;
                }
            }
            else if (!this.imageUrl.equals(friendProfile.imageUrl)) {
                return false;
            }
            if (this.image145x145 == null) {
                if (friendProfile.image145x145 != null) {
                    return false;
                }
            }
            else if (!this.image145x145.equals(friendProfile.image145x145)) {
                return false;
            }
            if (this.lastName == null) {
                if (friendProfile.lastName != null) {
                    return false;
                }
            }
            else if (!this.lastName.equals(friendProfile.lastName)) {
                return false;
            }
        }
        return true;
    }
    
    public String getBigImageUrl() {
        return this.image145x145;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getFullName() {
        if (StringUtils.isNotEmpty(this.getFirstName()) || StringUtils.isNotEmpty(this.getLastName())) {
            final StringBuilder sb = new StringBuilder();
            if (StringUtils.isNotEmpty(this.getFirstName())) {
                sb.append(this.getFirstName()).append(" ");
            }
            if (StringUtils.isNotEmpty(this.getLastName())) {
                sb.append(this.getLastName());
            }
            return sb.toString().trim();
        }
        return null;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("FriendProfile", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0146: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0146;
                        }
                        break;
                    }
                    case 132835675: {
                        if (s.equals("firstName")) {
                            n = 1;
                            break Label_0146;
                        }
                        break;
                    }
                    case -1459599807: {
                        if (s.equals("lastName")) {
                            n = 2;
                            break Label_0146;
                        }
                        break;
                    }
                    case -859610604: {
                        if (s.equals("imageUrl")) {
                            n = 3;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1012902577: {
                        if (s.equals("image145x145")) {
                            n = 4;
                            break Label_0146;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.id = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.firstName = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.lastName = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.imageUrl = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.image145x145 = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("FriendProfile", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "id": {
                this.id = jsonParser.getValueAsString();
                break;
            }
            case "firstName": {
                this.firstName = jsonParser.getValueAsString();
                break;
            }
            case "lastName": {
                this.lastName = jsonParser.getValueAsString();
                break;
            }
            case "imageUrl": {
                this.imageUrl = jsonParser.getValueAsString();
                break;
            }
            case "image145x145": {
                this.image145x145 = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
}
