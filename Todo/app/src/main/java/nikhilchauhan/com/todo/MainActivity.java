package nikhilchauhan.com.todo;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> myAdapter;
    ListView myListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.listViewId);
        dbHelper = new DbHelper(this);

        loadTask();

    }

    //adding icon to menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Load all task
    private void loadTask(){

    ArrayList<String> myTaskList = dbHelper.getMyTaskList();

    if (myAdapter == null){

        myAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.taskTitleId,myTaskList);
        myListView.setAdapter(myAdapter);

    }else {

        myAdapter.clear();
        myAdapter.addAll(myTaskList);
        myAdapter.notifyDataSetChanged();
    }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem myItem) {

        //because there can be multiple menu
        switch (myItem.getItemId()){

            case R.id.addTaskId: final EditText myEditText = new EditText(this);
                AlertDialog myDialog = new AlertDialog.Builder(this)
                        .setTitle("Add new Task")
                        .setMessage("Whats your task")
                        .setView(myEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String myTask = String.valueOf(myEditText.getText());
                                dbHelper.insertMyTask(myTask);

                                //calling load taks
                                loadTask();
                            }
                        })
                        .setNegativeButton("Cancel",null ).create();
                myDialog.show();
                return true;

        }

        return super.onOptionsItemSelected(myItem);
    }


    //delete button
    public void btnDeleteTask(View view){
        try {
            int myIndex = myListView.getPositionForView(view);
            String task1 = myAdapter.getItem(myIndex);
            dbHelper.deleteMyTask(task1);
            loadTask();

        } catch (Exception e){

            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }


    }

}
