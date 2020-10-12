package com.example.roombasic;

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
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonInsert,buttonClear;
    ViewModel viewModel;
    RecyclerView recyclerView;
    MyAdapter myAdapter1,myAdapter2;
    Switch aSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModel.class);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);
        aSwitch = findViewById(R.id.switch1);
        recyclerView = findViewById(R.id.recycleView);
        myAdapter1 = new MyAdapter(false,viewModel);
        myAdapter2 = new MyAdapter(true,viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    recyclerView.setAdapter(myAdapter2);
                }else{
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });

        viewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp = myAdapter1.getItemCount();
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                if (temp!=words.size()) {
                    myAdapter1.notifyDataSetChanged();//当数据改变时通知RecycleView改变视图
                    myAdapter2.notifyDataSetChanged();//当数据改变时通知RecycleView改变视图
                }
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] english = {
                    "Hello",
                    "Word",
                    "Android",
                    "Google",
                    "Studio",
                    "Project",
                    "Database",
                    "Recycle",
                    "View",
                    "String",
                    "Integer"
                };
                String[] chinese = {
                  "你好","世界","安卓","谷歌","工作室","项目","数据库","回收站","视图","字符串","整数类型"
                };
                for (int i=0;i<english.length;i++){
                    viewModel.insertWords(new Word(english[i],chinese[i]));
                }

            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteAllWords();
            }
        });
    }

}