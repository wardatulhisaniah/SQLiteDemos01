package id.ac.poliban.mi.atul.sqlitedemos01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.poliban.mi.atul.sqlitedemos01.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.mi.atul.sqlitedemos01.domain.Friend;

public class DetailActivity extends AppCompatActivity {
    private FriendDaoImplSQLite db;
    private EditText etID, etName, etAddress, etPhone, etPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        etID = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etPhoto = findViewById(R.id.et_photo);

        Button btSimpan = findViewById(R.id.bt_simpan);

        etID.setEnabled(false);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            if (getIntent().getExtras() == null) {
                getSupportActionBar().setTitle("Tambah");
                getSupportActionBar().setSubtitle("roman studios");
            } else {
                getSupportActionBar().setTitle("Update");
                getSupportActionBar().setSubtitle("roman studios");

                populateDetail();
            }
        }

        btSimpan.setOnClickListener(v -> {

            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Name tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (getIntent().getExtras() == null)
                tambahData();
            else
                updateData();
        });
    }
    private void populateDetail() {
        Friend friend = (Friend) getIntent().getSerializableExtra("friend");

        etID.setText(String.valueOf(friend.getId()));
        etName.setText(friend.getName());
        etAddress.setText(friend.getAddress());
        etPhone.setText(friend.getPhone());
        etPhoto.setText(friend.getPhoto());

        etName.requestFocus();
    }

    private void updateData() {
        db = new FriendDaoImplSQLite(this);
        db.update(new Friend(
                Integer.parseInt(etID.getText().toString()),
                etName.getText().toString(),
                etAddress.getText().toString(),
                etPhone.getText().toString(),
                etPhoto.getText().toString()
        ));
        db.close();
        finish();
    }

    private void clearForm() {
        etID.setText("");
        etName.setText("");
        etAddress.setText("");
        etPhone.setText("");
        etPhoto.setText("");
        etName.requestFocus();
    }

    private void tambahData() {
        db = new FriendDaoImplSQLite(this);
        db.insert(new Friend(
                etName.getText().toString(),
                etAddress.getText().toString(),
                etPhone.getText().toString(),
                etPhoto.getText().toString()
        ));
        db.close();
        finish();
    }


}
