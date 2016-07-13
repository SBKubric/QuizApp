package com.sbkubric.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showResult(View view) {
        try {
            int score = countScore();
            CharSequence answer = getString(R.string.toast_result_start) + String.valueOf(score) + getString(R.string.toast_result_end);
            Toast.makeText(view.getContext(), answer, Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {
            Toast.makeText(view.getContext(), R.string.unselected_values, Toast.LENGTH_SHORT).show();
        }
    }

    private int countScore() throws NullPointerException {
        int score = 0;
        int boxes_selected = 0;

        /**
         * Checking questions with RadioGroups.
         */
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgroup_question1);
        switch (rg.getCheckedRadioButtonId()) {
            default:
                break;
            case R.id.rbtn_question1_1:
                ++score;
                break;
        }

        rg = (RadioGroup) findViewById(R.id.rgroup_question4);
        switch (rg.getCheckedRadioButtonId()) {
            default:
                break;
            case R.id.rbtn_question4_4:
                ++score;
                break;
        }

        rg = (RadioGroup) findViewById(R.id.rgroup_question5);
        switch (rg.getCheckedRadioButtonId()) {
            default:
                break;
            case R.id.rbtn_question5_2:
                ++score;
                break;
        }

        /**
         * Checking checkboxes on the question 2.
         */
        CheckBox cb1 = (CheckBox) findViewById(R.id.chkbx_question2_ans1);
        CheckBox cb2 = (CheckBox) findViewById(R.id.chkbx_question2_ans3);
        if (cb1.isChecked() | cb2.isChecked()) {
            ++boxes_selected;
          if (cb1.isChecked() && cb2.isChecked()) { //this is the right answer
              ++score;  //so we adding 1 to the score
              ++boxes_selected;
          }
        }

        CheckBox cb = (CheckBox) findViewById(R.id.chkbx_question2_ans2);
        if (cb.isChecked()) {
            ++boxes_selected;
        }

        cb = (CheckBox) findViewById(R.id.chkbx_question2_ans4);
        if (cb.isChecked()) {
            ++boxes_selected;
        }

        if (boxes_selected == 0) {
            throw new NullPointerException();
        }

        /**
         * Checking the question with EditText.
         */

        CharSequence charSequence = ((EditText) findViewById(R.id.edit_text_answer)).getText();
        if (charSequence.length() != 0) {
            if (charSequence.toString().equals(getString(R.string.question3_answer))) {
                ++score;
            }
        } else {
            throw new NullPointerException();
        }

        return score;
    }
}
