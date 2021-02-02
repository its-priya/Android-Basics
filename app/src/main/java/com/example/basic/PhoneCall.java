package com.example.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class PhoneCall extends AppCompatActivity {
    ArrayList pList= new ArrayList();
    ListView phoneList;
    CustomAdapter phoneListAdapter;
    Button addContact;
    static String phoneNumber="";
    private static final int CALL_REQUEST_CODE= 1;
    private static final int ADD_REQUEST_CODE= 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);
        addContact= (Button)findViewById(R.id.addContactId);
        phoneList= (ListView)findViewById(R.id.phoneListId);
        addContactFun("Priya", "1234560789", R.drawable.ic_account_circle);
        addContactFun("Aleena", "6078912345", R.drawable.ic_account_circle);
        addContactFun("Jiya", "9872912345", R.drawable.ic_account_circle);
        addContactFun("Meher", "8942912345", R.drawable.ic_account_circle);
        addContactFun("Aliha", "7746912345", R.drawable.ic_account_circle);

        phoneListAdapter= new CustomAdapter();
        phoneList.setAdapter(phoneListAdapter);

        phoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*
                // Request Permission for making call directly.
                if(ActivityCompat.checkSelfPermission(PhoneCall.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PhoneCall.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                }*/
                Person person= (Person)adapterView.getItemAtPosition(position);
                phoneNumber= person.getPhoneNo();
                openDialer(phoneNumber);
            }
        });
        /*addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent profile= new Intent(PhoneCall.this, AddContact.class);
            Bundle b= new Bundle();
            b.putSerializable("ArrayList", (Serializable)pList);
            profile.putExtra("contactList", b);
            startActivityForResult(profile, ADD_REQUEST_CODE);
            }
        });*/
        registerForContextMenu(phoneList);
    }
    //Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater contextInflater= getMenuInflater();
        contextInflater.inflate(R.menu.menu1, menu);
        menu.setHeaderTitle("Select the Action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deleteId){
            Toast.makeText(PhoneCall.this, "Delete Menu Item", Toast.LENGTH_SHORT).show();
        }
        else{
            return false;
        }
        return true;
    }
    // Action Bar with Options Menu having basic functions:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creation of Options menu in the Action bar.
        getMenuInflater().inflate(R.menu.menu1, menu);
        MenuItem searchItem= menu.findItem(R.id.searchId);
        SearchView searchView= (SearchView)searchItem.getActionView();
        searchView.setQueryHint("Enter");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(PhoneCall.this, s, Toast.LENGTH_SHORT).show();
                //PhoneCall.this.phoneListAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //PhoneCall.this.phoneListAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Operations to be performed after selection of menu item.
        switch (item.getItemId()){
            case R.id.deleteId:{
                Toast.makeText(PhoneCall.this, "Delete option clicked", Toast.LENGTH_SHORT).show();
                // Operations u want to perform.
                return true;
            }
            case R.id.shareId:{
                Toast.makeText(PhoneCall.this, "Share option clicked", Toast.LENGTH_SHORT).show();
                // Operations u want to perform.
                return true;
            }
        }
        return false;
    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_REQUEST_CODE: {
                if(resultCode==RESULT_OK && data!=null) {
                    Bundle b= new Bundle();
                    b= getIntent().getBundleExtra("ArrayList");
                    pList= (ArrayList)b.getSerializable("newContactList");
                    phoneListAdapter.notifyDataSetChanged();
                }
                if(resultCode==RESULT_CANCELED){
                    Toast.makeText(PhoneCall.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }*/

    protected void addContactFun(String name, String phone, Integer img){
        pList.add(new Person(name, phone, img));
    }
    protected void openDialer(String phoneNumber){
        Uri u = Uri.parse("tel:"+phoneNumber);
        Intent dialIntent= new Intent(Intent.ACTION_DIAL, u);
        startActivity(dialIntent);
    }
    /*protected void makeCall(String phoneNumber){
        Uri u = Uri.parse("tel:"+phoneNumber);
        Intent callIntent= new Intent(Intent.ACTION_CALL, u);
        startActivity(callIntent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_REQUEST_CODE:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    makeCall(phoneNumber);
                }
                else
                    Toast.makeText(PhoneCall.this, "Phone Call permission denied!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }*/

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pList.size();
        }

        @Override
        public Object getItem(int i) {
            return pList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View pview= getLayoutInflater().inflate(R.layout.phone_row_data,null);

            TextView personName= (TextView)pview.findViewById(R.id.nameId);
            TextView personPhone= (TextView)pview.findViewById(R.id.phoneId);
            ImageView personImage= (ImageView)pview.findViewById(R.id.imageId);

            Person personR= (Person)getItem(i);
            personName.setText((String)personR.getPersonName());
            personPhone.setText((String)personR.getPhoneNo());
            personImage.setImageResource((Integer)personR.getPersonImage());

            return pview;
        }

        /*public Locale getFilter() {
            return filter;
        }

        public void setFilter(Locale filter) {
            this.filter = filter;
        }*/
    }
}