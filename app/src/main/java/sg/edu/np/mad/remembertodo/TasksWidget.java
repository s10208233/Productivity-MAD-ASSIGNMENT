package sg.edu.np.mad.remembertodo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import static android.content.Context.MODE_PRIVATE;
public class TasksWidget extends AppWidgetProvider {
    public static String GLOBAL_PREFS = "MyPrefs";
    static SharedPreferences sharedPreferences;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        //Instantiate the RemoteViews object//
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tasks_widget);
        setRemoteAdapter(context, views);

        //Request that the AppWidgetManager updates the application widget//
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {

        sharedPreferences = context.getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String color = sharedPreferences.getString("Color","");
        String title = sharedPreferences.getString("Category","");



        views.setRemoteAdapter(R.id.widgetListView, new Intent(context, TasksWidgetService.class));
        //  views.setInt(R.id.widgetContainer,"setBackgroundResource",R.drawable.rounded_corners_for_widget);


        views.setInt(R.id.widgetTitleLabel,"setBackgroundColor", Color.parseColor(colorNameToCode(color,"CC")));
        views.setInt(R.id.widgetListView,"setBackgroundColor", Color.parseColor(colorNameToCode(color,"80")));
        views.setTextViewText(R.id.widgetTitleLabel,title);


    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tasks_widget);
            setRemoteAdapter(context, views);

            //Update all instances of this widget//
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context,"Widget created successfully", Toast.LENGTH_LONG).show();
    }
    @Override public void onDisabled(Context context) {
        Toast.makeText(context,"All widgets deleted", Toast.LENGTH_LONG).show();
    }
    public static String colorNameToCode(String sel, String transparency){
        if(sel.matches("Red"))      {return "#"+transparency+"850000";}
        if(sel.matches("Green"))    {return "#"+transparency+"4F9300";}
        if(sel.matches("Blue"))     {return "#"+transparency+"0057B5";}
        if(sel.matches("Purple"))   {return "#"+transparency+"5A2DA8";}
        if(sel.matches("Yellow"))   {return "#"+transparency+"D3A20B";}
        if(sel.matches("Orange"))   {return "#"+transparency+"D67806";}
        if(sel.matches("Black"))    {return "#"+transparency+"000000";}
        return "#404040";
    }


}