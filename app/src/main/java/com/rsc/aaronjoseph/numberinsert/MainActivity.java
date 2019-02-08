package com.rsc.aaronjoseph.numberinsert;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements NumberFragment.NumberListener {

    private  static Integer receivedNumber = 1;
    private static  Integer NumberBack = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                NumberBack = NumberBack + 1;

                FragmentManager fragmentManager = getSupportFragmentManager();
                int backStackEntryCount = fragmentManager.getBackStackEntryCount();

                NumberFragment numberFragment = (NumberFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

                if (backStackEntryCount != NumberBack ) {
                    onBackPressed();
                    NumberBack = backStackEntryCount;
                } if (receivedNumber > 1) {
                    receivedNumber = receivedNumber - 1;
                    String NewNumber = Integer.toString(receivedNumber);

                    numberFragment.setNumberTextView(NewNumber);

                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, numberFragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    @Override
    public void AddNumber(String AddNumber) {
        receivedNumber = receivedNumber + 1;

        if (receivedNumber > 10) {
            String AddNumbers = Integer.toString(receivedNumber);

            NumberFragment numberFragments = (NumberFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
            numberFragments.setNumberTextView(AddNumbers);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment,  numberFragments);
            ft.addToBackStack(null);
            ft.commit();
        } else if (receivedNumber == 10) {
            receivedNumber = receivedNumber - 1;
            Toast.makeText(MainActivity.this, "You have reached the limit", Toast.LENGTH_SHORT).show();
        }

    }
}