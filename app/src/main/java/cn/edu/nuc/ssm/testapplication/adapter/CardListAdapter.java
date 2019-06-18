package cn.edu.nuc.ssm.testapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.edu.nuc.ssm.testapplication.R;
import cn.edu.nuc.ssm.testapplication.bean.Card;
import cn.edu.nuc.ssm.testapplication.bean.CardList;

/**
 * Created by zhuxi on 2019/6/18.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {

    private List<CardList> cardLists = null;
    private Context context = null;

    private CardListAdapter.OnItemClickListener onItemClickListener = null;

    //todo
    public void updateData(){

    }
    //todo
    public void addNewItem(){

    }
    //todo
    public void deleteItem(){

    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    //定义一个设置监听器的方法
    public void setOnItemClickListener(CardListAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }



    @NonNull
    @Override
    //生成为每个Item inflater出一个View, View直接封装在ViewHolder中
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_list, viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    //适配渲染数据到View中。方法提供给你了一viewHolder而不是原来的convertView。
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        //绑定数据
        CardList cardList = cardLists.get(position);

        myViewHolder.toolbar.inflateMenu(R.menu.card_list_menu);


        myViewHolder.title.setText(cardList.getTitle());
        myViewHolder.card_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        CardAdapter cardAdapter = new CardAdapter(context, cardList.getCards());
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context,"click " + position + " card", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context,"long click " + position + " card", Toast.LENGTH_SHORT).show();
            }
        });
        myViewHolder.card_recyclerView.setAdapter(cardAdapter);
        myViewHolder.card_recyclerView.setItemAnimator( new DefaultItemAnimator());





        //对RecyclerView的每一个itemView设置点击事件
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != onItemClickListener){
                    int pos = myViewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(myViewHolder.itemView, pos);
                }
            }
        });
        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = myViewHolder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(myViewHolder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });

        //toolbar的click
        myViewHolder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(context, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        myViewHolder.toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "toolbar long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardLists == null ? 0 : cardLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title = null;
        private Toolbar toolbar = null;
        private RecyclerView card_recyclerView = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_list_title);
            toolbar = itemView.findViewById(R.id.card_list_toolBar);
            card_recyclerView = itemView.findViewById(R.id.card_recyclerView);
        }
    }

    public CardListAdapter(Context context, List<CardList> cardLists) {
        this.cardLists = cardLists;
        this.context = context;
    }
}
