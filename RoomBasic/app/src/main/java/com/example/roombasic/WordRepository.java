package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>>allWordsLive;
    public WordRepository(Context context) {
        WordDatabase wordDatabase=WordDatabase.getDatabase(context.getApplicationContext());
        wordDao= wordDatabase.getWordDao();
        allWordsLive=wordDao.getAllWordsLive();
    }
    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }
    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    /** @noinspection deprecation*/
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public InsertAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }
    /** @noinspection deprecation*/
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;
        public DeleteAllAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
