package com.masuwes.firebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class show : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list: MutableList<Users>
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        // mendeklarasikan ref sebagai table USERS lalu mengisi list mutable dan medefinisikan " findViewById(R.id.listView) "
        ref = FirebaseDatabase.getInstance().getReference("USERS")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {

                    list.clear()
                    for (h in p0.children) {
                        val user = h.getValue(Users::class.java)
                        list.add(user!!)
                    }
                    val adapter = Adapter(this@show,R.layout.users, list)
                    listView.adapter = adapter
                }
            }

        })
    }
}
