package ca.ualberta.bgarbitt_sizebook;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static ca.ualberta.bgarbitt_sizebook.sizeBook.sizeList;

/**
 * Created by brettgarbitt on 2017-01-31.
 */
public class setSize extends Activity {

    //private static final String FILENAME = "file.sav";

    /**
     * The Size name.
     */
    public EditText sizeName;
    /**
     * The Size date.
     */
    public EditText sizeDate;
    /**
     * The Size neck.
     */
    public EditText sizeNeck;
    /**
     * The Size bust.
     */
    public EditText sizeBust;
    /**
     * The Size chest.
     */
    public EditText sizeChest;
    /**
     * The Size waist.
     */
    public EditText sizeWaist;
    /**
     * The Size inseam.
     */
    public EditText sizeInseam;
    /**
     * The Size comment.
     */
    public EditText sizeComment;
    /**
     * The Size hip.
     */
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

                /**
                 * This block of code is implemented if the user tries to hit the save button
                 * and they've entered a name in the name field. It is skipped if they left
                 * the name field empty.
                 */
                if (sizeName.getText().toString().length() != 0) {
                    //Storing entries in the class "measurements" found in "Sizes.java"
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

                    /**
                     * Adding our class that we just filled with measurements to the
                     * ArrayList<Sizes> sizeList.
                     */
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

    /**
     * saving the information in sizeList to an external file so that we can call
     * it in another location
     */
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

