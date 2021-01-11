package com.akyuz.rickandmorty.databinding;


import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.akyuz.rickandmorty.data.remote.Resource;
import com.akyuz.rickandmorty.view.base.BaseAdapter;

import java.util.List;

/**
 * Binding adapters
 */
final class ListBindingAdapter{

    private ListBindingAdapter(){
        // Private Constructor to hide the implicit one
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}
