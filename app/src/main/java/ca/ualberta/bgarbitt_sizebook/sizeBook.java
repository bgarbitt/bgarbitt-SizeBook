package ca.ualberta.bgarbitt_sizebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class sizeBook extends Activity {

    private static final String FILENAME = "file.sav";

    private ListView previousEntries;

    private String entryName;

    private ArrayList<Sizes> sizeList;
    private ArrayAdapter<Sizes> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizes);

        //Retrieving a Hash Map
        Intent intent = getIntent();
        HashMap<String, ArrayList> people = (HashMap<String, ArrayList>) intent.getSerializableExtra("personEntry");

        previousEntries = (ListView) findViewById(R.id.previousSizes);

        Button sizeButton = (Button) findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * What happens when we press the "SET SIZES" button in activity_size.xml
             *
             * @param v
             */
            public void onClick(View v) {
                setResult(RESULT_OK);

                //How we transfer to the next screen.
                Intent intent = new Intent(sizeBook.this, setSize.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //adapter = new ArrayAdapter<Sizes>(this, R.layout.list_item, sizeList);
        //previousEntries.setAdapter(adapter);
    }
}
