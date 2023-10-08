package com.example.vision2c; 
import androidx.annotation.NonNull; 
import androidx.appcompat.app.AppCompatActivity; 
import androidx.core.app.ActivityCompat; 
import androidx.core.content.ContextCompat; 
import android.Manifest; 
import android.content.Intent; 
import android.content.pm.PackageManager; 
import android.net.Uri; 
import android.os.Build; 
import android.os.Bundle; 
import android.speech.tts.TextToSpeech; 
import android.view.KeyEvent; 
import android.view.View; 
import android.widget.Button; 
 

import android.widget.EditText; 
import android.widget.TextView; 
import android.widget.Toast; 
import java.util.Locale; 
public class Audio_Dialpad extends AppCompatActivity { 
EditText number; 
// TextView lblinfo; 
TextToSpeech tts; 
Button call,one,two,three,four,five,six,seven,eight,nine,zero,hash,ash,clear,back; 
TextToSpeech toSpeech; 
private static final int Request_Call = 1; 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_audio_dialpad); 
number = (EditText) findViewById(R.id.edtPhoneNumber); 
call = (Button) findViewById(R.id.btnCall); 
one= (Button) findViewById(R.id.btnOne); 
two= (Button) findViewById(R.id.btnTwo); 
three= (Button) findViewById(R.id.btnThree); 
four= (Button) findViewById(R.id.btnFour); 
five= (Button) findViewById(R.id.btnFive); 
six= (Button) findViewById(R.id.btnSix); 
seven= (Button) findViewById(R.id.btnSeven); 
eight= (Button) findViewById(R.id.btnEight); 
nine= (Button) findViewById(R.id.btnNine); 
zero= (Button) findViewById(R.id.btnZero); 
ash= (Button) findViewById(R.id.btnAterisk); 
 
 
hash= (Button) findViewById(R.id.btnHash); 
clear= (Button) findViewById(R.id.btnClearAll); 
back = (Button) findViewById(R.id.btn_back); 
toSpeech = new TextToSpeech(getApplicationContext(), new 
TextToSpeech.OnInitListener(){ 
@Override 
public void onInit(int status) { 
if (status != TextToSpeech.ERROR) { 
toSpeech.setLanguage(Locale.ENGLISH); 
speak("Dialpad.Press volume up to repeat the pressed number and volume 
down to make a call"); 
 
 
 }); 
call.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) {makephonecall(); 
} 
}); 
ash.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("astrick"); 
number.append("*"); 
} 
}); 
hash.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak(" hash"); 
number.append("#"); 
} 
}); 
clear.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("clear"); 
String s=number.getText().toString(); 
s = s.substring(0, s.length() - 1); 
number.setText(""); 
number.append(s); 
 
 
}}); 
one.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("one"); 
number.append("1"); 
} 
}); 
two.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("two"); 
number.append("2"); 
} 
}); 
three.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("three"); 
number.append("3"); 
} 
}); 
four.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("four"); 
number.append("4"); 
} 
 
 
five.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("five"); 
number.append("5"); 
} 
}); 
six.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("six"); 
number.append("6"); 
} 
}); 
seven.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("seven"); 
number.append("7"); 
} 
}); 
eight.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("eight"); 
number.append("8"); 
} 
}); 
 
 
@Override 
public void onClick(View v) { 
speak("nine"); 
number.append("9"); 
} 
}); 
zero.setOnClickListener(new View.OnClickListener() { 
@Override 
public void onClick(View v) { 
speak("zero"); 
number.append("0"); 
} });} 
private void makephonecall() { 
String number1 = number.getText().toString(); 
if (number1.trim().length() == 10 ) { 
if (ContextCompat.checkSelfPermission(getApplicationContext(), 
Manifest.permission.CALL_PHONE) != 
 PackageManager.PERMISSION_GRANTED) { 
ActivityCompat.requestPermissions(Audio_Dialpad.this, new String[] 
{Manifest.permission.CALL_PHONE}, Request_Call); 
} else { 
String dial = "tel:" + number1; 
startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial))); 
} 