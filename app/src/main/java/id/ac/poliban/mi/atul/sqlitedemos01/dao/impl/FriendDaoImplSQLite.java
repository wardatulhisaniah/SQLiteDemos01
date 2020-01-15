package id.ac.poliban.mi.atul.sqlitedemos01.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.ac.poliban.mi.atul.sqlitedemos01.dao.FriendDao;
import id.ac.poliban.mi.atul.sqlitedemos01.domain.Friend;

public class FriendDaoImplSQLite extends SQLiteOpenHelper implements FriendDao {
    private static final String DB_NAME = "atul.db";
    private static final int DB_VERSION = 2;

    public FriendDaoImplSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists friend (" +
                "id integer not null primary key autoincrement," +
                "name text not null," +
                "address text," +
                "phone text," +
                "photo text" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            String sql = "drop table if exists friend";
            db.execSQL(sql);
            onCreate(db);
        }
    }

    @Override
    public void insert(Friend f) {
        String sql = "insert into friend values(?,?,?,?,?)";
        getWritableDatabase().execSQL(sql, new Object[]{
                null,
                f.getName(),
                f.getAddress(),
                f.getPhone(),
                f.getPhoto()
        });
    }

    @Override
    public void update(Friend f) {
        String sql = "update friend set name=?, address=?, phone=?, photo=? where id=?";
        getWritableDatabase().execSQL(sql, new Object[]{
                f.getName(),
                f.getAddress(),
                f.getPhone(),
                f.getPhoto(),
                f.getId()
        });
    }

    @Override
    public void delete(int id) {
        String sql = "delete from friend where id=?";
        getWritableDatabase().execSQL(sql, new Object[]{id});
    }

    @Override
    public Friend getAFriendById(int id) {
        Friend result = null;
        String sql = "select * from friend where id=?";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new
                String[]{String.valueOf(id)});
        if(cursor.moveToFirst())
            result = new Friend(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        cursor.close();
        return null;
    }

    @Override
    public List<Friend> getAllFriends() {
        List<Friend> result = new ArrayList<>();
        String sql = "select * from friend";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while(cursor.moveToNext())
            result.add(new Friend(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            ));
        cursor.close();
        return result;
    }
}
