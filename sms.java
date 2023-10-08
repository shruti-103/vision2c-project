package 
com.example.vision2c; import 
androidx.annotation.NonNull; 
import 
androidx.annotation.Nullable; 
import androidx.appcompat.app.AppCompatActivity; 
import androidx.core.app.ActivityCompat; 
import 
androidx.core.content.ContextCompat; 
import android.Manifest; 
import android.content.ActivityNotFoundException; 
import android.content.Intent; 
import 
android.content.pm.PackageManager; 
import android.os.Bundle; 
import android.provider.Telephony; 
import 
android.speech.RecognizerIntent; 
import 
android.speech.tts.TextToSpeech; 
import 
android.telephony.SmsManager; 
import android.view.KeyEvent; 
import android.view.View; 
 
35 
Import android.widget.Button; 
import android.widget.EditText; 
import android.widget.Toast; 
import java.util.ArrayList; 
import java.util.Locale; 
public class Sms_create extends AppCompatActivity { 
EditText txt_pNumber, txt_message; 
Button btn_send, btn_view_sms; 
TextToSpeech tts; 
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_sms_create); 
txt_message = findViewById(R.id.txt_message); 
txt_pNumber = findViewById(R.id.txt_phone_number); 
btn_send = findViewById(R.id.btn_send); btn_view_sms 
= findViewById(R.id.btn_view_sms); 
tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() 
{ 
@Override 
public void onInit(int i) { 
if(i==TextToSpeech.SUCCESS){ 
tts.setLanguage(Locale.US); 
tts.setSpeechRate(0.8f); 
tts.speak("Welcome to SMS section, please enter the number and your 
message.",TextToSpeech.QUEUE_ADD,null); 
 
 
 } 
} 
}); 
btn_send.setOnLongClickListener(new View.OnLongClickListener() { 
@Override 
public boolean onLongClick(View view) { 
int permissionCheck = ContextCompat.checkSelfPermission(Sms_create.this, 
Manifest.permission.SEND_SMS); 
if(permissionCheck == PackageManager.PERMISSION_GRANTED){ 
MyMessage(); 
} 
else{ 
ActivityCompat.requestPermissions(Sms_create.this,new String[] 
{Manifest.permission.SEND_SMS},0); 
} 
return false; 
} 
}); 
btn_view_sms.setOnLongClickListener(new View.OnLongClickListener() { 
@Override 
public boolean onLongClick(View view) { 
Intent intent = new Intent(getApplicationContext(), View_sms.class); 
startActivity(intent); 
return false; 
} 
}); 
 
 
 } 
public void btn_send(View view) { 
tts.speak("long press to send message",TextToSpeech.QUEUE_ADD,null); 
} 
private void MyMessage() { 
String phoneNumber = txt_pNumber.getText().toString().trim(); 
String Message = txt_message.getText().toString().trim(); 
if(txt_pNumber.getText().toString().equals("")|| 
!txt_message.getText().toString().equals("")) { 
SmsManager smsManager = SmsManager.getDefault(); 
smsManager.sendTextMessage(phoneNumber, null, Message, null, null); 
tts.speak("Message sent",TextToSpeech.QUEUE_ADD,null); 
// Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show(); 
} 
else { 
tts.speak("Message not sent",TextToSpeech.QUEUE_ADD,null); 
// Toast.makeText(this, "Please Enter Number or Message", 
Toast.LENGTH_SHORT).show(); 
} 
} 
@Override 
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, 
@NonNull int[] grantResults) { 
super.onRequestPermissionsResult(requestCode, permissions, grantResults); 
  
switch (requestCode){ 
case 0: 
if(grantResults.length >=0 && grantResults[0]== 
PackageManager.PERMISSION_GRANTED){ 
MyMessage();