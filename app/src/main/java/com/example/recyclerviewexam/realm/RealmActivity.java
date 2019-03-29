package com.example.recyclerviewexam.realm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityRealmBinding;
import com.example.recyclerviewexam.databinding.ItemTwoTextBinding;
import com.example.recyclerviewexam.models.Person;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmActivity extends AppCompatActivity {
    public static final String TAG = RealmActivity.class.getSimpleName();

    private ActivityRealmBinding mBinding;
    private Realm mRealm = Realm.getDefaultInstance();
    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_realm);

        RealmResults<Person> results = mRealm.where(Person.class)
                .sort("age", Sort.DESCENDING)
                .findAll();

        mAdapter = new PersonAdapter(results);
        mBinding.recyclerView.setAdapter(mAdapter);

        mBinding.addButton.setOnClickListener(v -> {
            mRealm.beginTransaction();
            Person user = mRealm.createObject(Person.class);
            user.setName(mBinding.nameEditText.getText().toString());
            user.setAge(Integer.parseInt(mBinding.ageEditText.getText().toString()));
            mRealm.commitTransaction();
        });

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 확정
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 글자마다
                Log.d(TAG, "onQueryTextChange: " + s);
                RealmResults<Person> results = mRealm.where(Person.class)
                        .sort("age", Sort.DESCENDING)
                        .contains("name", s)
                        .findAll();

                mAdapter.updateData(results);
                return true;
            }
        });


        // 삭제
        mBinding.removeButton.setOnClickListener(v -> {
            String removeName = mBinding.searchView.getQuery().toString();

            mRealm.beginTransaction();
            RealmResults<Person> results1 = mRealm.where(Person.class)
                    .equalTo("name", removeName)
                    .findAll();

            results1.deleteAllFromRealm();
            mRealm.commitTransaction();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private static class PersonAdapter extends RealmRecyclerViewAdapter<Person, PersonAdapter.PersonViewHolder> {


        public PersonAdapter(@Nullable OrderedRealmCollection<Person> data) {
            super(data, true);
            setHasStableIds(true);
        }

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two_text, viewGroup, false);
            return new PersonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
            personViewHolder.binding.setPerson(getItem(i));
        }

        public static class PersonViewHolder extends RecyclerView.ViewHolder {
            ItemTwoTextBinding binding;

            public PersonViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
