package com.example.mygrocerystore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mygrocerystore.R
import com.example.mygrocerystore.adapters.ExploreAdapter
import com.example.mygrocerystore.adapters.PopularAdapters
import com.example.mygrocerystore.models.ExploreCategory
import com.example.mygrocerystore.models.PopularCategory
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    private lateinit var popularRec: RecyclerView
    private lateinit var categoryRec: RecyclerView
    private lateinit var db: FirebaseFirestore


    //Popular Items
    private lateinit var popularAdapters: PopularAdapters
    private lateinit var popularCategoryList: ArrayList<PopularCategory>

    //Categeory Items
    private lateinit var exploreAdapters: ExploreAdapter
    private lateinit var categoryList: ArrayList<ExploreCategory>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_home, container, false)

        // Khởi tạo Firebase
        db = FirebaseFirestore.getInstance()

        // Ánh xạ RecyclerView
        popularRec = root.findViewById(R.id.popular_rec)
        categoryRec = root.findViewById(R.id.explore_rec)


        // Khởi tạo list và adapter cho Popular Items
        popularRec.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        popularCategoryList = ArrayList()
        popularAdapters = PopularAdapters(requireContext(), popularCategoryList)

        popularRec.adapter = popularAdapters

        // Lấy dữ liệu từ Firestore
        db.collection("PopularPtoducts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result?: emptyList()) {
                        val popularCategory = document.toObject(PopularCategory::class.java)
                        popularCategoryList.add(popularCategory)
                    }
                    popularAdapters.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error + ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }


        // Khởi tạo list và adapter cho Home Categories
        categoryRec.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        categoryList = ArrayList()
        exploreAdapters = ExploreAdapter(requireContext(), categoryList)

        categoryRec.adapter = exploreAdapters

        // Lấy dữ liệu từ Firestore
        db.collection("HomeCategory") // Changed collection name
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result ?: emptyList()) { // Handle null safely
                        val item = document.toObject(ExploreCategory::class.java) // Changed model type
                        categoryList.add(item)
                    }
                    exploreAdapters.notifyDataSetChanged() // Changed adapter name
                } else {
                    Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        return root
    }

}