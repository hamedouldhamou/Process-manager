package pm.com.processmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class ProcessArrayAdapter extends ArrayAdapter<ProcessInfo> {

    private final Context context;
    private final List<ProcessInfo> values;


    public ProcessArrayAdapter(@NonNull Context context, int resource, @NonNull List<ProcessInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.process_lv, parent, false);
        TextView txtProcessId = (TextView) rowView.findViewById(R.id.processid);
        TextView txtProcessName = (TextView) rowView.findViewById(R.id.processname);

        final CheckBox chkSelection = rowView.findViewById(R.id.chkSelection);

        final ProcessInfo info = values.get(position);

        txtProcessId.setText(info.getPid() + "");

        txtProcessName.setText(info.getName() + "");

        chkSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chkSelection.setSelected(!info.isSelected());
                info.setSelected(!info.isSelected());
            }
        });

        return rowView;
    }
}
