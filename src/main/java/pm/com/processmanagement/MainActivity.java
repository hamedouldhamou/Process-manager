package pm.com.processmanagement;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnRefresh;

    Button btnKill;

    ListView lv;

    List<ProcessInfo> processes = new ArrayList<>();

    ActivityManager amg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRefresh = findViewById(R.id.btnRefresh);

        btnKill = findViewById(R.id.btnKill);

        lv =  (ListView) findViewById(R.id.lvProcess);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllProccess();
            }
        });


        btnKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killProcess();
            }
        });

        amg = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        listAllProccess();
    }

    /**
     * Kill all selected processes
     */
    private void killProcess() {
        int cnt = 0;

        for(ProcessInfo info: processes){
            if(info.isSelected()) cnt++;
        }
        if(cnt == 0){
            new AlertDialog.Builder(this).setTitle("Alert").setMessage("No PID selected").show();
            return;
        }

        for(ProcessInfo info: processes){
            if(info.isSelected()) {
                android.os.Process.killProcess(info.getPid());
                android.os.Process.sendSignal(info.getPid(), android.os.Process.SIGNAL_KILL);
                amg.killBackgroundProcesses(info.getName());
            }
        }

        listAllProccess();
    }

    /**
     * List all current processes and running services
     */

    private void listAllProccess(){


        processes = new ArrayList<>();

        for (ActivityManager.RunningAppProcessInfo info : amg.getRunningAppProcesses()) {
            processes.add(new ProcessInfo(info.pid, info.processName));

        }

        for (ActivityManager.RunningServiceInfo info : amg.getRunningServices(Integer.MAX_VALUE)) {
            processes.add(new ProcessInfo(info.pid, info.process));
        }



        lv.setAdapter(new ProcessArrayAdapter(this, R.layout.process_lv, processes));
    }
}
