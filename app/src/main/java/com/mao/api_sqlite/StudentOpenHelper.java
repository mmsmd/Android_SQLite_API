package com.mao.api_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentOpenHelper extends SQLiteOpenHelper {
    /*
     * context 上下文
     * name 数据库名字
     * factory 游标工厂 默认为null
     * version 数据库版本号 从1开始不能降级
     *
     * info.db
     * */
    public StudentOpenHelper(@Nullable Context context) {
        super(context, "info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="create table student (id integer not null primary key autoincrement,name varvh(20),score double)";
        db.execSQL(sql);

        System.out.println("onCreate被执行");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("onUpgrade被执行");
    }
}
