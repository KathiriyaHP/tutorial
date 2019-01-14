package drcinfotech.com.myroomdb.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drcinfotech.com.myroomdb.R;
import drcinfotech.com.myroomdb.data.model.Food;
import drcinfotech.com.myroomdb.ui.adapter.FoodAdapter;
import drcinfotech.com.myroomdb.ui.viewmodel.FoodViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final int LOADER_FOOD = 1;

    @BindView(R.id.rv_foods_list) RecyclerView rv_foods_list;

    FoodAdapter foodAdapter;
    FoodViewModel foodModelView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_foods_list.setLayoutManager(gridLayoutManager);
//        getSupportLoaderManager().initLoader(LOADER_FOOD,null,mLoaderCallbacks);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFoodAdapter();
    }

    private void setFoodAdapter()
    {
        try{
            foodAdapter = new FoodAdapter(this,new ArrayList<Food>());
            rv_foods_list.setAdapter(foodAdapter);
            foodAdapter.setOnClickItemListener(new FoodAdapter.OnClickItemListener() {
                @Override
                public void OnClickFoodItem(View view, Food food) {
                    Intent intent = new Intent(MainActivity.this, FoodDetailsActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this,((ImageView)view.findViewById(R.id.img_food)), "img_food");
                    intent.putExtra("food",food);
                    startActivity(intent, options.toBundle());
                }
            });

            foodModelView = ViewModelProviders.of(this).get(FoodViewModel.class);
            foodModelView.getFoodList().observe(MainActivity.this, new Observer<List<Food>>() {
                @Override
                public void onChanged(@Nullable List<Food> foods) {
                    foodAdapter.addItems(foods);
                }
            });
        }
        catch (Exception e){e.printStackTrace();}
    }

    /*private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor> () {
        @Override
        public Loader onCreateLoader(int id, Bundle args)
        {
            switch (id)
            {
                case LOADER_FOOD:
                    return new CursorLoader(getApplicationContext(),
                            FoodContentProvider.URI_FOOD,
                            new String[]{Food.COLUMN_ID, Food.COLUMN_NAME, Food.COLUMN_IMAGE, Food.COLUMN_DESCRIPTION},
                            null,
                            null,
                            null);
                default:
                    throw new IllegalArgumentException("");
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor>  loader, Cursor data) {
            switch (loader.getId()){
                case LOADER_FOOD:
                    foodAdapter.setFoodes(data);
                    break;
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor>  loader) {

            switch (loader.getId())
            {
                case LOADER_FOOD:
                    foodAdapter.setFoodes(null);
                    break;
            }
        }
    };

    private static class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
    {
        private Cursor mCursor;
        private Context mContext;

        public FoodAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        void setFoodes(Cursor foodes)
        {
            mCursor =foodes;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            try {

                if (mCursor.moveToPosition(position))
                {
                    holder.tv_food_name.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(Food.COLUMN_NAME)));
                    Glide.with(mContext)
                            .load(mCursor.getString(mCursor.getColumnIndexOrThrow(Food.COLUMN_IMAGE)))
                            .into(holder.img_food);
                }

            }catch (Exception e){e.printStackTrace();}
        }

        @Override
        public int getItemCount() {
            return mCursor == null?0:mCursor.getCount();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView tv_food_name;
            ImageView img_food;

            public ViewHolder(ViewGroup viewGroup) {
                super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_food_menu,viewGroup,false));

                tv_food_name = itemView.findViewById(R.id.tv_food_name);
                img_food = itemView.findViewById(R.id.img_food);
            }
        }
    }*/
}
