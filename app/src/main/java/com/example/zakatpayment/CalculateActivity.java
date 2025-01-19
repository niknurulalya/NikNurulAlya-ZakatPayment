package com.example.zakatpayment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

    public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

        Toolbar myToolbar;
        EditText etNum1, etNum2;
        Button btnCal;
        TextView tvOutput, textView2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_calculate);

            btnCal = findViewById(R.id.btnCalculate);
            etNum1 = findViewById(R.id.etNumber1);
            etNum2 = findViewById(R.id.etNumber2);
            tvOutput = findViewById(R.id.tvOutput);
            btnCal.setOnClickListener(this);

            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            getSupportActionBar().setTitle("Zakat Payment");

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.my_light_primarydark));
            }

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.item_share) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - https://github.com ");
                startActivity(Intent.createChooser(shareIntent, null));

                return true;
            }
            else if(item.getItemId() == R.id.item_about) {

                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
            }
            else if(item.getItemId() == R.id.item_instruction) {

                Intent instructionIntent = new Intent(this, InstructionActivity.class);
                startActivity(instructionIntent);
            }
            return false;
        }


        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btnCalculate) {
                try {
                    // Clear previous error messages
                    TextView tvErrorMessage = findViewById(R.id.tvErrorMessage);
                    tvErrorMessage.setVisibility(View.GONE);

                    // Validate inputs
                    String weightInput = etNum1.getText().toString();
                    String valueInput = etNum2.getText().toString();

                    if (weightInput.isEmpty() || valueInput.isEmpty()) {
                        tvErrorMessage.setText("Please fill in all the fields.");
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        return;
                    }

                    // Retrieve inputs
                    double goldWeight = Double.parseDouble(etNum1.getText().toString()); // Gold weight in grams
                    double goldValuePerGram = Double.parseDouble(etNum2.getText().toString()); // Gold value per gram in RM



                    // Get selected gold type from RadioGroup
                    RadioGroup radioGroupGoldType = findViewById(R.id.radioGroupGoldType);
                    int selectedId = radioGroupGoldType.getCheckedRadioButtonId();
                    double exemptedWeight = 0;

                    if (selectedId == R.id.radio_keep) {
                        exemptedWeight = 85; // Exempted weight for Keep
                    } else if (selectedId == R.id.radio_wear) {
                        exemptedWeight = 200; // Exempted weight for Wear
                    } else {
                        tvErrorMessage.setText("Please select the type of gold.");
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        return;
                    }

                    // Calculations
                    double goldWeightAfterExemption = goldWeight - exemptedWeight;
                    if (goldWeightAfterExemption < 0) goldWeightAfterExemption = 0; // No negative weight

                    double totalGoldValue = goldWeight * goldValuePerGram; // Total value of gold
                    double zakatPayable = goldWeightAfterExemption * goldValuePerGram; // Zakat payable
                    double totalZakat = zakatPayable * 0.025; // Total Zakat (2.5%)

                    // Display results
                    String result = "Total Gold Value: RM " + totalGoldValue + "\n"
                            + "Gold weight minus X (uruf): " + goldWeightAfterExemption + " grams\n"
                            + "Zakat Payable: RM " + zakatPayable + "\n"
                            + "Total Zakat: RM " + totalZakat;
                    tvOutput.setText(result); // Display the results in the TextView
                } catch (NumberFormatException e) {
                    TextView tvErrorMessage = findViewById(R.id.tvErrorMessage);
                    tvErrorMessage.setText("Invalid input. Please enter numeric values only.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        }


    }
