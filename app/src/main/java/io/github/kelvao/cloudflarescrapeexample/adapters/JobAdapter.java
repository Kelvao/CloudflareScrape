package io.github.kelvao.cloudflarescrapeexample.adapters;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.kelvao.cloudflarescrapeexample.R;
import io.github.kelvao.cloudflarescrapeexample.models.JobModel;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private ArrayList<JobModel> jobs;
    private Callback callback;

    public JobAdapter(ArrayList<JobModel> jobs, Callback callback) {
        this.jobs = jobs;
        this.callback = callback;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        final JobModel job = jobs.get(position);
        holder.tv_company.setText(job.getCompany());
        holder.tv_title.setText(job.getTitle());
        if (job.getFeatured_text() != null) {
            holder.tv_featured_text.setVisibility(View.VISIBLE);
            holder.tv_featured_text.setText(job.getFeatured_text());
        }
        Resources res = holder.itemView.getContext().getResources();
        holder.tv_type.setText(String.format(res.getString(R.string.tv_type), job.getType()));
        holder.tv_location.setText(String.format(res.getString(R.string.tv_location), job.getLocation()));
        holder.itemView.setOnClickListener(view -> callback.onJobClick(job.getLink()));
    }

    @Override
    public int getItemCount() {
        return jobs == null ? 0 : jobs.size();
    }

    public interface Callback {
        void onJobClick(String link);
    }

    class JobViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_company)
        TextView tv_company;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_featured_text)
        TextView tv_featured_text;
        @BindView(R.id.tv_type)
        TextView tv_type;
        @BindView(R.id.tv_location)
        TextView tv_location;

        JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
