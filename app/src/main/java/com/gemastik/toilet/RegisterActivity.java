package com.gemastik.toilet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private BluetoothAdapter BA;
    private ListView myListView;
    private ArrayAdapter<String> BTArrayAdapter;
    private LinearLayout frmRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        BA = BluetoothAdapter.getDefaultAdapter();

        frmRegister = (LinearLayout) findViewById(R.id.formRegister);

        myListView = (ListView) findViewById(R.id.devicesFound);

        BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        myListView.setAdapter(BTArrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myListView.setVisibility(View.INVISIBLE);
                frmRegister.setVisibility(View.VISIBLE);
                EditText fingerprint = (EditText) findViewById(R.id.fingerprint);
                fingerprint.setText("af345f12cba4bde4");
            }
        });
        ImageView btnFingerPrint = (ImageView) findViewById(R.id.btnFingerprint);
        btnFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BA.isEnabled()){
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    find();
                }else{
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Scan Bluetooth")
                            .setMessage("Scan bluetooth sekarang ??")
                            .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    find();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }

    private void find(){
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
        BA.startDiscovery();
    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

//                Toast.makeText(context, "Found device " + device.getName(), Toast.LENGTH_LONG).show();
                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                BTArrayAdapter.notifyDataSetChanged();

                myListView.setVisibility(View.VISIBLE);

                frmRegister.setVisibility(View.INVISIBLE);
            }
        }
    };
}
