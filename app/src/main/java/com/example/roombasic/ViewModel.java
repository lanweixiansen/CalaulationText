package com.example.roombasic;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    public ViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);

    }

    LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }
    void insertWords(Word...words){
        wordRepository.insertWords(words);
    }
    void deleteWords(Word...words){
        wordRepository.deleteWords(words);
    }
    void updateWords(Word...words){
        wordRepository.updateWords(words);
    }
    void deleteAllWords(){
        wordRepository.deleteAllWords();
    }


}


