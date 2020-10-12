//一个数据库表的实体类
package com.example.roombasic;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {//创建数据库的实体类，表名Word，主键id,字段包含word,ChineseMessage
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String word;
    private String ChineseMeaning;
    private boolean chineseInvisible;

    public boolean isChineseInvisible() {
        return chineseInvisible;
    }

    public void setChineseInvisible(boolean chineseInvisible) {
        this.chineseInvisible = chineseInvisible;
    }

    public Word(String word, String chineseMeaning) {
        this.word = word;
        ChineseMeaning = chineseMeaning;
    }
    public Word(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return ChineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        ChineseMeaning = chineseMeaning;
    }
}
