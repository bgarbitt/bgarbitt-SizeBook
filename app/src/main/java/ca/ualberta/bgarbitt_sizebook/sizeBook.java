package ca.ualberta.bgarbitt_sizebook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class sizeBook extends Activity {

    private static final String FILENAME = "file.sav";

    public static ListView previousEntries;
    public static ArrayList<Sizes> sizeList;
    public static ArrayAdapter<Sizes> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizes);

        sizeList = new ArrayList<Sizes>();
        previousEntries = (ListView) findViewById(R.id.previousSizes);

        Button sizeButton = (Button) findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * What happens when we press the "SET SIZES" button in activity_size.xml
             *
             * @param v
             */
            public void onClick(View v) {

                //How we transfer to the next screen.
                Intent intent = new Intent(sizeBook.this, setSize.class);
                startActivity(intent);

            }
        });

        previousEntries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(sizeBook.this, editSize.class);
                editIntent.putExtra("index", position);
                startActivity(editIntent);
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
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Sizes>>(){}.getType();
            sizeList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            sizeList = new ArrayList<Sizes>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
