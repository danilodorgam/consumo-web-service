package com.danilodorgam.consumowebservice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.danilodorgam.consumowebservice.R;
import com.danilodorgam.consumowebservice.fragment.ConsultaCEPFragment;
import com.danilodorgam.consumowebservice.fragment.ListaCepsFragment;
import com.danilodorgam.consumowebservice.interfaces.RetornoWbInterface;
import com.danilodorgam.consumowebservice.webservice.ConsultarCep;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    carregaFragment(new ConsultaCEPFragment());
                    return true;
                case R.id.navigation_dashboard:
                    carregaFragment(new ListaCepsFragment());
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //
        carregaFragment(new ConsultaCEPFragment());

    }
    private void carregaFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        if(fm != null){
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.content,fragment);
            fragmentTransaction.commit();
        }

    }
}
