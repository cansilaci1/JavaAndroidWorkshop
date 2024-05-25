package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("ilaydakarkus", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS notes(noteid INTEGER PRIMARY KEY AUTOINCREMENT, note VARCHAR)");
            database.execSQL("ALTER TABLE notes ADD COLUMN noteid INTEGER");
            database.execSQL("ALTER TABLE notes ADD COLUMN note VARCHAR");

        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.button.setOnClickListener(view -> addNote());
        binding.buttonUpdate.setOnClickListener(view -> updateNote());
        binding.buttonDelete.setOnClickListener(view -> deleteNote());

    }

    private void displayNotes() {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("ilaydakarkus", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM notes", null);

            StringBuilder stringBuilder = new StringBuilder();
            int noteIdIndex = cursor.getColumnIndexOrThrow("noteid");
            int noteIndex = cursor.getColumnIndexOrThrow("note");

            while (cursor.moveToNext()) {
                int noteId = cursor.getInt(noteIdIndex);
                String note = cursor.getString(noteIndex);
                stringBuilder.append("Note ID: ").append(noteId).append(", Note: ").append(note).append("\n");
            }
            cursor.close();
            database.close();

            binding.textView.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNote() {
        String noteText = binding.editTextNote.getText().toString();
        if (noteText.trim().isEmpty()) {
            Toast.makeText(this, "Boş bir not ekleyemezsiniz", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("ilaydakarkus", MODE_PRIVATE, null);
            database.execSQL("INSERT INTO notes (note) VALUES ('" + noteText + "')");
            database.close();

            Toast.makeText(this, "Not başarıyla eklendi", Toast.LENGTH_SHORT).show();

            // Not eklendikten sonra editText'i temizle
            binding.editTextNote.setText("");

            // Notları tekrar yükle
            displayNotes(); // Notları göster
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateNote() {
        String noteIdText = binding.editTextNoteId.getText().toString();
        String noteText = binding.editTextNote.getText().toString();

        if (noteIdText.trim().isEmpty() || noteText.trim().isEmpty()) {
            Toast.makeText(this, "Not ID ve not metni boş olamaz", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int noteId = Integer.parseInt(noteIdText);
            SQLiteDatabase database = this.openOrCreateDatabase("ilaydakarkus", MODE_PRIVATE, null);
            database.execSQL("UPDATE notes SET note = '" + noteText + "' WHERE noteid = " + noteId);
            database.close();

            Toast.makeText(this, "Not başarıyla güncellendi", Toast.LENGTH_SHORT).show();

            // Notları tekrar yükle
            displayNotes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteNote() {
        String noteIdText = binding.editTextNoteId.getText().toString();

        if (noteIdText.trim().isEmpty()) {
            Toast.makeText(this, "Not ID boş olamaz", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int noteId = Integer.parseInt(noteIdText);
            SQLiteDatabase database = this.openOrCreateDatabase("ilaydakarkus", MODE_PRIVATE, null);
            database.execSQL("DELETE FROM notes WHERE noteid = " + noteId);
            database.close();

            Toast.makeText(this, "Not başarıyla silindi", Toast.LENGTH_SHORT).show();

            // Notları tekrar yükle
            displayNotes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}