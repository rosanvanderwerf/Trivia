import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.rosan.trivia.Highscore;
import com.example.rosan.trivia.R;

import java.util.ArrayList;

/* Created by rosan on 16-3-2018. */

public class HighscoresAdapter extends ArrayAdapter<Highscore> {

    private Context context;
    private ArrayList scores;
    private int resource;

    public HighscoresAdapter(Context context, int resource, ArrayList<Highscore> scores) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.scores = scores;
    }

    /* getView method, with:
    // getItem(position),
    // LayoutInflater,
    // find Views and put details in ListView */
}
