package ca.ualberta.bgarbitt_sizebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by brettgarbitt on 2017-01-31.
 */

public class setSize extends Activity {

    private EditText sizeName;
    private EditText sizeDate;
    private EditText sizeNeck;
    private EditText sizeBust;
    private EditText sizeChest;
    private EditText sizeWaist;
    private EditText sizeInseam;
    private EditText sizeComment;
    private EditText sizeHip;

    public HashMap<String, ArrayList> people = new HashMap<String, ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_size);

        sizeBust = (EditText) findViewById(R.id.sizeBust);
        sizeNeck = (EditText) findViewById(R.id.sizeNeck);
        sizeName = (EditText) findViewById(R.id.sizeName);
        sizeChest = (EditText) findViewById(R.id.sizeChest);
        sizeComment = (EditText) findViewById(R.id.sizeComment);
        sizeDate = (EditText) findViewById(R.id.sizeDate);
        sizeInseam = (EditText) findViewById(R.id.sizeInseam);
        sizeWaist = (EditText) findViewById(R.id.sizeWaist);
        sizeHip = (EditText) findViewById(R.id.sizeHip);

        //BUTTON!
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * What happens when we press "SAVE" button in activity_current_size.xml
             *
             * @param v
             */
            public void onClick(View v) {
                setResult(RESULT_OK);

                //A debugging method. Will remove later
                Toast.makeText(getApplicationContext(), "save measurements", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(setSize.this, sizeBook.class);

                if (sizeName != null) {
                    //storing the inputted texts in their respective Strings.
                    String textBust = sizeBust.getText().toString();
                    String textNeck = sizeNeck.getText().toString();
                    String textName = sizeName.getText().toString();
                    String textChest = sizeChest.getText().toString();
                    String textComment = sizeComment.getText().toString();
                    String textDate = sizeDate.getText().toString();
                    String textInseam = sizeInseam.getText().toString();
                    String textWaist = sizeWaist.getText().toString();
                    String textHip = sizeHip.getText().toString();

                    //Creating a list to store the measurements
                    ArrayList<String> measurements = new ArrayList<String>();
                    measurements.add(textName);
                    measurements.add(textDate);
                    measurements.add(textNeck);
                    measurements.add(textBust);
                    measurements.add(textChest);
                    measurements.add(textWaist);
                    measurements.add(textHip);
                    measurements.add(textInseam);
                    measurements.add(textComment);

                    /**
                     * storing the persons measurements in a Hash Map.
                     * Hash Map of type: people<String, ArrayList>
                     */
                    people.get(textName);
                    people.put(textName, measurements);

                    //Transferring the new updated Hash Map to the main screen.
                    intent.putExtra("personEntry", people);
                }

                /**
                 * How we return to the main screen. If we've added name entry,
                 * then the persons entries should be saved.
                 */
                startActivity(intent);
            }
        });
    }
}

