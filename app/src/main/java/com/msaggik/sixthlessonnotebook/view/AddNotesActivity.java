package com.msaggik.sixthlessonnotebook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msaggik.sixthlessonnotebook.R;
import com.msaggik.sixthlessonnotebook.viewmodel.DatabaseHelper;

public class AddNotesActivity extends AppCompatActivity {

    // создание полей
    private EditText title, description;
    private Button addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        // присваивание id полям
        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        addNote = findViewById(R.id.add_note);

        // обработка нажатия кнопки
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // если исправленный текст не пустой, то обновление записи в БД
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){

                    DatabaseHelper database = new DatabaseHelper(AddNotesActivity.this); // создание объекта БД в текущей активности
                    database.addNotes(title.getText().toString(), description.getText().toString()); // создание записи в БД

                    // создание намерения переключения активности
                    Intent intent = new Intent(AddNotesActivity.this, SecondActivity.class); // переключение обратно в активность демонстрации всех записей
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // установления флага экономии ресурсов
                    startActivity(intent);

                    finish(); // при нажатии на кнопку назад действие уничтожается и проиходит переход в активность SecondActivity

                } else { // иначе просто тост об отсутствии изменений
                    Toast.makeText(AddNotesActivity.this, "Необходимо заполнить оба поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}