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
import com.google.gson.Gson;

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

import static android.provider.Telephony.Mms.Part.FILENAME;
import static ca.ualberta.bgarbitt_sizebook.sizeBook.sizeList;

/**
 * Created by brettgarbitt on 2017-01-31.
 */

public class setSize extends Activity {

    public EditText sizeName;
    public EditText sizeDate;
    public EditText sizeNeck;
    public EditText sizeBust;
    public EditText sizeChest;
    public EditText sizeWaist;
    public EditText sizeInseam;
    public EditText sizeComment;
    public EditText sizeHip;

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

        //SAVE BUTTON!
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * What happens when we press the "SAVE" button in activity_current_size.xml
             *
             * @param v
             */
            public void onClick(View v) {

                if (sizeName != null) {
                    //Storing the entries in the class "measurements" found in "Sizes.java"
                    Sizes measurements = new Sizes(sizeName.getText().toString());

                    measurements.setTextName(sizeName.getText().toString());
                    measurements.setTextBust(sizeBust.getText().toString());
                    measurements.setTextNeck(sizeNeck.getText().toString());
                    measurements.setTextChest(sizeChest.getText().toString());
                    measurements.setTextComment(sizeComment.getText().toString());
                    measurements.setTextDate(sizeDate.getText().toString());
                    measurements.setTextInseam(sizeInseam.getText().toString());
                    measurements.setTextWaist(sizeWaist.getText().toString());
                    measurements.setTextHip(sizeHip.getText().toString());

                    sizeList.add(measurements);
                }

                /**
                 * How we return to the main screen. If we've added name entry,
                 * then the persons entries should be saved.
                 */
                saveInFile();
                finish();
            }
        });
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(sizeList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

