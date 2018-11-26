package nikhilchauhan.com.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

//public class DbHelper {


    public class DbHelper extends SQLiteOpenHelper{


        private static final String DB_NAME="Tester";
        private static final int DB_VER=1;
        private static final String DB_TABLE="Task";
        private static final String DB_COLUMN="TaskName";



        //creating database with name and version
        public DbHelper(Context context){

            super(context, DB_NAME,null,DB_VER);

        }


     @Override
        public void onCreate(SQLiteDatabase db){

            String myQuery = String.format("CREATE TABLE %s (ID INTEGER KEY AUTOINCREMENT, %s TEXT NOT NULL);", DB_TABLE,DB_COLUMN);
            db.execSQL(myQuery);

     }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String myQuery2 = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
            db.execSQL(myQuery2);
            onCreate(db);

        }

        //Begins here

       public void insertMyTask(String task){

          //for writng
          SQLiteDatabase db= this.getWritableDatabase();
           ContentValues myValues = new ContentValues();
           myValues.put(DB_COLUMN,task);

           db.insertWithOnConflict(DB_TABLE, null,myValues,SQLiteDatabase.CONFLICT_REPLACE);

       }

         public void deleteMyTask(String task){

            SQLiteDatabase db=this.getWritableDatabase();
            db.delete(DB_TABLE,DB_COLUMN +"=?",new String[]{task});
            db.close();;


         }

         public ArrayList<String> getMyTaskList(){

            ArrayList<String> myTaskList = new ArrayList<>();

            SQLiteDatabase db=this.getWritableDatabase();
             Cursor myCursor = db.query(DB_TABLE,new String[]{DB_COLUMN},null,null,null,null,null);
             while (myCursor.moveToNext()){
             int index=myCursor.getColumnIndex(DB_COLUMN);
             myTaskList.add(myCursor.getString(index));

             }
             myCursor.close();
             db.close();


            return myTaskList;

         }


    }

