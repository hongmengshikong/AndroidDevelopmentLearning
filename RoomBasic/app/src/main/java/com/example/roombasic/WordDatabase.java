package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton  只允许生成一个示例
@Database(entities = {Word.class},version = 1,exportSchema = false)//四个参数     Entity(用集合的方式,若有多个，则用","隔开),version,exportSchema
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    //抽象类
    public abstract WordDao getWordDao();
    //若有多个Entity，则写多个Dao
}
