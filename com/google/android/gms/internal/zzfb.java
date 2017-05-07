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

@zzgr
public class zzfb extends zzfh
{
    private final Context mContext;
    private final Map<String, String> zzvS;
    private String zzzV;
    private long zzzW;
    private long zzzX;
    private String zzzY;
    private String zzzZ;
    
    public zzfb(final zziz zziz, final Map<String, String> zzvS) {
        super(zziz, "createCalendarEvent");
        this.zzvS = zzvS;
        this.mContext = (Context)zziz.zzgZ();
        this.zzec();
    }
    
    private String zzah(final String s) {
        if (TextUtils.isEmpty((CharSequence)this.zzvS.get(s))) {
            return "";
        }
        return this.zzvS.get(s);
    }
    
    private long zzai(String s) {
        s = this.zzvS.get(s);
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
    
    private void zzec() {
        this.zzzV = this.zzah("description");
        this.zzzY = this.zzah("summary");
        this.zzzW = this.zzai("start_ticks");
        this.zzzX = this.zzai("end_ticks");
        this.zzzZ = this.zzah("location");
    }
    
    Intent createIntent() {
        final Intent setData = new Intent("android.intent.action.EDIT").setData(CalendarContract$Events.CONTENT_URI);
        setData.putExtra("title", this.zzzV);
        setData.putExtra("eventLocation", this.zzzZ);
        setData.putExtra("description", this.zzzY);
        if (this.zzzW > -1L) {
            setData.putExtra("beginTime", this.zzzW);
        }
        if (this.zzzX > -1L) {
            setData.putExtra("endTime", this.zzzX);
        }
        setData.setFlags(268435456);
        return setData;
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzak("Activity context is not available.");
            return;
        }
        if (!zzp.zzbv().zzL(this.mContext).zzdb()) {
            this.zzak("This feature is not available on the device.");
            return;
        }
        final AlertDialog$Builder zzK = zzp.zzbv().zzK(this.mContext);
        zzK.setTitle((CharSequence)zzp.zzby().zzd(R$string.create_calendar_title, "Create calendar event"));
        zzK.setMessage((CharSequence)zzp.zzby().zzd(R$string.create_calendar_message, "Allow Ad to create a calendar event?"));
        zzK.setPositiveButton((CharSequence)zzp.zzby().zzd(R$string.accept, "Accept"), (DialogInterface$OnClickListener)new zzfb$1(this));
        zzK.setNegativeButton((CharSequence)zzp.zzby().zzd(R$string.decline, "Decline"), (DialogInterface$OnClickListener)new zzfb$2(this));
        zzK.create().show();
    }
}
