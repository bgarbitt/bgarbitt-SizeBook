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
        //HashMap<String, ArrayList> people = (HashMap<String, ArrayList>) intent.getSerializableExtra("personEntry");

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

                adapter.notifyDataSetChanged();
                saveInFile();

                //How we transfer to the next screen.
                Intent intent = new Intent(sizeBook.this, setSize.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadFromFile();

        adapter = new ArrayAdapter<Sizes>(this, R.layout.list_item, sizeList);
        previousEntries.setAdapter(adapter);
    }

    private void loadFromFile() {
        //Added this line even though it wasn't in the tutorial
        ArrayList<String> names = new ArrayList<String>();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        } catch (FileNotFoundException e) {
            sizeList = new ArrayList<Sizes>();
            sizeList.add(Sizes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            //Context.MODE_PRIVATE could have just been 0, same thing.

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later.
            // if we have a better idea of what's wrong, we can change this.
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
