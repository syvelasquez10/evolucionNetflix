// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import com.google.android.gms.tagmanager.zzbg;
import java.util.ArrayList;
import org.json.JSONException;
import java.util.List;
import java.util.Iterator;
import com.google.android.gms.tagmanager.zzdf;
import org.json.JSONObject;
import java.util.Set;
import org.json.JSONArray;

final class zzqj
{
    static zzag$zza zza(final int n, final JSONArray jsonArray, final zzag$zza[] array, final Set<Integer> set) {
        final int n2 = 0;
        int i = 0;
        if (set.contains(n)) {
            zzfi("Value cycle detected. Current value reference: " + n + "." + "  Previous value references: " + set + ".");
        }
        final Integer zza = zza(jsonArray, n, "values");
        if (array[n] != null) {
            return array[n];
        }
        set.add(n);
        final zzag$zza zzag$zza = new zzag$zza();
        if (zza instanceof JSONArray) {
            final JSONArray jsonArray2 = (JSONArray)zza;
            zzag$zza.type = 2;
            zzag$zza.zzje = true;
            zzag$zza.zziV = new zzag$zza[jsonArray2.length()];
            while (i < zzag$zza.zziV.length) {
                zzag$zza.zziV[i] = zza(jsonArray2.getInt(i), jsonArray, array, set);
                ++i;
            }
        }
        else if (zza instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)zza;
            final JSONArray optJSONArray = jsonObject.optJSONArray("escaping");
            if (optJSONArray != null) {
                zzag$zza.zzjd = new int[optJSONArray.length()];
                for (int j = 0; j < zzag$zza.zzjd.length; ++j) {
                    zzag$zza.zzjd[j] = optJSONArray.getInt(j);
                }
            }
            if (jsonObject.has("function_id")) {
                zzag$zza.type = 5;
                zzag$zza.zziZ = jsonObject.getString("function_id");
            }
            else if (jsonObject.has("macro_reference")) {
                zzag$zza.type = 4;
                zzag$zza.zzje = true;
                zzag$zza.zziY = zzdf.zzg(zza(jsonObject.getInt("macro_reference"), jsonArray, array, set));
            }
            else if (jsonObject.has("template_token")) {
                zzag$zza.type = 7;
                zzag$zza.zzje = true;
                final JSONArray jsonArray3 = jsonObject.getJSONArray("template_token");
                zzag$zza.zzjc = new zzag$zza[jsonArray3.length()];
                for (int k = n2; k < zzag$zza.zzjc.length; ++k) {
                    zzag$zza.zzjc[k] = zza(jsonArray3.getInt(k), jsonArray, array, set);
                }
            }
            else {
                zzag$zza.type = 3;
                zzag$zza.zzje = true;
                final int length = jsonObject.length();
                zzag$zza.zziW = new zzag$zza[length];
                zzag$zza.zziX = new zzag$zza[length];
                final Iterator keys = jsonObject.keys();
                int n3 = 0;
                while (keys.hasNext()) {
                    final String s = keys.next();
                    zzag$zza.zziW[n3] = zza(new Integer(s), jsonArray, array, set);
                    zzag$zza.zziX[n3] = zza(jsonObject.getInt(s), jsonArray, array, set);
                    ++n3;
                }
            }
        }
        else if (zza instanceof String) {
            zzag$zza.zziU = (String)zza;
            zzag$zza.type = 1;
        }
        else if (zza instanceof Boolean) {
            zzag$zza.zzjb = (Boolean)(Object)zza;
            zzag$zza.type = 8;
        }
        else if (zza instanceof Integer) {
            zzag$zza.zzja = zza;
            zzag$zza.type = 6;
        }
        else {
            zzfi("Invalid value type: " + zza);
        }
        array[n] = zzag$zza;
        set.remove(n);
        return zzag$zza;
    }
    
    static zzqp$zza zza(final JSONObject jsonObject, final JSONArray jsonArray, final JSONArray jsonArray2, final zzag$zza[] array, int i) {
        final zzqp$zzb zzBC = zzqp$zza.zzBC();
        JSONArray jsonArray3;
        JSONObject jsonObject2;
        String s;
        zzag$zza zzag$zza;
        for (jsonArray3 = jsonObject.getJSONArray("property"), i = 0; i < jsonArray3.length(); ++i) {
            jsonObject2 = zza(jsonArray, jsonArray3.getInt(i), "properties");
            s = zza(jsonArray2, jsonObject2.getInt("key"), "key");
            zzag$zza = zza(array, jsonObject2.getInt("value"), "value");
            if (zzae.zzgv.toString().equals(s)) {
                zzBC.zzq(zzag$zza);
            }
            else {
                zzBC.zzb(s, zzag$zza);
            }
        }
        return zzBC.zzBE();
    }
    
    static zzqp$zze zza(final JSONObject jsonObject, final List<zzqp$zza> list, final List<zzqp$zza> list2, final List<zzqp$zza> list3, final zzag$zza[] array) {
        final zzqp$zzf zzBJ = zzqp$zze.zzBJ();
        final JSONArray optJSONArray = jsonObject.optJSONArray("positive_predicate");
        final JSONArray optJSONArray2 = jsonObject.optJSONArray("negative_predicate");
        final JSONArray optJSONArray3 = jsonObject.optJSONArray("add_tag");
        final JSONArray optJSONArray4 = jsonObject.optJSONArray("remove_tag");
        final JSONArray optJSONArray5 = jsonObject.optJSONArray("add_tag_rule_name");
        final JSONArray optJSONArray6 = jsonObject.optJSONArray("remove_tag_rule_name");
        final JSONArray optJSONArray7 = jsonObject.optJSONArray("add_macro");
        final JSONArray optJSONArray8 = jsonObject.optJSONArray("remove_macro");
        final JSONArray optJSONArray9 = jsonObject.optJSONArray("add_macro_rule_name");
        final JSONArray optJSONArray10 = jsonObject.optJSONArray("remove_macro_rule_name");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); ++i) {
                zzBJ.zzd(list3.get(optJSONArray.getInt(i)));
            }
        }
        if (optJSONArray2 != null) {
            for (int j = 0; j < optJSONArray2.length(); ++j) {
                zzBJ.zze(list3.get(optJSONArray2.getInt(j)));
            }
        }
        if (optJSONArray3 != null) {
            for (int k = 0; k < optJSONArray3.length(); ++k) {
                zzBJ.zzf(list.get(optJSONArray3.getInt(k)));
            }
        }
        if (optJSONArray4 != null) {
            for (int l = 0; l < optJSONArray4.length(); ++l) {
                zzBJ.zzg(list.get(optJSONArray4.getInt(l)));
            }
        }
        if (optJSONArray5 != null) {
            for (int n = 0; n < optJSONArray5.length(); ++n) {
                zzBJ.zzfl(array[optJSONArray5.getInt(n)].zziU);
            }
        }
        if (optJSONArray6 != null) {
            for (int n2 = 0; n2 < optJSONArray6.length(); ++n2) {
                zzBJ.zzfm(array[optJSONArray6.getInt(n2)].zziU);
            }
        }
        if (optJSONArray7 != null) {
            for (int n3 = 0; n3 < optJSONArray7.length(); ++n3) {
                zzBJ.zzh(list2.get(optJSONArray7.getInt(n3)));
            }
        }
        if (optJSONArray8 != null) {
            for (int n4 = 0; n4 < optJSONArray8.length(); ++n4) {
                zzBJ.zzi(list2.get(optJSONArray8.getInt(n4)));
            }
        }
        if (optJSONArray9 != null) {
            for (int n5 = 0; n5 < optJSONArray9.length(); ++n5) {
                zzBJ.zzfn(array[optJSONArray9.getInt(n5)].zziU);
            }
        }
        if (optJSONArray10 != null) {
            for (int n6 = 0; n6 < optJSONArray10.length(); ++n6) {
                zzBJ.zzfo(array[optJSONArray10.getInt(n6)].zziU);
            }
        }
        return zzBJ.zzBU();
    }
    
    private static <T> T zza(final JSONArray jsonArray, final int n, final String s) {
        if (n >= 0 && n < jsonArray.length()) {
            try {
                return (T)jsonArray.get(n);
            }
            catch (JSONException ex) {}
        }
        zzfi("Index out of bounds detected: " + n + " in " + s);
        return null;
    }
    
    private static <T> T zza(final T[] array, final int n, final String s) {
        if (n < 0 || n >= array.length) {
            zzfi("Index out of bounds detected: " + n + " in " + s);
        }
        return array[n];
    }
    
    static List<zzqp$zza> zza(final JSONArray jsonArray, final JSONArray jsonArray2, final JSONArray jsonArray3, final zzag$zza[] array) {
        final ArrayList<zzqp$zza> list = new ArrayList<zzqp$zza>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(zza(jsonArray.getJSONObject(i), jsonArray2, jsonArray3, array, i));
        }
        return list;
    }
    
    static zzqp$zzc zzeN(final String s) {
        final JSONObject jsonObject = new JSONObject(s);
        final Object value = jsonObject.get("resource");
        if (value instanceof JSONObject) {
            final JSONObject jsonObject2 = (JSONObject)value;
            final zzqp$zzd zzBF = zzqp$zzc.zzBF();
            final zzag$zza[] zzj = zzj(jsonObject2);
            final JSONArray jsonArray = jsonObject2.getJSONArray("properties");
            final JSONArray jsonArray2 = jsonObject2.getJSONArray("key");
            final List<zzqp$zza> zza = zza(jsonObject2.getJSONArray("tags"), jsonArray, jsonArray2, zzj);
            final List<zzqp$zza> zza2 = zza(jsonObject2.getJSONArray("predicates"), jsonArray, jsonArray2, zzj);
            final List<zzqp$zza> zza3 = zza(jsonObject2.getJSONArray("macros"), jsonArray, jsonArray2, zzj);
            final Iterator<zzqp$zza> iterator = zza3.iterator();
            while (iterator.hasNext()) {
                zzBF.zzc(iterator.next());
            }
            final JSONArray jsonArray3 = jsonObject2.getJSONArray("rules");
            for (int i = 0; i < jsonArray3.length(); ++i) {
                zzBF.zzb(zza(jsonArray3.getJSONObject(i), zza, zza3, zza2, zzj));
            }
            zzBF.zzfk("1");
            zzBF.zzjm(1);
            if (jsonObject.optJSONArray("runtime") != null) {}
            return zzBF.zzBI();
        }
        throw new zzqp$zzg("Resource map not found");
    }
    
    private static void zzfi(final String s) {
        zzbg.e(s);
        throw new zzqp$zzg(s);
    }
    
    static zzag$zza[] zzj(final JSONObject jsonObject) {
        final Object opt = jsonObject.opt("values");
        if (opt instanceof JSONArray) {
            final JSONArray jsonArray = (JSONArray)opt;
            final zzag$zza[] array = new zzag$zza[jsonArray.length()];
            for (int i = 0; i < array.length; ++i) {
                zza(i, jsonArray, array, new HashSet<Integer>(0));
            }
            return array;
        }
        throw new zzqp$zzg("Missing Values list");
    }
}
