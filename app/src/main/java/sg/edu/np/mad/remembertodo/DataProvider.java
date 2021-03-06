package sg.edu.np.mad.remembertodo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static sg.edu.np.mad.remembertodo.ViewTaskActivity.static_categorylist;

public class DataProvider implements RemoteViewsService.RemoteViewsFactory {
    public String GLOBAL_PREFS = "MyPrefs";
    SharedPreferences sharedPreferences;
    TaskCategory myListView;
    Context mContext = null;
    Gson gson = new Gson();
    public DataProvider(Context context, Intent intent) {
        mContext = context; }
        @Override
        public void onCreate() {
            initData();
        }
        @Override
        public void onDataSetChanged() {
            initData();

        }
        @Override
        public void onDestroy() {

        }
        @Override
        public int getCount() {
            if (myListView == null) {
                return 0;
            }
        return myListView.getTaskList().size();
        }
        @Override
        public RemoteViews getViewAt(int position) {

        RemoteViews view_category = new RemoteViews(mContext.getPackageName(),R.layout.category_list_widget);
//            view_category.setInt(R.id.widgetItemContainer,"setBackgroundColor", Color.parseColor(colorNameToCode(myListView.getColorCode())));
        view_category.setTextViewText(R.id.list_item_task, myListView.getTaskList().get(position).getTaskName());
        view_category.setTextViewText(R.id.list_item_date, myListView.getTaskList().get(position).getDueDate());
        if(myListView.getTaskList().get(position).isCompleted()){
            view_category.setViewVisibility(R.id.tick, View.VISIBLE);
        }
        else{
            view_category.setViewVisibility(R.id.tick, View.INVISIBLE);
        }

        return view_category;
        }
        public String colorNameToCode(String sel){
            if(sel.matches("Red"))      {return "#850000";}
            if(sel.matches("Green"))    {return "#4F9300";}
            if(sel.matches("Blue"))     {return "#0057B5";}
            if(sel.matches("Purple"))   {return "#5A2DA8";}
            if(sel.matches("Yellow"))   {return "#D3A20B";}
            if(sel.matches("Orange"))   {return "#D67806";}
            if(sel.matches("Black"))    {return "#000000";}
            return "#404040";
        }
        @Override
        public RemoteViews getLoadingView() {
        return null;
        }
        @Override
        public int getViewTypeCount() {
        return 1;
        }
        @Override
        public long getItemId(int position) {
        return position;
        }
        @Override
        public boolean hasStableIds() {
        return true;
        }
        //Data initialisation to show on widget
        private void initData() {
            int count = 0;
            sharedPreferences = mContext.getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
            String category = sharedPreferences.getString("Category","");
            if (static_categorylist != null){
                count = static_categorylist.size();
            }
            for (int i = 0; i < count;i++){

                if (static_categorylist.get(i).getTaskCategoryName().equals(category)){
                    myListView = static_categorylist.get(i);

                }
            }
        }

    }

