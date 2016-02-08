package net.nofool.dev.bmicalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Switch unitSwitch;
    private EditText weightEdit;
    private EditText hightFTEdit;
    private EditText hightINEdit;
    private Button calcButton;
    private TextView resultText;
    private int mode = 0;
    private Double hightFT;
    private Double hightIN;
    private Double weight;
    private String con;
    private Double resultNum;
    private String result;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEdit = (EditText)findViewById(R.id.weightEditText);
        hightFTEdit = (EditText)findViewById(R.id.hightEditText);
        hightINEdit = (EditText)findViewById(R.id.inchEditText);
        resultText = (TextView)findViewById(R.id.resultTextView);
        weightEdit.setHint("Pounds");
        hightFTEdit.setHint("Feet");
        hightINEdit.setHint("Inches");
        unitSwitch = (Switch)findViewById(R.id.unitSwitch);
        unitSwitch.setText("Imperial");
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked == true){
                    unitSwitch.setText("");
                    unitSwitch.setText("Metric");
                    mode =1;
                    hightFTEdit.setHint("Meters");
                    hightINEdit.setHint("CentiMeters");
                    weightEdit.setHint("KiloGrams");

                } else {
                    unitSwitch.setText("");
                    unitSwitch.setText("Imperial");
                    mode = 0;
                    hightFTEdit.setHint("Feet");
                    hightINEdit.setHint("Inches");
                    weightEdit.setHint("Pounds");
                }
            }
        });
        calcButton = (Button)findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }
    public void sendMessage(){
        if (mode == 0){
            if (hightFTEdit.getText().length()!=0) {
                con = hightFTEdit.getText().toString();
                hightFT = Double.parseDouble(con);
            } else{
                hightFT=0.0;
            }
            if (hightINEdit.getText().length()!=0) {
                con = hightINEdit.getText().toString();
                hightIN = Double.parseDouble(con);
            } else {
                hightIN=0.0;
            }
            hightIN=hightFT*12 + hightIN;
            if (weightEdit.getText().length()!=0) {
                con = weightEdit.getText().toString();
                weight = Double.parseDouble(con);
            } else {
                weight = 0.0;
            }
            resultNum =703*(weight/(hightIN*hightIN));
            if (Double.isNaN(resultNum)){
                resultText.setText("Please enter numbers in the fields provided!");
            } else {
                resultText.setText("Your BMI is:" + df.format(resultNum));
            }
        } else if (mode ==1){
           if (hightFTEdit.getText().length()!=0) {
             con = hightFTEdit.getText().toString();
             hightFT= Double.parseDouble(con);
            } else{
                hightFT=0.0;
            }
            if (hightINEdit.getText().length()!=0) {
                con = hightINEdit.getText().toString();
                hightIN = Double.parseDouble(con);
            } else {
                hightIN=0.0;
            }
            if (weightEdit.getText().length()!=0) {
                con = weightEdit.getText().toString();
                weight = Double.parseDouble(con);
            } else {
                weight = 0.0;
            }
            hightFT = (hightFT +(hightIN/100));
            resultNum = (weight/(hightFT*hightFT));
            if (Double.isNaN(resultNum)){
                resultText.setText("Please enter numbers in the fields provided!");
            } else {
                resultText.setText("Your BMI is:" + df.format(resultNum));
            }
        }
    }
}
