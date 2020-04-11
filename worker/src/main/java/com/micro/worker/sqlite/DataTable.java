package com.micro.worker.sqlite;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public abstract class DataTable extends DataSupport implements Serializable {

    public static <T> boolean isExist(Class<T> modelClass, String... conditions) {
        return DataTable.isExist(modelClass, conditions);
    }

    public static int count(String tableName) {
        return DataTable.count(tableName);
    }

    public static <T> T getDataItem(Class<T> modelClass,String[] conditions) {
        return DataTable.where(conditions).findFirst(modelClass);
    }

    public static <T> T getDataItem(Class<T> modelClass, String[] columns, String[] conditions) {
        return DataTable.select(columns).where(conditions).findFirst(modelClass);
    }

    public static <T> List<T> getDataList(Class<T> modelClass, String[] columns, String[] conditions, String column, int value) {
        return DataTable.select(columns).where(conditions).order(column).limit(value).find(modelClass);
    }

    public static <T> List<T> getDataAll(Class<T> modelClass) {
        return DataTable.findAll(modelClass);
    }

    public static <T> int delete(Class<T> modelClass) {
        return DataTable.delete(modelClass);
    }

    public static <T extends DataTable> void saveOrUpdate(T model, String... conditions){
        model.saveOrUpdate(conditions);
    }
}