package com.example.vision2c; 
import androidx.appcompat.app.AppCompatActivity; 
 

import android.database.Cursor; 
import android.os.Bundle; 
import android.speech.tts.TextToSpeech; 
import android.view.View; 
import android.widget.AdapterView; 
import android.widget.ArrayAdapter; 
import android.widget.ListView; 
import android.widget.Toast; 
import java.util.ArrayList; 
import java.util.Locale; 
public class View_notes extends AppCompatActivity { 
DatabaseHelper db; 
ListView userlist; 
TextToSpeech tts; 
ArrayList<String> listItem; 
ArrayAdapter adapter;
  
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_view_notes); 
db = new DatabaseHelper(this); 
listItem = new ArrayList<>(); 
userlist = findViewById(R.id.view_notes); 
viewData(); 
tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() 
{ 
@Override 
public void onInit(int i) { 
if(i==TextToSpeech.SUCCESS){ 
tts.setLanguage(Locale.US); 
tts.setSpeechRate(0.8f); 
tts.speak("View notes.",TextToSpeech.QUEUE_ADD,null); 
} 
} 
}); 
userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
@Override 
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { 
String text = userlist.getItemAtPosition(i).toString(); 
tts.speak(text,TextToSpeech.QUEUE_ADD,null); 
Toast.makeText(View_notes.this,""+text, Toast.LENGTH_SHORT).show(); 
} 
}); 
} 
 

Cursor cursor = db.getListContents(); if 
(cursor.getCount() == 0){ 
Toast.makeText(this, "No data to 
show", 
Toast.LENGTH_SHORT).show(); 
} else { 
while (cursor.moveToNext()){ 
listItem.add(cursor.getString(1)); 
} 
adapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, listItem); 
userlist.setAdapter(adapter); } } 
} 
