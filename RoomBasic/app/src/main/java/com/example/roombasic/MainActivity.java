package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordDatabase wordDatabase;
    WordDao wordDao;
    TextView textView;
    Button buttonInsert,buttonUpdate,buttonDelete,buttonClear;
    LiveData<List<Word>>allWordsLive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordDatabase= Room.databaseBuilder(this,WordDatabase.class,"word_database")//获取wordDatabase对象,传递3个参数 Context,类的名称,数据库文件名称
                .allowMainThreadQueries()//强制允许在主线程使用
                .build();//呼叫.build()创建
        wordDao=wordDatabase.getWordDao();
        allWordsLive=wordDao.getAllWordsLive();
        textView=findViewById(R.id.textView);
        allWordsLive.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text= new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    Word word=words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });
        buttonInsert=findViewById(R.id.buttonInsert);
        buttonDelete=findViewById(R.id.buttonDelete);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        buttonClear=findViewById(R.id.buttonClear);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1=new Word("Hello","你好!");
                Word word2=new Word("World","世界!");
                wordDao.insertWords(word1,word2);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word=new Word("Hi","你好!");
                word.setId(20);
                wordDao.updateWords(word);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word=new Word("Hi","你好!");
                word.setId(17);
                wordDao.deleteWords(word);
            }
        });

    }
}