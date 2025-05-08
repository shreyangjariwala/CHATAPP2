import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp2.R


class MainActivity : AppCompatActivity() {
    private var adapter: MyAdapter? = null
    private lateinit var items: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val addNoteButton = findViewById<Button>(R.id.add_button)


       
        items = ArrayList()

        items.add("Temp Add Element")


        adapter = MyAdapter(this, items)
        listView.adapter = adapter

        
        addNoteButton.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                AddNoteActivity::class.java
            )
            val REQUEST_CODE_ADD_NOTE = null
            startActivity(REQUEST_CODE_ADD_NOTE)
        }
    }

    private fun startActivity(requestCodeAddNote: Int) {

    }


    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val REQUEST_CODE_ADD_NOTE = null
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            val newNote = data?.getStringExtra("NEW_NOTE")
            if (newNote != null) {
                items.add(newNote)
                adapter!!.notifyDataSetChanged()
            }
        }
    }

}