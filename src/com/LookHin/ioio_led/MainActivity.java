package com.LookHin.ioio_led;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ToggleButton;


/*
 * LED 0 = PIN 0 (On Board)
 * LED 1 = PIN 1
 * LED 2 = PIN 2
 * LED 3 = PIN 3
 * LED 4 = PIN 4
 * LED 5 = PIN 5
 * LED 6 = PIN 6
 * LED 7 = PIN 7
 */

public class MainActivity extends IOIOActivity {
	
	private ToggleButton toggleButton0;
	private ToggleButton toggleButton1;
	private ToggleButton toggleButton2;
	private ToggleButton toggleButton3;
	private ToggleButton toggleButton4;
	private ToggleButton toggleButton5;
	private ToggleButton toggleButton6;
	private ToggleButton toggleButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        toggleButton0 = (ToggleButton) findViewById(R.id.toggleButton0);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);
        toggleButton5 = (ToggleButton) findViewById(R.id.toggleButton5);
        toggleButton6 = (ToggleButton) findViewById(R.id.toggleButton6);
        toggleButton7 = (ToggleButton) findViewById(R.id.toggleButton7);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.action_about:
        	//Toast.makeText(getApplicationContext(), "Show About", Toast.LENGTH_SHORT).show();
        	
        	Intent about = new Intent(this, AboutActivity.class);
    		startActivity(about);
    		
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    } 
    
    
    class Looper extends BaseIOIOLooper {
		
    	private DigitalOutput digital_led0;
    	private DigitalOutput digital_led1;
    	private DigitalOutput digital_led2;
    	private DigitalOutput digital_led3;
    	private DigitalOutput digital_led4;
    	private DigitalOutput digital_led5;
    	private DigitalOutput digital_led6;
    	private DigitalOutput digital_led7;


		@Override
		protected void setup() throws ConnectionLostException {

			digital_led0 = ioio_.openDigitalOutput(0,true);
			digital_led1 = ioio_.openDigitalOutput(1,false);
			digital_led2 = ioio_.openDigitalOutput(2,false);
			digital_led3 = ioio_.openDigitalOutput(3,false);
			digital_led4 = ioio_.openDigitalOutput(4,false);
			digital_led5 = ioio_.openDigitalOutput(5,false);
			digital_led6 = ioio_.openDigitalOutput(6,false);
			digital_led7 = ioio_.openDigitalOutput(7,false);
			
			runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(getApplicationContext(), "IOIO Connect", Toast.LENGTH_SHORT).show();
				}
			});
			
		}

		@Override
		public void loop() throws ConnectionLostException {
			
			digital_led0.write(!toggleButton0.isChecked());
			
			digital_led1.write(toggleButton1.isChecked());
			digital_led2.write(toggleButton2.isChecked());
			digital_led3.write(toggleButton3.isChecked());
			digital_led4.write(toggleButton4.isChecked());
			digital_led5.write(toggleButton5.isChecked());
			digital_led6.write(toggleButton6.isChecked());
			digital_led7.write(toggleButton7.isChecked());
						
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				
			}
			
		}
	}

	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}
    
}
