package techtask.nanda.techniciantask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.model.task.Result;

public class TaskAvailableListAdapter extends RecyclerView.Adapter<TaskAvailableListAdapter.ViewHolder> {

    private List<Result> taskList;

    public TaskAvailableListAdapter(List<Result> taskList) {
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
        try {
            Result taskPos = taskList.get(position);
            holder.tvTaskTitle.setText(taskPos.getNama_wo());
            //holder.tvTaskDescription.setText(taskPos.getKet_wo());

        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTaskTitle, tvTaskDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title);
            //tvTaskDescription = itemView.findViewById(R.id.tv_task_description);
        }
    }
}
