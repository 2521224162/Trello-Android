package cn.edu.nuc.ssm.testapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.ssm.testapplication.adapter.CardListAdapter;
import cn.edu.nuc.ssm.testapplication.bean.Card;
import cn.edu.nuc.ssm.testapplication.bean.CardList;
import cn.edu.nuc.ssm.testapplication.util.SpaceItemDecoration;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView board = null;
    private List<CardList> cardLists = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        test_giveCardListsDefaultValue();
        board = findViewById(R.id.board_recyclerView);
        board.addItemDecoration(new SpaceItemDecoration(30));
        board.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CardListAdapter cardListAdapter = new CardListAdapter(this, cardLists);
        cardListAdapter.setOnItemClickListener(new CardListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(NavigationActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(NavigationActivity.this,"LongClick " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
        board.setAdapter(cardListAdapter);
        board.setItemAnimator( new DefaultItemAnimator());



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action/
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void test_giveCardListsDefaultValue(){
        cardLists = new ArrayList<CardList>();

        Card C0 = new Card();
        C0.title = "aaa";
        Card C1 = new Card();
        C1.title = "aaa";
        Card C2 = new Card();
        C2.title = "bbb";
        Card C3 = new Card();
        C3.title = "ccc";
        Card C4 = new Card();
        C4.title = "aaddda";
        Card C5 = new Card();
        C5.title = "eee";
        Card C6 = new Card();
        C6.title = "aafffa";
        Card C7 = new Card();
        C7.title = "ggg";

        List<Card> cards0 = new ArrayList<>();
        cards0.add(C0);
        List<Card> cards1 = new ArrayList<>();
        cards1.add(C1);
        cards1.add(C2);
        cards1.add(C3);
        List<Card> cards2 = new ArrayList<>();
        cards2.add(C4);
        cards2.add(C5);
        cards2.add(C6);
        cards2.add(C7);

        CardList c0 = new CardList();
        c0.setTitle("abc");
        c0.setCards(cards0);
        CardList c1 = new CardList();
        c1.setTitle("123");
        c1.setCards(cards1);
        CardList c2 = new CardList();
        c2.setTitle("zxt");
        c2.setCards(cards2);

        cardLists.add(c0);
        cardLists.add(c1);
        cardLists.add(c2);
    }
}
