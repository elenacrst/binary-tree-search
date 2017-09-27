package com.example.elena.binarysearchtree;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView mRecyclerView;
    private static NumberAdapter mAdapter;
    public static BinarySearchTree sTree = new BinarySearchTree();
    private EditText mSearchEdit;
    private TextView mFoundText, mMinText, mMaxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();

        FloatingActionButton mFabAddNumber = (FloatingActionButton) findViewById(R.id.fab_add);
        mFabAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        findViews();

    }

    private void findViews() {
        mSearchEdit = (EditText) findViewById(R.id.edit_search);
        mFoundText = (TextView) findViewById(R.id.found_text);
        mMinText = (TextView) findViewById(R.id.min_text);
        mMaxText = (TextView) findViewById(R.id.max_text);
    }

    private void showDialog(){
        AlertDialog.Builder mAlert;
        final EditText mEditTextAdd;
        mAlert= new AlertDialog.Builder(this);
        mEditTextAdd = new EditText(this);
        mEditTextAdd.setInputType(InputType.TYPE_CLASS_NUMBER);

        mAlert.setMessage("Add number to tree");
        mAlert.setView(mEditTextAdd);
        mAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(mEditTextAdd.getText() != null){
                      String text = mEditTextAdd.getText().toString();
                      double input = Double.parseDouble(text);
                      sTree.insert(input);

                      refreshRecyclerView();
                }
            }
        });
        mAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        mAlert.show();
    }

    private void initializeRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new NumberAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static void refreshRecyclerView() {
        BinarySearchTree.Node root = sTree.getRoot();
       sTree.setList(root);
        mAdapter = new NumberAdapter();
        mAdapter.setData(sTree.getList());
        mRecyclerView.setAdapter(mAdapter);

    }

    public void onSearchImageClick(View view) {
        mSearchEdit.clearFocus();
        String searchText = mSearchEdit.getText().toString();
        if (searchText.length() != 0){
            double searchValue = Double.parseDouble(searchText);
            boolean found = sTree.search(searchValue);
            if (found){
                mFoundText.setText(getString(R.string.found_text, searchText));
            }else
                mFoundText.setText(getString(R.string.not_found_text, searchText));
        }
    }

    public void onMinButtonClick(View view) {
        String min = sTree.getMin()+"";
        mMinText.setText(getString(R.string.min,min));
    }

    public void onMaxButtonClick(View view) {
        String max = sTree.getMax()+"";
        mMaxText.setText(getString(R.string.max,max));
    }
}
