// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R$string;
import com.google.android.gms.ads.internal.zzp;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;

@zzgk
public class zzev extends zzfb
{
    private final Context mContext;
    private final Map<String, String> zzvs;
    private String zzzi;
    private long zzzj;
    private long zzzk;
    private String zzzl;
    private String zzzm;
    
    public zzev(final zzip zzip, final Map<String, String> zzvs) {
        super(zzip, "createCalendarEvent");
        this.zzvs = zzvs;
        this.mContext = (Context)zzip.zzgN();
        this.zzdV();
    }
    
    private String zzae(final String s) {
        if (TextUtils.isEmpty((CharSequence)this.zzvs.get(s))) {
            return "";
        }
        return this.zzvs.get(s);
    }
    
    private long zzaf(String s) {
        s = this.zzvs.get(s);
        if (s == null) {
            return -1L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            return -1L;
        }
    }
    
    private void zzdV() {
        this.zzzi = this.zzae("description");
        this.zzzl = this.zzae("summary");
        this.zzzj = this.zzaf("start_ticks");
        this.zzzk = this.zzaf("end_ticks");
        this.zzzm = this.zzae("location");
    }
    
    Intent createIntent() {
        final Intent setData = new Intent("android.intent.action.EDIT").setData(CalendarContract$Events.CONTENT_URI);
        setData.putExtra("title", this.zzzi);
        setData.putExtra("eventLocation", this.zzzm);
        setData.putExtra("description", this.zzzl);
        if (this.zzzj > -1L) {
            setData.putExtra("beginTime", this.zzzj);
        }
        if (this.zzzk > -1L) {
            setData.putExtra("endTime", this.zzzk);
        }
        setData.setFlags(268435456);
        return setData;
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzah("Activity context is not available.");
            return;
        }
        if (!zzp.zzbx().zzM(this.mContext).zzda()) {
            this.zzah("This feature is not available on the device.");
            return;
        }
        final AlertDialog$Builder zzL = zzp.zzbx().zzL(this.mContext);
        zzL.setTitle((CharSequence)zzp.zzbA().zzc(R$string.create_calendar_title, "Create calendar event"));
        zzL.setMessage((CharSequence)zzp.zzbA().zzc(R$string.create_calendar_message, "Allow Ad to create a calendar event?"));
        zzL.setPositiveButton((CharSequence)zzp.zzbA().zzc(R$string.accept, "Accept"), (DialogInterface$OnClickListener)new zzev$1(this));
        zzL.setNegativeButton((CharSequence)zzp.zzbA().zzc(R$string.decline, "Decline"), (DialogInterface$OnClickListener)new zzev$2(this));
        zzL.create().show();
    }
}
