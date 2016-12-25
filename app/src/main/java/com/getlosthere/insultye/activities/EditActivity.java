package com.getlosthere.insultye.activities;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.adapters.WordsAdapter;
import com.getlosthere.insultye.databinding.ActivityEditBinding;
import com.getlosthere.insultye.models.Word;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    RecyclerView rvWords;
    ImageButton ibSalutation;
    ImageButton ibNoun;
    ImageButton ibSingleAdjective;
    ImageButton ibDoubleAdjective;
    private ActivityEditBinding binding;
    ArrayList<Word> words;
    WordsAdapter wordsAdapter;
    private View view;
    private Paint p = new Paint();
    private AlertDialog.Builder alertDialog;
    private boolean add;
    private EditText etWord;
    private int editPosition;
    private final Integer SALUTATION = 1;
    private final Integer SINGLE_ADJ = 2;
    private final Integer DOUBLE_ADJ = 3;
    private final Integer NOUN = 4;
    Integer type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        rvWords = binding.rvWords;

        ibSalutation = binding.ibSalutation;
        ibSalutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = SALUTATION;
                replaceWords(Word.getAll(SALUTATION));
            }
        });
        ibNoun = binding.ibNoun;
        ibNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = NOUN;
                replaceWords(Word.getAll(NOUN));
            }
        });
        ibSingleAdjective = binding.ibAdjective1;
        ibSingleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = SINGLE_ADJ;
                replaceWords(Word.getAll(SINGLE_ADJ));
            }
        });
        ibDoubleAdjective = binding.ibAdjective2;
        ibDoubleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = DOUBLE_ADJ;
                replaceWords(Word.getAll(DOUBLE_ADJ));
            }
        });
        words = new ArrayList<Word>();
        type = SALUTATION;
        populateWords();
        initSwipe();
        initDialog();
    }

    private void populateWords() {
        wordsAdapter = new WordsAdapter(this, words);
        rvWords.setAdapter(wordsAdapter);
        rvWords.setLayoutManager(new LinearLayoutManager(this));
        replaceWords(Word.getAll(type));
    }

    private void replaceWords(ArrayList<Word> newWords){
        int oldSize = words.size();
        words.clear();
        wordsAdapter.notifyItemRangeRemoved(0,oldSize);
        words.addAll(newWords);
        wordsAdapter.notifyItemRangeInserted(0, newWords.size());
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    Word word = words.get(position);
                    word.delete();
                    wordsAdapter.removeItem(position);
                } else {
                    removeView();
                    editPosition = position;
                    alertDialog.setTitle("Edit Word");
                    etWord.setText(words.get(position).getValue());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white_18dp);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white_18dp);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvWords);
    }
    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog(){
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_edit,null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(add){
                    add = false;
                    wordsAdapter.addItem(etWord.getText().toString(), type);
                    dialog.dismiss();
                } else {
                    wordsAdapter.editItem(editPosition, etWord.getText().toString());
                    // TODO edit word in DB - wordsAdapter.editItem(String )
                    // words.set(editPosition,etWord.getText().toString());
                    wordsAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        // TODO fix this into a binding
        etWord = (EditText) view.findViewById(R.id.etWord);
    }
}
