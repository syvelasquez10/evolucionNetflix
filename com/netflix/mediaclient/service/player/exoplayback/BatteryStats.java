// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;

class BatteryStats
{
    private BatteryStats$BStat atEndPlay;
    private BatteryStats$BStat atStartPlay;
    private Context mContext;
    private boolean present;
    private int scale;
    private String technology;
    private boolean wasCharged;
    
    public BatteryStats(final Context mContext, final boolean present, final String technology, final int scale) {
        this.mContext = mContext;
        this.present = present;
        this.technology = technology;
        this.scale = scale;
        this.atStartPlay = new BatteryStats$BStat(this);
        this.atEndPlay = new BatteryStats$BStat(this);
    }
    
    public static BatteryStats createBatteryStats(final Context context) {
        final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return new BatteryStats(context, registerReceiver.getExtras().getBoolean("present"), registerReceiver.getExtras().getString("technology"), registerReceiver.getIntExtra("scale", -1));
    }
    
    public JSONObject getJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("technology", (Object)this.technology);
            jsonObject.put("scale", this.scale);
            jsonObject.put("present", this.present);
            jsonObject.put("wasCharged", this.wasCharged);
            jsonObject.put("atStart", (Object)this.atStartPlay.getJSON());
            jsonObject.put("atEnd", (Object)this.atEndPlay.getJSON());
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    public void setWasCharged(final boolean wasCharged) {
        this.wasCharged = wasCharged;
    }
    
    public void updateBatteryStat(final boolean b) {
        final Intent registerReceiver = this.mContext.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        final int intExtra = registerReceiver.getIntExtra("level", -1);
        final int intExtra2 = registerReceiver.getIntExtra("health", 0);
        final int intExtra3 = registerReceiver.getIntExtra("plugged", 0);
        final int intExtra4 = registerReceiver.getIntExtra("status", 0);
        final int intExtra5 = registerReceiver.getIntExtra("temperature", 0);
        final int intExtra6 = registerReceiver.getIntExtra("voltage", 0);
        if (b) {
            this.atStartPlay.setStat(intExtra, intExtra2, intExtra3, intExtra4, intExtra5, intExtra6);
            return;
        }
        this.atEndPlay.setStat(intExtra, intExtra2, intExtra3, intExtra4, intExtra5, intExtra6);
    }
}
