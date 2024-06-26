package com.example.words;

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
    void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }
    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }
    /** @noinspection deprecation*/
    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public UpdateAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
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
    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public DeleteAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
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
