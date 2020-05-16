package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easychoices.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Restaurant;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MyAdapter extends PagerAdapter {

    Context context;
    List<Restaurant> restaurantList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Restaurant> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return restaurantList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // for inflate view
        View view = inflater.inflate(R.layout.view_pager_item,container,false);

        ImageView restaurant_image = (ImageView)view.findViewById(R.id.restaurant_image);
        TextView restaurant_title = (TextView)view.findViewById(R.id.restaurant_title);
        TextView restaurant_description= (TextView)view.findViewById(R.id.restaurant_description);
        FloatingActionButton btn_fav =(FloatingActionButton)view.findViewById(R.id.btn_favourite);

        // event handelling
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Restaurant clicked",Toast.LENGTH_SHORT);
            }
        });
        // set Data
        Picasso.get().load(restaurantList.get(position).getImage()).into(restaurant_image);
        restaurant_title.setText(restaurantList.get(position).getTitle());
        restaurant_description.setText(restaurantList.get(position).getDescription());

        container.addView(view);
        return view;




    }
}
