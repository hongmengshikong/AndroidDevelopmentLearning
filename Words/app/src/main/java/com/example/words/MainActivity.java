package com.example.words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.words.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonInsert,buttonClear;
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    Switch aSwitch;
    MyAdapter myAdapter1,myAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        wordViewModel=new ViewModelProvider(this).get(WordViewModel.class);
        myAdapter1=new MyAdapter(false,wordViewModel);
        myAdapter2=new MyAdapter(true,wordViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);
        aSwitch=findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerView.setAdapter(myAdapter2);
                }else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });

        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp=myAdapter1.getItemCount();
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                if (temp!=words.size()) {
                    myAdapter1.notifyDataSetChanged();
                    myAdapter2.notifyDataSetChanged();
                }
            }
        });
        buttonInsert=findViewById(R.id.buttonInsert);
        buttonClear=findViewById(R.id.buttonClear);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] english={
                        "Hello",
                        "Word",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese={
                        "你好!",
                        "世界",
                        "安卓",
                        "谷歌",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };
                for (int i = 0; i < english.length; i++) {
                    wordViewModel.insertWords(new Word(english[i],chinese[i]));
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.deleteAllWords();
            }
        });
    }

}