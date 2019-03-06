package techtask.nanda.techniciantask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.model.TaskPengerjaan;
import techtask.nanda.techniciantask.model._realm.RTaskPengerjaan;

public class RTaskPendingListAdapter extends RecyclerView.Adapter<RTaskPendingListAdapter.ViewHolder> {

    private List<RTaskPengerjaan> taskList;

    public RTaskPendingListAdapter(List<RTaskPengerjaan> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_available_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.tvTaskDesc.setText(taskPos.getTanggal_pengerjaan());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTaskTitle;
        public TextView tvTaskDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title);
            //tvTaskDesc = itemView.findViewById(R.id.tv_task_description);
        }
    }
}
