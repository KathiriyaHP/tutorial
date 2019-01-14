package drcinfotech.com.myroomdb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drcinfotech.com.myroomdb.R;
import drcinfotech.com.myroomdb.data.model.Food;

/**
 * Created by harshadpk on 29/01/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    Context mContext;
    List<Food> foodList;

    OnClickItemListener onClickItemListener;

    public interface OnClickItemListener{
        public void OnClickFoodItem(View view, Food food);
    }

    public FoodAdapter(Context mContext, List<Food> foodList) {
        this.mContext = mContext;
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            final Food food  = foodList.get(position);

            holder.tv_food_name.setText(""+food.name);

            Glide.with(mContext)
                    .load(food.image)
                    .into(holder.img_food);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItemListener != null)
                        onClickItemListener.OnClickFoodItem(v,food);
                }
            });
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public int getItemCount() {
        return foodList == null?0:foodList.size();
    }

    public void addItems(List<Food> foods){
        this.foodList = foods;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_food) ImageView img_food;
        @BindView(R.id.tv_food_name) TextView tv_food_name;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.itemView  = itemView;
        }
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }
}