package com.example.arithmeticcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

class EvaluateString {
    static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/'))
                    {
                        double t = parseFactor();
                        if(t != 0)
                            x /= t; // division
                        else
                            throw new RuntimeException("");
                    }
                    else if(eat('%'))
                    {
                        double t = parseFactor();
                        if(t != 0)
                            x %= t; // modulo
                        else
                            throw new RuntimeException("");
                    }
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView t;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.editText);
        t = findViewById(R.id.textView);
        ed.setMovementMethod(new ScrollingMovementMethod());

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void fun_1(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "1";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_2(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "2";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_3(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "3";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_4(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "4";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_5(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "5";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_6(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "6";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_7(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "7";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_8(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "8";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_9(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "9";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_0(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "0";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_dot(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += ".";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_add(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "+";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_sub(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "-";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_mul(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "*";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_div(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "/";
        ed.setText(x);
        v.vibrate(50);
    }
    public void fun_mod(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += "%";
        ed.setText(x);
        v.vibrate(50);
    }

    @SuppressLint("SetTextI18n")
    public void show_result(View obj)
    {
        String x = String.valueOf(ed.getText());
        try {
            double res = EvaluateString.eval(x);
            System.out.println(res);
            t.setText(Double.toString(res));
        } catch(Exception e) {
            t.setText("invalid input ...");
        }
        v.vibrate(50);
    }

    public void backspace(View obj)
    {
        String x = String.valueOf(ed.getText());

        if(x.length() != 0) {
            x = x.substring(0, x.length() - 1);
            ed.setText(x);
        }
        else
            ed.setText("");
        v.vibrate(50);
    }

    public void fun_clear(View obj)
    {
        ed.setText("");
        t.setText("");
        v.vibrate(200);
    }

    public void fun_openbrace(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += '(';
        ed.setText(x);
        v.vibrate(50);
    }

    public void fun_closebrace(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += ')';
        ed.setText(x);
        v.vibrate(50);
    }

    public void fun_power(View obj)
    {
        String x = String.valueOf(ed.getText());
        x += '^';
        ed.setText(x);
        v.vibrate(50);
    }

    public void fun_prev_ans(View obj)
    {
        String x;
        if(t.getText()!="" && t.getText()!="invalid input ...")
            x = String.valueOf(t.getText());
        else
            x = String.valueOf('0');
        ed.setText(x);
        v.vibrate(50);
    }


    ////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_info:
                startActivity(new Intent(MainActivity.this, Pop.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}