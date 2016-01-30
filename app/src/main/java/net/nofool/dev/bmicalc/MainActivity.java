package net.nofool.dev.bmicalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEdit = (EditText)findViewById(R.id.weightEditText);
        hightFTEdit = (EditText)findViewById(R.id.hightEditText);
        hightINEdit = (EditText)findViewById(R.id.inchEditText);
        resultText = (TextView)findViewById(R.id.resultTextView);
        unitSwitch = (Switch)findViewById(R.id.unitSwitch);
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
    }
    public void sendMessage(){
        if (mode == 0){
            con = hightFTEdit.getText().toString();
            hightFT= Double.parseDouble(con);
            con = hightINEdit.getText().toString();
            hightIN = Double.parseDouble(con);
            hightIN=hightFT*12 + hightIN;
            con = weightEdit.getText().toString();
            weight = Double.parseDouble(con);
            resultNum =703*(weight/(hightIN*hightIN));
            resultText.setText("Your BMI is:"+resultNum);
        } else if (mode ==1){
            con = hightFTEdit.getText().toString();
            hightFT= Double.parseDouble(con);
            con = hightINEdit.getText().toString();
            hightIN = Double.parseDouble(con);
            hightFT=hightFT + (hightIN*.01);
            con = weightEdit.getText().toString();
            weight = Double.parseDouble(con);
            resultNum = (weight/(hightIN*hightIN));
            resultText.setText("Your BMI is:"+resultNum);
        }
    }
}
