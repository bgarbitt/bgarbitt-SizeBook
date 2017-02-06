package ca.ualberta.bgarbitt_sizebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static ca.ualberta.bgarbitt_sizebook.sizeBook.sizeList;

/**
 * Created by brettgarbitt on 2017-02-05.
 */
public class editSize extends Activity {
    /**
     * The Edit name.
     */
    public EditText editName;
    /**
     * The Edit date.
     */
    public EditText editDate;
    /**
     * The Edit neck.
     */
    public EditText editNeck;
    /**
     * The Edit bust.
     */
    public EditText editBust;
    /**
     * The Edit chest.
     */
    public EditText editChest;
    /**
     * The Edit waist.
     */
    public EditText editWaist;
    /**
     * The Edit inseam.
     */
    public EditText editInseam;
    /**
     * The Edit comment.
     */
    public EditText editComment;
    /**
     * The Edit hip.
     */
    public EditText editHip;

    /**
     * The Person.
     */
    public Sizes person;
    /**
     * The Index. Used to find the user's requested measurements.
     */
    public Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_size);
        Bundle data = getIntent().getExtras();

        if (data != null) {
            index = data.getInt("index");
        }

        person = sizeList.get(index);

        editBust = (EditText) findViewById(R.id.sizeBust);
        editNeck = (EditText) findViewById(R.id.sizeNeck);
        editName = (EditText) findViewById(R.id.sizeName);
        editChest = (EditText) findViewById(R.id.sizeChest);
        editComment = (EditText) findViewById(R.id.sizeComment);
        editDate = (EditText) findViewById(R.id.sizeDate);
        editInseam = (EditText) findViewById(R.id.sizeInseam);
        editWaist = (EditText) findViewById(R.id.sizeWaist);
        editHip = (EditText) findViewById(R.id.sizeHip);

        editBust.setText(person.getTextBust());
        editNeck.setText(person.getTextNeck());
        editName.setText(person.getTextName());
        editChest.setText(person.getTextChest());
        editComment.setText(person.getTextComment());
        editDate.setText(person.getTextDate());
        editInseam.setText(person.getTextInseam());
        editWaist.setText(person.getTextWaist());
        editHip.setText(person.getTextHip());



        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName.getText().toString().length() != 0) {
                    person.setTextBust(editBust.getText().toString());
                    person.setTextNeck(editNeck.getText().toString());
                    person.setTextName(editName.getText().toString());
                    person.setTextChest(editChest.getText().toString());
                    person.setTextComment(editComment.getText().toString());
                    person.setTextDate(editDate.getText().toString());
                    person.setTextInseam(editInseam.getText().toString());
                    person.setTextWaist(editWaist.getText().toString());
                    person.setTextHip(editHip.getText().toString());

                    saveInFile();
                    finish();
                } else {
                    finish();
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeList.remove(person);
                saveInFile();
                finish();
            }
        });
    }

    /**
     * saving the information in sizeList to an external file so that we can call
     * it in another location.
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
