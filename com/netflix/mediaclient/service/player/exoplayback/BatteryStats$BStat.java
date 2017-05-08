// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import org.json.JSONException;
import org.json.JSONObject;

class BatteryStats$BStat
{
    public int health;
    public int level;
    public int plugged;
    public int status;
    public int temperature;
    final /* synthetic */ BatteryStats this$0;
    public int voltage;
    
    BatteryStats$BStat(final BatteryStats this$0) {
        this.this$0 = this$0;
    }
    
    public JSONObject getJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("level", this.level);
            jsonObject.put("health", this.health);
            jsonObject.put("plugged", this.plugged);
            jsonObject.put("status", this.status);
            jsonObject.put("temperature", this.temperature);
            jsonObject.put("voltage", this.voltage);
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    public void setStat(final int level, final int health, final int plugged, final int status, final int temperature, final int voltage) {
        this.level = level;
        this.health = health;
        this.plugged = plugged;
        this.status = status;
        this.temperature = temperature;
        this.voltage = voltage;
    }
    
    @Override
    public String toString() {
        return "BStat{level=" + this.level + ", health=" + this.health + ", plugged=" + this.plugged + ", status=" + this.status + ", temperature=" + this.temperature + ", voltage=" + this.voltage + '}';
    }
}
