package in.android.cuong.firebasetutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetValueActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mUserList;

    private ArrayList<String> mUsernames = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_value);

        // create instance of FirebaseDatabase connect "https://fir-tutorials-1d984.firebaseio.com"
        // remember change Rules of FirebaseDatabase-> ".read": "auth" == null
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserList = (ListView) findViewById(R.id.list_user);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mUsernames);
        mUserList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Get new user and add to username list
                String value = dataSnapshot.getValue(String.class);
                mUsernames.add(value);

                // Get new key and add key list
                String key = dataSnapshot.getKey();
                mKeys.add(key);

                // update list view
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // get updated value
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                // get index of updated value and set to username list
                int index = mKeys.indexOf(key);
                mUsernames.set(index, value);

                // update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
