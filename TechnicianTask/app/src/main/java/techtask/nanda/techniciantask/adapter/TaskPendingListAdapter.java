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

public class TaskPendingListAdapter extends RecyclerView.Adapter<TaskPendingListAdapter.ViewHolder> {

    private List<TaskPengerjaan> taskList;

    public TaskPendingListAdapter(List<TaskPengerjaan> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_available_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskPengerjaan taskPos = taskList.get(position);
        holder.tvTaskTitle.setText(taskPos.getNama_wo());
        //holder.tvTaskDesc.setText(taskPos.getKet_wo());
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
