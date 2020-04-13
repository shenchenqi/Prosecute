package com.micro.root.utils;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * created by kilin on 20-3-17 下午1:32
 */
public class Lang {

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Collection<?> collection2) {
        return collection2 == null || collection2.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection2) {
        return !isEmpty(collection2);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <T> boolean isEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] tArr) {
        return !isEmpty(tArr);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static <T> boolean isEquals(T t, T t2) {
        if (t == null || t2 == null) {
            return false;
        }
        return t.equals(t2);
    }

    public static boolean isEqualsIgnoreCase(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static <T> boolean isNotEquals(T t, T t2) {
        if (t == null && t2 == null) {
            return false;
        }
        if (t == null || t2 == null) {
            return true;
        }
        return !t.equals(t2);
    }

    public static boolean isNotEqualsIgnoreCase(String str, String str2) {
        if (str == null || str2 == null) {
            return true;
        }
        return !str.equalsIgnoreCase(str2);
    }

    public static int count(Collection<?> collection2) {
        if (collection2 == null) {
            return 0;
        }
        return collection2.size();
    }

    public static int count(Map<?, ?> map) {
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public static <T> int count(T[] tArr) {
        if (tArr == null) {
            return 0;
        }
        return tArr.length;
    }

    public static int count(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public static int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int toInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static long toLong(String str, int i) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return (long) i;
        }
    }

    public static double toDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static double toDouble(String str, int i) {
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return (double) i;
        }
    }

    public static float toFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static float toFloat(String str, int i) {
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return (float) i;
        }
    }

    public static String toDate(String str, String str2) {
        return toDate(str, (long) toInt(str2));
    }

    public static String toDate(String str, long j) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j * 1000));
    }

    public static String toDate(String str, Date date2) {
        if (date2 == null) {
            return "";
        }
        return new SimpleDateFormat(str, Locale.getDefault()).format(date2);
    }

    public static String tryToDate(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).format(new Date(str));
        } catch (Exception e) {
            return str;
        }
    }

    public static boolean containsKey(Intent intent, String str) {
        return intent != null && intent.hasExtra(str);
    }

    public static boolean containsKey(Bundle bundle, String str) {
        return bundle != null && bundle.containsKey(str);
    }

    public static <T> ArrayList<T> newArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (!(tArr == null || tArr.length == 0)) {
            for (T add : tArr) {
                arrayList.add(add);
            }
        }
        return arrayList;
    }

    public static <K, V> Map<K, V> newHashMap(Object... objArr) {
        HashMap hashMap = new HashMap();
        if (objArr != null && objArr.length > 0) {
            for (int i = 0; i < objArr.length; i += 2) {
                int i2 = i + 1;
                if (i < objArr.length && i2 < objArr.length) {
                    hashMap.put(objArr[i], objArr[i2]);
                }
            }
        }
        return hashMap;
    }

    public static <K> Set<K> newHashSet(K... kArr) {
        HashSet hashSet = new HashSet();
        if (kArr != null && kArr.length > 0) {
            for (K add : kArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    public static <T> T lastElement(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static <T> T firstElement(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public static <T> T elementAt(List<T> list, int i, T t) {
        return (list == null || list.size() == 0 || i < 0 || i >= list.size()) ? t : list.get(i);
    }

    public static <T> T elementAt(List<T> list, int i) {
        if (list == null || list.size() == 0 || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static <T> boolean contains(T[] tArr, T t) {
        if (tArr == null || tArr.length == 0 || t == null) {
            return false;
        }
        for (T isEquals : tArr) {
            if (isEquals(isEquals, t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(List<T> list, T t) {
        if (list == null || list.size() == 0 || t == null) {
            return false;
        }
        return list.contains(t);
    }

    public static <T> boolean contains(Set<T> set, T t) {
        if (set == null || set.size() == 0 || t == null) {
            return false;
        }
        return set.contains(t);
    }

    public static <K, V> boolean containsKey(Map<K, V> map, K k) {
        if (map == null || map.size() == 0 || k == null) {
            return false;
        }
        return map.containsKey(k);
    }

    public static <K, V> boolean containsValue(Map<K, V> map, V v) {
        if (map == null || map.size() == 0 || v == null) {
            return false;
        }
        return map.containsValue(v);
    }

    public static String[] split(String str, String str2) {
        if (str == null) {
            return null;
        }
        return str.split(str2);
    }

    public static List<String> split(String str, int i) {
        List<String> arrayList = new ArrayList<>();
        if (str == null) {
            return arrayList;
        }
        if (str.length() <= i) {
            arrayList.add(str);
        } else {
            int i2 = 0;
            while (i2 < str.length()) {
                int i3 = i2 + i;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                arrayList.add(str.substring(i2, i3));
                i2 = i3;
            }
        }
        return arrayList;
    }

    public static String getRepeatString(String str, int i, String str2) {
        if (i == 0) {
            return "";
        }
        if (i == 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(str);
            if (i2 < i - 1) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static String[] toArray(List<String> list) {
        String[] strArr = new String[count((Collection<?>) list)];
        for (int i = 0; i < count((Collection<?>) list); i++) {
            strArr[i] = (String) list.get(i);
        }
        return strArr;
    }

    public static <T> List<List<T>> splitList(List<T> list, int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = null;
        for (int i2 = 0; i2 < count(list); i2++) {
            if (i2 % i == 0) {
                arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
            }
            arrayList2.add(list.get(i2));
        }
        return arrayList;
    }

    public static long dayDiffer(long maxTime) {
        long time = System.currentTimeMillis() - maxTime;
        long day = 1000 * 60 * 60 * 24;
        return time / day;
    }
}