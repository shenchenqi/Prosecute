package com.micro.root.preferences;

import java.util.HashMap;

/**
 * created by kilin on 20-3-17 下午1:17
 */
public class AssocArray extends HashMap<Object, Object> {
    public static AssocArray array() {
        return new AssocArray();
    }

    public AssocArray add(Object str, Object obj) {
        put(str, obj);
        return this;
    }

    public Object getData(Object str, Object obj) {
        return containsKey(str) ? get(str) : obj;
    }

    public int getInt(Object str, int i) {
        Object obj = get(str);
        if (obj == null) {
            return i;
        }
        if (obj instanceof String) {
            return Integer.parseInt(obj.toString());
        }
        return (Integer) obj;
    }

    public boolean getBool(Object str, boolean z) {
        Object obj = get(str);
        if (obj == null) {
            return z;
        }
        return (Boolean) obj;
    }

    public long getLong(Object str, long j) {
        Object obj = get(str);
        if (obj == null) {
            return j;
        }
        if (obj instanceof String) {
            return Long.parseLong(obj.toString());
        }
        return (Long) obj;
    }

    public String getString(Object str, String str2) {
        Object obj = get(str);
        if (obj == null) {
            return str2;
        }
        return obj.toString();
    }

    public void incrementInt(Object str, int i) {
        put(str, (containsKey(str) ? (Integer) get(str) : 0) + i);
    }

    public void incrementLong(Object str, long j) {
        put(str, ((!containsKey(str) || get(str) == null) ? 0 : (Long) get(str)) + j);
    }
}