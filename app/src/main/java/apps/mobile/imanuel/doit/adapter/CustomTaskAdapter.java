package apps.mobile.imanuel.doit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apps.mobile.imanuel.doit.R;
import apps.mobile.imanuel.doit.model.ModelTask;

/**
 * Created by Imanuel on 14/04/2016.
 */
public class CustomTaskAdapter extends BaseAdapter {
    List<ModelTask> m_listTask = new ArrayList<>();
    Context m_context;
    LayoutInflater m_layoutInflater;

    public CustomTaskAdapter(Context context, List<ModelTask> list){
        this.m_listTask = list;
        this.m_context = context;
        this.m_layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return m_listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return m_listTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getTaskId(int position) {
        return m_listTask.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = m_layoutInflater.inflate(R.layout.cust_item_main, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.due_date = (TextView) convertView.findViewById(R.id.tv_dueDate);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(m_listTask.get(position).getTitle());
        holder.due_date.setText(m_listTask.get(position).getDue_date());

        return convertView;
    }


    static class ViewHolder {
        TextView title;
        TextView due_date;
        String status;
        int id;

    }
}
