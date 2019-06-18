package cn.edu.nuc.ssm.testapplication.bean;

import java.util.List;

/**
 * Created by zhuxi on 2019/6/18.
 */
public class CardList {

    private List<Card> cards;
    //private int size;
    private String title;

    ///


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
