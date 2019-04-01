package com.example.recyclerviewexam.firebase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class FirebaseActivity extends AppCompatActivity {
    private static final String TAG = FirebaseActivity.class.getSimpleName();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private PersonFirestoreRecyclerAdapter mAdapter;

    private ActivityRealmBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_realm);

        Query query = FirebaseFirestore.getInstance()
                .collection("persons")
                .orderBy("age", Query.Direction.DESCENDING)
                .limit(50);

        FirestoreRecyclerOptions<Person> options = new FirestoreRecyclerOptions.Builder<Person>()
                .setQuery(query, Person.class)
                .build();

        mAdapter = new PersonFirestoreRecyclerAdapter(options);

        mBinding.recyclerView.setAdapter(mAdapter);

        mBinding.addButton.setOnClickListener(v -> {
            addPerson();
        });


        mBinding.removeButton.setOnClickListener(v -> {
            removePerson();
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
                Query query = FirebaseFirestore.getInstance()
                        .collection("persons")
                        .orderBy("name")
                        .startAt(s);

                return true;
            }
        });
    }

    private void removePerson() {
        Query query = FirebaseFirestore.getInstance()
                .collection("persons")
                .whereEqualTo("name", mBinding.nameEditText.getText().toString())
                .whereEqualTo("age", Integer.parseInt(mBinding.ageEditText.getText().toString()));

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                db.collection("persons")
                        .document(snapshot.getId())
                        .delete()
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully deleted!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error deleting document", e));
            }
        });

    }

    private void addPerson() {
        // Create a new person with a first and last name
        Map<String, Object> person = new HashMap<>();
        person.put("name", mBinding.nameEditText.getText().toString());
        person.put("age", Integer.parseInt(mBinding.ageEditText.getText().toString()));

        // Add a new document with a generated ID
        db.collection("persons").add(person);
    }

    private static class PersonFirestoreRecyclerAdapter extends FirestoreRecyclerAdapter<Person, PersonFirestoreRecyclerAdapter.PersonViewHolder> {

        /**
         * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
         * FirestoreRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public PersonFirestoreRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Person> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull PersonViewHolder holder, int position, @NonNull Person model) {
            holder.binding.setPerson(getItem(position));
        }

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two_text, viewGroup, false);
            PersonViewHolder holder = new PersonViewHolder(itemView);
            itemView.setOnClickListener(v -> {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                FirebaseFirestore.getInstance().collection("persons").document(snapshot.getId()).delete();
            });
            return holder;
        }

        public static class PersonViewHolder extends RecyclerView.ViewHolder {
            ItemTwoTextBinding binding;

            public PersonViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
