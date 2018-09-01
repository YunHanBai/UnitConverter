package com.bai.unitconverter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;

    public CategoryAdapter(List<Category> categories){
        categoryList = categories;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View categoryView;
        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(View view){
            super(view);
            categoryView = view;
            categoryImage = view.findViewById(R.id.category_image);
            categoryName = view.findViewById(R.id.category_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_category, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // Add onClick event to the Category view
        holder.categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Category category = categoryList.get(position);
                // Move to ConversionActivity page
                Intent intent = new Intent(v.getContext(), ConversionActivity.class);
                intent.putExtra("type", category.getCategoryName());
                intent.putExtra("order", category.getOrder());
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryImage.setImageResource(category.getImageId());
    }

    @Override
    public int getItemCount(){
        return categoryList.size();
    }
}
