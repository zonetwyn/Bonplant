package com.zonetwyn.projects.bonplant.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zonetwyn.projects.bonplant.R;
import com.zonetwyn.projects.bonplant.activities.ShopActivity;
import com.zonetwyn.projects.bonplant.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView status;
        public TextView price;
        public TextView seeProfile;
        public TextView addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            seeProfile = itemView.findViewById(R.id.seeProfile);
            addToCart = itemView.findViewById(R.id.addToCart);
        }
    }

    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_product, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder viewHolder, int i) {

        // applying typefaces
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf");
        viewHolder.name.setTypeface(typeface);
        viewHolder.status.setTypeface(typeface);
        viewHolder.price.setTypeface(typeface);
        viewHolder.seeProfile.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-Medium.otf"));
        viewHolder.addToCart.setTypeface(typeface);

        viewHolder.seeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
