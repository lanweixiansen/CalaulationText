
package com.example.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class},version = 3,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){//synchronized声明用来防止多个线程同时访问
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word  ADD COLUMN chineseInvisible INTEGER NOT NULL DEFAULT 1");
        }
    };

}
