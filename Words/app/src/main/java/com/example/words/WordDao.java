package com.example.words;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao//Database access object 接口
public interface WordDao {
    @Insert
    void insertWords(Word... words);
    @Update //修改
    void updateWords(Word... words);
    @Delete //删除
    void  deleteWords(Word... words);
    @Query("DELETE FROM WORD")//清空
    void deleteAllWords();

    @Query("SELECT *FROM WORD ORDER BY ID ASC")////查询表来自word 排序方式通过id  降序排列
//    List<Word> getAllWords();
    LiveData<List<Word>>getAllWordsLive();
}
