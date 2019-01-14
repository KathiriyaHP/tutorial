package drcinfotech.com.myroomdb.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drcinfotech.com.myroomdb.R;
import drcinfotech.com.myroomdb.data.model.Food;

public class FoodDetailsActivity extends AppCompatActivity {

    public static final String TAG = FoodDetailsActivity.class.getSimpleName();

    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.img_food)ImageView img_food;
    @BindView(R.id.tv_descriptions) TextView tv_descriptions;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras().containsKey("food"))
        {
            Food food = (Food) getIntent().getExtras().getSerializable("food");
            Log.e(TAG,"Food : "+food.getName());

            Glide.with(this)
                    .load(food.image)
                    .into(img_food);
            tv_descriptions.setText(food.getDescription());
            toolbar.setTitle(""+food.getName());
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        collapsingToolbar.setTitle(""+toolbar.getTitle());
//        collapsingToolbar.setExpandedTitleColor(Color.RED);
        collapsingToolbar.setTitleEnabled(true);
    }

    @OnClick(R.id.tv_back)
    public void OnClickBack(View view)
    {
        finish();
    }
}
