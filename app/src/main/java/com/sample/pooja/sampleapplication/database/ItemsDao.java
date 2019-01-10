package com.sample.pooja.sampleapplication.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ItemsDao {
    @Query("Select * from suryaItems")
    LiveData<List<ItemsListsEntity>> getAllMessage();

    @Delete
    void deleteItems(ItemsListsEntity message);

    @Insert(onConflict = IGNORE)
    void insertNewItem(ItemsListsEntity newItem);

    @Insert(onConflict = REPLACE)
    void insertListOfItems(List<ItemsListsEntity> newItem);
}
