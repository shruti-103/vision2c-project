
package com.example.vision2c; 
import androidx.appcompat.app.AppCompatActivity; 
import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import 
android.os.BatteryManager; 
import android.os.Bundle; 
import 
android.speech.tts.TextToSpeech; 
import android.widget.TextView; 
import java.util.Locale; 
TextView status_label, percentage_val; 
TextToSpeech tts; 
@Override 
public void onReceive(Context context, Intent intent) { 
status_label = ((battery)context).findViewById(R.id.status_label); 
percentage_val = ((battery)context).findViewById(R.id.percentage_val); String 
action = intent.getAction(); 
tts = new TextToSpeech(context.getApplicationContext(), new 
TextToSpeech.OnInitListener() { 
@Override 
public void onInit(int status) { 
 if (status != TextToSpeech.ERROR) { 
tts.setLanguage(Locale.ENGLISH); tts.speak("The battery 
 
 
percentage is” 
+percentage_val.getText().toString()+status_label.getText().
toString(),TextToSpeech.QUEU E_ADD,null); 
} 
} 
}); 
if(action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)){ 
//status 
int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1); 
String message = ""; 
switch (status){ 
case BatteryManager.BATTERY_STATUS_FULL: 
 message 
= "Full"; break; 
case BatteryManager.BATTERY_STATUS_CHARGING: 
message = "Charging"; 
break; 
case BatteryManager.BATTERY_STATUS_DISCHARGING: 
message = "Discharging"; 
break; 
case BatteryManager.BATTERY_STATUS_NOT_CHARGING: 
message = "Not Charging"; 
break; 
case BatteryManager.BATTERY_STATUS_UNKNOWN: 
message = "Unknown"; 
 
 
break; 
} 
status_label.setText(message); 
//percentage 
int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1); 
int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1); 
int percentage = level * 100/scale; 
percentage_val.setText(percentage + "%"); 
} 
} 