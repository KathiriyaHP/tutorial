package drcinfotech.com.myroomdb.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import drcinfotech.com.myroomdb.data.model.Food;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by harshadpk on 28/01/2018.
 */

@Dao
public interface FoodDAO
{
    @Query("SELECT COUNT(*) FROM Food")
    int count();

    @Insert(onConflict = REPLACE)
    long insert(Food food);

    @Insert(onConflict = REPLACE)
    long[] inserAll(Food[] foods);

    @Query("Select * from Food")
    Cursor selectAll();

    @Query("select * from Food")
    LiveData<List<Food>> getAllFoods();

    @Query("Select * from Food where id = :id")
    Cursor selectById(long id);

    @Query("delete from Food where id = :id")
    int deleteById(long id);

    @Update
    int update(Food food);
}