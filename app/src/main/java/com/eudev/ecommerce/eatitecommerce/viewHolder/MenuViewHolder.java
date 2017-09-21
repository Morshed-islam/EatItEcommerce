package com.eudev.ecommerce.eatitecommerce.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eudev.ecommerce.eatitecommerce.R;
import com.eudev.ecommerce.eatitecommerce.interfaces.ItemClickListener;

/**
 * Created by Morshed on 9/21/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View view) {
        super(view);


        txtMenuName =  view.findViewById(R.id.menu_item_tv);
        imageView = (ImageView) view.findViewById(R.id.menu_img);

        view.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
