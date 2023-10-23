package com.example.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
//singleton  只允许生成一个示例
@Database(entities = {Word.class},version = 3,exportSchema = false)//四个参数     Entity(用集合的方式,若有多个，则用","隔开),version,exportSchema
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    .addMigrations(MIGRATION_2_3)//自己创建迁移策略
                    .build();
        }
        return INSTANCE;
    }
    //抽象类
    public abstract WordDao getWordDao();
    //若有多个Entity，则写多个Dao
    static final Migration MIGRATION_2_3 =new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

}
