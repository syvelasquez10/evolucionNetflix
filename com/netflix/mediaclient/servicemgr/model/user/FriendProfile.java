// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.user;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class FriendProfile implements JsonPopulator
{
    private static final String TAG = "FriendProfile";
    private String firstName;
    private String id;
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
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getFullName() {
        return (this.getFirstName() + " " + this.getLastName()).trim();
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
        if (Log.isLoggable("FriendProfile", 2)) {
            Log.v("FriendProfile", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0142: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0142;
                        }
                        break;
                    }
                    case 132835675: {
                        if (s.equals("firstName")) {
                            n = 1;
                            break Label_0142;
                        }
                        break;
                    }
                    case -1459599807: {
                        if (s.equals("lastName")) {
                            n = 2;
                            break Label_0142;
                        }
                        break;
                    }
                    case -859610604: {
                        if (s.equals("imageUrl")) {
                            n = 3;
                            break Label_0142;
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
            }
        }
    }
}
