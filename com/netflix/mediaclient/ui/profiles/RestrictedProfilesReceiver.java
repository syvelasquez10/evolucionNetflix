// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.os.Bundle;
import java.util.ArrayList;
import android.content.RestrictionEntry;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.os.UserManager;
import android.content.Context;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;

@TargetApi(18)
public class RestrictedProfilesReceiver extends BroadcastReceiver
{
    private static final String KEY_DISABLE_PROFILE_SWITCHING = "key_disable_profile_switching";
    private static final String TAG = "RestrictedProfilesReceiver";
    
    public static boolean isProfileSwitchingDisabled(final Context context) {
        return ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName()).getBoolean("key_disable_profile_switching");
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (AndroidUtils.getAndroidVersion() < 18) {
            return;
        }
        final String action = intent.getAction();
        if (!"android.intent.action.GET_RESTRICTION_ENTRIES".equals(action)) {
            Log.w("RestrictedProfilesReceiver", "Unknown action: " + action);
            return;
        }
        Log.i("RestrictedProfilesReceiver", "Adding restriction to disable profile switching");
        final RestrictionEntry restrictionEntry = new RestrictionEntry("key_disable_profile_switching", intent.getBundleExtra("android.intent.extra.restrictions_bundle").getBoolean("key_disable_profile_switching", false));
        restrictionEntry.setTitle(context.getString(2131493269));
        final ArrayList<RestrictionEntry> list = new ArrayList<RestrictionEntry>();
        list.add(restrictionEntry);
        final Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("android.intent.extra.restrictions_list", (ArrayList)list);
        this.setResult(-1, (String)null, bundle);
    }
}
