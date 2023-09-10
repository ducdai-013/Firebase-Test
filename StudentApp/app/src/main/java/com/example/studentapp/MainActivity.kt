import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// ...

class MainActivity : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var saveButton: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputName = findViewById(R.id.inputName)
        saveButton = findViewById(R.id.saveButton)

        // Kết nối tới Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("TenHocSinh")

        saveButton.setOnClickListener(View.OnClickListener {
            val tenHocSinh = inputName.text.toString().trim()
            if (!tenHocSinh.isEmpty()) {
                // Lưu tên học sinh vào Firebase Realtime Database
                val key = databaseReference.push().key
                key?.let {
                    databaseReference.child(it).setValue(tenHocSinh)
                }
                inputName.setText("")
            }
        })
    }
}
