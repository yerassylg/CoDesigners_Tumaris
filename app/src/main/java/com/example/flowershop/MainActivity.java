package com.example.flowershop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private CardView cv11, cv21, cv31, cv41;
    private CardView cv12, cv22, cv32, cv42;
    private CardView cv13, cv23, cv33, cv43;

    private TextView numTV11, numTV21, numTV31, numTV41;
    private TextView numTV12, numTV22, numTV32, numTV42;
    private TextView numTV13, numTV23, numTV33, numTV43;

    private TextView priceTV;

    private LinearLayout dragAndDropLL;

    private int num11, num21, num31, num41 = 0;
    private int num12, num22, num32, num42 = 0;
    private int num13, num23, num33, num43 = 0;

    private int price11 = 450, price21 = 800, price31 = 450, price41 = 250;
    private int price12 = 1500, price22 = 1250, price32 = 1750, price42 = 1250;
    private int price13 = 250, price23 = 1200, price33 = 550, price43 = 8000;

    private int priceTotal = 0;

    private int dragResourceId;

    private ImageView basketImg;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // accessing objects
        {
            cv11 = findViewById(R.id.cv11);
            cv21 = findViewById(R.id.cv21);
            cv31 = findViewById(R.id.cv31);
            cv41 = findViewById(R.id.cv41);

            cv12 = findViewById(R.id.cv12);
            cv22 = findViewById(R.id.cv22);
            cv32 = findViewById(R.id.cv32);
            cv42 = findViewById(R.id.cv42);

            cv13 = findViewById(R.id.cv13);
            cv23 = findViewById(R.id.cv23);
            cv33 = findViewById(R.id.cv33);
            cv43 = findViewById(R.id.cv43);

            numTV11 = findViewById(R.id.numTV11);
            numTV21 = findViewById(R.id.numTV21);
            numTV31 = findViewById(R.id.numTV31);
            numTV41 = findViewById(R.id.numTV41);

            numTV12 = findViewById(R.id.numTV12);
            numTV22 = findViewById(R.id.numTV22);
            numTV32 = findViewById(R.id.numTV32);
            numTV42 = findViewById(R.id.numTV42);

            numTV13 = findViewById(R.id.numTV13);
            numTV23 = findViewById(R.id.numTV23);
            numTV33 = findViewById(R.id.numTV33);
            numTV43 = findViewById(R.id.numTV43);

            priceTV = findViewById(R.id.priceTV);

            dragAndDropLL = findViewById(R.id.dragAndDropLL);

            basketImg = findViewById(R.id.basketImg);
        }

        dragAndDropLL.setOnDragListener(new View.OnDragListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();

                if (action == DragEvent.ACTION_DROP) {
                    View dragView = (View) event.getLocalState();
                    dragResourceId = dragView.getId();
                    switch (dragResourceId)
                    {
                        case R.id.cv11:
                            num11 += 1;
                            handleDrags(num11, price11, numTV11);
                            break;
                        case R.id.cv21:
                            num21 += 1;
                            handleDrags(num21, price21, numTV21);
                            break;
                        case R.id.cv31:
                            num31 += 1;
                            handleDrags(num31, price31, numTV31);
                            break;
                        case R.id.cv41:
                            num41 += 1;
                            handleDrags(num41, price41, numTV41);
                            break;
                        case R.id.cv12:
                            num12 += 1;
                            handleDrags(num12, price12, numTV12);
                            break;
                        case R.id.cv22:
                            num22 += 1;
                            handleDrags(num22, price22, numTV22);
                            break;
                        case R.id.cv32:
                            num32 += 1;
                            handleDrags(num32, price32, numTV32);
                            break;
                        case R.id.cv42:
                            num42 += 1;
                            handleDrags(num42, price42, numTV42);
                            break;
                        case R.id.cv13:
                            num13 += 1;
                            handleDrags(num13, price13, numTV13);
                            break;
                        case R.id.cv23:
                            num23 += 1;
                            handleDrags(num23, price23, numTV23);
                            break;
                        case R.id.cv33:
                            num33 += 1;
                            handleDrags(num33, price33, numTV33);
                            break;
                        case R.id.cv43:
                            num43 += 1;
                            handleDrags(num43, price43, numTV43);
                            break;
                        default: break;
                    }
                }
                return true;
            }
        });

        // setting dragAndDrop method to cardViews
        {
            dragAndDrop(cv11, "11");
            dragAndDrop(cv21, "21");
            dragAndDrop(cv31, "31");
            dragAndDrop(cv41, "41");

            dragAndDrop(cv12, "12");
            dragAndDrop(cv22, "22");
            dragAndDrop(cv32, "32");
            dragAndDrop(cv42, "42");

            dragAndDrop(cv13, "13");
            dragAndDrop(cv23, "23");
            dragAndDrop(cv33, "33");
            dragAndDrop(cv43, "43");
        }

        basketImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (priceTotal > 0)
                    Toast.makeText(getBaseContext(), "Заказ уже в пути!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "Чтобы совершить заказ нужно что-то купить", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void dragAndDrop(CardView cv, String label)
    {
        cv.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText(label, "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(cv);
                v.startDragAndDrop(data, shadow, v, 0);
                return false;
            }
        });
    }

    private void handleDrags(int num, int price, TextView numTV)
    {
        priceTotal = priceTotal + price;
        numTV.setText(num + " шт");
        priceTV.setText("К оплате: " + priceTotal + " тг");
    }
}