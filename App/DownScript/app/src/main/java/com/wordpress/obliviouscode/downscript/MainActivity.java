package com.wordpress.obliviouscode.downscript;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import it.sauronsoftware.ftp4j.FTPClient;

public class MainActivity extends AppCompatActivity {

    public String filename;
    private View mProgressView;
    private View mLoginFormView;
    private Upload mAuthTask = null;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mLoginFormView=findViewById(R.id.req_form);
        mProgressView=findViewById(R.id.login_progress);

        final EditText e = (EditText) findViewById(R.id.editText);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = e.getText().toString();
                showProgress(true);
                mAuthTask = new Upload();
                mAuthTask.execute((Void)null);
                e.setText("");
            }
        });


    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class Upload extends AsyncTask<Void, Void, Boolean> {

//        UserLoginTask(String data[]) {
//        }

        @Override
        protected Boolean doInBackground(Void... params) {
            FTPClient client = new FTPClient();

            File file = new File(context.getFilesDir(), "links.txt");
            try {

                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write("<pre>".getBytes());
                    stream.write(filename.getBytes());
                    stream.write(" - n".getBytes());
                    stream.write("</pre>".getBytes());
                    stream.close();
                } catch (IOException e) {

                }

            }

            catch(FileNotFoundException e)
            {

            }

            try
            {
                client.connect("31.220.16.14");
                client.login("u157992303","ritesh@6969");
                client.setPassive(true);
                client.changeDirectory("/public_html/");
                client.append(new File(context.getFilesDir(), "links.html"));
                System.out.println("Uploading");
            }


            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if(success)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("File now in Queue.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }

            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Connection Error");
                alertDialog.setMessage("Please check your internet connection.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
