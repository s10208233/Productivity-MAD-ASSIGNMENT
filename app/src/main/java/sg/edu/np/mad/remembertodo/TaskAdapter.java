package sg.edu.np.mad.remembertodo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    ArrayList<Task> data;

    public TaskAdapter(ArrayList<Task> input) {
        data = input;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_task, parent, false);
        return new TaskViewHolder(item);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        holder.Single_Task_Name.setText(data.get(position).getTaskName());
        holder.Single_Task_DueDate.setText(data.get(position).getDueDate().toString());

        if (data.get(position).isCompleted() == true) {
            holder.Single_Task_Checkbox.setChecked(true);
        }

        if (position%2 == 0){
            holder.task_constraint_layout.setBackgroundColor(Color.parseColor("#66000000"));
        }

    }

    @Override
    public int getItemCount() { return data.size(); }
}
