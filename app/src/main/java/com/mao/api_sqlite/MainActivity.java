package com.mao.api_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mao.api_sqlite.R;
import com.mao.api_sqlite.StudentOpenHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private StudentOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        1.获取帮助类 获得sqlitedatabases对象 帮助增删改查
        helper=new StudentOpenHelper(this);
    }

    public void add(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("name","张三");
        values.put("score","98");

        long row=db.insert("student",null, values);

        if(row==-1){
            Toast.makeText(this,"添加失败",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    public void delete(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        int row=db.delete("student","name=?",new String[]{"张三"});

        if(row==0){
            Toast.makeText(this,"删除失败",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"删除成功",Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    public  void update(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("score",110);

        int row=db.update("student",values,"name=?",new String[]{"张三"});

        if(row==0){
            Toast.makeText(this,"更新失败",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"更新成功",Toast.LENGTH_LONG).show();
        }

        db.close();

    }

    public  void find(View view){

        SQLiteDatabase db=helper.getReadableDatabase();

        Cursor cursor=db.query("student",null,null,null,null,null,null);

        ArrayList<com.mao.sqlite.Student>list=new ArrayList<com.mao.sqlite.Student>();
        while (cursor.moveToNext()){

            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            double score=cursor.getDouble(2);

            com.mao.sqlite.Student s=new com.mao.sqlite.Student(id, name, score);
            list.add(s);
        }

        for(com.mao.sqlite.Student s:list){

            System.out.println(s.toString());
        }

        cursor.close();
        db.close();
    }
}
