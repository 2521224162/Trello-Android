package cn.edu.nuc.ssm.testapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<Card> cards = null;
    private Context context = null;

    private CardAdapter.OnItemClickListener onItemClickListener;


    //todo
    public void updateData(){

    }
    //todo
    public void addNewItem(){

    }
    //todo
    public void deleteItem(){

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(CardAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }




    @NonNull
    @Override
    //生成为每个Item inflater出一个View, View直接封装在ViewHolder中
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_in_list, viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    //适配渲染数据到View中。方法提供给你了一viewHolder而不是原来的convertView。
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        Card card= cards.get(i);

        if(null != card){
            myViewHolder.title.setText(card.getTitle());

        }

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
                if(null != onItemClickListener){
                    int pos = myViewHolder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(myViewHolder.itemView, pos);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title_in_list);
        }
    }

    public CardAdapter(Context context, List<Card> cards) {
        this.cards = cards;
        this.context = context;
    }
}
