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
    public EditText editName;
    public EditText editDate;
    public EditText editNeck;
    public EditText editBust;
    public EditText editChest;
    public EditText editWaist;
    public EditText editInseam;
    public EditText editComment;
    public EditText editHip;

    public Sizes person;
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

        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName != null) {
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
