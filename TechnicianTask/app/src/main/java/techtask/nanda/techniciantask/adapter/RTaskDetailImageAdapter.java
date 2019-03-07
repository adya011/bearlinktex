package techtask.nanda.techniciantask.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.model._realm.RTaskReportPhoto;

public class RTaskDetailImageAdapter extends RecyclerView.Adapter<RTaskDetailImageAdapter.ViewHolder> {
    private List<RTaskReportPhoto> taskList;
    private Activity activity;

    public RTaskDetailImageAdapter(List<RTaskReportPhoto> taskList, Activity activity) {
        this.taskList = taskList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_task_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RTaskReportPhoto taskPos = taskList.get(position);

        Log.d("mydebug_img", "Report photo: " + taskPos.getReport_photo());

        if (taskPos.getReport_photo() != null) {
            Bitmap decodedImg = bitmapDecode(taskPos.getReport_photo());
            holder.imgReport.setImageBitmap(decodedImg);
            holder.tvLongLat.setText(taskPos.getLongitude_photo() + ", " + taskPos.getLatitude_photo());
        }

        int pos = position + 1;
        holder.tvImage.setText("Gambar " + pos);

        switch (pos) {
            case 1:
                holder.tvImage.setText(pos + ". Label OOp");
                break;
            case 2:
                holder.tvImage.setText(pos + ". Port Odp");
                break;
            case 3:
                holder.tvImage.setText(pos + ". Barcode");
                break;
            case 4:
                holder.tvImage.setText(pos + ". Hr");
                break;
            case 5:
                holder.tvImage.setText(pos + ". Ikr");
                break;
            case 6:
                holder.tvImage.setText(pos + ". Roset");
                break;
            case 7:
                holder.tvImage.setText(pos + ". Sn Ont");
                break;
            case 8:
                holder.tvImage.setText(pos + ". Layanan Up");
                break;
            case 9:
                holder.tvImage.setText(pos + ". Evident Pel");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgReport;
        public TextView tvImage, tvLongLat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReport = itemView.findViewById(R.id.img_task);
            tvImage = itemView.findViewById(R.id.tv_image);
            tvLongLat = itemView.findViewById(R.id.tv_longlat);
        }
    }

    private Bitmap bitmapDecode(String encoded) {
        byte[] imageAsBytes = Base64.decode(encoded, Base64.NO_WRAP);
        Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        return bmp;
    }
}
