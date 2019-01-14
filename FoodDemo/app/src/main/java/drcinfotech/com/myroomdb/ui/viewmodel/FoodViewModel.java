package drcinfotech.com.myroomdb.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import drcinfotech.com.myroomdb.data.FoodDatabase;
import drcinfotech.com.myroomdb.data.model.Food;

/**
 * Created by harshadpk on 29/01/2018.
 */

public class FoodViewModel extends AndroidViewModel
{
    private final LiveData<List<Food>> foodList;

    private FoodDatabase foodDatabase;

    public FoodViewModel(Application application) {
        super(application);
        foodDatabase = FoodDatabase.getInstance(application);
        foodList = foodDatabase.foodDAO().getAllFoods();
        Log.e("TAG","foodList  :-> ");
    }

    public LiveData<List<Food>> getFoodList(){
        return foodList;
    }
}