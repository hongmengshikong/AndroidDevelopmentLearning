package com.example.roombasic;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Word.class},version = 1,exportSchema = false)//四个参数     Entity(用集合的方式,若有多个，则用","隔开),version,exportSchema
public abstract class WordDatabase extends RoomDatabase {
    //抽象类
    public abstract WordDao getWordDao();
    //若有多个Entity，则写多个Dao
}
