// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Array;

public final class ja
{
    private static void a(String s, final Object o, final StringBuffer sb, final StringBuffer sb2) throws IllegalAccessException, InvocationTargetException {
        if (o != null) {
            Label_0431: {
                if (!(o instanceof iz)) {
                    break Label_0431;
                }
                final int length = sb.length();
                if (s != null) {
                    sb2.append(sb).append(aE(s)).append(" <\n");
                    sb.append("  ");
                }
                final Class<?> class1 = o.getClass();
                final Field[] fields = class1.getFields();
                for (int length2 = fields.length, i = 0; i < length2; ++i) {
                    final Field field = fields[i];
                    final int modifiers = field.getModifiers();
                    final String name = field.getName();
                    if ((modifiers & 0x1) == 0x1 && (modifiers & 0x8) != 0x8 && !name.startsWith("_") && !name.endsWith("_")) {
                        final Class<?> type = field.getType();
                        final Object value = field.get(o);
                        if (type.isArray()) {
                            if (type.getComponentType() == Byte.TYPE) {
                                a(name, value, sb, sb2);
                            }
                            else {
                                int length3;
                                if (value == null) {
                                    length3 = 0;
                                }
                                else {
                                    length3 = Array.getLength(value);
                                }
                                for (int j = 0; j < length3; ++j) {
                                    a(name, Array.get(value, j), sb, sb2);
                                }
                            }
                        }
                        else {
                            a(name, value, sb, sb2);
                        }
                    }
                }
                final Method[] methods = class1.getMethods();
                final int length4 = methods.length;
                int n = 0;
            Label_0349_Outer:
                while (true) {
                    Label_0409: {
                        if (n >= length4) {
                            break Label_0409;
                        }
                        final String name2 = methods[n].getName();
                        while (true) {
                            if (!name2.startsWith("set")) {
                                break Label_0349;
                            }
                            final String substring = name2.substring(3);
                            try {
                                if (class1.getMethod("has" + substring, (Class<?>[])new Class[0]).invoke(o, new Object[0])) {
                                    try {
                                        a(substring, class1.getMethod("get" + substring, (Class<?>[])new Class[0]).invoke(o, new Object[0]), sb, sb2);
                                        break Label_0349;
                                        Label_0490: {
                                            a((byte[])o, sb2);
                                        }
                                        // iftrue(Label_0511:, !o instanceof byte[])
                                        // iftrue(Label_0004:, s == null)
                                        // iftrue(Label_0490:, !o instanceof String)
                                        while (true) {
                                            break Label_0482;
                                            Block_18: {
                                                Block_17: {
                                                    break Block_17;
                                                    s = aE(s);
                                                    sb2.append(sb).append(s).append(": ");
                                                    break Block_18;
                                                }
                                                sb.setLength(length);
                                                sb2.append(sb).append(">\n");
                                                return;
                                                sb2.append("\n");
                                                return;
                                            }
                                            s = aF((String)o);
                                            sb2.append("\"").append(s).append("\"");
                                            continue;
                                        }
                                        Label_0511: {
                                            sb2.append(o);
                                        }
                                    }
                                    catch (NoSuchMethodException ex) {}
                                }
                                ++n;
                                continue Label_0349_Outer;
                            }
                            catch (NoSuchMethodException ex2) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        Label_0004:;
    }
    
    private static void a(final byte[] array, final StringBuffer sb) {
        if (array == null) {
            sb.append("\"\"");
            return;
        }
        sb.append('\"');
        for (int i = 0; i < array.length; ++i) {
            final byte b = array[i];
            if (b == 92 || b == 34) {
                sb.append('\\').append((char)b);
            }
            else if (b >= 32 && b < 127) {
                sb.append((char)b);
            }
            else {
                sb.append(String.format("\\%03o", b));
            }
        }
        sb.append('\"');
    }
    
    private static String aE(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(char1));
            }
            else if (Character.isUpperCase(char1)) {
                sb.append('_').append(Character.toLowerCase(char1));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    private static String aF(final String s) {
        String string = s;
        if (!s.startsWith("http")) {
            string = s;
            if (s.length() > 200) {
                string = s.substring(0, 200) + "[...]";
            }
        }
        return aa(string);
    }
    
    private static String aa(final String s) {
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= ' ' && char1 <= '~' && char1 != '\"' && char1 != '\'') {
                sb.append(char1);
            }
            else {
                sb.append(String.format("\\u%04x", (int)char1));
            }
        }
        return sb.toString();
    }
    
    public static <T extends iz> String b(final T t) {
        if (t == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        try {
            a(null, t, new StringBuffer(), sb);
            return sb.toString();
        }
        catch (IllegalAccessException ex) {
            return "Error printing proto: " + ex.getMessage();
        }
        catch (InvocationTargetException ex2) {
            return "Error printing proto: " + ex2.getMessage();
        }
    }
}
