package com.shareeasy.shareeasy.ui.gethelp.service;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.info_details;

public class choose_seervicesss extends Fragment {
    public static final String TAG = "choose_servicess";

    private ListView listView;
    private Button button;

    public choose_seervicesss(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_servicesss, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Car Models Dropdown
        assert getActivity() != null;


        this.listView = (ListView) view.findViewById(R.id.listView);
        this.button = (Button) view.findViewById(R.id.button);

        // CHOICE_MODE_NONE: (Default)
        // (listView.setItemChecked(..) doest not work with CHOICE_MODE_NONE).
        // CHOICE_MODE_SINGLE:
        // CHOICE_MODE_MULTIPLE:
        // CHOICE_MODE_MULTIPLE_MODAL:
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                ServicesAccount user = (ServicesAccount) listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });
        //

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSelectedItems();
                StringBuilder sb = printSelectedItems();
                info_details.servicesss = sb;
                Toast.makeText(getContext(), "Selected items are: "+sb.toString(), Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.action_chooseServiceFragment_to_chooseProductFragment);
            }
        });

        this.initListViewData();
    }

    private void initListViewData()  {

        ServicesAccount Transportation = new ServicesAccount("Transportation","E.g Moving items",false);
        ServicesAccount Medical = new ServicesAccount("Medical","E.g providing Medicines",false);
        ServicesAccount Career = new ServicesAccount("Career Talks","", false);
        ServicesAccount Mentorship = new ServicesAccount("Mentorship and Motivation","", false);
        ServicesAccount Spiritual = new ServicesAccount("Spiritual Services","", false);


        ServicesAccount[] users = new ServicesAccount[]{Transportation,Medical, Career,Mentorship,Spiritual};

        // android.R.layout.simple_list_item_checked:
        // ListItem is very simple (Only one CheckedTextView).
        ArrayAdapter<ServicesAccount> arrayAdapter = new ArrayAdapter<ServicesAccount>(getContext(), android.R.layout.simple_list_item_multiple_choice, users);

        this.listView.setAdapter(arrayAdapter);

        for(int i=0;i< users.length; i++ )  {
            this.listView.setItemChecked(i,users[i].isActive());
        }
    }

    // When user click "Print Selected Items".
    public StringBuilder printSelectedItems()  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();
        //Toast.makeText(getContext(), "List: "+sp.toString(), Toast.LENGTH_SHORT).show();

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){
                ServicesAccount user= (ServicesAccount) listView.getItemAtPosition(i);
                // Or:
                // String s = ((CheckedTextView) listView.getChildAt(i)).getText().toString();
                //
                String s= user.getTittle();
                sb = sb.append(" "+s);
            }
        }
        return sb;


    }


}
