// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.HashMap;

public class XMLTokener extends JSONTokener
{
    public static final HashMap entity;
    
    static {
        (entity = new HashMap(8)).put("amp", XML.AMP);
        XMLTokener.entity.put("apos", XML.APOS);
        XMLTokener.entity.put("gt", XML.GT);
        XMLTokener.entity.put("lt", XML.LT);
        XMLTokener.entity.put("quot", XML.QUOT);
    }
    
    public XMLTokener(final String s) {
        super(s);
    }
    
    public String nextCDATA() {
        final StringBuffer sb = new StringBuffer();
        int length;
        do {
            final char next = this.next();
            if (this.end()) {
                throw this.syntaxError("Unclosed CDATA");
            }
            sb.append(next);
            length = sb.length() - 3;
        } while (length < 0 || sb.charAt(length) != ']' || sb.charAt(length + 1) != ']' || sb.charAt(length + 2) != '>');
        sb.setLength(length);
        return sb.toString();
    }
    
    public Object nextContent() {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));
        if (c == '\0') {
            return null;
        }
        if (c == '<') {
            return XML.LT;
        }
        final StringBuffer sb = new StringBuffer();
        while (c != '<' && c != '\0') {
            if (c == '&') {
                sb.append(this.nextEntity(c));
            }
            else {
                sb.append(c);
            }
            c = this.next();
        }
        this.back();
        return sb.toString().trim();
    }
    
    public Object nextEntity(final char c) {
        final StringBuffer sb = new StringBuffer();
        char next;
        while (true) {
            next = this.next();
            if (!Character.isLetterOrDigit(next) && next != '#') {
                break;
            }
            sb.append(Character.toLowerCase(next));
        }
        if (next != ';') {
            throw this.syntaxError("Missing ';' in XML entity: &" + (Object)sb);
        }
        final String string = sb.toString();
        final Object value = XMLTokener.entity.get(string);
        if (value != null) {
            return value;
        }
        return c + string + ";";
    }
    
    public Object nextMeta() {
        char next;
        do {
            next = this.next();
        } while (Character.isWhitespace(next));
        switch (next) {
            default: {
                while (true) {
                    final char next2 = this.next();
                    if (Character.isWhitespace(next2)) {
                        return Boolean.TRUE;
                    }
                    switch (next2) {
                        default: {
                            continue;
                        }
                        case '\0':
                        case '!':
                        case '\"':
                        case '\'':
                        case '/':
                        case '<':
                        case '=':
                        case '>':
                        case '?': {
                            this.back();
                            return Boolean.TRUE;
                        }
                    }
                }
                break;
            }
            case '\0': {
                throw this.syntaxError("Misshaped meta tag");
            }
            case '<': {
                return XML.LT;
            }
            case '>': {
                return XML.GT;
            }
            case '/': {
                return XML.SLASH;
            }
            case '=': {
                return XML.EQ;
            }
            case '!': {
                return XML.BANG;
            }
            case '?': {
                return XML.QUEST;
            }
            case '\"':
            case '\'': {
                char next3;
                do {
                    next3 = this.next();
                    if (next3 == '\0') {
                        throw this.syntaxError("Unterminated string");
                    }
                } while (next3 != next);
                return Boolean.TRUE;
            }
        }
    }
    
    public Object nextToken() {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));
        switch (c) {
            default: {
                final StringBuffer sb = new StringBuffer();
                while (true) {
                    sb.append(c);
                    c = this.next();
                    if (Character.isWhitespace(c)) {
                        return sb.toString();
                    }
                    switch (c) {
                        default: {
                            continue;
                        }
                        case '\0': {
                            return sb.toString();
                        }
                        case '!':
                        case '/':
                        case '=':
                        case '>':
                        case '?':
                        case '[':
                        case ']': {
                            this.back();
                            return sb.toString();
                        }
                        case '\"':
                        case '\'':
                        case '<': {
                            throw this.syntaxError("Bad character in a name");
                        }
                    }
                }
                break;
            }
            case '\0': {
                throw this.syntaxError("Misshaped element");
            }
            case '<': {
                throw this.syntaxError("Misplaced '<'");
            }
            case '>': {
                return XML.GT;
            }
            case '/': {
                return XML.SLASH;
            }
            case '=': {
                return XML.EQ;
            }
            case '!': {
                return XML.BANG;
            }
            case '?': {
                return XML.QUEST;
            }
            case '\"':
            case '\'': {
                final StringBuffer sb2 = new StringBuffer();
                while (true) {
                    final char next = this.next();
                    if (next == '\0') {
                        throw this.syntaxError("Unterminated string");
                    }
                    if (next == c) {
                        return sb2.toString();
                    }
                    if (next == '&') {
                        sb2.append(this.nextEntity(next));
                    }
                    else {
                        sb2.append(next);
                    }
                }
                break;
            }
        }
    }
    
    public boolean skipPast(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            final char next = this.next();
            if (next == '\0') {
                return false;
            }
            array[i] = next;
        }
        int n = 0;
    Label_0069:
        while (true) {
            int n2 = n;
            int j = 0;
            while (true) {
                while (j < length) {
                    if (array[n2] != s.charAt(j)) {
                        final int n3 = 0;
                        if (n3 != 0) {
                            return true;
                        }
                        final char next2 = this.next();
                        if (next2 == '\0') {
                            return false;
                        }
                        array[n] = next2;
                        final int n4 = n + 1;
                        if ((n = n4) >= length) {
                            n = n4 - length;
                            continue Label_0069;
                        }
                        continue Label_0069;
                    }
                    else {
                        final int n5 = n2 + 1;
                        if ((n2 = n5) >= length) {
                            n2 = n5 - length;
                        }
                        ++j;
                    }
                }
                final int n3 = 1;
                continue;
            }
        }
    }
}
