package com.fastlib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fastlib.base.OldViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 16/12/29.
 * 单类型适配器
 */
public abstract class FastAdapter<T> extends BaseAdapter{
    private int mItemId;
    protected List<T> mData;
    protected Context mContext;

    public abstract void binding(int position,T data,OldViewHolder holder);

    public FastAdapter(Context context,int itemId){
        this(context,itemId,new ArrayList<T>());
    }

    public FastAdapter(Context context,int itemId,List<T> data){
        mContext=context;
        mItemId=itemId;
        mData=data;
        if(mData==null)
            mData=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        OldViewHolder holder=OldViewHolder.get(mContext,convertView,parent,mItemId);
        binding(position,mData.get(position),holder);
        return holder.getConvertView();
    }

    /**
     * 设置数据到适配器
     * @param list
     */
    public void setData(List<T> list){
        if(list==null) mData.clear();
        else mData=list;
        notifyDataSetChanged();
    }

    /**
     * 增加数据到适配器
     * @param data
     */
    public void addData(T data){
        mData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 增加一组数据到适配器
     * @param data
     */
    public void addData(List<T> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 如果已存在，不加入到适配器，否则加入
     * @param data
     */
    public void addIfNotExist(T data){
        if(!mData.contains(data))
            addData(data);
    }

    /**
     * 如果已存在，不加入到适配器，否则加入
     * @param data
     */
    public void addIfNotExist(List<T> data){
        for(T t:data)
            addIfNotExist(t);
    }

    /**
     * 从适配器中移除某对象
     * @param data
     */
    public void remove(T data){
        mData.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 从适配器中移除某个位置的对象
     * @param position
     */
    public void remove(int position){
        mData.remove(position);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }
}