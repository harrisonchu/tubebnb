package tubemogul.airtube.com.airtube;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ListActivity {

    String[] itemname ={
            "Kyle Boss",
            "Jeff Zhan",
            "Random Randomer",
            "Harrison Chu",
            "Peter Wang",
            "Jason Xie"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Map<String, Object>> list = buildData();
        String[] from = { "name", "purpose" };
        int[] to = { R.id.Itemname, R.id.icon };

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.mylist, from, to);
        setListAdapter(adapter);
    }

    private ArrayList<Map<String, Object>> buildData() {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(putData("Camille Ong", R.mipmap.ic_launcher));
        list.add(putData("Kyle Boss", R.mipmap.home2));
        list.add(putData("Kamel Dabwan", R.mipmap.home3));
        list.add(putData("Michael Okuma", R.mipmap.image4));
        list.add(putData("Stephen Hunt", R.mipmap.home5));

        return list;
    }

    private HashMap<String, Object> putData(String name, Integer purpose) {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("name", name);
        item.put("purpose", purpose);
        return item;
    }
}