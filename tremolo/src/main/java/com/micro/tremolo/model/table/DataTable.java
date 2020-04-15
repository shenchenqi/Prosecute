package com.micro.tremolo.model.table;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public abstract class DataTable extends DataSupport implements Serializable {

    public synchronized static <T> boolean isExist(Class<T> modelClass, String... conditions) {
        return DataTable.isExist(modelClass, conditions);
    }

    public synchronized static int count(String tableName) {
        return DataTable.count(tableName);
    }

    public synchronized static <T> T getDataItem(Class<T> modelClass,String[] conditions) {
        return DataTable.where(conditions).findFirst(modelClass);
    }

    public synchronized static <T> T getDataItem(Class<T> modelClass, String[] columns, String[] conditions) {
        return DataTable.select(columns).where(conditions).findFirst(modelClass);
    }

    public synchronized static <T> List<T> getDataList(Class<T> modelClass, String[] columns, String[] conditions, String column, int value) {
        return DataTable.select(columns).where(conditions).order(column).limit(value).find(modelClass);
    }

    public synchronized static <T> List<T> getDataAll(Class<T> modelClass) {
        return DataTable.findAll(modelClass);
    }

    public synchronized static <T> int delete(Class<T> modelClass) {
        return DataTable.delete(modelClass);
    }

    public synchronized static <T extends DataTable> boolean saveOrUpdate(T model, String... conditions){
        return model.saveOrUpdate(conditions);
    }
}