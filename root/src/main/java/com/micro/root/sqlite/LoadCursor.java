package com.micro.root.sqlite;

import com.micro.root.preferences.AssocArray;

/**
 * created by kilin on 20-3-17 下午1:23
 */
public class LoadCursor {

    public static AssocArray getCursor(android.database.Cursor cursor) {
        AssocArray array = AssocArray.array();
        for (int next = 0; next < cursor.getColumnCount(); next++) {
            String columnName = cursor.getColumnName(next);
            int type = cursor.getType(next);
            if (type == 1) {
                array.add(columnName, cursor.getLong(next));
            } else if (type == 2) {
                array.add(columnName, cursor.getFloat(next));
            } else if (type == 4) {
                array.add(columnName, cursor.getBlob(next));
            } else {
                array.add(columnName, cursor.getString(next));
            }
        }
        return array;
    }

    public static void closeCursor(android.database.Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}